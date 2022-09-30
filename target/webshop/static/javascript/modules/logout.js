// INNER USED CONSTANT VARIABLES //
const cartButton = document.querySelector("#cart-button");
const orderButton = document.querySelector("#order-button");
const userButton = document.querySelector("#user-button");
const registrationButton = document.querySelector("#registration-button");
const loginButton = document.querySelector("#login-button");
const logoutButton = document.querySelector("#logout-button");

// EXPORTED FUNCTION(S) //
export function addEventListenerToLogoutButton() {
    logoutButton.addEventListener('click', ()=> {
        // Logout
        sessionStorage.removeItem("session-token");

        // Hide authentication related features
        hideAuthenticationRelatedFeatures();

        // Show not authentication related features
        showNotAuthenticationRelatedFeatures();
    });
}

// INNER FUNCTION(S) //
function hideAuthenticationRelatedFeatures() {
    // Header menu buttons
    cartButton.classList.replace("header-button-right", "hidden");
    orderButton.classList.replace("header-button-right", "hidden");
    userButton.classList.replace("header-button-right", "hidden");
    logoutButton.classList.replace("header-button-right", "hidden");

    // Add to cart buttons on product cards
    const addToCartButtons = document.querySelectorAll('.add-to-cart-button');
    if (addToCartButtons != null) {
        for (let addToCartButton of addToCartButtons) {
            addToCartButton.setAttribute("hidden", "");
        }
    }
}

function showNotAuthenticationRelatedFeatures() {
    // Header menu buttons
    loginButton.classList.replace("hidden", "header-button-right");
    registrationButton.classList.replace("hidden", "header-button-right");
}