<!-- prettier-ignore -->
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%-- set active page --%>
<c:choose>
  <c:when test="${fn:endsWith(requestDestination, '/')}">
    <c:set var="navHomeClass" value=" active" />
  </c:when>
  <c:when test="${fn:endsWith(requestDestination, '/workout')}">
    <c:set var="navWorkoutClass" value=" active" />
  </c:when>
  <c:when test="${fn:endsWith(requestDestination, '/planner')}">
    <c:set var="navPlannerClass" value=" active" />
  </c:when>
  <c:when test="${fn:endsWith(requestDestination, '/exercise')}">
    <c:set var="navExerciseClass" value=" active" />
  </c:when>
</c:choose>

<%-- display account dropdown if the user is signed in --%>
<c:choose>
  <c:when test="${user == null}">
    <c:set var="greeting" value="Have an account?" />
    <c:set var="dropdown" value="/include/form/dropdown/signin.jsp" />
  </c:when>
  <c:otherwise>
    <c:set var="greeting" value="Hi, ${user.name}!" />
    <c:set var="dropdown" value="/include/account_dropdown.jsp" />
  </c:otherwise>
</c:choose>

<nav class="navbar navbar-expand-md bg-dark navbar-dark py-3 fixed-top">
  <div class="container">
    <a class="navbar-brand p-0" href="#">
      <img class="d-inline-block align-text-top" src="<c:url value='/image/logo.svg'/>" width="64" height="64" />
    </a>

    <button class="navbar-toggler" data-bs-target="#navmenu" data-bs-toggle="collapse" type="button">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div id="navmenu" class="collapse navbar-collapse">
      <ul class="navbar-nav me-auto">
        <li class="nav-item d-md-none d-lg-block">
          <a class="nav-link${navHomeClass}" href="<c:url value='/' />">
            <i class="fas fa-home"></i>
            Home
          </a>
        </li>

        <li class="nav-item">
          <a class="nav-link${navExerciseClass}" href="<c:url value='/exercise' />">
            <i class="fas fa-person-running"></i>
            Exercises
          </a>
        </li>

        <li class="nav-item">
          <a class="nav-link${navPlannerClass}" href="<c:url value='/planner' />">
            <i class="fas fa-calendar-alt"></i>
            <span class="d-md-none d-lg-inline">Routine</span> Planner
          </a>
        </li>

        <li class="nav-item">
          <a class="nav-link${navWorkoutClass}" href="<c:url value='/workout' />">
            <i class="fas fa-stopwatch"></i>
            <span class="d-md-none d-lg-inline">Today's </span>Workout
          </a>
        </li>
      </ul>

      <span class="navbar-text px-md-3 d-none d-md-block">${greeting}</span>

      <ul class="navbar-nav">
        <li class="nav-item">
          <c:import url="${dropdown}" charEncoding="UTF-8" />
        </li>
      </ul>
    </div>
  </div>
</nav>
