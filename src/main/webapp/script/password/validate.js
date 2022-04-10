$(window).on("load", function () {
    let $password = $("#password");

    // lock icon in password and confirm password fields
    class LockIcon {
        static state = {
            neutral: { color: "text-dark" },
            valid: { color: "text-success" },
            invalid: { color: "text-danger" }
        };

        constructor(lockIconElement) {
            this.icon = lockIconElement;
        };

        changeState(newState) {
            this.icon
                .removeClass(Object.values(LockIcon.state).map(s => s.color))
                .addClass(newState.color);
        };
    };

    // PASSWORD CONFIRMATION
    class ConfirmationLockIcon extends LockIcon {
        #iconTopLayer;
        static state = {
            neutral: {
                color: "text-dark",
                topLayer: "fa-check",
                topLayerTransform: "shrink-5 down-2",
            },
            valid: {
                color: "text-success",
                topLayer: "fa-check",
                topLayerTransform: "shrink-5 down-2",
            },
            invalid: {
                color: "text-danger",
                topLayer: "fa-xmark",
                topLayerTransform: "shrink-5 down-3",
            }
        };

        constructor(lockIconElement) {
            super(lockIconElement);
            this.#iconTopLayer = this.icon.find(":last-child");
        };

        changeState(newState) {
            super.changeState(newState);
            this.#iconTopLayer
                .removeClass(Object.values(ConfirmationLockIcon.state).map(s => s.topLayer))
                .addClass(newState.topLayer)
                .attr("data-fa-transform", newState.topLayerTransform);
        };
    };

    let $confirmation = $("#confirm-password");
    let $confirmationPasswordIcon = $("#confirm-password-icon");

    $password.add($confirmation).on("input", function () {
        let confirmationLockIcon = new ConfirmationLockIcon($confirmationPasswordIcon);
        let passwordVal = $password.val();
        let confirmationVal = $confirmation.val();

        $confirmation.get(0).setCustomValidity("");

        if (passwordVal === "" && confirmationVal === "") {
            confirmationLockIcon.changeState(ConfirmationLockIcon.state.neutral)
        } else if (passwordVal === confirmationVal) {
            confirmationLockIcon.changeState(ConfirmationLockIcon.state.valid)
        } else {
            confirmationLockIcon.changeState(ConfirmationLockIcon.state.invalid)
            $confirmation.get(0).setCustomValidity("Passwords do not match.");
        }
    });

    // PASSWORD VALIDATION
    let $validationMessage = $("#password-validation-message");
    let validationMessageStart = $("<strong>Your pasword must contain a minimum of:</strong>")
    let validationMessageList = $("<ul class=\"list-unstyled m-1\"></ul>")
    let validationMessageListItems = [
        "8 characters",
        "1 upper case letter [A-Z]",
        "1 lower case letter [a-z]",
        "1 numeric character [0-9]"
    ];

    validationMessageListItems.forEach(item => {
        validationMessageList.append(
            `<li>
                <i class="fas fa-circle" data-fa-transform="shrink-1 down-1"></i>
                <strong>${item}</strong>
            </li>`);
    });

    $validationMessage.append(validationMessageStart.add(validationMessageList));

    $(".new-password-form").on("submit", function (event) {
        let $icon = $("#password-icon");

        function passwordIsValid(passwordVal) {
            let isValid = true;
            let requirements = [/\S{8,}/, /[A-Z]/, /[a-z]/, /[0-9]/]
            let validationMessageIconList = validationMessageList.find("svg");

            for (let i = 0; i < requirements.length; i++) {
                let requirementIsMet = requirements[i].test(passwordVal);
                let $validationMessageIcon = $(validationMessageIconList[i]);

                $validationMessageIcon.removeClass(function (index, className) {
                    return (className.match(/(^|\s)(fa-|text-)\S+/g) || []).join(' ');
                })

                if (requirementIsMet) {
                    $validationMessageIcon
                        .addClass("fa-solid fa-circle-check text-success");
                } else {
                    $validationMessageIcon
                        .addClass("fa-solid fa-circle-xmark text-danger");
                }

                if (isValid && !requirementIsMet) {
                    isValid = false;
                }
            }

            $icon.removeClass("text-success text-danger");

            if (passwordVal === "") {
                $password.get(0).setCustomValidity("Please enter a password.")
            } else if (isValid) {
                $icon.addClass("text-success");
                $password.get(0).setCustomValidity("")
            } else {
                $icon.addClass("text-danger");
                $password.get(0).setCustomValidity("Password is not strong enough")
            }

            return isValid
        };

        if (!passwordIsValid($password.val())) {
            $password.on("input", function (event) {
                passwordIsValid($(event.target).val())
            });
            $validationMessage.removeClass("d-none");
            event.preventDefault();
        } else {
            $password.get(0).setCustomValidity("");
        }

    });
});
