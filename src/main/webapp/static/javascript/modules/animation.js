// EXPORTED FUNCTION(S) //
export function fadeInElements(classNameOfElementsToFade, duration) {
    let elements = document.getElementsByClassName(classNameOfElementsToFade);
    for (let i = 0; i < elements.length; ++i) {
        setTimeout(() => {
            elements[i].classList.add('animation-fadein');
        }, i * duration);
    }
}