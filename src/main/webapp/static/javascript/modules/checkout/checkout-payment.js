// INNER USED CONSTANT VARIABLE(S) //
import {showConfirmationStep} from "./checkout-confirmaiton.js";
import {fetchData} from "../fetch.js";

const remainingSecond = 30;

// EXPORTED FUNCTION(S) //
export function showPaymentStep() {
    // Mark earlier steps as finished
    markPreviousStepsAsFinished();

    // Highlight delivery step
    highlightReviewStep();

    // Disable close button
    disableCloseButton();

    // Disable previous button
    disablePreviousButton();

    // Disable next button
    changeNextButton(remainingSecond);

    // Empty modal dialog content
    const modalContentContainer = document.querySelector("#checkout-modal-content-container");
    modalContentContainer.innerHTML = "";

    // Add payment content
    const paymentContent = `
        <div id="modal-payment-content">
            <div id='modal-payment-content-logo-text-container'>
                <img src="/static/images/barion.png" id="modal-payment-content-picture">
                <div id="modal-payment-content-text">
                We will automatically redirect you to the payment site of Barion, where you can settle the order.
                Barion will automatically redirect you this page after payment.
                Please <b>DO NOT</b> close any of the tabs of your browser during the payment process!              
                </div>
                
                <img src="/static/images/caution.png" id="modal-payment-caution-picture">
                <div id="modal-payment-caution-text">
                The implementation of the payment process is still in progress. Until it is ready I mocked 
                it, which practically means that one "Failed" and one "Succeeded" payment will be generated 
                for the order when the countdown is over. You can check the orders and the relevant payment
                attempts by clicking on the Orders tab.
                </div>
            </div>
        </div>
    `;
    modalContentContainer.insertAdjacentHTML('afterbegin', paymentContent);

    // Start countdown
    activateRedirectCountDown(remainingSecond);
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
}

function highlightReviewStep() {
    const highlightedStep = document.querySelector(".checkout-modal-step-selected");
    highlightedStep.classList.remove("checkout-modal-step-selected");

    const reviewStep = document.querySelector("#checkout-modal-payment-step");
    reviewStep.classList.add("checkout-modal-step-selected");
}

function disableCloseButton() {
    let checkoutModalCloseButton = document.querySelector("#checkout-modal-close-button");
    checkoutModalCloseButton.classList.add("disabled-button");
}

function disablePreviousButton() {
    let modalPreviousButton = document.querySelector("#checkout-modal-previous-button");
    modalPreviousButton.classList.add("disabled-button");
}

function changeNextButton(remainingSeconds) {
    let modalNextButton = document.querySelector("#checkout-modal-next-button");
    modalNextButton.classList.add("disabled-button");
    modalNextButton.innerHTML = `<span>Redirect in <b>${remainingSeconds}</b> second.</span>`;
}

function activateRedirectCountDown(remainingSeconds) {
    const modalNextButton = document.querySelector("#checkout-modal-next-button");

    let countdown = setInterval( async () => {
        if (remainingSeconds <= -1) {
            clearInterval(countdown);
            await mockPayment();
        } else if (remainingSeconds == 0) {
            modalNextButton.innerHTML = `<span>Redirecting...</span>`;
            remainingSeconds -= 1;
        } else {
            modalNextButton.innerHTML = `<span>Redirect in <b>${remainingSeconds}</b> second.</span>`;
            remainingSeconds -= 1;
        }
    }, 1000);
}

async function mockPayment() {
    await fetchData("POST", `/mock-payment`, {"session-token": sessionStorage.getItem("session-token")}, null, null, null);
    showConfirmationStep();
}