import {showCartStep} from "./checkout-cart.js";
import {showReviewStep} from "./checkout-review.js";

// EXPORTED FUNCTION(S) //
export async function showDeliveryStep() {
    // Mark earlier steps as finished
    const finishedSteps = document.querySelectorAll(".checkout-modal-step-finished");
    for (let finishedStep of finishedSteps) {
        finishedStep.classList.remove("checkout-modal-step-finished");
    }

    const cartStep = document.querySelector("#checkout-modal-cart-step");
    cartStep.classList.add("checkout-modal-step-finished");

    // Highlight delivery step
    const highlightedSteps = document.querySelectorAll(".checkout-modal-step-selected");
    for (let highlightedStep of highlightedSteps) {
        highlightedStep.classList.remove("checkout-modal-step-selected");
    }

    const deliveryStep = document.querySelector("#checkout-modal-delivery-step");
    deliveryStep.classList.add("checkout-modal-step-selected");

    // Change previous button
    let modalPreviousButton = document.querySelector("#checkout-modal-previous-button");
    modalPreviousButton.remove();

    const checkoutModalFooterContainerRightUnit = document.querySelector("#checkout-modal-footer-container-right-unit");
    checkoutModalFooterContainerRightUnit.insertAdjacentHTML("beforeend", `<div id="checkout-modal-previous-button">Previous</div>`);

    modalPreviousButton = document.querySelector("#checkout-modal-previous-button");
    modalPreviousButton.addEventListener('click', async () => await showCartStep());

    // Change next button
    let modalNextButton = document.querySelector("#checkout-modal-next-button");
    modalNextButton.remove();

    checkoutModalFooterContainerRightUnit.insertAdjacentHTML("beforeend", `<div id="checkout-modal-next-button">Next</div>`);

    modalNextButton = document.querySelector("#checkout-modal-next-button");
    modalNextButton.addEventListener('click', async () => await showReviewStep());

    // Empty modal dialog content
    const modalContentContainer = document.querySelector("#checkout-modal-content-container");
    modalContentContainer.innerHTML = "";

    // Get delivery data from backend (contact information, shipping address, billing address)
    console.log("Delivery Step");
}
