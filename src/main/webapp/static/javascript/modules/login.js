import {fetchData} from "./fetch.js";

// INNER USED CONSTANT VARIABLES //
const body = document.querySelector('body');
const cartButton = document.querySelector("#cart-button");
const orderButton = document.querySelector("#order-button");
const userButton = document.querySelector("#user-button");
const registrationButton = document.querySelector("#registration-button");
const loginButton = document.querySelector("#login-button");
const logoutButton = document.querySelector("#logout-button");

let loginModalCancelButton;
let modalDialog;

// EXPORTED FUNCTION(S) //
export function addEventListenerToLoginButton() {
    loginButton.addEventListener('click', () => {
        // Disable scrolling on site
        body.classList.add("block-scroll");

        // Add login modal dialog
        body.insertAdjacentHTML('beforeend',    `
                                                            <div id="modal-dialog">
                                                                <div id="modal-fade">
                                                                    <div id="modal-dialog-container" class="animated animation-fadein">
                                                                        <div id="login-modal-header-container">
                                                                            <img src="/static/images/icons/login.png">
                                                                            <div id="login-modal-title">Login user</div>
                                                                        </div>
                                                                        <div id="login-modal-content-container">
                                                                            <input type="text" id="login-modal-email-input" placeholder="E-mail" required>
                                                                            <input type="password" id="login-modal-password-input" placeholder="Password" required>
                                                                        </div>
                                                                        <div id="login-modal-footer-container">
                                                                            <div id="login-modal-cancel-button">Cancel</div>
                                                                            <div id="login-modal-login-button">Login</div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        `);

        // Put focus on e-mail input
        const registrationModalEmailInput = document.querySelector("#login-modal-email-input");
        registrationModalEmailInput.focus()

        // Add event listener to cancel button
        AddEventListenerToCancelButton();

        // Add event listener to login button
        addEventListenerToModalLoginButton();
    });
}

// INNER FUNCTION(S) //
function AddEventListenerToCancelButton() {
    loginModalCancelButton = document.querySelector("#login-modal-cancel-button");
    loginModalCancelButton.addEventListener('click', ()=> closeModalDialog());
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
            hideAuthenticationRelatedFeatures();

            // Show authentication related features
            showAuthenticationRelatedFeatures()

            // Close modal dialog
            closeModalDialog();
        }
    });
}

function hideAuthenticationRelatedFeatures() {
    // Header menu buttons
    loginButton.classList.replace("header-button-right", "hidden");
    registrationButton.classList.replace("header-button-right", "hidden");
}

function showAuthenticationRelatedFeatures() {
    // Header menu buttons
    cartButton.classList.replace("hidden", "header-button-right");
    orderButton.classList.replace("hidden", "header-button-right");
    userButton.classList.replace("hidden", "header-button-right");
    logoutButton.classList.replace("hidden", "header-button-right");

    // Add to cart buttons on product cards
    const addToCartButtons = document.querySelectorAll('.add-to-cart-button');
    if (addToCartButtons != null) {
        for (let addToCartButton of addToCartButtons) {
            addToCartButton.removeAttribute("hidden");
        }
    }
}

function closeModalDialog() {
    // Enable scrolling on site
    body.classList.remove("block-scroll");

    // Remove modal dialog
    modalDialog = document.querySelector("#modal-dialog");
    modalDialog.remove();
}