// INNER USED CONSTANT VARIABLES //
import {fetchData} from "./fetch.js";

const body = document.querySelector('body');
const userButton = document.querySelector("#user-button");

let userModalCloseButton;
let modalDialog;

// EXPORTED FUNCTIONS //
export function addEventListenerToUserButton() {
    userButton.addEventListener('click', () => {
        // Disable scrolling on site
        body.classList.add("block-scroll");

        // Add login modal dialog
        body.insertAdjacentHTML('beforeend', `
            <div id="modal-dialog">
                <div id="modal-fade">
                    <div id="modal-dialog-container">
                        <div id="user-modal-header-container">
                            <div id="user-modal-header">
                                <img src="/static/images/icons/user.png">
                                <div id="user-modal-title">User Setting</div>
                            </div>
                            <div id="user-modal-tabs">
                                <div id="user-modal-contact-tab" class="user-modal-tab user-modal-tab-selected">Contact</div>
                                <div id="user-modal-password-tab" class="user-modal-tab">Password</div>
                                <div id="user-modal-shipping-address-tab" class="user-modal-tab">Shipping Address</div>
                                <div id="user-modal-billing-address-tab" class="user-modal-tab">Billing Address</div>
                            </div>
    
                        </div>
                        <div id="user-modal-content-container"></div>
                        <div id="user-modal-footer-container">
                            <div id="user-modal-close-button">Close</div>
                            <div id="user-modal-update-button"></div>
                        </div>
                    </div>
                </div>
            </div>
        `);

        // Add event listener to close button
        addEventListenerToCloseButton();

        // Add event listeners to all tabs
        addEventListenerToContactTab();
        addEventListenerToPasswordTab();
        addEventListenerToShippingAddressTab();
        addEventListenerToBillingAddressTab();

        // Show first tab (contact) at opening
        const contactTab = document.querySelector("#user-modal-contact-tab");
        contactTab.click();
    });
}

function addEventListenerToCloseButton() {
    userModalCloseButton = document.querySelector("#user-modal-close-button");
    userModalCloseButton.addEventListener('click', ()=> closeModalDialog());
}

function closeModalDialog() {
    // Enable scrolling on site
    body.classList.remove("block-scroll");

    // Remove modal dialog
    modalDialog = document.querySelector("#modal-dialog");
    modalDialog.remove();
}

function addEventListenerToContactTab() {
    const contactTab = document.querySelector("#user-modal-contact-tab");
    contactTab.addEventListener('click', ()=> {
        // Highlight password tab
        const selectedTab = document.querySelector('.user-modal-tab-selected');
        selectedTab.classList.remove("user-modal-tab-selected")

        contactTab.classList.add("user-modal-tab-selected");

        // Empty dialog content
        const userModalContentContainer = document.querySelector("#user-modal-content-container");
        userModalContentContainer.innerHTML = "";

        // Fill dialog content with contact related fields
        userModalContentContainer.insertAdjacentHTML('afterbegin', `
            <div class="user-modal-content-container-row">
                <label for="user-modal-name-input">Name:</label><input type="text" id="user-modal-name-input" required>
            </div>
            <div class="user-modal-content-container-row">
                <label for="user-modal-email-input">E-mail:</label><input type="text" id="user-modal-email-input" required>
            </div>
            <div class="user-modal-content-container-row">
                <label for="user-modal-phone-input">Phone:</label><input type="text" id="user-modal-phone-input" required>
            </div>
            <div class="user-modal-content-container-row" id="user-modal-operation-result"></div>
        `);

        // Change modal update button text
        const userModalUpdateButton = document.querySelector("#user-modal-update-button");
        userModalUpdateButton.textContent = "Update Contact";

        // Add event listener to modal update button
        userModalUpdateButton.addEventListener('click', async () => {
            // Get input values
            const userModalNameInput = document.querySelector("#user-modal-name-input");
            const userModalNameInputValue = userModalNameInput.value;

            const userModalEmailInput = document.querySelector("#user-modal-email-input");
            const userModalEmailInputValue = userModalEmailInput.value;

            const userModalPhoneInput = document.querySelector("#user-modal-phone-input");
            const userModalPhoneInputValue = userModalPhoneInput.value;

            // Update user data
            const response = await fetchData("PUT", `/user`, {"session-token": sessionStorage.getItem("session-token")}, `{"name": "${userModalNameInputValue}", "email": "${userModalEmailInputValue}", "phone": "${userModalPhoneInputValue}"}`, "application/json", null);
            if (response.ok) {
                const UserModalOperationResult = document.querySelector("#user-modal-operation-result");
                UserModalOperationResult.textContent = "Contact details updated successfully.";
            } else {
                UserModalOperationResult.textContent = "Contact details update was unsuccessful.";
            }
        });
    });
}

