window.addEventListener('load', async () => {
    const productCategories = await fetchDataJson("/product-categories");
    console.log(productCategories);

    const productSuppliers = await fetchDataJson("/product-suppliers");
    console.log(productSuppliers);

    const productsByCategory = await fetchDataJson("/products-by-category?category-id=2570833b-0fcd-4ebb-8844-9296f2e94259");     // TODO: Change to automatic id search.
    console.log(productsByCategory);

    const productsBySupplier = await fetchDataJson("/products-by-supplier?supplier-id=02846481-0e34-435e-9c6d-6fd1affde2b9");     // TODO: Change to automatic id search.
    console.log(productsBySupplier);

    // const addProductToCart = await fetchDataJson("/cart", "POST", {productId: "f7faed42-3519-4b95-9d5d-da0a924cf92f", userId: "bdc7f29e-0aa8-42ca-8070-5baba303185e"});         // TODO: JS Console shows error!
    // console.log(addProductToCart);
    //
    // const removeProductFromCart = await fetchDataJson("/cart", "DELETE", {productId: "f7faed42-3519-4b95-9d5d-da0a924cf92f", userId: "bdc7f29e-0aa8-42ca-8070-5baba303185e"});  // TODO: JS Console shows error!
    // console.log(addProductToCart);

    const cart = await fetchDataJson("/cart?user-id=bdc7f29e-0aa8-42ca-8070-5baba303185e");
    console.log(cart);

    const totalPrice = await fetchDataJson("/total-price?user-id=bdc7f29e-0aa8-42ca-8070-5baba303185e");
    console.log(totalPrice);

    // const addUser = await fetchDataJson("/user", "POST", {email: "proba@proba.hu", password: "titkosJelszo"});  // TODO: JS Console shows error!
    // console.log(addUser);
    //
    // const removeUser = await fetchDataText("/user", "DELETE", "a2e3201d-a825-47fa-9428-959baa7c92e2");  // TODO: JS Console shows error!
    // console.log(removeUser);
});

async function fetchDataJson(url, methodType, payload){
    let request = (typeof methodType == undefined) ? { method: "GET" } :
                                                        { method: methodType,
                                                          headers: { 'Content-type': 'application/json' },
                                                          body: JSON.stringify(payload) };
    const response = await fetch(url, request);
    if (response.ok) {
        return await response.json();
    }
}

async function fetchDataText(url, methodType, payload){
    let request = { method: methodType,
                    headers: { 'Content-type': 'text/plain' },
                    body: payload };
    const response = await fetch(url, request);
    return response.statusText;
}


