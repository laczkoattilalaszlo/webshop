// EXPORTED FUNCTION(S) //
export function addEventListenerToSortByPriceDropdownMenu() {
    const sortByPriceInput = document.querySelector("#sort-by-input");
    sortByPriceInput.addEventListener('click', ()=> {
        const sortByInputValue = sortByPriceInput.value;
        sortListedProducts(sortByInputValue);
    });
}

// INNER FUNCTION(S) //
function sortListedProducts(direction) {
    // Sort listed products in-place
    const listedProductsJSON = sessionStorage.getItem("listed-products");
    let listedProducts = JSON.parse(listedProductsJSON);

    if (direction == "ascending") {
        listedProducts.sort((productA, productB) => productA.price - productB.price);
    } else if (direction == "descending") {
        listedProducts.sort((productA, productB) => productA.price - productB.price);
        listedProducts.reverse();
    }

    // Show sorted products
    const productContainer = document.querySelector("#product-container");
    productContainer.innerHTML = "";

    for (let product of listedProducts) {
        // Set visibility state of 'Add to cart' button according to the authentication
        let VisibilityStateOfAddToCartButton = (sessionStorage.getItem("session-token") == null) ? "hidden" : "";

        // Show product cards
        productContainer.insertAdjacentHTML("beforeend",
            `
                <div class="product">
                    <div class="top-product-unit">
                        <img class="product-photo" src="static/images/products/product-placeholder.jpeg">
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
}