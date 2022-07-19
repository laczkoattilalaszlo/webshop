import {fetchData} from "./fetch.js";

// INNER USED CONSTANT VARIABLES //
const body = document.querySelector('body');
const cartButton = document.querySelector("#cart-button");

// EXPORTED FUNCTIONS //
export function addEventListenerToCartButton() {
    cartButton.addEventListener('click', async () => {
        // Disable scrolling on site
        body.classList.add("block-scroll");

        // Add login modal dialog
        body.insertAdjacentHTML('beforeend', `
            <div id="modal-dialog">
                <div id="modal-fade">
                    <div id="modal-dialog-container">
                        <div id="checkout-modal-header-container">
                            <div id="checkout-modal-header">
                                <div id="checkout-modal-title">Checkout Process</div>
                            </div>
                            <div id="checkout-modal-steps">
                                <div id="checkout-modal-cart-step" class="checkout-modal-step">
                                    <img src="/static/images/icons/cart.png"><div>Cart</div>
                                </div>
                                <img src="/static/images/icons/next.png" class="checkout-modal-step-separator">
                                <div id="checkout-modal-delivery-step" class="checkout-modal-step">
                                    <img src="/static/images/icons/delivery.png"><div>Delivery</div>
                                </div>
                                <img src="/static/images/icons/next.png" class="checkout-modal-step-separator">
                                <div id="checkout-modal-review-step" class="checkout-modal-step">
                                    <img src="/static/images/icons/review.png"><div>Review</div>
                                </div>
                                <img src="/static/images/icons/next.png" class="checkout-modal-step-separator">
                                <div id="checkout-modal-payment-step" class="checkout-modal-step">
                                    <img src="/static/images/icons/payment.png"><div>Payment</div>
                                </div>
                                <img src="/static/images/icons/next.png" class="checkout-modal-step-separator">
                                <div id="checkout-modal-confirmation-step" class="checkout-modal-step">
                                    <img src="/static/images/icons/confirmation.png"><div>Confirmation</div>
                                </div>
                            </div>
    
                        </div>
                        <div id="checkout-modal-content-container"></div>
                        <div id="checkout-modal-footer-container">
                            <div id="checkout-modal-footer-container-left-unit">
                                <div id="checkout-modal-close-button">Close</div>
                            </div>
                            <div id="checkout-modal-footer-container-right-unit">
                                <div id="checkout-modal-previous-button">Previous</div>
                                <div id="checkout-modal-next-button">Next</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        `);

        // Add event listener to close button
        addEventListenerToCloseButton();

        // Show cart step
        await showCartStep();
    });

    // Click when loading site (development only)
    cartButton.click();
}

// INNER FUNCTIONS //
function addEventListenerToCloseButton() {
    const checkoutModalCloseButton = document.querySelector("#checkout-modal-close-button");
    checkoutModalCloseButton.addEventListener('click', ()=> closeModalDialog());
}

function closeModalDialog() {
    // Enable scrolling on site
    body.classList.remove("block-scroll");

    // Remove modal dialog
    const modalDialog = document.querySelector("#modal-dialog");
    modalDialog.remove();
}

function addEventListenerToNextButton(actionToExecute) {
    const modalNextButton = document.querySelector("#checkout-modal-next-button");
    modalNextButton.addEventListener('click', async () => await actionToExecute());
}

async function showCartStep() {
    // Highlight cart step
    const cartStep = document.querySelector("#checkout-modal-cart-step");
    cartStep.classList.add("checkout-modal-step-selected");

    // Hide previous button
    const modalPreviousButton = document.querySelector("#checkout-modal-previous-button");
    modalPreviousButton.classList.add("invisible");

    // Add event listener to next button
    addEventListenerToNextButton(showDeliveryStep);

    // Empty dialog content
    const userModalContentContainer = document.querySelector("#checkout-modal-content-container");
    userModalContentContainer.innerHTML = "";

    // Get cart from backend
    const cart = await fetchData("GET", `/cart`, {"session-token": sessionStorage.getItem("session-token")}, null, null, "JSON");

    // Show cart content in a table
    if (cart.length === 0) {
        userModalContentContainer.insertAdjacentHTML('afterbegin', "<div id='modal-cart-is-empty-text'>Cart is empty, choose product from product categories!</div>");
    } else {
        let cartTable = `<table id="checkout-modal-cart-table">
                            <colgroup>
                                <col id="checkout-modal-picture-column" >
                                <col id="checkout-modal-supplier-and-name-column">
                                <col id="checkout-modal-price-and-currency-column">
                                <col id="checkout-modal-quantity-column">
                                <col id="checkout-modal-sub-total-column">
                                <col id="checkout-modal-buttons-column">
                            </colgroup>
                            <tr>
                                <th class="checkout-modal-left-aligned-column"></th>
                                <th class="checkout-modal-left-aligned-column"></th>
                                <th class="checkout-modal-left-aligned-column">Unit price</th>
                                <th class="checkout-modal-center-aligned-column">Quantity</th>
                                <th class="checkout-modal-right-aligned-column">Sub-total</th>
                                <th class="checkout-modal-right-aligned-column"></th>
                            </tr>`;

        let totalPrice = 0;
        let currency = cart[0].currency;
        for (let product of cart) {
            let subTotalPrice = product.price * product.quantity;
            totalPrice += subTotalPrice;
            cartTable += `<tr>
                            <td class="checkout-modal-left-aligned-column"><img class="checkout-modal-product-photo" src="${product.picture}"></td>
                            <td class="checkout-modal-left-aligned-column">${product.supplierName} ${product.name}</td>
                            <td class="checkout-modal-left-aligned-column">${product.price} ${product.currency}</td>
                            <td class="checkout-modal-center-aligned-column">${product.quantity}</td>
                            <td class="checkout-modal-right-aligned-column">${subTotalPrice} ${product.currency}</td>
                            <td class="checkout-modal-right-aligned-column">
                                <button class="checkout-modal-add-to-cart-button" data-product-id="${product.productId}" type="button">+</button> 
                                <button class="checkout-modal-remove-from-cart-button" data-product-id="${product.productId}" type="button">-</button>
                            </td class="checkout-modal-right-aligned-column">
                          </tr>`;
        }

        cartTable += `  <tr id="checkout-modal-total-row">
                            <td class="checkout-modal-left-aligned-column"></td>
                            <td class="checkout-modal-left-aligned-column"></td>
                            <td class="checkout-modal-left-aligned-column"></td>
                            <td class="checkout-modal-center-aligned-column" id="checkout-modal-total-prize-text"><div>Total:</div></td>
                            <td class="checkout-modal-right-aligned-column" id="checkout-modal-total-price"><div>${totalPrice} ${currency}</div></td>
                            <td class="checkout-modal-right-aligned-column"></td>
                        </tr>
                      </table>`;

        userModalContentContainer.insertAdjacentHTML('afterbegin', cartTable);
    }




}

async function showDeliveryStep() {
    console.log("Delivery Step Content");
}