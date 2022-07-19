import {fetchData} from "./fetch.js";

// INNER USED CONSTANT VARIABLES //
const body = document.querySelector('body');
const cartButton = document.querySelector("#cart-button");

// EXPORTED FUNCTIONS //
export function addEventListenerToCartButton() {
    cartButton.addEventListener('click', async () => {
        // Disable scrolling on site
        body.classList.add("block-scroll");

        // Add login modal dialog
        body.insertAdjacentHTML('beforeend', `
            <div id="modal-dialog">
                <div id="modal-fade">
                    <div id="modal-dialog-container">
                        <div id="checkout-modal-header-container">
                            <div id="checkout-modal-header">
                                <div id="checkout-modal-title">Checkout Process</div>
                            </div>
                            <div id="checkout-modal-steps">
                                <div id="checkout-modal-cart-step" class="checkout-modal-step">
                                    <img src="/static/images/icons/cart.png"><div>Cart</div>
                                </div>
                                <img src="/static/images/icons/next.png" class="checkout-modal-step-separator">
                                <div id="checkout-modal-delivery-step" class="checkout-modal-step">
                                    <img src="/static/images/icons/delivery.png"><div>Delivery</div>
                                </div>
                                <img src="/static/images/icons/next.png" class="checkout-modal-step-separator">
                                <div id="checkout-modal-review-step" class="checkout-modal-step">
                                    <img src="/static/images/icons/review.png"><div>Review</div>
                                </div>
                                <img src="/static/images/icons/next.png" class="checkout-modal-step-separator">
                                <div id="checkout-modal-payment-step" class="checkout-modal-step">
                                    <img src="/static/images/icons/payment.png"><div>Payment</div>
                                </div>
                                <img src="/static/images/icons/next.png" class="checkout-modal-step-separator">
                                <div id="checkout-modal-confirmation-step" class="checkout-modal-step">
                                    <img src="/static/images/icons/confirmation.png"><div>Confirmation</div>
                                </div>
                            </div>
    
                        </div>
                        <div id="checkout-modal-content-container"></div>
                        <div id="checkout-modal-footer-container">
                            <div id="checkout-modal-footer-container-left-unit">
                                <div id="checkout-modal-close-button">Close</div>
                            </div>
                            <div id="checkout-modal-footer-container-right-unit">
                                <div id="checkout-modal-previous-button">Previous</div>
                                <div id="checkout-modal-next-button">Next</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        `);

        // Add event listener to close button
        addEventListenerToCloseButton();

        // Show cart step
        await showCartStep();
    });

    // Click when loading site (development only)
    cartButton.click();
}

// INNER FUNCTIONS //
function addEventListenerToCloseButton() {
    const checkoutModalCloseButton = document.querySelector("#checkout-modal-close-button");
    checkoutModalCloseButton.addEventListener('click', ()=> closeModalDialog());
}

function closeModalDialog() {
    // Enable scrolling on site
    body.classList.remove("block-scroll");

    // Remove modal dialog
    const modalDialog = document.querySelector("#modal-dialog");
    modalDialog.remove();
}

function changePreviousButton(actionToExecute) {
    let modalPreviousButton = document.querySelector("#checkout-modal-previous-button");
    modalPreviousButton.remove();

    const checkoutModalFooterContainerRightUnit = document.querySelector("#checkout-modal-footer-container-right-unit");
    checkoutModalFooterContainerRightUnit.insertAdjacentHTML("beforeend", `<div id="checkout-modal-previous-button">Previous</div>`);

    modalPreviousButton = document.querySelector("#checkout-modal-previous-button");
    modalPreviousButton.addEventListener('click', async () => await actionToExecute());
}

function changeNextButton(actionToExecute) {
    let modalNextButton = document.querySelector("#checkout-modal-next-button");
    modalNextButton.remove();

    const checkoutModalFooterContainerRightUnit = document.querySelector("#checkout-modal-footer-container-right-unit");
    checkoutModalFooterContainerRightUnit.insertAdjacentHTML("beforeend", `<div id="checkout-modal-next-button">Next</div>`);

    modalNextButton = document.querySelector("#checkout-modal-next-button");
    modalNextButton.addEventListener('click', async () => await actionToExecute());
}

