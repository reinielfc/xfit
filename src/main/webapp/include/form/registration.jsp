<%@page contentType="text/html" pageEncoding="UTF-8" %> <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="https://cdnjs.cloudflare.com/ajax/libs/zxcvbn/4.4.2/zxcvbn.js"></script>
<script src="<c:url value='/script/password/validate.js' />"></script>
<script src="<c:url value='/script/password/strength.js' />"></script>

<form id="registration-form" action="register" method="post">
  <!-- name -->
  <div class="form-group">
    <label for="name">Name</label>
    <div class="input-group">
      <input class="form-control" name="name" type="text" value="${user.name}" placeholder="John Smith" required />
      <span class="input-group-text"><i class="fa fa-user"></i></span>
    </div>
  </div>

  <!-- email -->
  <div class="form-group">
    <label for="email">Email Address</label>
    <div class="input-group">
      <input class="form-control${emailIsValid ? "is-invalid" : ""}" name="email" type="email" value="${user.email}"
      placeholder="jsmith@example.com" required />
      <span class="input-group-text"><i class="fa fa-envelope"></i></span>
      <div class="invalid-feedback">This email address is already in use.</div>
    </div>
  </div>

  <!-- password -->
  <div class="form-group">
    <label for="password">Password</label>
    <div class="input-group">
      <input id="password" class="form-control" name="password" type="password" placeholder="********" required />
      <span id="password-icon" class="input-group-text">
        <i class="fa fa-lock"></i>
      </span>
    </div>
  </div>

  <!-- confirm password -->
  <div class="form-group">
    <label for="confirm-password">Confirm Password</label>
    <div class="input-group">
      <input id="confirm-password" class="form-control" type="password" placeholder="********" required />
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
    <div id="password-strength-meter" class="progress-bar" role="progressbar" style="width: 0%"></div>
  </div>
  <small id="password-strength-feedback-warning" class="text-muted"></small>

  <!-- validation message -->
  <div id="password-validation-message" class="small d-none"></div>
</form>

