const categoryContainer = document.querySelector("#category-container");

window.addEventListener('load', async () => {
    await loadProductCategories();
});

async function loadProductCategories() {
    const productCategories = await fetchData("GET", "/product-types?by=category", null, null, null, "JSON");
    for (let productCategory of productCategories) {
        categoryContainer.insertAdjacentHTML("beforeend", `<div id="${productCategory.id}" class="category-button">${productCategory.name}</div>`)
    }
}

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


