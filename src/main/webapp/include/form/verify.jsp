<!-- prettier-ignore -->
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="codeIsValid"  value="${codeIsValid  == null || codeIsValid  ? '' : ' is-invalid' }" />
<c:set var="emailIsValid" value="${emailIsValid == null || emailIsValid ? '' : ' is-invalid' }" />

<script src="<c:url value='/script/form.js' />"></script>

<!-- 
==== VERIFY FORM =======================================================================================================
-->
<c:if test="${!(message == null || message.blank)}">
  <!-- message -->
  <p class="small">${message}</p>
</c:if>

<div class="mb-3">
  <p>
    We have sent an email to
    <a class="text-decoration-none" href="mailto:${recipient.email}">${recipient.email}</a>.
  </p>
  <p>
    Check your inbox and verify that you have received an email with a 6-digit code. If you don't see it in your inbox,
    check your spam folder or use another email.
  </p>
</div>

<div class="container">
  <!-- code -->
  <div class="form-group">
    <label class="form-label" for="code">Enter your code here:</label>
    <div class="input-group${codeIsValid}">
      <input
        class="form-control numeric-only text-center${codeIsValid}"
        name="code"
        type="text"
        placeholder="123456"
        maxlength="6"
        pattern="[0-9]{6}"
      />
    </div>
    <div class="invalid-feedback">Code does not match.</div>
  </div>

  <!-- send again -->
  <button class="btn btn-link p-0 text-decoration-none" type="submit" name="action" value="resend">
    <small>Send again?</small>
  </button>

  <!-- change email -->
  <a class="btn btn-link p-0 text-decoration-none" data-bs-toggle="collapse" data-bs-target="#email-group">
    <small>Change email?</small>
  </a>

  <div id="email-group" class="form-group collapse mt-3">
    <label class="form-label" for="email">Enter a new email address here:</label>
    <div class="input-group${emailIsValid}">
      <!-- prettier-ignore-->
      <input id="email" class="form-control${emailIsValid}" name="email" type="email" value="${recipient.email}" placeholder="${recipient.email}" />
      <button class="btn btn-primary" type="submit" name="action" value="verify">
        <i class="fa fa-envelope"></i> Send
      </button>
    </div>
    <div class="invalid-feedback">Email address ${recipient.email} is already in use.</div>
  </div>
</div>
<!-- 
==== END VERIFY FORM ===================================================================================================
-->
