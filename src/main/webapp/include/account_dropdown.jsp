<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="dropdown">
  <button id="signin-dropdown-button" class="btn btn-primary" data-bs-toggle="dropdown" type="button">
    <i class="fa fa-user-circle"></i>
    Account
  </button>
  <ul class="dropdown-menu dropdown-menu-md-end mt-2">
    <!-- TODO: equipment settiings page -->
    <li>
      <a class="dropdown-item disabled" href="<c:url value='/configure?setting=equipment' />">
        <i class="fa fa-dumbbell"></i>
        Equipment (Coming soon)
      </a>
    </li>
    <!-- TODO: custom exercises dedicated page -->
    <li>
      <a class="dropdown-item disabled" href="<c:url value='/configure?setting=exercises' />">
        <i class="fa fa-pen-to-square"></i>
        Custom Exercises (Coming soon)
      </a>
    </li>
    <!-- TODO: settings -->
    <li>
      <a class="dropdown-item disabled" href="<c:url value='/configure' />">
        <i class="fa fa-cog"></i>
        Settings (Coming soon)
      </a>
    </li>
    <div class="dropdown-divider"></div>
    <!-- TODO: add sign out confirm dialog-->
    <li class="dropdown-item-text">
      <a class="btn btn-outline-danger w-100" href="<c:url value='/signout' />">Sign out</a>
    </li>
  </ul>
</div>
