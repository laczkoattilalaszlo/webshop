import {loadProductCategoryButtons} from "./product.js";
import {registrationButtonAddEventListener} from "./registration.js";

// WINDOW ONLOAD //
window.addEventListener('load', async () => {
    await loadProductCategoryButtons();
    registrationButtonAddEventListener();
});

