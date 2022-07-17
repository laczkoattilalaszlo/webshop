import {loadProductCategoryButtons} from "./modules/product.js";
import {addEventListenerToRegistrationButton} from "./modules/registration.js";
import {addEventListenerToLoginButton} from "./modules/login.js";
import {addEventListenerToLogoutButton} from "./modules/logout.js";

// WINDOW ONLOAD //
window.addEventListener('load', async () => {
    await loadProductCategoryButtons();
    addEventListenerToRegistrationButton();
    addEventListenerToLoginButton();
    addEventListenerToLogoutButton();
});

