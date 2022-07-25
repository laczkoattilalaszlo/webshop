import {fetchData} from "./fetch.js";

// INNER USED CONSTANT VARIABLES //
const body = document.querySelector('body');
const registrationButton = document.querySelector("#registration-button");

let registrationModalCancelButton;
let modalDialog;

// EXPORTED FUNCTION(S) //
export function addEventListenerToRegistrationButton() {
    registrationButton.addEventListener('click', () => {
        // Disable scrolling on site
        body.classList.add("block-scroll");
        
        // Add registration modal dialog
        body.insertAdjacentHTML('beforeend',    `
                                                            <div id="modal-dialog">
                                                                <div id="modal-fade">
                                                                    <div id="modal-dialog-container">
                                                                        <div id="registration-modal-header-container">
                                                                            <img src="/static/images/icons/registration.png">
                                                                            <div id="registration-modal-title">Register user</div>
                                                                        </div>
                                                                        <div id="registration-modal-content-container">
                                                                            <input type="text" id="registration-modal-email-input" placeholder="E-mail" required>
                                                                            <input type="text" id="registration-modal-password-input" placeholder="Password" required>
                                                                            <input type="text" id="registration-modal-password-confirmation-input" placeholder="Confirm Password" required>
                                                                        </div>
                                                                        <div id="registration-modal-footer-container">
                                                                            <div id="registration-modal-cancel-button">Cancel</div>
                                                                            <div id="registration-modal-registration-button">Register</div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        `);

        // Put focus on e-mail input
        const registrationModalEmailInput = document.querySelector("#registration-modal-email-input");
        registrationModalEmailInput.focus()

        // Add event listener to cancel button
        addEventListenerToCancelButton();

        // Add event listener to registration button
        addEventListenerToModalRegistrationButton();
    });
}

// INNER FUNCTION(S) //
function addEventListenerToCancelButton() {
    registrationModalCancelButton = document.querySelector("#registration-modal-cancel-button");
    registrationModalCancelButton.addEventListener('click', ()=> closeModalDialog());
}

function addEventListenerToModalRegistrationButton() {
    const modalRegistrationButton = document.querySelector("#registration-modal-registration-button");
    modalRegistrationButton.addEventListener('click', async ()=> {
        // Get input values
        const registrationModalEmailInput = document.querySelector("#registration-modal-email-input");
        const registrationModalEmailInputValue = registrationModalEmailInput.value;

        const registrationModalPasswordInput = document.querySelector("#registration-modal-password-input");
        const registrationModalPasswordInputValue = registrationModalPasswordInput.value;

        const registrationModalPasswordConfirmationInput = document.querySelector("#registration-modal-password-confirmation-input");
        const registrationModalPasswordConfirmationInputValue = registrationModalPasswordConfirmationInput.value;

        // Validate input values
        if (registrationModalPasswordInputValue === registrationModalPasswordConfirmationInputValue) {
            // Send values to the backend
            const response = await fetchData("POST", `/user`, null, `{"email": "${registrationModalEmailInputValue}", "password": "${registrationModalPasswordInputValue}"}`, "application/json", null);

            // Close modal dialog
            if (response.ok) {
                closeModalDialog();
            }
        }
    });
}

function closeModalDialog() {
    // Enable scrolling on site
    body.classList.remove("block-scroll");

    // Remove modal dialog
    modalDialog = document.querySelector("#modal-dialog");
    modalDialog.remove();
}
