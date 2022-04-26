<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="dropdown">
  <button id="signin-dropdown-button" class="btn btn-outline-secondary" data-bs-toggle="dropdown" type="button">
    Sign in
  </button>

  <div class="dropdown-menu dropdown-menu-md-end mt-2 w-100 w-md-24">
    <form id="signin-dropdown-form" class="dropdown-item-text" action="signin" method="POST">
      <c:import url="/include/form/signin.jsp" charEncoding="UTF-8" />
    </form>
    <div class="dropdown-divider"></div>

    <div class="dropdown-item-text d-flex flex-wrap">
      <a class="btn btn-outline-primary me-auto" href="<c:url value='/register' />">Register</a>
      <button class="btn btn-primary" form="signin-dropdown-form" type="submit">Sign in</button>
    </div>
  </div>
</div>
