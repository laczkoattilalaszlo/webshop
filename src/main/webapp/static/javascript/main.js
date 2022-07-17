import {loadProductCategoryButtons} from "./modules/product.js";
import {addEventListenerToRegistrationButton} from "./modules/registration.js";
import {addEventListenerToLoginButton} from "./modules/login.js";
import {addEventListenerToLogoutButton} from "./modules/logout.js";
import {setVisibilityOfHeaderButtons} from "./modules/header.js";

// WINDOW ONLOAD //
window.addEventListener('load', async () => {
    setVisibilityOfHeaderButtons();         // According to the authentication
    await loadProductCategoryButtons();
    addEventListenerToRegistrationButton();
    addEventListenerToLoginButton();
    addEventListenerToLogoutButton();
});

