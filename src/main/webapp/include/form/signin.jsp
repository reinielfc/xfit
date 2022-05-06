<!-- prettier-ignore -->
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="rememberMe" value="${rememberMe == null || rememberMe.blank ? '' : 'checked' }" />

<!-- 
==== SIGNIN FORM =======================================================================================================
-->
<c:if test="${!(message == null || message.blank)}">
  <c:set var="validationClass" value=" is-invalid" />
  <!-- message -->
  <p class="small mb-3 text-danger">${message}</p>
</c:if>

<!-- email -->
<div class="form-group mb-3">
  <label class="form-label" for="signin-email">Email</label>
  <div class="input-group">
    <!-- prettier-ignore -->
    <input id="signin-email" class="form-control${validationClass}" name="email" type="email" value="${email}" placeholder="jsmith@example.com" required />
    <span class="input-group-text"><i class="fa fa-envelope"></i></span>
  </div>
</div>

<!-- password -->
<div class="form-group mb-2">
  <label class="form-label" for="signin-password">Password</label>
  <div class="input-group">
    <!-- prettier-ignore -->
    <input id="signin-password" class="form-control${validationClass}" name="password" type="password" placeholder="********" required />
    <span class="input-group-text"><i class="fa fa-lock"></i></span>
  </div>
</div>

<!-- remember me -->
<div class="from-check">
  <input id="signin-remember-me" class="form-check-input" name="rememberMe" type="checkbox" ${rememberMe} />
  <label class="form-check-label" for="signin-remember-me">Remember me</label>
</div>
<!--
==== END SIGNIN FORM ===================================================================================================
-->
