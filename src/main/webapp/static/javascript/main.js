import {loadProductCategoryButtons} from "./modules/product.js";
import {addEventListenerToRegistrationButton} from "./modules/registration.js";
import {addEventListenerToLoginButton} from "./modules/login.js";

// WINDOW ONLOAD //
window.addEventListener('load', async () => {
    console.log(sessionStorage.getItem("session-token"));
    await loadProductCategoryButtons();
    addEventListenerToRegistrationButton();
    addEventListenerToLoginButton();
});

