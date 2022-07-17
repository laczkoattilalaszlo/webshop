// INNER USED CONSTANT VARIABLES //
const informationButton = document.querySelector("#information-button");
const cartButton = document.querySelector("#cart-button");
const orderButton = document.querySelector("#order-button");
const userButton = document.querySelector("#user-button");
const registrationButton = document.querySelector("#registration-button");
const loginButton = document.querySelector("#login-button");
const logoutButton = document.querySelector("#logout-button");

// EXPORTED FUNCTIONS //
export function setVisibilityOfHeaderButtons() {
    if (sessionStorage.getItem("session-token") == null) {      // Logged out
        informationButton.classList.add("header-button-left");
        cartButton.classList.add("hidden");
        orderButton.classList.add("hidden");
        userButton.classList.add("hidden");
        registrationButton.classList.add("header-button-right");
        loginButton.classList.add("header-button-right");
        logoutButton.classList.add("hidden");
    } else {                                                        // Logged in
        informationButton.classList.add("header-button-left");
        cartButton.classList.add("header-button-right");
        orderButton.classList.add("header-button-right");
        userButton.classList.add("header-button-right");
        registrationButton.classList.add("hidden");
        loginButton.classList.add("hidden");
        logoutButton.classList.add("header-button-right");
    }

}