async function showCartStep() {
    // Remove highlight from delivery step
    const deliveryStep = document.querySelector("#checkout-modal-delivery-step");
    deliveryStep.classList.remove("checkout-modal-step-selected");

    // Remove finished from cart step
    const cartStep = document.querySelector("#checkout-modal-cart-step");
    deliveryStep.classList.remove("checkout-modal-step-finished");

    // Add highlight to cart step
    cartStep.classList.add("checkout-modal-step-selected");

    // Change previous button
    let modalPreviousButton = document.querySelector("#checkout-modal-previous-button");
    modalPreviousButton.remove();

    const checkoutModalFooterContainerRightUnit = document.querySelector("#checkout-modal-footer-container-right-unit");
    checkoutModalFooterContainerRightUnit.insertAdjacentHTML("beforeend", `<div id="checkout-modal-previous-button">Previous</div>`);

    modalPreviousButton = document.querySelector("#checkout-modal-previous-button");
    modalPreviousButton.addEventListener('click', async () => await showCartStep());

    // // Hide previous button
    modalPreviousButton = document.querySelector("#checkout-modal-previous-button");
    modalPreviousButton.classList.add("invisible");

    // Change next button
    let modalNextButton = document.querySelector("#checkout-modal-next-button");
    modalNextButton.remove();

    checkoutModalFooterContainerRightUnit.insertAdjacentHTML("beforeend", `<div id="checkout-modal-next-button">Next</div>`);

    modalNextButton = document.querySelector("#checkout-modal-next-button");
    modalNextButton.addEventListener('click', async () => await showDeliveryStep());

    // Empty modal dialog content
    const modalContentContainer = document.querySelector("#checkout-modal-content-container");
    modalContentContainer.innerHTML = "";

    // Get cart from backend
    const cart = await fetchData("GET", `/cart`, {"session-token": sessionStorage.getItem("session-token")}, null, null, "JSON");

    // Show cart content in a table
    if (cart.length === 0) {
        modalContentContainer.insertAdjacentHTML('afterbegin', "<div id='modal-cart-is-empty-text'>Cart is empty, choose product from product categories!</div>");
    } else {
        let cartTable = `<table id="checkout-modal-cart-table">
                            <colgroup>
                                <col id="checkout-modal-picture-column" >
                                <col id="checkout-modal-supplier-and-name-column">
                                <col id="checkout-modal-price-and-currency-column">
                                <col id="checkout-modal-quantity-column">
                                <col id="checkout-modal-sub-total-column">
                                <col id="checkout-modal-buttons-column">
                            </colgroup>
                            <thead>
                                <tr>
                                    <th class="checkout-modal-left-aligned-column"></th>
                                    <th class="checkout-modal-left-aligned-column"></th>
                                    <th class="checkout-modal-left-aligned-column">Unit price</th>
                                    <th class="checkout-modal-center-aligned-column">Quantity</th>
                                    <th class="checkout-modal-right-aligned-column">Sub-total</th>
                                    <th class="checkout-modal-right-aligned-column"></th>
                                </tr>
                            </thead>`;

        let totalPrice = 0;
        let currency = cart[0].currency;
        for (let product of cart) {
            let subTotalPrice = product.price * product.quantity;
            totalPrice += subTotalPrice;
            cartTable += `<tbody>
                            <tr>
                                <td class="checkout-modal-left-aligned-column"><img class="checkout-modal-product-photo" src="${product.picture}"></td>
                                <td class="checkout-modal-left-aligned-column">${product.supplierName} ${product.name}</td>
                                <td class="checkout-modal-left-aligned-column">${product.price} ${product.currency}</td>
                                <td class="checkout-modal-center-aligned-column">${product.quantity}</td>
                                <td class="checkout-modal-right-aligned-column">${subTotalPrice} ${product.currency}</td>
                                <td class="checkout-modal-right-aligned-column">
                                    <button class="checkout-modal-add-to-cart-button" data-product-id="${product.productId}" type="button">+</button> 
                                    <button class="checkout-modal-remove-from-cart-button" data-product-id="${product.productId}" type="button">-</button>
                                </td class="checkout-modal-right-aligned-column">
                            </tr>
                          </tbody>`;
        }

        cartTable += `   <tfoot>
                            <tr>
                                <td class="checkout-modal-left-aligned-column"></td>
                                <td class="checkout-modal-left-aligned-column"></td>
                                <td class="checkout-modal-left-aligned-column"></td>
                                <td class="checkout-modal-center-aligned-column" id="checkout-modal-total-prize-text"><div>Total:</div></td>
                                <td class="checkout-modal-right-aligned-column" id="checkout-modal-total-price"><div>${totalPrice} ${currency}</div></td>
                                <td class="checkout-modal-right-aligned-column"></td>
                            </tr>
                         </tfoot>
                      </table>`;

        modalContentContainer.insertAdjacentHTML('afterbegin', cartTable);
    }

    // Add event listener to 'Modal add to cart' button
    const modalAddToCartButtons = document.querySelectorAll(".checkout-modal-add-to-cart-button");
    for(let modalAddToCartButton of modalAddToCartButtons) {
        modalAddToCartButton.addEventListener('click', async () => {
            const productId = modalAddToCartButton.dataset.productId;
            const response = await fetchData("POST", `/cart`, {"session-token": sessionStorage.getItem("session-token")}, `${productId}`, null, null);
            if (response.ok) {
                // Empty modal dialog content
                modalContentContainer.innerHTML = "";
                await showCartStep();
            }
        });
    }

    // Add event listener to 'Modal remove from cart' button
    const modalRemoveFromCartButtons = document.querySelectorAll(".checkout-modal-remove-from-cart-button");
    for(let modalRemoveFromCartButton of modalRemoveFromCartButtons) {
        modalRemoveFromCartButton.addEventListener('click', async () => {
            const productId = modalRemoveFromCartButton.dataset.productId;
            const response = await fetchData("DELETE", `/cart`, {"session-token": sessionStorage.getItem("session-token")}, `${productId}`, null, null);
            if (response.ok) {
                // Empty modal dialog content
                modalContentContainer.innerHTML = "";
                await showCartStep();
            }
        });
    }

    // Create active order if it does not exist
    await fetchData("POST", `/active-order`, {"session-token": sessionStorage.getItem("session-token")}, null, null, null);

    // Transfer cart content to active order cart
    await fetchData("PUT", `/active-order-cart`, {"session-token": sessionStorage.getItem("session-token")}, null, null, null);
}

async function showDeliveryStep() {
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

async function showReviewStep() {
    console.log("Review Step");
}