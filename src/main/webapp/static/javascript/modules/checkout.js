import {fetchData} from "./fetch.js";

// INNER USED CONSTANT VARIABLES //
const body = document.querySelector('body');
const cartButton = document.querySelector("#cart-button");

// EXPORTED FUNCTIONS //
export function addEventListenerToCartButton() {
    cartButton.addEventListener('click', () => {
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
                                <div id="checkout-modal-cart-step" class="checkout-modal-step checkout-modal-step-selected">
                                    <img src="/static/images/icons/cart.png"><div>Cart</div>
                                </div>
                                <img src="/static/images/icons/next.png" class="checkout-model-step-separator">
                                <div id="checkout-modal-delivery-step" class="checkout-modal-step">
                                    <img src="/static/images/icons/delivery.png"><div>Delivery</div>
                                </div>
                                <img src="/static/images/icons/next.png" class="checkout-model-step-separator">
                                <div id="checkout-modal-review-step" class="checkout-modal-step">
                                    <img src="/static/images/icons/review.png"><div>Review</div>
                                </div>
                                <img src="/static/images/icons/next.png" class="checkout-model-step-separator">
                                <div id="checkout-modal-payment-step" class="checkout-modal-step">
                                    <img src="/static/images/icons/payment.png"><div>Payment</div>
                                </div>
                                <img src="/static/images/icons/next.png" class="checkout-model-step-separator">
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
    });
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