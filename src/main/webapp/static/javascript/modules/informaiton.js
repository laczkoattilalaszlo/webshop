// INNER USED CONSTANT VARIABLE(S) //
const body = document.querySelector('body');
const informationButton = document.querySelector("#information-button");

// EXPORTED FUNCTION(S) //
export function addEventListenerToInformationButton() {
    informationButton.addEventListener('click', () => {
        // Disable scrolling on site
        body.classList.add("block-scroll");

        // Add login modal dialog
        body.insertAdjacentHTML('beforeend',    `
                                                            <div id="modal-dialog">
                                                                <div id="modal-fade">
                                                                    <div id="modal-dialog-container" class="animated animation-fadein">
                                                                        <div id="information-modal-header-container">
                                                                            <img src="/static/images/icons/information.png">
                                                                            <div id="information-modal-title">Information</div>
                                                                        </div>
                                                                        <div id="information-modal-content-container">
                                                                            <div class="information-modal-content-container-unit">
                                                                                <div class="information-modal-unit-title">What is this?</div>
                                                                                <div class="information-modal-unit-text">
                                                                                    This is a webshop themed web application, which purpose is to
                                                                                    demonstrate a part of my gained knowledge in field of programming
                                                                                    languages and technologies I learnt so far.
                                                                                    The name of the webshop is Vanilla Shop which tries to represent
                                                                                    my intention to use only pure Java and JavaScript without any robust
                                                                                    framework or library. For more details check the links below!
                                                                                </div>
                                                                                <div class="information-modal-unit-links">
                                                                                    <a href="https://github.com/laczkoattilalaszlo/webshop" target="_blank">
                                                                                        <div>
                                                                                            <img class="information-link-icon" src="/static/images/icons/github.png"><span>Source and README</span>
                                                                                        </div>
                                                                                    </a>
                                                                                    <a href="https://github.com/laczkoattilalaszlo" target="_blank">
                                                                                        <div>
                                                                                            <img class="information-link-icon" src="/static/images/icons/www.png"><span>My other projects</span>
                                                                                        </div>
                                                                                    </a>
                                                                                </div>
                                                                            </div>
                                                    
                                                                            <div class="information-modal-content-container-unit">
                                                                                <div class="information-modal-unit-title">Who am I?</div>
                                                                                <div class="information-modal-unit-text">
                                                                                    I am Attila László Laczkó, I worked 5 years in Software Development as a Senior QA Engineer. I always wanted to
                                                                                    understand deeper how web applications work, so I started to learn programming. I both enjoy dealing with backend
                                                                                    (Java, Spring, ...) and frontend (JavaScript, React, ...). If you want to know more about me check the links below!
                                                                                </div>
                                                                                <div class="information-modal-unit-links">
                                                                                    <a href="https://www.linkedin.com/in/attila-l%C3%A1szl%C3%B3-laczk%C3%B3-353857187" target="_blank">
                                                                                        <div>
                                                                                            <img class="information-link-icon" src="/static/images/icons/linkedin.png"><span>Contact me</span>
                                                                                        </div>
                                                                                    </a>
                                                                                    <a href="https://drive.google.com/file/d/1dB1ddsnmbAhrWcQo9fPZbEMcn14AWVq6/view?usp=sharing" target="_blank">
                                                                                        <div>
                                                                                            <img class="information-link-icon" src="/static/images/icons/cv.png"><span>Curriculum Vitae</span>
                                                                                        </div>
                                                                                    </a>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        <div id="information-modal-footer-container">
                                                                            <div id="information-modal-close-button">Close</div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        `);

        // Add event listener to cancel button
        AddEventListenerToCancelButton();
    });
}

// INNER FUNCTION(S) //
function AddEventListenerToCancelButton() {
    const loginModalCancelButton = document.querySelector("#information-modal-close-button");
    loginModalCancelButton.addEventListener('click', ()=> closeModalDialog());
}

function closeModalDialog() {
    // Enable scrolling on site
    body.classList.remove("block-scroll");

    // Remove modal dialog
    const modalDialog = document.querySelector("#modal-dialog");
    modalDialog.remove();
}