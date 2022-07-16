// CONSTANT VARIABLES //
const categoryContainer = document.querySelector("#category-container");
const productContainer = document.querySelector("#product-container");

// WINDOW ONLOAD //
window.addEventListener('load', async () => {
    await loadProductCategoryButtons();
});

// FUNCTIONS //
async function fetchData(methodType, url, headerContent, bodyContent, contentType, expectedType) {
    let request;
    if (headerContent != null || bodyContent != null || contentType!= null || expectedType != null) {
        request = { method: methodType,
            headers: { 'Content-type': contentType, ...headerContent },
            body: bodyContent };
    }

    const response = await fetch(url, request);
    if (expectedType == "JSON" && response.ok) {
        return await response.json();
    } else if (expectedType == "Text" && response.ok) {
        return await response.text();
    } else {
        return response.statusText;
    }
}

async function loadProductCategoryButtons() {
    // Fetch product categories and create buttons for them
    const productCategories = await fetchData("GET", "/product-categories", null, null, null, "JSON");
    for (let productCategory of productCategories) {
        categoryContainer.insertAdjacentHTML("beforeend", `<div id="${productCategory.id}" class="category-button">${productCategory.name}</div>`);
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

async function expandProductCategoryButton(categoryButton) {
    // Add fetched product supplier buttons under clicked product category button
    const productSuppliersOfCategory = await fetchData("GET", `/product-suppliers-by-category?category-id=${categoryButton.id}`, null, null, null, "JSON");
    for (let productSupplier of productSuppliersOfCategory) {
        categoryButton.insertAdjacentHTML("afterend", `<div data-category-id="${categoryButton.id}" data-supplier-id="${productSupplier.id}" class="supplier-button">${productSupplier.name}</div>`);
    }

    // Add event listeners to the product supplier buttons of the product category
    const supplierButtons = document.querySelectorAll(".supplier-button");
    for (let supplierButton of supplierButtons) {
        if (supplierButton.dataset.categoryId == categoryButton.id) {
            supplierButton.addEventListener('click', async () => {
                await listProducts(supplierButton);
            });
        }
    }
}

function shrinkProductCategoryButton(categoryButton) {
    const existingProductSupplierButtons = document.querySelectorAll(".supplier-button");
    existingProductSupplierButtons.forEach(productSupplierButton => (productSupplierButton.dataset.categoryId == categoryButton.id) ? productSupplierButton.remove() : "");
}

async function listProducts(supplierButton) {
    // Empty product-container
    productContainer.innerHTML = "";

    // Fetch products and show them
    const products = await fetchData("GET", `/products-by-category-and-supplier?category-id=${supplierButton.dataset.categoryId}&supplier-id=${supplierButton.dataset.supplierId}`, null, null, null, "JSON");
    for (let product of products) {
        productContainer.insertAdjacentHTML("beforeend",
            `
                <div class="product">
                    <div class="top-product-unit">
                        <img class="product-photo" src="/static/images/products/product-placeholder.jpeg">
                        <div class="product-supplier-name"><span class="product-supplier">${product.supplierName}</span> <span class="product-name">${product.name}</span></div>
                        <div class="product-description">${product.description}</div>
                    </div>
                    <div class="bottom-product-unit">
                        <div class="product-price-currency"><span class="product-price">${product.price}</span> <span class="product-currency">${product.currency}</span></div>
                        <div class="add-to-cart-button" data-product-id="${product.id}" hidden>Add to cart</div>
                    </div>
                </div>
            `);
    }

}