function addEventListenerToPasswordTab() {
    const passwordTab = document.querySelector("#user-modal-password-tab");
    passwordTab.addEventListener('click', ()=> {
        // Highlight password tab
        const selectedTab = document.querySelector('.user-modal-tab-selected');
        selectedTab.classList.remove("user-modal-tab-selected")

        passwordTab.classList.add("user-modal-tab-selected");

        // Empty dialog content
        const userModalContentContainer = document.querySelector("#user-modal-content-container");
        userModalContentContainer.innerHTML = "";

        // Fill dialog content with password related fields
        userModalContentContainer.insertAdjacentHTML('afterbegin', `
            <div class="user-modal-content-container-row">
                <label for="user-modal-current-password-input">Current Password:</label><input type="text" id="user-modal-current-password-input" required>
            </div>
            <div class="user-modal-content-container-row">
                <label for="user-modal-new-password-input">New Password:</label><input type="text" id="user-modal-new-password-input" required>
            </div>
            <div class="user-modal-content-container-row">
                <label for="user-modal-password-confirmation-input">Password Confirmation:</label><input type="text" id="user-modal-password-confirmation-input" required>
            </div>
            <div class="user-modal-content-container-row" id="user-modal-operation-result"></div>
        `);

        // Change modal update button
        let userModalUpdateButton = document.querySelector("#user-modal-update-button");
        userModalUpdateButton.remove();
        const userModalFooterContainer = document.querySelector("#user-modal-footer-container");
        userModalFooterContainer.insertAdjacentHTML("beforeend", `<div id="user-modal-update-button">Update Password</div>`);

        // Add event listener to modal update button
        userModalUpdateButton = document.querySelector("#user-modal-update-button");
        userModalUpdateButton.addEventListener('click', async () => {
            // Get input values
            const userModalCurrentPasswordInput = document.querySelector("#user-modal-current-password-input");
            const userModalCurrentPasswordInputValue = userModalCurrentPasswordInput.value;

            const userModalNewPasswordInput = document.querySelector("#user-modal-new-password-input");
            const userModalNewPasswordInputValue = userModalNewPasswordInput.value;

            const userModalPasswordConfirmationInput = document.querySelector("#user-modal-password-confirmation-input");
            const userModalPasswordConfirmationInputValue = userModalPasswordConfirmationInput.value;

            if (userModalNewPasswordInputValue === userModalPasswordConfirmationInputValue) {
                // Update user data
                const response = await fetchData("PUT", `/user-authentication`, {"session-token": sessionStorage.getItem("session-token")}, `{"currentPassword": "${userModalCurrentPasswordInputValue}", "newPassword": "${userModalNewPasswordInputValue}"}`, "application/json", null);

                // Show result of update operation in modal dialog
                const UserModalOperationResult = document.querySelector("#user-modal-operation-result");
                if (response.ok) {
                    const UserModalOperationResult = document.querySelector("#user-modal-operation-result");
                    UserModalOperationResult.textContent = "Password updated successfully.";
                } else {
                    UserModalOperationResult.textContent = "Password update was unsuccessful.";
                }
            }
        });
    });
}

