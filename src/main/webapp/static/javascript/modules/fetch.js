// EXPORTED FUNCTION(S) //
export async function fetchData(methodType, url, headerContent, bodyContent, contentType, responseType) {
    let request;
    if (headerContent != null || bodyContent != null || contentType!= null || responseType != null) {
        request = { method: methodType,
            headers: { 'Content-type': contentType, ...headerContent },
            body: bodyContent };
    }

    const response = await fetch(url, request);
    if (responseType == "JSON" && response.ok) {
        return await response.json();
    } else if (responseType == "Text" && response.ok) {
        return await response.text();
    } else {
        return response;
    }
}