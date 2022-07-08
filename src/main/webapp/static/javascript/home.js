window.addEventListener('load', async () => {
    console.log("Test log");
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


