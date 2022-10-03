import {fetchData} from "../fetch.js";
import {closeModalDialog} from "./checkout.js";
import {showDeliveryStep} from "./checkout-delivery.js";

// EXPORTED FUNCTION(S) //
export async function showCartStep() {
    // Remove highlight from delivery step
    const deliveryStep = document.querySelector("#checkout-modal-delivery-step");
    deliveryStep.classList.remove("checkout-modal-step-selected");

    // Remove finished from cart step
    const cartStep = document.querySelector("#checkout-modal-cart-step");
    deliveryStep.classList.remove("checkout-modal-step-finished");

    // Add highlight to cart step
    cartStep.classList.add("checkout-modal-step-selected");

    // Empty modal dialog content
    const modalContentContainer = document.querySelector("#checkout-modal-content-container");
    modalContentContainer.innerHTML = "";

    // Get cart from backend
    const cart = await fetchData("GET", `/cart`, {"session-token": sessionStorage.getItem("session-token")}, null, null, "JSON");

    // Show cart content in a table
    if (cart.length === 0) {
        modalContentContainer.insertAdjacentHTML('afterbegin', `
            <div id='modal-cart-is-empty-text-container'>
                <div id='modal-cart-is-empty-text'>Cart is empty, choose product from product categories!</div>
            </div>
        `);
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
                                <td class="checkout-modal-left-aligned-column"><img class="checkout-modal-product-photo" src="static/images/products/${(product.picture != null) ? product.picture : "product-placeholder.jpeg"}"></td>
                                <td class="checkout-modal-left-aligned-column checkout-modal-column-with-right-padding">${product.supplierName} ${product.name}</td>
                                <td class="checkout-modal-left-aligned-column">${product.price} ${product.currency}</td>
                                <td class="checkout-modal-center-aligned-column">${product.quantity}</td>
                                <td class="checkout-modal-right-aligned-column">${subTotalPrice} ${product.currency}</td>
                                <td class="checkout-modal-right-aligned-column">
                                    <button class="checkout-modal-add-to-cart-button" data-product-id="${product.productId}" type="button">+</button> 
                                    <button class="checkout-modal-remove-from-cart-button" data-product-id="${product.productId}" type="button">-</button>
                                <td class="checkout-modal-right-aligned-column">
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

    // Change close button
    changeCloseButton();

    // Change previous button
    changePreviousButton();

    // Hide previous button
    const modalPreviousButton = document.querySelector("#checkout-modal-previous-button");
    modalPreviousButton.classList.add("invisible");

    // Change next button
    changeNextButton(cart);

    // Add event listener to 'Modal add to cart' button
    addEventListenerToModalAddToCartButton();

    // Add event listener to 'Modal remove from cart' button
    addEventListenerToModalRemoveCartButton();

    // Create active order if it does not exist
    await fetchData("POST", `/active-order`, {"session-token": sessionStorage.getItem("session-token")}, null, null, null);
}

// INNER FUNCTION(S) //
function changeCloseButton() {
    let checkoutModalCloseButton = document.querySelector("#checkout-modal-close-button");
    checkoutModalCloseButton.remove();

    const checkoutModalFooterContainerLeftUnit = document.querySelector("#checkout-modal-footer-container-left-unit");
    checkoutModalFooterContainerLeftUnit.insertAdjacentHTML('afterbegin', `<div id="checkout-modal-close-button">Close</div>`);

    checkoutModalCloseButton = document.querySelector("#checkout-modal-close-button");
    checkoutModalCloseButton.addEventListener('click', async () => closeModalDialog());
}

function changePreviousButton() {
    let modalPreviousButton = document.querySelector("#checkout-modal-previous-button");
    modalPreviousButton.remove();

    const checkoutModalFooterContainerRightUnit = document.querySelector("#checkout-modal-footer-container-right-unit");
    checkoutModalFooterContainerRightUnit.insertAdjacentHTML("beforeend", `<div id="checkout-modal-previous-button">Previous</div>`);

    modalPreviousButton = document.querySelector("#checkout-modal-previous-button");
    modalPreviousButton.addEventListener('click', async () => await showCartStep());
}

function changeNextButton(cart) {
    let modalNextButton = document.querySelector("#checkout-modal-next-button");
    modalNextButton.remove();

    if (cart.length != 0) {
        const checkoutModalFooterContainerRightUnit = document.querySelector("#checkout-modal-footer-container-right-unit");
        checkoutModalFooterContainerRightUnit.insertAdjacentHTML("beforeend", `<div id="checkout-modal-next-button">Next</div>`);

        modalNextButton = document.querySelector("#checkout-modal-next-button");
        modalNextButton.addEventListener('click', async () => {
            await transferCartContentToActiveOrderCart();
            await showDeliveryStep();
        });
    }
}

function addEventListenerToModalAddToCartButton() {
    const modalAddToCartButtons = document.querySelectorAll(".checkout-modal-add-to-cart-button");
    for(let modalAddToCartButton of modalAddToCartButtons) {
        modalAddToCartButton.addEventListener('click', async () => {
            const productId = modalAddToCartButton.dataset.productId;
            const response = await fetchData("POST", `/cart`, {"session-token": sessionStorage.getItem("session-token")}, `${productId}`, null, null);
            if (response.ok) {
                // Empty modal dialog content
                const modalContentContainer = document.querySelector("#checkout-modal-content-container");
                modalContentContainer.innerHTML = "";

                // Show cart step
                await showCartStep();
            }
        });
    }
}

function addEventListenerToModalRemoveCartButton() {
    const modalRemoveFromCartButtons = document.querySelectorAll(".checkout-modal-remove-from-cart-button");
    for(let modalRemoveFromCartButton of modalRemoveFromCartButtons) {
        modalRemoveFromCartButton.addEventListener('click', async () => {
            const productId = modalRemoveFromCartButton.dataset.productId;
            const response = await fetchData("DELETE", `/cart`, {"session-token": sessionStorage.getItem("session-token")}, `${productId}`, null, null);
            if (response.ok) {
                // Empty modal dialog content
                const modalContentContainer = document.querySelector("#checkout-modal-content-container");
                modalContentContainer.innerHTML = "";

                // Show cart step
                await showCartStep();
            }
        });
    }
}

async function transferCartContentToActiveOrderCart() {
    await fetchData("PUT", `/active-order-cart`, {"session-token": sessionStorage.getItem("session-token")}, null, null, null);
}
