// EXPORTED FUNCTIONS //
export async function fetchData(methodType, url, headerContent, bodyContent, contentType, expectedType) {
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