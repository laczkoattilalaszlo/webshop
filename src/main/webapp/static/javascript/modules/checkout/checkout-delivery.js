import {showCartStep} from "./checkout-cart.js";
import {showReviewStep} from "./checkout-review.js";

// EXPORTED FUNCTION(S) //
export async function showDeliveryStep() {
    // Mark earlier steps as finished
    const finishedSteps = document.querySelectorAll(".checkout-modal-step-finished");
    for (let finishedStep of finishedSteps) {
        finishedStep.classList.remove("checkout-modal-step-finished");
    }

    const cartStep = document.querySelector("#checkout-modal-cart-step");
    cartStep.classList.add("checkout-modal-step-finished");

    // Highlight delivery step
    const highlightedSteps = document.querySelectorAll(".checkout-modal-step-selected");
    for (let highlightedStep of highlightedSteps) {
        highlightedStep.classList.remove("checkout-modal-step-selected");
    }

    const deliveryStep = document.querySelector("#checkout-modal-delivery-step");
    deliveryStep.classList.add("checkout-modal-step-selected");

    // Change previous button
    let modalPreviousButton = document.querySelector("#checkout-modal-previous-button");
    modalPreviousButton.remove();

    const checkoutModalFooterContainerRightUnit = document.querySelector("#checkout-modal-footer-container-right-unit");
    checkoutModalFooterContainerRightUnit.insertAdjacentHTML("beforeend", `<div id="checkout-modal-previous-button">Previous</div>`);

    modalPreviousButton = document.querySelector("#checkout-modal-previous-button");
    modalPreviousButton.addEventListener('click', async () => await showCartStep());

    // Change next button
    let modalNextButton = document.querySelector("#checkout-modal-next-button");
    modalNextButton.remove();

    checkoutModalFooterContainerRightUnit.insertAdjacentHTML("beforeend", `<div id="checkout-modal-next-button">Next</div>`);

    modalNextButton = document.querySelector("#checkout-modal-next-button");
    modalNextButton.addEventListener('click', async () => await showReviewStep());

    // Empty modal dialog content
    const modalContentContainer = document.querySelector("#checkout-modal-content-container");
    modalContentContainer.innerHTML = "";

    // Get delivery data from backend (contact information, shipping address, billing address)
    // Get delivery data from backend (contact information, shipping address, billing address)
    // Get delivery data from backend (contact information, shipping address, billing address)
    // Get delivery data from backend (contact information, shipping address, billing address)
    // Get delivery data from backend (contact information, shipping address, billing address)

    //
    modalContentContainer.insertAdjacentHTML('afterbegin', `
        <div class="delivery-content-unit">
            <div class="delivery-content-unit-title">
                <span>Customer Contact</span>
                <button type="button">Load Contact Details</button>
            </div>
            <div class="delivery-content-unit-content">
                <div class="checkout-modal-content-container-single-row">
                    <label for="contact-name-input">Name:</label><input type="text" id="contact-name-input" required>
                </div>
                <div class="checkout-modal-content-container-double-row">
                    <div class="checkout-modal-content-container-double-row-unit" id="contact-email-container">
                        <label for="contact-email-input">E-mail:</label><input type="text" id="contact-email-input" required>
                    </div>
                    <div class="checkout-modal-content-container-double-row-unit" id="contact-phone-container">
                        <label for="contact-phone-input">Phone:</label><input type="text" id="contact-phone-input" required>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="delivery-content-unit">
            <div class="delivery-content-unit-title">
                <span>Shipping Details</span>
                <button type="button">Load Shipping Details</button>
            </div>
            <div class="delivery-content-unit-content">
                <div class="checkout-modal-content-container-single-row">
                    <label for="shipping-name-input">Name / Company:</label><input type="text" id="shipping-name-input" required>
                </div>
                <div class="checkout-modal-content-container-triple-row">
                    <div class="checkout-modal-content-container-triple-row-unit" id="shipping-zip-container">
                        <label for="shipping-zip-input">Zip:</label><input type="text" id="shipping-zip-input" required>
                    </div>
                    <div class="checkout-modal-content-container-triple-row-unit" id="shipping-country-container">
                        <label for="shipping-country-input">Country:</label><input type="text" id="shipping-country-input" required>
                    </div>
                    <div class="checkout-modal-content-container-triple-row-unit" id="shipping-city-container">
                        <label for="shipping-city-input">City:</label><input type="text" id="shipping-city-input" required>
                    </div>
                </div>
                <div class="checkout-modal-content-container-single-row">
                    <label for="shipping-address-input">Address:</label><input type="text" id="shipping-address-input" required>
                </div>
            </div>
        </div>
        
        <div class="delivery-content-unit">
            <div class="delivery-content-unit-title">
                <span>Billing Details</span>
                <button type="button">Load Billing Details</button>
            </div>
            <div class="delivery-content-unit-content">
                <div class="checkout-modal-content-container-single-row">
                    <label for="billing-name-input">Name / Company:</label><input type="text" id="billing-name-input" required>
                </div>
                <div class="checkout-modal-content-container-triple-row">
                    <div class="checkout-modal-content-container-triple-row-unit" id="billing-zip-container">
                        <label for="billing-zip-input">Zip:</label><input type="text" id="billing-zip-input" required>
                    </div>
                    <div class="checkout-modal-content-container-triple-row-unit" id="billing-country-container">
                        <label for="billing-country-input">Country:</label><input type="text" id="billing-country-input" required>
                    </div>
                    <div class="checkout-modal-content-container-triple-row-unit" id="billing-city-container">
                        <label for="billing-city-input">City:</label><input type="text" id="billing-city-input" required>
                    </div>
                </div>
                <div class="checkout-modal-content-container-single-row">
                    <label for="billing-address-input">Address:</label><input type="text" id="billing-address-input" required>
                </div>
            </div>
        </div>
    `);
}
