<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- register -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/zxcvbn/4.4.2/zxcvbn.js"></script>
<script src="<c:url value='/script/password/validate.js' />"></script>
<script src="<c:url value='/script/password/strength.js' />"></script>

<div
  id="create-acount-modal"
  class="modal new-password-form"
  role="dialog"
  tabindex="-1"
>
  <div class="modal-dialog modal-fullscreen-md-down" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Register</h5>
      </div>
      <form id="registration-form" action="register" method="post">
        <div class="modal-body">
            
          <!-- name -->
          <div class="form-group">
            <label for="name">Name</label>
            <div class="input-group">
              <input
                class="form-control"
                name="name"
                type="text"
                value="${user.name}"
                placeholder="John Smith"
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
                class="form-control"
                name="email"
                type="email"
                value="${user.email}"
                placeholder="jsmith@example.com"
                required
              />
              <span class="input-group-text"
                ><i class="fa fa-envelope"></i
              ></span>
            </div>
            <span class="text-danger small">${message}</span>
          </div>

          <!-- password -->
          <div class="form-group">
            <label for="password">Password</label>
            <div class="input-group">
              <input
                id="password"
                class="form-control"
                name="password"
                type="password"
                placeholder="********"
                required
              />
              <span id="password-icon" class="input-group-text">
                <i class="fa fa-lock"></i>
              </span>
            </div>
          </div>

          <!-- confirm password -->
          <div class="form-group">
            <label for="confirm-password">Confirm Password</label>
            <div class="input-group">
              <input
                id="confirm-password"
                class="form-control"
                type="password"
                placeholder="********"
                required
              />
              <span id="confirm-password-icon" class="input-group-text">
                <span class="fa-layers">
                  <i class="fa-solid fa-lock"></i>
                  <i
                    id="confirm-password-icon-layer-top"
                    class="fa-inverse fa-solid fa-check"
                    data-fa-transform="shrink-5 down-2"
                  ></i>
                </span>
              </span>
            </div>
          </div>

          <!-- password strength -->
          <div class="progress my-2">
            <div
              id="password-strength-meter"
              class="progress-bar"
              role="progressbar"
              style="width: 0%"
            ></div>
          </div>
          <small
            id="password-strength-feedback-warning"
            class="text-muted"
          ></small>

          <!-- validation message -->
          <div id="password-validation-message" class="small d-none"></div>
        </div>
        <div class="modal-footer">
          <button class="btn btn-primary" type="submit">Sign Up</button>
        </div>
      </form>
    </div>
  </div>
</div>
