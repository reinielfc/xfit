<!-- prettier-ignore -->
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="emailIsValid" value="${emailIsValid == null || emailIsValid ? '' : ' is-invalid' }" />
<c:set var="passwordIsValid" value="${passwordIsValid == null || passwordIsValid ? '' : ' is-invalid' }" />

<!-- 
==== REGISTER FORM =====================================================================================================
-->
<!-- scripts -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/zxcvbn/4.4.2/zxcvbn.js"></script>
<script src="<c:url value='/script/password/validate.js' />"></script>
<script src="<c:url value='/script/password/strength.js' />"></script>

<!-- action -->
<input type="hidden" name="action" value="register" />

<c:if test="${!(message == null || message.blank)}">
  <!-- message -->
  <p class="small">${message}</p>
</c:if>

<!-- name -->
<div class="form-group mb-3">
  <label class="form-label" for="name">Name</label>
  <div class="input-group">
    <!-- prettier-ignore-->
    <input id="name" class="form-control" name="name" type="text" value="${newUser.name}" placeholder="John Smith" required />
    <span class="input-group-text"><i class="fa fa-user"></i></span>
  </div>
</div>

<!-- email -->
<div class="form-group mb-3">
  <label class="form-label" for="email">Email Address</label>
  <div class="input-group${emailIsValid}">
    <!-- prettier-ignore-->
    <input id="email" class="form-control${emailIsValid}" name="email" type="email" value="${newUser.email}" placeholder="jsmith@example.com" required />
    <span class="input-group-text"><i class="fa fa-envelope"></i></span>
  </div>
  <div class="invalid-feedback">Email address ${newUser.email} is already in use.</div>
</div>

<!-- TODO: add have an account? sign in link -->

<!-- password -->
<div class="form-group mb-3">
  <label class="form-label" for="password">Password</label>
  <div class="input-group${passwordIsValid}">
    <!-- prettier-ignore-->
    <input id="password" class="form-control${passwordIsValid}" name="password" type="password" placeholder="********" required />
    <span id="password-icon" class="input-group-text">
      <i class="fa fa-lock"></i>
    </span>
  </div>
  <div class="invalid-feedback">
    <p>
      Password is not strong enough!<br />
      Make sure it is at least 8 characters long, and include at least 1 character from A-Z, a-z, and 0-9.
    </p>
  </div>
</div>

<!-- confirm password -->
<div class="form-group mb-3">
  <label class="form-label" for="confirm-password">Confirm Password</label>
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

<!-- password strength  -->
<div class="progress my-3">
  <div id="password-strength-meter" class="progress-bar" role="progressbar" style="width: 0%"></div>
</div>

<small id="password-strength-feedback-warning" class="text-muted"></small>

<!-- password validation message -->
<div id="password-validation-message" class="small d-none"></div>
<!-- 
==== END REGISTER FORM =================================================================================================
-->

