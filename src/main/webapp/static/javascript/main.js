import {loadProductCategoryButtons} from "./modules/product.js";
import {addEventListenerToInformationButton} from "./modules/informaiton.js";
import {addEventListenerToRegistrationButton} from "./modules/registration.js";
import {addEventListenerToLoginButton} from "./modules/login.js";
import {addEventListenerToLogoutButton} from "./modules/logout.js";
import {setVisibilityOfHeaderButtons} from "./modules/header.js";
import {addEventListenerToUserButton} from "./modules/user.js";
import {addEventListenerToCartButton} from "./modules/checkout/checkout.js";

// WINDOW ONLOAD //
window.addEventListener('load', async () => {
    setVisibilityOfHeaderButtons();     // According to the authentication
    await loadProductCategoryButtons();
    addEventListenerToInformationButton();
    addEventListenerToRegistrationButton();
    addEventListenerToLoginButton();
    addEventListenerToLogoutButton();
    addEventListenerToUserButton();
    addEventListenerToCartButton();
    document.querySelector("#information-button").click();      // To show Information dialog at page load
});

