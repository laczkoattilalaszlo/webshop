import {fetchData} from "../fetch.js";
import {closeModalDialog} from "./checkout.js";
import {showDeliveryStep} from "./checkout-delivery.js";
import {showPaymentStep} from "./checkout-payment.js";

// EXPORTED FUNCTION(S) //
export async function showReviewStep() {
    // Mark earlier steps as finished
    markPreviousStepsAsFinished();

    // Highlight delivery step
    highlightReviewStep();

    // Change close button
    changeCloseButton();

    // Change previous button
    changePreviousButton();

    // Change next button
    changeNextButton();

    // Empty modal dialog content
    const modalContentContainer = document.querySelector("#checkout-modal-content-container");
    modalContentContainer.innerHTML = "";

    // Get active order
    let activeOrder = await fetchData("GET", `/active-order`, {"session-token": sessionStorage.getItem("session-token")}, null, null, "JSON");

    // Show active order
    let activeOrderContent = `
        <div class="review-content-unit">
            <div class="review-content-unit-title">Contact Details</div>
            <div class="review-content-unit-content">
                <div class="review-content-unit-content-row">
                    <div class="review-content-unit-content-row-title">Name:</div><div class="review-content-unit-content-row-content">${activeOrder.orderContact.name}</div>
                </div>
                <div class="review-content-unit-content-row">
                    <div class="review-content-unit-content-row-title">E-mail:</div><div class="review-content-unit-content-row-content">${activeOrder.orderContact.email}</div>
                </div>
                <div class="review-content-unit-content-row">
                    <div class="review-content-unit-content-row-title">Phone:</div><div class="review-content-unit-content-row-content">${activeOrder.orderContact.phone}</div>
                </div>
            </div>
        </div>
    
        <div class="review-content-double-unit-container">
            <div class="review-content-unit">
                <div class="review-content-unit-title">Shipping Details</div>
                <div class="review-content-unit-content">
                    <div class="review-content-unit-content-row">
                        <div class="review-content-unit-content-row-title">Zip:</div><div class="review-content-unit-content-row-content">${activeOrder.orderShippingAddress.zip}</div>
                    </div>
                    <div class="review-content-unit-content-row">
                        <div class="review-content-unit-content-row-title">Country:</div><div class="review-content-unit-content-row-content">${activeOrder.orderShippingAddress.country}</div>
                    </div>
                    <div class="review-content-unit-content-row">
                        <div class="review-content-unit-content-row-title">City:</div><div class="review-content-unit-content-row-content">${activeOrder.orderShippingAddress.city}</div>
                    </div>
                    <div class="review-content-unit-content-row">
                        <div class="review-content-unit-content-row-title">Address:</div><div class="review-content-unit-content-row-content">${activeOrder.orderShippingAddress.address}</div>
                    </div>
                </div>
            </div>
    
            <div class="review-content-unit">
                <div class="review-content-unit-title">Billing Details</div>
                <div class="review-content-unit-content">
                    <div class="review-content-unit-content-row">
                        <div class="review-content-unit-content-row-title">Zip:</div><div class="review-content-unit-content-row-content">${activeOrder.orderBillingAddress.zip}</div>
                    </div>
                    <div class="review-content-unit-content-row">
                        <div class="review-content-unit-content-row-title">Country:</div><div class="review-content-unit-content-row-content">${activeOrder.orderBillingAddress.country}</div>
                    </div>
                    <div class="review-content-unit-content-row">
                        <div class="review-content-unit-content-row-title">City:</div><div class="review-content-unit-content-row-content">${activeOrder.orderBillingAddress.city}</div>
                    </div>
                    <div class="review-content-unit-content-row">
                        <div class="review-content-unit-content-row-title">Address:</div><div class="review-content-unit-content-row-content">${activeOrder.orderBillingAddress.address}</div>
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
    let currency = activeOrder.orderCart[0].currency;
    for (let product of activeOrder.orderCart) {
        let subTotalPrice = product.unitPrice * product.quantity;
        totalPrice += subTotalPrice;
        activeOrderContent += `
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

    activeOrderContent += `
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

    modalContentContainer.insertAdjacentHTML('afterbegin', activeOrderContent);
}

// INNER FUNCTION(S) //
function markPreviousStepsAsFinished() {
    const finishedSteps = document.querySelectorAll(".checkout-modal-step-finished");
    finishedSteps.forEach( (finishedStep) => finishedStep.classList.remove("checkout-modal-step-finished"));

    const cartStep = document.querySelector("#checkout-modal-cart-step");
    cartStep.classList.add("checkout-modal-step-finished");

    const deliveryStep = document.querySelector("#checkout-modal-delivery-step");
    deliveryStep.classList.add("checkout-modal-step-finished");
}

function highlightReviewStep() {
    const highlightedStep = document.querySelector(".checkout-modal-step-selected");
    highlightedStep.classList.remove("checkout-modal-step-selected");

    const reviewStep = document.querySelector("#checkout-modal-review-step");
    reviewStep.classList.add("checkout-modal-step-selected");
}

function changeCloseButton() {
    let checkoutModalCloseButton = document.querySelector("#checkout-modal-close-button");
    checkoutModalCloseButton.remove();

    const checkoutModalFooterContainerLeftUnit = document.querySelector("#checkout-modal-footer-container-left-unit");
    checkoutModalFooterContainerLeftUnit.insertAdjacentHTML('afterbegin', `<div id="checkout-modal-close-button">Close</div>`);

    checkoutModalCloseButton = document.querySelector("#checkout-modal-close-button");
    checkoutModalCloseButton.addEventListener('click', async () => {
        closeModalDialog();
    });
}

function changePreviousButton() {
    let modalPreviousButton = document.querySelector("#checkout-modal-previous-button");
    modalPreviousButton.remove();

    const checkoutModalFooterContainerRightUnit = document.querySelector("#checkout-modal-footer-container-right-unit");
    checkoutModalFooterContainerRightUnit.insertAdjacentHTML("beforeend", `<div id="checkout-modal-previous-button">Previous</div>`);

    modalPreviousButton = document.querySelector("#checkout-modal-previous-button");
    modalPreviousButton.addEventListener('click', async () => {
        await showDeliveryStep();
    });
}

function changeNextButton() {
    let modalNextButton = document.querySelector("#checkout-modal-next-button");
    modalNextButton.remove();

    const checkoutModalFooterContainerRightUnit = document.querySelector("#checkout-modal-footer-container-right-unit");
    checkoutModalFooterContainerRightUnit.insertAdjacentHTML("beforeend", `<div id="checkout-modal-next-button">Pay</div>`);

    modalNextButton = document.querySelector("#checkout-modal-next-button");
    modalNextButton.addEventListener('click', async () => {
        await showPaymentStep();
    });
}