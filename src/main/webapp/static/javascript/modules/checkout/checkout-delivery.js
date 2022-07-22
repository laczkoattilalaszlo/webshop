import {fetchData} from "../fetch.js";
import {closeModalDialog} from "./checkout.js";
import {showCartStep} from "./checkout-cart.js";
import {showReviewStep} from "./checkout-review.js";

// EXPORTED FUNCTION(S) //
export async function showDeliveryStep() {
    // Mark earlier steps as finished
    markEarlierStepsAsFinished();

    // Highlight delivery step
    highlightDeliveryStep();

    // Change close button
    changeCloseButton();

    // Change previous button
    changePreviousButton();

    // Change next button
    changeNextButton();

    // Empty modal dialog content
    const modalContentContainer = document.querySelector("#checkout-modal-content-container");
    modalContentContainer.innerHTML = "";

    // Get active order contact, active order shipping address and active order billing address
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
    addEventListenerToLoadContactDetailsButton();

    // Add event listeners to 'Load Shipping Details' buttons
    addEventListenerToLoadShippingDetailsButton();

    // Add event listeners to 'Load Billing Details' buttons
    addEventListenerToLoadBillingDetailsButton();
}

// INNER FUNCTION(S) //
function markEarlierStepsAsFinished() {
    const finishedSteps = document.querySelectorAll(".checkout-modal-step-finished");
    for (let finishedStep of finishedSteps) {
        finishedStep.classList.remove("checkout-modal-step-finished");
    }

    const cartStep = document.querySelector("#checkout-modal-cart-step");
    cartStep.classList.add("checkout-modal-step-finished");
}

function highlightDeliveryStep() {
    const highlightedSteps = document.querySelectorAll(".checkout-modal-step-selected");
    for (let highlightedStep of highlightedSteps) {
        highlightedStep.classList.remove("checkout-modal-step-selected");
    }

    const deliveryStep = document.querySelector("#checkout-modal-delivery-step");
    deliveryStep.classList.add("checkout-modal-step-selected");
}

function changeCloseButton() {
    let checkoutModalCloseButton = document.querySelector("#checkout-modal-close-button");
    checkoutModalCloseButton.remove();

    const checkoutModalFooterContainerLeftUnit = document.querySelector("#checkout-modal-footer-container-left-unit");
    checkoutModalFooterContainerLeftUnit.insertAdjacentHTML('afterbegin', `<div id="checkout-modal-close-button">Close</div>`);

    checkoutModalCloseButton = document.querySelector("#checkout-modal-close-button");
    checkoutModalCloseButton.addEventListener('click', async () => {
        await updateActiveOrderContact();
        await updateActiveOrderShippingAddress();
        await updateActiveOrderBillingAddress();
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
        await updateActiveOrderContact();
        await updateActiveOrderShippingAddress();
        await updateActiveOrderBillingAddress();
        await showCartStep();
    });
}

function changeNextButton() {
    let modalNextButton = document.querySelector("#checkout-modal-next-button");
    modalNextButton.remove();

    const checkoutModalFooterContainerRightUnit = document.querySelector("#checkout-modal-footer-container-right-unit");
    checkoutModalFooterContainerRightUnit.insertAdjacentHTML("beforeend", `<div id="checkout-modal-next-button">Next</div>`);

    modalNextButton = document.querySelector("#checkout-modal-next-button");
    modalNextButton.addEventListener('click', async () => {
        await updateActiveOrderContact();
        await updateActiveOrderShippingAddress();
        await updateActiveOrderBillingAddress();
        await showReviewStep();
    });
}

async function updateActiveOrderContact() {
    // Get input values
    const contactNameInput = document.querySelector('#contact-name-input');
    const contactNameInputValue = contactNameInput.value;

    const contactEmailInput = document.querySelector('#contact-email-input');
    const contactEmailInputValue = contactEmailInput.value;

    const contactPhoneInput = document.querySelector('#contact-phone-input');
    const contactPhoneInputValue = contactPhoneInput.value;

    // Update active order contact
    await fetchData("PUT", `/active-order-contact`, {"session-token": sessionStorage.getItem("session-token")}, `{"name": "${contactNameInputValue}", "email": "${contactEmailInputValue}", "phone": "${contactPhoneInputValue}"}`, "application/json", null);
}

async function updateActiveOrderShippingAddress() {
    // Get input values
    const shippingNameInput = document.querySelector('#shipping-name-input');
    const shippingNameInputValue = shippingNameInput.value;

    const shippingZipInput = document.querySelector('#shipping-zip-input');
    const shippingZipInputValue = shippingZipInput.value;


    const shippingCountryInput = document.querySelector('#shipping-country-input');
    const shippingCountryInputValue = shippingCountryInput.value;


    const shippingCityInput = document.querySelector('#shipping-city-input');
    const shippingCityInputValue = shippingCityInput.value;


    const shippingAddressInput = document.querySelector('#shipping-address-input');
    const shippingAddressInputValue = shippingAddressInput.value;

    // Update active order shipping address
    await fetchData("PUT", `/active-order-address?type=shipping`, {"session-token": sessionStorage.getItem("session-token")}, `{"name": "${shippingNameInputValue}", "zip": "${shippingZipInputValue}", "country": "${shippingCountryInputValue}", "city": "${shippingCityInputValue}", "address": "${shippingAddressInputValue}"}`, "application/json", null);
}

async function updateActiveOrderBillingAddress() {
    // Get input values
    const billingNameInput = document.querySelector('#billing-name-input');
    const billingNameInputValue = billingNameInput.value;

    const billingZipInput = document.querySelector('#billing-zip-input');
    const billingZipInputValue = billingZipInput.value;


    const billingCountryInput = document.querySelector('#billing-country-input');
    const billingCountryInputValue = billingCountryInput.value;


    const billingCityInput = document.querySelector('#billing-city-input');
    const billingCityInputValue = billingCityInput.value;


    const billingAddressInput = document.querySelector('#billing-address-input');
    const billingAddressInputValue = billingAddressInput.value;

    // Update active order billing address
    await fetchData("PUT", `/active-order-address?type=billing`, {"session-token": sessionStorage.getItem("session-token")}, `{"name": "${billingNameInputValue}", "zip": "${billingZipInputValue}", "country": "${billingCountryInputValue}", "city": "${billingCityInputValue}", "address": "${billingAddressInputValue}"}`, "application/json", null);
}

function addEventListenerToLoadContactDetailsButton() {
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
}

function addEventListenerToLoadShippingDetailsButton() {
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
}

function addEventListenerToLoadBillingDetailsButton() {
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