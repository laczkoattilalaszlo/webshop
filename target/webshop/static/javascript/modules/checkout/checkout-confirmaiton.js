// EXPORTED FUNCTION(S) //
import {closeModalDialog} from "./checkout.js";

export function showConfirmationStep() {
    // Mark earlier steps as finished
    markPreviousStepsAsFinished();

    // Highlight delivery step
    highlightReviewStep();

    // Change close button
    removeCloseButton();

    // Change previous button
    hidePreviousButton();

    // Change next button
    changeNextButton();

    // Empty modal dialog content
    const modalContentContainer = document.querySelector("#checkout-modal-content-container");
    modalContentContainer.innerHTML = "";

    // Add confirmation content
    const confirmationContent = `
        <div id="modal-confirmation-content">
            <div id='modal-confirmation-content-logo-text-container'>
                <img src="/static/images/icons/payed-order.png" id="modal-confirmation-content-picture">
                <div id="modal-payment-content-text">
                Order placed successfully!
                </div>
            </div>
        </div>
    `;
    modalContentContainer.insertAdjacentHTML('afterbegin', confirmationContent);
}

// INNER FUNCTION(S) //
function markPreviousStepsAsFinished() {
    const finishedSteps = document.querySelectorAll(".checkout-modal-step-finished");
    finishedSteps.forEach( (finishedStep) => finishedStep.classList.remove("checkout-modal-step-finished"));

    const cartStep = document.querySelector("#checkout-modal-cart-step");
    cartStep.classList.add("checkout-modal-step-finished");

    const deliveryStep = document.querySelector("#checkout-modal-delivery-step");
    deliveryStep.classList.add("checkout-modal-step-finished");

    const reviewStep = document.querySelector("#checkout-modal-review-step");
    reviewStep.classList.add("checkout-modal-step-finished");

    const paymentStep = document.querySelector("#checkout-modal-payment-step");
    paymentStep.classList.add("checkout-modal-step-finished");
}

function highlightReviewStep() {
    const highlightedStep = document.querySelector(".checkout-modal-step-selected");
    highlightedStep.classList.remove("checkout-modal-step-selected");

    const paymentStep = document.querySelector("#checkout-modal-confirmation-step");
    paymentStep.classList.add("checkout-modal-step-selected");
}

function removeCloseButton() {
    let checkoutModalCloseButton = document.querySelector("#checkout-modal-close-button");
    checkoutModalCloseButton.remove();
}

function hidePreviousButton() {
    let modalPreviousButton = document.querySelector("#checkout-modal-previous-button");
    modalPreviousButton.classList.add("invisible");
}

function changeNextButton() {
    let modalNextButton = document.querySelector("#checkout-modal-next-button");
    modalNextButton.remove();

    const checkoutModalFooterContainerRightUnit = document.querySelector("#checkout-modal-footer-container-right-unit");
    checkoutModalFooterContainerRightUnit.insertAdjacentHTML("beforeend", `<div id="checkout-modal-next-button">OK</div>`);

    modalNextButton = document.querySelector("#checkout-modal-next-button");
    modalNextButton.addEventListener('click', async () => {
        closeModalDialog();
    });
}
