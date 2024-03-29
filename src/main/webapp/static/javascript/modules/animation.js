// EXPORTED FUNCTION(S) //
export function fadeInElements(classNameOfElementsToFade, duration) {
    let elements = document.getElementsByClassName(classNameOfElementsToFade);

    // Fade in elements
    for (let i = 0; i < elements.length; ++i) {
        setTimeout(() => {
            elements[i].classList.add('animation-fadein');
        }, i * duration);
    }

    // Remove fade-in related classes from elements
    for (let i = 0; i < elements.length; ++i) {
        setTimeout(() => {
            elements[i].classList.remove('animated');
            elements[i].classList.remove('animation-fadein');
        }, i * duration);
    }
}

export function FadeInElementsAfterWaitForLoadAllImagesCompletely(classNameOfElementsToFade, fadeDuration, classNameOfImagesForWait, checkInterval) {
    let imageLoadWaiter = setInterval(function () {
        const images = document.querySelectorAll(classNameOfImagesForWait);
        let hasUnLoadedImage = false;
        images.forEach(image => image.complete == false ? hasUnLoadedImage = true : false);
        if (hasUnLoadedImage == false) {
            fadeInElements(classNameOfElementsToFade, fadeDuration);
            clearInterval(imageLoadWaiter);
        }
    }, checkInterval);
}