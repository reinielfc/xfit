<%@page contentType="text/html" pageEncoding="UTF-8" %> <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/include/head.jsp" charEncoding="UTF-8" />

<!-- verify email modal -->
<div
  id="verify-email-modal"
  class="modal"
  data-bs-backdrop="false"
  data-bs-show="true"
  data-show=""
  tabindex="-1"
  role="dialog"
>
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Verify Email</h5>
      </div>
      <div class="modal-body">
        <form id="email-verification-form" action="verify" method="post">
          <span>We have sent an email with a code to ${user.email}jsmith@example.org to verify it.</span>
          <div class="form-group mt-2">
            <label for="code">Enter your code here:</label>
            <div class="input-group">
              <input
                class="form-control numeric-only"
                type="text"
                name="code"
                placeholder="XXXXXX"
                maxlength="6"
                pattern="[0-9]{6}"
                required
              />
            </div>
          </div>
          <input type="hidden" name="action" value="code" />
        </form>
        <form action="validate">
          <button type="submit" class="btn btn-link">
            <small>Send again?</small>
          </button>
          <input type="hidden" name="action" value="send" />
        </form>
        <button class="btn btn-link" data-bs-toggle="collapse" data-bs-target="#email">
          <small>Change email?</small>
        </button>
      </div>
      <form action="validate">
        <div id="email" class="form-group collapse mt-2">
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
            <span class="input-group-text"><i class="fa fa-envelope"></i></span>
            <div class="invalid-feedback">This email address is already in use.</div>
          </div>
        </div>
      </form>
    </div>
    <div class="modal-footer">
      <a class="btn btn-secondary" href="/">Cancel Registration</a>
      <button type="submit" form="email-verification-form" class="btn btn-primary">Submit</button>
    </div>
  </div>
</div>
<script>
  $("body").addClass("bg-dark");
  $(".modal").show();
</script>
<c:import url="/include/foot.html" charEncoding="UTF-8" />
