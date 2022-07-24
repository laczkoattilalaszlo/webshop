import {fetchData} from "./fetch.js";

// INNER USED CONSTANT VARIABLE(S) //
const body = document.querySelector('body');
const orderButton = document.querySelector("#order-button");

// EXPORTED FUNCTION(S) //
export function addEventListenerToOrderButton() {
    orderButton.addEventListener('click', async () => {
        // Disable scrolling on site
        body.classList.add("block-scroll");

        // Add order modal dialog
        let orderModalContent = `<div id="modal-dialog">
                                    <div id="modal-fade">
                                        <div id="modal-dialog-container">
                                            <div id="order-modal-header-container">
                                                <img src="/static/images/icons/order.png">
                                                <div id="order-modal-title">Orders</div>
                                            </div>
                                            <div id="order-modal-content-container"></div>
                                            <div id="order-modal-footer-container">
                                                <div id="order-modal-close-button">Close</div>
                                            </div>
                                        </div>
                                    </div>
                                 </div>`;
        body.insertAdjacentHTML('beforeend', orderModalContent);

        // Add event listener to close button
        AddEventListenerToCancelButton();

        // Get paid orders
        let paidOrders = await fetchData("GET", `/paid-order`, {"session-token": sessionStorage.getItem("session-token")}, null, null, "JSON");

        // Show paid orders
        showPaidOrderLines(paidOrders);

        // AddEventListenerToPaidOrderLines
        const paidOrderLines = document.querySelectorAll(".order");
        for (let paidOrderLine of paidOrderLines) {
            paidOrderLine.addEventListener("click", async () => {
                const orderTitle = paidOrderLine.children[0];
                if (orderTitle.classList.contains("order-title-open")) {
                    // Change order line border
                    orderTitle.classList.replace("order-title-open", "order-title-closed");

                    // Change shrink button to expand
                    const shrinkButton = orderTitle.children[2];
                    shrinkButton.remove();
                    orderTitle.insertAdjacentHTML("beforeend", `<div class="shrink-expand-button">▾</div>`);

                    // Remove order-content
                    const oderContent = paidOrderLine.children[1];
                    oderContent.remove();
                } else {
                    // Change order line border
                    orderTitle.classList.replace("order-title-closed", "order-title-open");

                    // Change expand button to shrink
                    const expandButton = orderTitle.children[2];
                    expandButton.remove();
                    orderTitle.insertAdjacentHTML("beforeend", `<div class="shrink-expand-button">▴</div>`);

                    // Get paid order
                    const paidOrder = await fetchData("POST", `/paid-order`, {"session-token": sessionStorage.getItem("session-token")}, `${paidOrderLine.id}`, "plain/text", "JSON");

                    // Expand paid order line
                    let paidOrderContent = `
                        <div class="order-content">
                            <div class="review-content-unit">
                                <div class="review-content-unit-title">Contact Details</div>
                                <div class="review-content-unit-content">
                                    <div class="review-content-unit-content-row">
                                        <div class="review-content-unit-content-row-title">Name:</div><div class="review-content-unit-content-row-content">${paidOrder.orderContact.name}</div>
                                    </div>
                                    <div class="review-content-unit-content-row">
                                        <div class="review-content-unit-content-row-title">E-mail:</div><div class="review-content-unit-content-row-content">${paidOrder.orderContact.email}</div>
                                    </div>
                                    <div class="review-content-unit-content-row">
                                        <div class="review-content-unit-content-row-title">Phone:</div><div class="review-content-unit-content-row-content">${paidOrder.orderContact.phone}</div>
                                    </div>
                                </div>
                            </div>
        
                            <div class="review-content-double-unit-container">
                                <div class="review-content-unit">
                                    <div class="review-content-unit-title">Shipping Details</div>
                                    <div class="review-content-unit-content">
                                        <div class="review-content-unit-content-row">
                                            <div class="review-content-unit-content-row-title">Zip:</div><div class="review-content-unit-content-row-content">${paidOrder.orderShippingAddress.zip}</div>
                                        </div>
                                        <div class="review-content-unit-content-row">
                                            <div class="review-content-unit-content-row-title">Country:</div><div class="review-content-unit-content-row-content">${paidOrder.orderShippingAddress.country}</div>
                                        </div>
                                        <div class="review-content-unit-content-row">
                                            <div class="review-content-unit-content-row-title">City:</div><div class="review-content-unit-content-row-content">${paidOrder.orderShippingAddress.city}</div>
                                        </div>
                                        <div class="review-content-unit-content-row">
                                            <div class="review-content-unit-content-row-title">Address:</div><div class="review-content-unit-content-row-content">${paidOrder.orderShippingAddress.address}</div>
                                        </div>
                                    </div>
                                </div>
        
                                <div class="review-content-unit">
                                    <div class="review-content-unit-title">Billing Details</div>
                                    <div class="review-content-unit-content">
                                        <div class="review-content-unit-content-row">
                                            <div class="review-content-unit-content-row-title">Zip:</div><div class="review-content-unit-content-row-content">${paidOrder.orderBillingAddress.zip}</div>
                                        </div>
                                        <div class="review-content-unit-content-row">
                                            <div class="review-content-unit-content-row-title">Country:</div><div class="review-content-unit-content-row-content">${paidOrder.orderBillingAddress.country}</div>
                                        </div>
                                        <div class="review-content-unit-content-row">
                                            <div class="review-content-unit-content-row-title">City:</div><div class="review-content-unit-content-row-content">${paidOrder.orderBillingAddress.city}</div>
                                        </div>
                                        <div class="review-content-unit-content-row">
                                            <div class="review-content-unit-content-row-title">Address:</div><div class="review-content-unit-content-row-content">${paidOrder.orderBillingAddress.address}</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="review-content-unit">
                                <div class="review-content-unit-title">Cart</div>
                                <div class="review-content-unit-content">
                                    <table id="checkout-modal-review-table">
                                        <colgroup>
                                            <col id="checkout-modal-review-table-supplier-and-name-column">
                                            <col id="checkout-modal-review-table-price-and-currency-column">
                                            <col id="checkout-modal-review-table-quantity-column">
                                            <col id="checkout-modal-review-table-sub-total-column">
                                        </colgroup>
                                        <thead>
                                            <tr>
                                                <th class="checkout-modal-review-table-left-aligned-column">Product</th>
                                                <th class="checkout-modal-review-table-left-aligned-column">Unit price</th>
                                                <th class="checkout-modal-review-table-center-aligned-column">Quantity</th>
                                                <th class="checkout-modal-review-table-right-aligned-column">Sub-total</th>
                                            </tr>
                                        </thead>
                    `;

                    let totalPrice = 0;
                    let currency = paidOrder.orderCart[0].currency;
                    for (let product of paidOrder.orderCart) {
                        let subTotalPrice = product.unitPrice * product.quantity;
                        totalPrice += subTotalPrice;
                        paidOrderContent += `
                            <tbody>
                                <tr>
                                    <td class="checkout-modal-review-table-left-aligned-column checkout-modal-column-with-right-padding">${product.productSupplier} ${product.productName}</td>
                                    <td class="checkout-modal-review-table-left-aligned-column">${product.unitPrice} ${product.currency}</td>
                                    <td class="checkout-modal-review-table-center-aligned-column">${product.quantity}</td>
                                    <td class="checkout-modal-review-table-right-aligned-column">${subTotalPrice} ${product.currency}</td>
                                </tr>
                            </tbody>
                        `;
                    }

                    paidOrderContent += `
                                    <tfoot>
                                        <tr>
                                            <td class="checkout-modal-review-table-left-aligned-column"></td>
                                            <td class="checkout-modal-review-table-left-aligned-column"></td>
                                            <td class="checkout-modal-review-table-center-aligned-column" id="checkout-modal-review-table-total-prize-text"><div>Total:</div></td>
                                            <td class="checkout-modal-review-table-right-aligned-column" id="checkout-modal-review-table-total-price"><div>${totalPrice} ${currency}</div></td>
                                        </tr>
                                    </tfoot>
                                </table>
                            </div>
                        </div>
                    `;

                    paidOrderContent += `
                        <div class="review-content-unit">
                            <div class="review-content-unit-title">Payment attempts</div>
                            <div class="review-content-unit-content">
                    `;

                    for (let payment of paidOrder.orderPayments) {
                        paidOrderContent += `
                            <div class="review-content-unit-content-row">
                                <div class="review-content-unit-content-row-title">
                                    ${payment.startTimestamp.time.hour}:${payment.startTimestamp.time.minute} - 
                                    ${payment.startTimestamp.date.day}.
                                    ${payment.startTimestamp.date.month}.
                                    ${payment.startTimestamp.date.year}.
                                </div>
                                <div class="review-content-unit-content-row-content">
                                    ${payment.payment_state}
                                </div>
                            </div>
                        `;
                    }

                    paidOrderContent += `
                                </div>
                            </div>
                        </div>
                    `;

                    paidOrderLine.insertAdjacentHTML("beforeend", paidOrderContent);
                }
            });
        }
    });
}

