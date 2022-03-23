<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!-- signup modal -->
    <!-- TODO: Add image-->
    <div class="modal fade" id="signup-modal" data-bs-backdrop="static" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-fullscreen-md-down" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title">Sign Up</h5>
              <button class="btn-close" type="button" data-bs-dismiss="modal"></button>
            </div>
            <form action="<c:url value='/register'/>" method="post">
              <div class="modal-body">
                <!-- name -->
                <div class="form-group">
                  <label for="name">Name</label>
                  <div class="input-group">
                    <input
                      type="text"
                      name="name"
                      class="form-control"
                      placeholder="John Smith"
                      value="${user.name}"
                      required
                    />
                    <span class="input-group-text"><i class="fa fa-user"></i></span>
                  </div>
                </div>
              
                <!-- email -->
                <div class="form-group">
                  <label for="email">Email Address</label>
                  <div class="input-group">
                    <input
                      type="email"
                      name="email"
                      class="form-control"
                      placeholder="jsmith@example.com"
                      value="${user.email}"
                      required
                    />
                    <span class="input-group-text"><i class="fa fa-envelope"></i></span>
                  </div>
                </div>
  
                <!-- password -->
                <div class="form-group">
                  <label for="password">Password</label>
                  <div class="input-group">
                    <input
                      type="password"
                      name="password"
                      id="signup-password"
                      class="form-control"
                      placeholder="********"
                      required
                    />
                    <span class="input-group-text"><i class="fa fa-lock"></i></span>
                  </div>
                </div>
  
                <!-- confirm password -->
                <div class="form-group">
                  <label for="confirm-password">Confirm Password</label>
                  <div class="input-group">
                    <input
                      type="password"
                      id="signup-confirm-password"
                      class="form-control"
                      placeholder="********"
                      required
                    />
                    <span class="input-group-text" id="signup-confirm-password-icon"><i class="fa fa-lock"></i></span>
                  </div>
                </div>
                
                <script>
                  var password = document.getElementById("signup-password");
                  var confirmPassword = document.getElementById("signup-confirm-password");
                  var confirmPasswordIcon = document.getElementById("signup-confirm-password-icon");
  
                  function validatePassword() {
                    if (password.value != confirmPassword.value) {
                      confirmPassword.setCustomValidity("Passwords do not match.");
                      //confirmPasswordIcon.className = 'input-group-text text-danger'; TODO
                    } else {
                      confirmPassword.setCustomValidity("");
                      //confirmPasswordIcon.className = 'input-group-text'; TODO
                    }
                  }
  
                  password.onchange = validatePassword;
                  confirmPassword.onkeyup = validatePassword;
                </script>
  
                <!-- password strength -->
                <div class="progress my-2">
                  <div class="progress-bar" id="password-strength-meter" role="progressbar" style="width: 0%"></div>
                </div>
                <small class="text-muted" id="password-strength-feedback-warning"></small>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/zxcvbn/4.2.0/zxcvbn.js"></script>
                <script>
                  var strength = {
                    0: [0, "Worst", "secondary"],
                    1: [25, "Bad", "danger"],
                    2: [50, "Weak", "warning"],
                    3: [75, "Good", "success"],
                    4: [100, "Strong", "primary"],
                  };
  
                  var password = document.getElementById("signup-password");
                  var meter = document.getElementById("password-strength-meter");
                  var warning = document.getElementById("password-strength-feedback-warning");
                  var suggestion = document.getElementById("password-strength-feedback-suggestion");
  
                  password.addEventListener("input", function () {
                    var val = password.value;
                    var result = zxcvbn(val);
  
                    var precentageFill, rating, color;
                    [precentageFill, rating, color] = strength[result.score];
  
                    if (val !== "") {
                      meter.style = `width: \${precentageFill}%;`;
                      meter.innerHTML = "<strong>" + rating + "</strong>";
                      meter.className = "progress-bar bg-" + color;
                      warning.innerText = result.feedback.warning;
                    } else {
                      meter.style = "width: 0%;";
                      meter.innerText = "";
                      warning.innerText = "";
                    }
                  });
                </script>
              </div>
              <div class="modal-footer">
                <button class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <button class="btn btn-primary" type="submit">Sign Up</button>
              </div>
            </form>
          </div>
        </div>
      </div>

      <!-- scrap entered data on -->
      <script>
        $("#signup-modal").on("hidden.bs.modal", function () {
          $(this).find("form").trigger("reset");
        });
      </script>
