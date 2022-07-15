// CONSTANT VARIABLES //
const categoryContainer = document.querySelector("#category-container");

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
            // Remove product existing product supplier buttons
            const existingProductSupplierButtons = document.querySelectorAll(".supplier-button");
            existingProductSupplierButtons.forEach(productSupplierButton => productSupplierButton.remove());

            // Add fetched product supplier buttons
            const productSuppliersOfCategory = await fetchData("GET", `/product-suppliers-by-category?category-id=${categoryButton.id}`, null, null, null, "JSON");
            for (let productSupplier of productSuppliersOfCategory) {
                categoryButton.insertAdjacentHTML("afterend", `<div id="${productSupplier.id}" class="supplier-button">${productSupplier.name}</div>`);
            }
        });
    }
}




