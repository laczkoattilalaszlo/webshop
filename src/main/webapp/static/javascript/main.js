import {loadProductCategoryButtons} from "./product.js";
import {registrationButtonAddEventListener} from "./registration.js";
import {loginButtonAddEventListener} from "./login.js";

// WINDOW ONLOAD //
window.addEventListener('load', async () => {
    await loadProductCategoryButtons();
    registrationButtonAddEventListener();
    loginButtonAddEventListener();
});

