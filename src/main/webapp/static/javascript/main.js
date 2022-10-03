import {loadProductCategoryButtons, loadRandomProductsAtPageLoad} from "./modules/product.js";
import {addEventListenerToInformationButton} from "./modules/informaiton.js";
import {addEventListenerToRegistrationButton} from "./modules/registration.js";
import {addEventListenerToLoginButton} from "./modules/login.js";
import {addEventListenerToLogoutButton} from "./modules/logout.js";
import {setVisibilityOfHeaderButtons} from "./modules/header.js";
import {addEventListenerToUserButton} from "./modules/user.js";
import {addEventListenerToCartButton} from "./modules/checkout/checkout.js";
import {addEventListenerToOrderButton} from "./modules/order.js";
import {addEventListenerToSortByPriceDropdownMenu} from "./modules/sort.js";
import {addEventListenerToSearchInput} from "./modules/search.js";

// INNER FUNCTION(S) //
function showUIElements() {
    document.querySelector("#header-container").classList.remove("invisible");
    document.querySelector("#content-container").classList.remove("invisible");
    document.querySelector("#footer-container").classList.remove("invisible");
    document.querySelector("#modal-dialog").classList.remove("invisible");
}

// WINDOW ONLOAD //
window.addEventListener('load', async () => {
    await loadProductCategoryButtons();

    await loadRandomProductsAtPageLoad(12);               // Loads 10 random product

    setVisibilityOfHeaderButtons();                                     // According to the authentication
    addEventListenerToInformationButton();
    addEventListenerToCartButton();
    addEventListenerToOrderButton();
    addEventListenerToUserButton();
    addEventListenerToRegistrationButton();
    addEventListenerToLoginButton();
    addEventListenerToLogoutButton();

    addEventListenerToSearchInput();
    addEventListenerToSortByPriceDropdownMenu();

    document.querySelector("#information-button").click();      // To show Information dialog at page load

    showUIElements();
});

