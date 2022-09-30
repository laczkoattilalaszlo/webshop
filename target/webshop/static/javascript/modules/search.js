// EXPORTED FUNCTION(S) //
import {fetchData} from "./fetch.js";

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

        // Show matched products
        const productContainer = document.querySelector("#product-container");
        productContainer.innerHTML = "";

        for (let product of matchedProducts) {
            // Set visibility state of 'Add to cart' button according to the authentication
            let VisibilityStateOfAddToCartButton = (sessionStorage.getItem("session-token") == null) ? "hidden" : "";

            // Show product cards
            productContainer.insertAdjacentHTML("beforeend",
                `
                <div class="product">
                    <div class="top-product-unit">
                        <img class="product-photo" src="static/images/products/${(product.picture != null) ? product.picture : "product-placeholder.jpeg"}">
                        <div class="product-supplier-name"><span class="product-supplier">${product.supplierName}</span> <span class="product-name">${product.name}</span></div>
                        <div class="product-description">${product.description}</div>
                    </div>
                    <div class="bottom-product-unit">
                        <div class="product-price-currency"><span class="product-price">${product.price}</span> <span class="product-currency">${product.currency}</span></div>
                        <div class="add-to-cart-button" data-product-id="${product.id}" ${VisibilityStateOfAddToCartButton}>Add to cart</div>
                    </div>
                </div>
            `);
        }
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