function addEventListenerToShippingAddressTab() {
    const shippingAddressTab = document.querySelector("#user-modal-shipping-address-tab");
    shippingAddressTab.addEventListener('click', ()=> {
        // Highlight password tab
        const selectedTab = document.querySelector('.user-modal-tab-selected');
        selectedTab.classList.remove("user-modal-tab-selected")

        shippingAddressTab.classList.add("user-modal-tab-selected");

        // Empty dialog content
        const userModalContentContainer = document.querySelector("#user-modal-content-container");
        userModalContentContainer.innerHTML = "";

        // Fill dialog content with shipping address related fields
        userModalContentContainer.insertAdjacentHTML('afterbegin', `
            <div class="user-modal-content-container-double-row">
                <div class="user-modal-content-container-double-row-row" id="zip-container">
                    <label for="user-modal-zip-input">Zip:</label><input type="text" id="user-modal-zip-input" required>
                </div>
                <div class="user-modal-content-container-double-row-row" id="country-container">
                    <label for="user-modal-country-input">Country:</label><input type="text" id="user-modal-country-input" required>
                </div>                
            </div>
            <div class="user-modal-content-container-row">
                <label for="user-modal-city-input">City:</label><input type="text" id="user-modal-city-input" required>
            </div>
            <div class="user-modal-content-container-row">
                <label for="user-modal-address-input">Address:</label><input type="text" id="user-modal-address-input" required>
            </div>
            <div class="user-modal-content-container-row" id="user-modal-operation-result"></div>
        `);

        // Change modal update button
        let userModalUpdateButton = document.querySelector("#user-modal-update-button");
        userModalUpdateButton.remove();
        const userModalFooterContainer = document.querySelector("#user-modal-footer-container");
        userModalFooterContainer.insertAdjacentHTML("beforeend", `<div id="user-modal-update-button">Update Shipping Address</div>`);

        // Add event listener to modal update button
        userModalUpdateButton = document.querySelector("#user-modal-update-button");
        userModalUpdateButton.addEventListener('click', async () => {
            // Get input values
            const userModalZipInput = document.querySelector("#user-modal-zip-input");
            const userModalZipInputValue = userModalZipInput.value;

            const userModalCountryInput = document.querySelector("#user-modal-country-input");
            const userModalCountryInputValue = userModalCountryInput.value;

            const userModalCityInput = document.querySelector("#user-modal-city-input");
            const userModalCityInputValue = userModalCityInput.value;

            const userModalAddressInput = document.querySelector("#user-modal-address-input");
            const userModalAddressInputValue = userModalAddressInput.value;

            // Update user data
            await fetchData("PUT", `/address?type=shipping`, {"session-token": sessionStorage.getItem("session-token")}, `{"zip": "${userModalZipInputValue}", "country": "${userModalCountryInputValue}", "city": "${userModalCityInputValue}", "address": "${userModalAddressInputValue}"}`, "application/json", null);
        });
    });
}

function addEventListenerToBillingAddressTab() {
    const billingAddressTab = document.querySelector("#user-modal-billing-address-tab");
    billingAddressTab.addEventListener('click', ()=> {
        // Highlight password tab
        const selectedTab = document.querySelector('.user-modal-tab-selected');
        selectedTab.classList.remove("user-modal-tab-selected")

        billingAddressTab.classList.add("user-modal-tab-selected");

        // Empty dialog content
        const userModalContentContainer = document.querySelector("#user-modal-content-container");
        userModalContentContainer.innerHTML = "";

        // Fill dialog content with billing address related fields
        userModalContentContainer.insertAdjacentHTML('afterbegin', `
            <div class="user-modal-content-container-double-row">
                <div class="user-modal-content-container-double-row-row" id="zip-container">
                    <label for="user-modal-zip-input">Zip:</label><input type="text" id="user-modal-zip-input" required>
                </div>
                <div class="user-modal-content-container-double-row-row" id="country-container">
                    <label for="user-modal-country-input">Country:</label><input type="text" id="user-modal-country-input" required>
                </div>                
            </div>
            <div class="user-modal-content-container-row">
                <label for="user-modal-city-input">City:</label><input type="text" id="user-modal-city-input" required>
            </div>
            <div class="user-modal-content-container-row">
                <label for="user-modal-address-input">Address:</label><input type="text" id="user-modal-address-input" required>
            </div>
            <div class="user-modal-content-container-row" id="user-modal-operation-result"></div>
        `);

        // Change modal update button
        let userModalUpdateButton = document.querySelector("#user-modal-update-button");
        userModalUpdateButton.remove();
        const userModalFooterContainer = document.querySelector("#user-modal-footer-container");
        userModalFooterContainer.insertAdjacentHTML("beforeend", `<div id="user-modal-update-button">Update Shipping Address</div>`);

        // Add event listener to modal update button
        userModalUpdateButton = document.querySelector("#user-modal-update-button");
        userModalUpdateButton.addEventListener('click', async () => {
            // Get input values
            const userModalZipInput = document.querySelector("#user-modal-zip-input");
            const userModalZipInputValue = userModalZipInput.value;

            const userModalCountryInput = document.querySelector("#user-modal-country-input");
            const userModalCountryInputValue = userModalCountryInput.value;

            const userModalCityInput = document.querySelector("#user-modal-city-input");
            const userModalCityInputValue = userModalCityInput.value;

            const userModalAddressInput = document.querySelector("#user-modal-address-input");
            const userModalAddressInputValue = userModalAddressInput.value;

            // Update user data
            await fetchData("PUT", `/address?type=billing`, {"session-token": sessionStorage.getItem("session-token")}, `{"zip": "${userModalZipInputValue}", "country": "${userModalCountryInputValue}", "city": "${userModalCityInputValue}", "address": "${userModalAddressInputValue}"}`, "application/json", null);
        });
    });
}