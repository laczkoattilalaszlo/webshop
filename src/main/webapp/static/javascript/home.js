window.addEventListener('load', async () => {
    const productCategories = await fetchData("/product-categories");
    console.log(productCategories);

    const productSuppliers = await fetchData("/product-suppliers");
    console.log(productSuppliers);

    const productsByCategories = await fetchData("/products-by-category?category-id=53dec9c0-fc85-11ec-b939-0242ac120002");     // TODO: Change to automatic id search.
    console.log(productsByCategories);
});

async function fetchData(url){
    const response = await fetch(url, {method: 'GET'});
    if (response.ok) {
        return await response.json();
    }
}


