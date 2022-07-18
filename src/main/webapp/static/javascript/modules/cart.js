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
                        <div id="cart-modal-header-container">
                            <div id="cart-modal-header">
                                <div id="cart-modal-title">Checkout Process</div>
                            </div>
                            <div id="cart-modal-steps">
                                <div id="cart-modal-cart-step" class="cart-modal-step cart-modal-step-finished">
                                    <img src="/static/images/icons/cart.png"><div>Cart</div>
                                </div>
                                <img src="/static/images/icons/next.png" class="cart-model-step-separator">
                                <div id="cart-modal-delivery-step" class="cart-modal-step cart-modal-step-finished">
                                    <img src="/static/images/icons/delivery.png"><div>Delivery</div>
                                </div>
                                <img src="/static/images/icons/next.png" class="cart-model-step-separator">
                                <div id="cart-modal-review-step" class="cart-modal-step cart-modal-step-selected">
                                    <img src="/static/images/icons/review.png"><div>Review</div>
                                </div>
                                <img src="/static/images/icons/next.png" class="cart-model-step-separator">
                                <div id="cart-modal-payment-step" class="cart-modal-step">
                                    <img src="/static/images/icons/payment.png"><div>Payment</div>
                                </div>
                                <img src="/static/images/icons/next.png" class="cart-model-step-separator">
                                <div id="cart-modal-confirmation-step" class="cart-modal-step">
                                    <img src="/static/images/icons/confirmation.png"><div>Confirmation</div>
                                </div>
                            </div>
    
                        </div>
                        <div id="cart-modal-content-container"></div>
                        <div id="cart-modal-footer-container">
                            <div id="cart-modal-previous-button">Previous</div>
                            <div id="cart-modal-next-button">Next</div>
                        </div>
                    </div>
                </div>
            </div>
        `);
    });
}