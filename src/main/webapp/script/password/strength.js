$(window).on("load", function () {
    const strength = {
        0: [25, "Bad", "danger"],
        1: [25, "Bad", "danger"],
        2: [50, "Weak", "warning"],
        3: [75, "Good", "success"],
        4: [100, "Strong", "primary"],
    };

    let $email      = $("#email");
    let $username   = $("#username");
    let $password   = $("#password");
    let $meter      = $("#password-strength-meter");
    let $warning    = $("#password-strength-feedback-warning");

    $password.add($email).add($username).on("input", function () {
        let passwordVal = $password.val();
        let usernameVal = $username.val();
        let emailVal    = $email.val();
        let result      = zxcvbn(passwordVal, user_inputs=[usernameVal, emailVal]);
        

        let precentageFill, rating, color;
        [precentageFill, rating, color] = strength[result.score];

        if (passwordVal !== "") {
            $meter
                .css({ width: precentageFill + '%' })
                .html("<strong>" + rating + "</strong>")
                .removeClassStartingWith("bg-")
                .addClass("bg-" + color)

            $warning
                .text(result.feedback.warning)
        } else {
            $meter
                .css({ width: "0%" })
                .empty()

            $warning.empty()
        }
    });
});
