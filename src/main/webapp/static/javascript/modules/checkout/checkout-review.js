import {closeModalDialog} from "./checkout.js";
import {showDeliveryStep} from "./checkout-delivery.js";
import {showPaymentStep} from "./checkout-payment.js";

// EXPORTED FUNCTION(S) //
export async function showReviewStep() {
    // Mark earlier steps as finished
    markPreviousStepsAsFinished();

    // Highlight delivery step
    highlightReviewStep();

    // Change close button
    changeCloseButton();

    // Change previous button
    changePreviousButton();

    // Change next button
    changeNextButton();

    // Empty modal dialog content
    const modalContentContainer = document.querySelector("#checkout-modal-content-container");
    modalContentContainer.innerHTML = "";
}

// INNER FUNCTION(S) //
function markPreviousStepsAsFinished() {
    const finishedSteps = document.querySelectorAll(".checkout-modal-step-finished");
    finishedSteps.forEach( (finishedStep) => finishedStep.classList.remove("checkout-modal-step-finished"));

    const cartStep = document.querySelector("#checkout-modal-cart-step");
    cartStep.classList.add("checkout-modal-step-finished");

    const deliveryStep = document.querySelector("#checkout-modal-delivery-step");
    deliveryStep.classList.add("checkout-modal-step-finished");
}

function highlightReviewStep() {
    const highlightedStep = document.querySelector(".checkout-modal-step-selected");
    highlightedStep.classList.remove("checkout-modal-step-selected");

    const reviewStep = document.querySelector("#checkout-modal-review-step");
    reviewStep.classList.add("checkout-modal-step-selected");
}

function changeCloseButton() {
    let checkoutModalCloseButton = document.querySelector("#checkout-modal-close-button");
    checkoutModalCloseButton.remove();

    const checkoutModalFooterContainerLeftUnit = document.querySelector("#checkout-modal-footer-container-left-unit");
    checkoutModalFooterContainerLeftUnit.insertAdjacentHTML('afterbegin', `<div id="checkout-modal-close-button">Close</div>`);

    checkoutModalCloseButton = document.querySelector("#checkout-modal-close-button");
    checkoutModalCloseButton.addEventListener('click', async () => {
        closeModalDialog();
    });
}

function changePreviousButton() {
    let modalPreviousButton = document.querySelector("#checkout-modal-previous-button");
    modalPreviousButton.remove();

    const checkoutModalFooterContainerRightUnit = document.querySelector("#checkout-modal-footer-container-right-unit");
    checkoutModalFooterContainerRightUnit.insertAdjacentHTML("beforeend", `<div id="checkout-modal-previous-button">Previous</div>`);

    modalPreviousButton = document.querySelector("#checkout-modal-previous-button");
    modalPreviousButton.addEventListener('click', async () => {
        await showDeliveryStep();
    });
}

function changeNextButton() {
    let modalNextButton = document.querySelector("#checkout-modal-next-button");
    modalNextButton.remove();

    const checkoutModalFooterContainerRightUnit = document.querySelector("#checkout-modal-footer-container-right-unit");
    checkoutModalFooterContainerRightUnit.insertAdjacentHTML("beforeend", `<div id="checkout-modal-next-button">Next</div>`);

    modalNextButton = document.querySelector("#checkout-modal-next-button");
    modalNextButton.addEventListener('click', async () => {
        await showPaymentStep();
    });
}