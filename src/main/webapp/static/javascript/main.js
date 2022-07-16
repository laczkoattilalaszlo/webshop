import {loadProductCategoryButtons} from "./modules/product.js";
import {registrationButtonAddEventListener} from "./modules/registration.js";
import {loginButtonAddEventListener} from "./modules/login.js";

// WINDOW ONLOAD //
window.addEventListener('load', async () => {
    await loadProductCategoryButtons();
    registrationButtonAddEventListener();
    loginButtonAddEventListener();
});

