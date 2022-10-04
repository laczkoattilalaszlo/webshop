// EXPORTED FUNCTION(S) //
import {showProducts} from "./product.js";

export function addEventListenerToSortByPriceDropdownMenu() {
    const sortByPriceInput = document.querySelector("#sort-by-input");
    sortByPriceInput.addEventListener('click', ()=> {
        const sortByInputValue = sortByPriceInput.value;
        sortListedProducts(sortByInputValue);
    });
}

// INNER FUNCTION(S) //
function sortListedProducts(direction) {
    const actualSortDirection = sessionStorage.getItem("sort-direction");

    if (actualSortDirection != direction) {
        // Sort listed products in-place
        const listedProductsJSON = sessionStorage.getItem("listed-products");
        let listedProducts = JSON.parse(listedProductsJSON);

        if (direction == "ascending") {
            listedProducts.sort((productA, productB) => productA.price - productB.price);
            sessionStorage.setItem("sort-direction", "ascending");
        } else if (direction == "descending") {
            listedProducts.sort((productA, productB) => productA.price - productB.price);
            listedProducts.reverse();
            sessionStorage.setItem("sort-direction", "descending");
        }

        // Show sorted products and fade in product cards
        showProducts(listedProducts);

        // Add sorted products to show to the session-storage
        const productJSON = JSON.stringify(listedProducts);
        sessionStorage.setItem("listed-products", productJSON);
    }
}