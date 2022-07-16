import {fetchData} from "./fetch.js";

// INNER USED CONSTANT VARIABLES //
const body = document.querySelector('body');
const registrationButton = document.querySelector("#registration-button");

let registrationModalCancelButton;
let modalDialog;

// EXPORTED FUNCTIONS //
export function registrationButtonAddEventListener() {
    // Show modal dialog
    registrationButton.addEventListener('click', () => {
        body.classList.add("block-scroll");
        body.insertAdjacentHTML('beforeend',    `
                                                            <div id="modal-dialog">
                                                                <div id="modal-fade">
                                                                    <div id="modal-dialog-container">
                                                                        <div id="registration-modal-header-container">
                                                                            <img src="/static/images/icons/registration.png">
                                                                            <div id="registration-modal-title">Register user</div>
                                                                        </div>
                                                                        <div id="registration-modal-content-container">
                                                                            <input type="text" id="modal-registration-email-input" placeholder="E-mail" required>
                                                                            <input type="text" id="modal-registration-password-input" placeholder="Password" required>
                                                                            <input type="text" id="modal-registration-password-confirmation-input" placeholder="Confirm Password" required>
                                                                        </div>
                                                                        <div id="registration-modal-footer-container">
                                                                            <div id="registration-modal-cancel-button">Cancel</div>
                                                                            <div id="registration-modal-registration-button">Register</div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        `);
        // Add event listener to cancel button
        AddEventListenerToCancelButton();

        // Add event listener to registration button
        addEventListenerToRegistrationButton();
    });
}

// INNER FUNCTIONS //
function AddEventListenerToCancelButton() {
    registrationModalCancelButton = document.querySelector("#registration-modal-cancel-button");
    modalDialog = document.querySelector("#modal-dialog");
    registrationModalCancelButton.addEventListener('click', ()=> modalDialog.remove());
}

function addEventListenerToRegistrationButton() {
    const registrationButton = document.querySelector("#registration-modal-registration-button");
    registrationButton.addEventListener('click', async ()=> {
        // Get input values
        const modalRegistrationEmailInput = document.querySelector("#modal-registration-email-input");
        const modalRegistrationEmailInputValue = modalRegistrationEmailInput.value;

        const modalRegistrationPasswordInput = document.querySelector("#modal-registration-password-input");
        const modalRegistrationPasswordInputValue = modalRegistrationPasswordInput.value;

        const modalRegistrationPasswordConfirmationInput = document.querySelector("#modal-registration-password-confirmation-input");
        const modalRegistrationPasswordConfirmationInputValue = modalRegistrationPasswordConfirmationInput.value;

        // Validate input values
        if (modalRegistrationPasswordInputValue === modalRegistrationPasswordConfirmationInputValue) {
            // Send values to the backend
            const response = await fetchData("POST", `/user`, null, `{"email": "${modalRegistrationEmailInputValue}", "password": "${modalRegistrationPasswordInputValue}"}`, "application/json", );

            // Close modal dialog
            if (response.ok) {
                modalDialog.remove();
            }
        }
    });
}