function AddEventListenerToCancelButton() {
    const orderModalCloseButton = document.querySelector("#order-modal-close-button");
    orderModalCloseButton.addEventListener('click', ()=> closeModalDialog());
}

function closeModalDialog() {
    // Enable scrolling on site
    body.classList.remove("block-scroll");

    // Remove modal dialog
    const modalDialog = document.querySelector("#modal-dialog");
    modalDialog.remove();
}

function showPaidOrderLines(paidOrders) {
    const orderModalContentContainer = document.querySelector("#order-modal-content-container");
    for (let paidOrder of paidOrders) {
        orderModalContentContainer.insertAdjacentHTML("beforeend", `
            <div class="order" id="${paidOrder.id}">
                <div class="order-title-closed">
                    <img class="order-title-icon" src="/static/images/icons/payed-order.png">
                    <div class="order-title-text">
                        ${paidOrder.successfulPaymentStartTimestamp.time.hour}:${paidOrder.successfulPaymentStartTimestamp.time.minute} - 
                        ${paidOrder.successfulPaymentStartTimestamp.date.day}.
                        ${paidOrder.successfulPaymentStartTimestamp.date.month}.
                        ${paidOrder.successfulPaymentStartTimestamp.date.year}.
                    </div>
                    <div class="shrink-expand-button">▾</div>
                </div>
            </div>
        `);
    }
}
