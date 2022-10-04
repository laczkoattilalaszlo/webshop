// EXPORTED FUNCTION(S) //
import {fetchData} from "./fetch.js";
import {showProducts} from "./product.js";

export function addEventListenerToSearchInput() {
    const searchInput = document.querySelector("#search-input");
    searchInput.addEventListener('keyup', async () => {
        // Search for products which match the search criteria (supplier name + product name or description contains the given string)
        const searchRange = document.querySelector('.search-range:checked');
        const searchRangeValue = searchRange.value;

        const searchInputValue = document.querySelector("#search-input").value;

        let matchedProducts;
        if (searchRangeValue == "listed-products") {
            matchedProducts = searchListedProducts(searchInputValue);
        } else if (searchRangeValue == "all-products") {
            matchedProducts = await searchAllProducts(searchInputValue);
        }

        // Sort matched products in-place
        if (sessionStorage.getItem("sort-direction") == "ascending") {
            matchedProducts.sort((productA, productB) => productA.price - productB.price);
        } else {
            matchedProducts.sort((productA, productB) => productA.price - productB.price);
            matchedProducts.reverse();
        }

        // Add matched products to the session-storage
        const productJSON = JSON.stringify(matchedProducts);
        sessionStorage.setItem("listed-products", productJSON);

        // Show sorted matched products and fade in product cards
        showProducts(matchedProducts);
    });
}

// INNER FUNCTION(S) //
function searchListedProducts(searchInputValue) {
    const listedProductsJSON = sessionStorage.getItem("listed-products");
    const listedProducts = JSON.parse(listedProductsJSON);

    const matchedProducts = listedProducts.filter((product) => {
                                return ( (product.supplierName.toLowerCase() + ' ' + product.name.toLowerCase()).includes(searchInputValue.toLowerCase()) ) ||
                                       product.description.toLowerCase().includes(searchInputValue.toLowerCase());
                            });

    return matchedProducts
}

async function searchAllProducts(searchInputValue) {
    const matchedProducts = await fetchData("GET", `/searched-products?searched-text=${searchInputValue}`, null, null, null, "JSON");
    return matchedProducts;
}
