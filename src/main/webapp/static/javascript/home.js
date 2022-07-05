window.addEventListener('load', async () => {
    const dataFromServer = await fetchData("/product-category/get-all");
    console.log(dataFromServer);
});

async function fetchData(url){
    const response = await fetch(url, {method: 'GET'});
    if (response.ok) {
        return await response.json();
    }
}


