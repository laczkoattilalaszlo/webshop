// INNER USED CONSTANT VARIABLES //
const body = document.querySelector('body');
const registrationButton = document.querySelector("#registration-button");

// EXPORTED FUNCTIONS //
export function registrationButtonAddEventListener() {
    registrationButton.addEventListener('click', () => {
        body.classList.add("block-scroll");
        body.insertAdjacentHTML('beforeend',    `
                                                            <div id="modal-dialog">
                                                                <div id="modal-fade">
                                                                    <div id="modal-dialog-container">
                                                                        <div id="registration-modal-header-container">
                                                                            <img src="/static/images/icons/register.png">
                                                                            <div id="registration-modal-title">Register user</div>
                                                                        </div>
                                                                        <div id="registration-modal-content-container">
                                                                            <input type="text" id="modal-registration-email-input" placeholder="E-mail" required>
                                                                            <input type="text" id="modal-registration-password-input" placeholder="Password" required>
                                                                            <input type="text" id="modal-registration-password-confirmation-input" placeholder="Confirm Password" required>
                                                                        </div>
                                                                        <div id="registration-modal-footer-container">
                                                                            <div id="registration-modal-cancel-button">Cancel</div>
                                                                            <div id="registration-modal-register-button">Register</div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        `);
    });
}