window.addEventListener('load', async () => {
    const productCategories = await fetchData("/product-categories");
    console.log(productCategories);

    const productSuppliers = await fetchData("/product-suppliers");
    console.log(productSuppliers);

    const productsByCategory = await fetchData("/products-by-category?category-id=2570833b-0fcd-4ebb-8844-9296f2e94259");     // TODO: Change to automatic id search.
    console.log(productsByCategory);

    const productsBySupplier = await fetchData("/products-by-supplier?supplier-id=02846481-0e34-435e-9c6d-6fd1affde2b9");     // TODO: Change to automatic id search.
    console.log(productsBySupplier);
});

async function fetchData(url){
    const response = await fetch(url, {method: 'GET'});
    if (response.ok) {
        return await response.json();
    }
}


