<%@page contentType="text/html" pageEncoding="UTF-8" %> <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- navbar -->
<nav class="navbar navbar-expand-md bg-dark navbar-dark py-3 fixed-top">
  <div class="container">
    <a class="navbar-brand p-0" href="#">
      <img class="d-inline-block align-text-top" src="<c:url value='/image/logo.svg'/>" width="64" height="64"></img>
    </a>
    <button class="navbar-toggler" data-bs-target="#navmenu" data-bs-toggle="collapse" type="button">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div id="navmenu" class="collapse navbar-collapse">
      <ul class="navbar-nav me-auto">
        <li class="nav-item d-md-none d-lg-block">
          <a class="nav-link${pageContext.request.requestURI.endsWith('/') ? ' active' : ''}" href='<c:url value="/" />'>
            <i class="fas fa-home"></i>
            Home
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link${activePage.equals('exercises') ? ' active' : ''}" href='<c:url value="/exercise" />'>
            <i class="fas fa-person-running"></i>
            Exercises
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link${activePage.equals('planner') ? ' active' : ''}" href="#">
            <i class="fas fa-calendar-alt"></i>
            <span class="d-md-none d-lg-inline">Routine</span> Planner
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link${activePage.equals('workout') ? ' active' : ''}" href="#">
            <i class="fas fa-stopwatch"></i>
            <span class="d-md-none d-lg-inline">Today's </span>Workout
          </a>
        </li>
      </ul>
      <c:choose>
        <c:when test="${true}">
          <!-- test="${not empty signedInUser}"> -->
          <c:import url="/include/form/signin-dropdown.jsp" charEncoding="UTF-8" />
        </c:when>
        <c:otherwise>
          <c:import url="/include/profile-dropdown.jsp" charEncoding="UTF-8" />
        </c:otherwise>
      </c:choose>
    </div>
  </div>
</nav>

<c:if test="${true}">
  <!-- test="${empty signedInUser}"> -->
  <c:import url="/include/form/registration-modal.jsp" charEncoding="UTF-8" />
</c:if>
