window.addEventListener('load', async () => {
    const productCategories = await fetchData("/product-category");
    console.log(productCategories);

    const productSuppliers = await fetchData("/product-supplier");
    console.log(productSuppliers);
});

async function fetchData(url){
    const response = await fetch(url, {method: 'GET'});
    if (response.ok) {
        return await response.json();
    }
}


