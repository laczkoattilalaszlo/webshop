window.addEventListener('load', async () => {
    const productCategories = await fetchData("/product-categories");
    console.log(productCategories);

    const productSuppliers = await fetchData("/product-suppliers");
    console.log(productSuppliers);

    const productsByCategory = await fetchData("/products-by-category?category-id=2570833b-0fcd-4ebb-8844-9296f2e94259");     // TODO: Change to automatic id search.
    console.log(productsByCategory);

    const productsBySupplier = await fetchData("/products-by-supplier?supplier-id=02846481-0e34-435e-9c6d-6fd1affde2b9");     // TODO: Change to automatic id search.
    console.log(productsBySupplier);

    // const addProductToCart = await fetchData("/cart", "POST", {productId: "f7faed42-3519-4b95-9d5d-da0a924cf92f", userId: "bdc7f29e-0aa8-42ca-8070-5baba303185e"});         // TODO: JS Console shows error!
    // console.log(addProductToCart);
    //
    // const removeProductFromCart = await fetchData("/cart", "DELETE", {productId: "f7faed42-3519-4b95-9d5d-da0a924cf92f", userId: "bdc7f29e-0aa8-42ca-8070-5baba303185e"});  // TODO: JS Console shows error!
    // console.log(addProductToCart);

    const cart = await fetchData("/cart?user-id=bdc7f29e-0aa8-42ca-8070-5baba303185e");
    console.log(cart);

    const totalPrice = await fetchData("/total-price?user-id=bdc7f29e-0aa8-42ca-8070-5baba303185e");
    console.log(totalPrice);
});

async function fetchData(url, methodType, payload){
    let request = (typeof methodType == undefined) ? { method: "GET" } :
                                                        { method: methodType,
                                                          headers: { 'Content-type': 'application/json' },
                                                          body: JSON.stringify(payload) };
    const response = await fetch(url, request);
    if (response.ok) {
        return await response.json();
    }
}


