import {loadProductCategoryButtons} from "./modules/product.js";
import {addEventListenerToInformationButton} from "./modules/informaiton.js";
import {addEventListenerToRegistrationButton} from "./modules/registration.js";
import {addEventListenerToLoginButton} from "./modules/login.js";
import {addEventListenerToLogoutButton} from "./modules/logout.js";
import {setVisibilityOfHeaderButtons} from "./modules/header.js";
import {addEventListenerToUserButton} from "./modules/user.js";
import {addEventListenerToCartButton} from "./modules/checkout/checkout.js";
import {addEventListenerToOrderButton} from "./modules/order.js";

// WINDOW ONLOAD //
window.addEventListener('load', async () => {
    await loadProductCategoryButtons();

    setVisibilityOfHeaderButtons();                                     // According to the authentication
    addEventListenerToInformationButton();
    addEventListenerToCartButton();
    addEventListenerToOrderButton();
    addEventListenerToUserButton();
    addEventListenerToRegistrationButton();
    addEventListenerToLoginButton();
    addEventListenerToLogoutButton();

    document.querySelector("#information-button").click();      // To show Information dialog at page load
});

