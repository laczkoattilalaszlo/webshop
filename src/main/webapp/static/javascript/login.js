import {fetchData} from "./fetch.js";

// INNER USED CONSTANT VARIABLES //
const body = document.querySelector('body');
const cartButton = document.querySelector("#cart-button");
const orderButton = document.querySelector("#order-button");
const userButton = document.querySelector("#user-button");
const logoutButton = document.querySelector("#logout-button");
const loginButton = document.querySelector("#login-button");
const registerButton = document.querySelector("#registration-button");

let loginModalCancelButton;
let modalDialog;

// EXPORTED FUNCTIONS //
export function loginButtonAddEventListener() {
    // Show modal dialog
    loginButton.addEventListener('click', () => {
        body.classList.add("block-scroll");
        body.insertAdjacentHTML('beforeend',    `
                                                            <div id="modal-dialog">
                                                                <div id="modal-fade">
                                                                    <div id="modal-dialog-container">
                                                                        <div id="login-modal-header-container">
                                                                            <img src="/static/images/icons/login.png">
                                                                            <div id="login-modal-title">Login user</div>
                                                                        </div>
                                                                        <div id="login-modal-content-container">
                                                                            <input type="text" id="login-modal-email-input" placeholder="E-mail" required>
                                                                            <input type="text" id="login-modal-password-input" placeholder="Password" required>
                                                                        </div>
                                                                        <div id="login-modal-footer-container">
                                                                            <div id="login-modal-cancel-button">Cancel</div>
                                                                            <div id="login-modal-login-button">Login</div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        `);
        // Add event listener to cancel button
        AddEventListenerToCancelButton();

        // Add event listener to login button
        addEventListenerToModalLoginButton();
    });
}

// INNER FUNCTIONS //
function AddEventListenerToCancelButton() {
    loginModalCancelButton = document.querySelector("#login-modal-cancel-button");
    modalDialog = document.querySelector("#modal-dialog");
    loginModalCancelButton.addEventListener('click', ()=> modalDialog.remove());
}

function addEventListenerToModalLoginButton() {
    const modalLoginButton = document.querySelector("#login-modal-login-button");
    modalLoginButton.addEventListener('click', async ()=> {
        // Get input values
        const loginModalEmailInput = document.querySelector("#login-modal-email-input");
        const loginModalEmailInputValue = loginModalEmailInput.value;

        const loginModalPasswordInput = document.querySelector("#login-modal-password-input");
        const loginModalPasswordInputValue = loginModalPasswordInput.value;

        // Send values to the backend
        const response = await fetchData("POST", `/user-authentication`, null, `{"email": "${loginModalEmailInputValue}", "password": "${loginModalPasswordInputValue}"}`, "plain/text", );

        if (response.ok) {
            // Store session-token in session storage
            const sessionToken = response.headers.get("session-token");
            sessionStorage.setItem("session-token", sessionToken);

            // Hide not authentication related features
            loginButton.classList.replace("header-button-right", "hidden");
            registerButton.classList.replace("header-button-right", "hidden");

            // Show authentication related features
            cartButton.classList.replace("hidden", "header-button-right");
            orderButton.classList.replace("hidden", "header-button-right");
            userButton.classList.replace("hidden", "header-button-right");
            logoutButton.classList.replace("hidden", "header-button-right");

            // Close modal dialog
            modalDialog.remove();
        }
    });
}
