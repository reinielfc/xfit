<%@page contentType="text/html" pageEncoding="UTF-8" %> <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- navbar -->
<style>
  body::before {
    display: block;
    content: "";
    height: 72px;
  }
</style>

<nav class="navbar navbar-expand-md bg-dark navbar-dark py-3 fixed-top">
  <div class="container">
    <a class="navbar-brand" href="#"><b>XFit</b></a>
    <button
      class="navbar-toggler"
      data-bs-target="#navmenu"
      data-bs-toggle="collapse"
      type="button"
    >
      <span class="navbar-toggler-icon"></span>
    </button>
    <div id="navmenu" class="collapse navbar-collapse">
      <ul class="navbar-nav me-auto">
        <li class="nav-item">
          <a class="nav-link active" href="#">
            <i class="fas fa-home"></i>
            Home
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">
            <i class="fas fa-person-running"></i>
            Exercises
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">
            <i class="fas fa-calendar-alt"></i>
            Routine
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">
            <i class="fas fa-stopwatch"></i>
            <span class="d-md-none d-lg-inline">Today's </span>Workout
          </a>
        </li>
      </ul>
    </div>
  </div>
</nav>

<c:import url="/include/registration-modal.jsp" charEncoding="UTF-8" />
