import {fetchData} from "./fetch.js";

// INNER USED CONSTANT VARIABLES //
const categoryContainer = document.querySelector("#category-container");
const productContainer = document.querySelector("#product-container");

// EXPORTED FUNCTION(S) //
export async function loadProductCategoryButtons() {
    // Delete category and supplier selection (reason: it is necessary not to keep selections, when the user reloads the page)
    sessionStorage.removeItem("selected-category-id");
    sessionStorage.removeItem("selected-supplier-id");

    // Fetch product categories and create buttons for them
    const productCategories = await fetchData("GET", "/product-categories", null, null, null, "JSON");
    for (let productCategory of productCategories) {
        categoryContainer.insertAdjacentHTML("beforeend", `<div id="${productCategory.id}" class="category-button"><span class="shrink-expand-symbol">▸</span><span>${productCategory.name}</span></div>`);
    }

    // Add event listeners to the product category buttons
    const categoryButtons = document.querySelectorAll(".category-button");
    for (let categoryButton of categoryButtons) {
        categoryButton.addEventListener('click', async () => {
            if (categoryButton.nextElementSibling != null && categoryButton.nextElementSibling.classList.contains("supplier-button") ) {
                shrinkProductCategoryButton(categoryButton);
            } else {
                await expandProductCategoryButton(categoryButton);
            }
        });
    }
}

export async function loadRandomProductsAtPageLoad(productQuantity) {
    // Fetch products and show them
    const randomProducts = await fetchData("GET", `/random-products?product-quantity=${productQuantity}`, null, null, null, "JSON");

    // Add products to show to the session-storage
    const randomProductJSON = JSON.stringify(randomProducts);
    sessionStorage.setItem("listed-products", randomProductJSON);

    // Hide product-container while loading products
    productContainer.classList.add("invisible");

    // Show products
    for (let product of randomProducts) {
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

    // Show product-container after loading products
    setTimeout(() => productContainer.classList.remove("invisible"), 100);

    // Add event listeners to 'add to cart' buttons
    addEventListenerToAddToCartButtons();
}

// INNER FUNCTION(S) //
async function expandProductCategoryButton(categoryButton) {
    // Change shrinked symbol to expanded symbol
    categoryButton.firstChild.remove();
    categoryButton.insertAdjacentHTML("afterbegin", `<span class="shrink-expand-symbol">▾</span>`);

    // Add fetched product supplier buttons under clicked product category button
    const productSuppliersOfCategory = await fetchData("GET", `/product-suppliers-by-category?category-id=${categoryButton.id}`, null, null, null, "JSON");
    for (let productSupplier of productSuppliersOfCategory) {
        if (sessionStorage.getItem("selected-category-id") == categoryButton.id && sessionStorage.getItem("selected-supplier-id") == productSupplier.id) {  // Checks whether supplier button already selected
            categoryButton.insertAdjacentHTML("afterend", `<div data-category-id="${categoryButton.id}" data-supplier-id="${productSupplier.id}" class="supplier-button supplier-button-selected">${productSupplier.name}</div>`);
        } else {
            categoryButton.insertAdjacentHTML("afterend", `<div data-category-id="${categoryButton.id}" data-supplier-id="${productSupplier.id}" class="supplier-button">${productSupplier.name}</div>`);
        }
    }

    // Add event listeners to the product supplier buttons of the product category
    const supplierButtons = document.querySelectorAll(".supplier-button");
    for (let supplierButton of supplierButtons) {
        if (supplierButton.dataset.categoryId == categoryButton.id) {
            supplierButton.addEventListener('click', async () => {
                markSupplierButtonAsSelected(supplierButton, supplierButtons);
                await loadProducts(supplierButton);
            });
        }
    }
}

function shrinkProductCategoryButton(categoryButton) {
    // Change expanded symbol to shrinked symbol
    categoryButton.firstChild.remove();
    categoryButton.insertAdjacentHTML("afterbegin", `<span class="shrink-expand-symbol">▸</span>`);

    // Shrink
    const existingProductSupplierButtons = document.querySelectorAll(".supplier-button");
    existingProductSupplierButtons.forEach(productSupplierButton => (productSupplierButton.dataset.categoryId == categoryButton.id) ? productSupplierButton.remove() : "");
}

function markSupplierButtonAsSelected(supplierButton) {
    // Remove selection from selected button
    const supplierButtons = document.querySelectorAll(".supplier-button");
    for (let supplierButton of supplierButtons) {
        supplierButton.classList.remove("supplier-button-selected");
    }

    // Mark as selected
    supplierButton.classList.add("supplier-button-selected");
    sessionStorage.setItem("selected-category-id", supplierButton.dataset.categoryId);
    sessionStorage.setItem("selected-supplier-id", supplierButton.dataset.supplierId);
}

async function loadProducts(supplierButton) {
    // Empty product-container
    productContainer.innerHTML = "";

    // Fetch products and show them
    const products = await fetchData("GET", `/products-by-category-and-supplier?category-id=${supplierButton.dataset.categoryId}&supplier-id=${supplierButton.dataset.supplierId}`, null, null, null, "JSON");

    // Add products to show to the session-storage
    const productJSON = JSON.stringify(products);
    sessionStorage.setItem("listed-products", productJSON);

    // Hide product-container while loading products
    productContainer.classList.add("invisible");

    // Show products
    for (let product of products) {
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

    // Show product-container after loading products
    setTimeout(() => productContainer.classList.remove("invisible"), 100);

    // Add event listeners to 'add to cart' buttons
    addEventListenerToAddToCartButtons();
}

function addEventListenerToAddToCartButtons() {
    const addToCartButtons = document.querySelectorAll(".add-to-cart-button");
    for (let addToCartButton of addToCartButtons) {
        addToCartButton.addEventListener('click', async () => {
            const productId = addToCartButton.dataset.productId;
            await fetchData("POST", `/cart`, {"session-token": sessionStorage.getItem("session-token")}, `${productId}`, "plain/text", null);
        });
    }
}
