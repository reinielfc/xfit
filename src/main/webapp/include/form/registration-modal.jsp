<%@page contentType="text/html" pageEncoding="UTF-8" %> <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="registration-modal" class="modal new-password-form" role="dialog" tabindex="-1">
  <div class="modal-dialog modal-fullscreen-md-down" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Register</h5>
      </div>
      <div class="modal-body">
        <c:import url="/include/form/registration.jsp" charEncoding="UTF-8" />
      </div>
      <div class="modal-footer">
        <button class="btn btn-primary" form="registration-form" type="submit">Register</button>
      </div>
    </div>
  </div>
</div>
