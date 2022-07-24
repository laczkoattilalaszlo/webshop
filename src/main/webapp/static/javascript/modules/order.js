// INNER USED CONSTANT VARIABLE(S) //
import {fetchData} from "./fetch.js";

const body = document.querySelector('body');
const orderButton = document.querySelector("#order-button");

// EXPORTED FUNCTION(S) //
export function addEventListenerToOrderButton() {
    orderButton.addEventListener('click', async () => {
        // Disable scrolling on site
        body.classList.add("block-scroll");

        // Add order modal dialog
        let orderModalContent = `<div id="modal-dialog">
                                    <div id="modal-fade">
                                        <div id="modal-dialog-container">
                                            <div id="order-modal-header-container">
                                                <img src="/static/images/icons/order.png">
                                                <div id="order-modal-title">Orders</div>
                                            </div>
                                            <div id="order-modal-content-container"></div>
                                            <div id="order-modal-footer-container">
                                                <div id="order-modal-close-button">Close</div>
                                            </div>
                                        </div>
                                    </div>
                                 </div>`;


        // Get paid orders
        let paidOrders = await fetchData("GET", `/paid-orders`, {"session-token": sessionStorage.getItem("session-token")}, null, null, "JSON");
        console.log(paidOrders);


        // List orders


        `
        <div class="order">
            <div class="order-title-closed">
                <span class="order-title-text">2222.22.22. 22:22</span><span class="shrink-expand-button">▴ ▾</span>
            </div>
            <div class="order-content"></div>
        </div>
        `

        body.insertAdjacentHTML('beforeend', orderModalContent);

        // Add event listener to close button
        AddEventListenerToCancelButton();
    });
}

function AddEventListenerToCancelButton() {
    const orderModalCloseButton = document.querySelector("#order-modal-close-button");
    orderModalCloseButton.addEventListener('click', ()=> closeModalDialog());
}

export function closeModalDialog() {
    // Enable scrolling on site
    body.classList.remove("block-scroll");

    // Remove modal dialog
    const modalDialog = document.querySelector("#modal-dialog");
    modalDialog.remove();
}
