// EXPORTED FUNCTION(S) //
export function fadeInElements(classNameOfElementsToFade, duration) {
    let elements = document.getElementsByClassName(classNameOfElementsToFade);
    for (let i = 0; i < elements.length; ++i) {
        setTimeout(() => {
            elements[i].classList.add('animation-fadein');
        }, i * duration);
    }
}

export function FadeInElementsAfterWaitForLoadAllImagesCompletely(classNameOfElementsToFade, fadeDuration, classNameOfImagesForWait) {
    let imageLoadWaiter = setInterval(function () {
        const images = document.querySelectorAll(classNameOfImagesForWait);
        let hasUnLoadedImage = false;
        images.forEach(image => image.complete == false ? hasUnLoadedImage = true : false);
        if (hasUnLoadedImage == false) {
            fadeInElements(classNameOfElementsToFade, fadeDuration);
            clearInterval(imageLoadWaiter);
        }
    }, 100);
}