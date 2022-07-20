import {showCartStep} from "./checkout-cart.js";
import {showReviewStep} from "./checkout-review.js";
import {fetchData} from "../fetch.js";

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

    // Get order contact, order shipping address and order billing address
    let orderContact = await fetchData("GET", `/active-order-contact`, {"session-token": sessionStorage.getItem("session-token")}, null, null, "JSON");
    if (orderContact == null) {
        orderContact = {name: "", email: "", phone: ""};
    }

    let orderShippingAddress = await fetchData("GET", `/active-order-address?type=shipping`, {"session-token": sessionStorage.getItem("session-token")}, null, null, "JSON");
    if (orderShippingAddress == null) {
        orderShippingAddress = {name: "", zip: "", country: "", city: "", address: ""};
    }

    let orderBillingAddress = await fetchData("GET", `/active-order-address?type=billing`, {"session-token": sessionStorage.getItem("session-token")}, null, null, "JSON");
    if (orderBillingAddress == null) {
        orderBillingAddress = {name: "", zip: "", country: "", city: "", address: ""};
    }

    // Show delivery step
    modalContentContainer.insertAdjacentHTML('afterbegin', `
        <div class="delivery-content-unit">
            <div class="delivery-content-unit-title">
                <span>Contact Details</span>
                <button id="load-contact-details-button" type="button">Load Contact Details</button>
            </div>
            <div class="delivery-content-unit-content">
                <div class="checkout-modal-content-container-single-row">
                    <label for="contact-name-input">Name:</label><input type="text" id="contact-name-input" value="${orderContact.name}" required>
                </div>
                <div class="checkout-modal-content-container-double-row">
                    <div class="checkout-modal-content-container-double-row-unit" id="contact-email-container">
                        <label for="contact-email-input">E-mail:</label><input type="text" id="contact-email-input" value="${orderContact.email}" required>
                    </div>
                    <div class="checkout-modal-content-container-double-row-unit" id="contact-phone-container">
                        <label for="contact-phone-input">Phone:</label><input type="text" id="contact-phone-input" value="${orderContact.phone}" required>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="delivery-content-unit">
            <div class="delivery-content-unit-title">
                <span>Shipping Details</span>
                <button id="load-shipping-details-button" type="button">Load Shipping Details</button>
            </div>
            <div class="delivery-content-unit-content">
                <div class="checkout-modal-content-container-single-row">
                    <label for="shipping-name-input">Name / Company:</label><input type="text" id="shipping-name-input" value="${orderShippingAddress.name}" required>
                </div>
                <div class="checkout-modal-content-container-triple-row">
                    <div class="checkout-modal-content-container-triple-row-unit" id="shipping-zip-container">
                        <label for="shipping-zip-input">Zip:</label><input type="text" id="shipping-zip-input" value="${orderShippingAddress.zip}" required>
                    </div>
                    <div class="checkout-modal-content-container-triple-row-unit" id="shipping-country-container">
                        <label for="shipping-country-input">Country:</label><input type="text" id="shipping-country-input" value="${orderShippingAddress.country}" required>
                    </div>
                    <div class="checkout-modal-content-container-triple-row-unit" id="shipping-city-container">
                        <label for="shipping-city-input">City:</label><input type="text" id="shipping-city-input" value="${orderShippingAddress.city}" required>
                    </div>
                </div>
                <div class="checkout-modal-content-container-single-row">
                    <label for="shipping-address-input">Address:</label><input type="text" id="shipping-address-input" value="${orderShippingAddress.address}" required>
                </div>
            </div>
        </div>
        
        <div class="delivery-content-unit">
            <div class="delivery-content-unit-title">
                <span>Billing Details</span>
                <button id="load-billing-details-button" type="button">Load Billing Details</button>
            </div>
            <div class="delivery-content-unit-content">
                <div class="checkout-modal-content-container-single-row">
                    <label for="billing-name-input">Name / Company:</label><input type="text" id="billing-name-input" value="${orderBillingAddress.name}" required>
                </div>
                <div class="checkout-modal-content-container-triple-row">
                    <div class="checkout-modal-content-container-triple-row-unit" id="billing-zip-container">
                        <label for="billing-zip-input">Zip:</label><input type="text" id="billing-zip-input" value="${orderBillingAddress.zip}" required>
                    </div>
                    <div class="checkout-modal-content-container-triple-row-unit" id="billing-country-container">
                        <label for="billing-country-input">Country:</label><input type="text" id="billing-country-input" value="${orderBillingAddress.country}" required>
                    </div>
                    <div class="checkout-modal-content-container-triple-row-unit" id="billing-city-container">
                        <label for="billing-city-input">City:</label><input type="text" id="billing-city-input" value="${orderBillingAddress.city}" required>
                    </div>
                </div>
                <div class="checkout-modal-content-container-single-row">
                    <label for="billing-address-input">Address:</label><input type="text" id="billing-address-input" value="${orderBillingAddress.address}" required>
                </div>
            </div>
        </div>
    `);

    // Add event listeners to 'Load Contact Details' buttons
    const loadContactDetailsButton = document.querySelector("#load-contact-details-button");
    loadContactDetailsButton.addEventListener('click', async () => {
        // Get existing user data from backend
        const userContact = await fetchData("GET", `/user`, {"session-token": sessionStorage.getItem("session-token")}, null, null, "JSON");

        // Fill contact related fields
        const contactNameInput = document.querySelector('#contact-name-input');
        contactNameInput.setAttribute("value", userContact.name);

        const contactEmailInput = document.querySelector('#contact-email-input');
        contactEmailInput.setAttribute("value", userContact.email);

        const contactPhoneInput = document.querySelector('#contact-phone-input');
        contactPhoneInput.setAttribute("value", userContact.phone);
    });

    // Add event listeners to 'Load Shipping Details' buttons
    const loadShippingDetailsButton = document.querySelector("#load-shipping-details-button");
    loadShippingDetailsButton.addEventListener('click', async () => {
        // Get existing user data from backend
        let userShippingAddress = await fetchData("GET", `/address?type=shipping`, {"session-token": sessionStorage.getItem("session-token")}, null, null, "JSON");
        if (userShippingAddress == null) {
            userShippingAddress = {name: "", zip: "", country: "", city: "", address: ""};
        }

        // Fill shipping related fields
        const shippingNameInput = document.querySelector('#shipping-name-input');
        shippingNameInput.setAttribute("value", userShippingAddress.name);

        const shippingZipInput = document.querySelector('#shipping-zip-input');
        shippingZipInput.setAttribute("value", userShippingAddress.zip);

        const shippingCountryInput = document.querySelector('#shipping-country-input');
        shippingCountryInput.setAttribute("value", userShippingAddress.country);

        const shippingCityInput = document.querySelector('#shipping-city-input');
        shippingCityInput.setAttribute("value", userShippingAddress.city);

        const shippingAddressInput = document.querySelector('#shipping-address-input');
        shippingAddressInput.setAttribute("value", userShippingAddress.address);
    });

    // Add event listeners to 'Load Billing Details' buttons
    const loadBillingDetailsButton = document.querySelector("#load-billing-details-button");
    loadBillingDetailsButton.addEventListener('click', async () => {
        // Get existing user data from backend
        let userBillingAddress = await fetchData("GET", `/address?type=billing`, {"session-token": sessionStorage.getItem("session-token")}, null, null, "JSON");
        if (userBillingAddress == null) {
            userBillingAddress = {name: "", zip: "", country: "", city: "", address: ""};
        }

        // Fill billing related fields
        const billingNameInput = document.querySelector('#billing-name-input');
        billingNameInput.setAttribute("value", userBillingAddress.name);

        const billingZipInput = document.querySelector('#billing-zip-input');
        billingZipInput.setAttribute("value", userBillingAddress.zip);

        const billingCountryInput = document.querySelector('#billing-country-input');
        billingCountryInput.setAttribute("value", userBillingAddress.country);

        const billingCityInput = document.querySelector('#billing-city-input');
        billingCityInput.setAttribute("value", userBillingAddress.city);

        const billingAddressInput = document.querySelector('#billing-address-input');
        billingAddressInput.setAttribute("value", userBillingAddress.address);
    });
}
