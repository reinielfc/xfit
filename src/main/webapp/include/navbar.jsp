<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!-- Navbar -->
    <nav class="navbar navbar-expand-md bg-dark navbar-dark py-3 fixed-top">
      <div class="container">
        <a href="#" class="navbar-brand"><b>XFit</b></a>

        <button
          class="navbar-toggler"
          type="button"
          data-toggle="collapse"
          data-target="#navmenu"
        >
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navmenu">
          <ul class="navbar-nav mr-auto">
            <li class="nav-item">
              <a href="#" class="nav-link active">
                <i class="fas fa-home"></i>
                Home
              </a>
            </li>
            <li class="nav-item">
              <a href="#" class="nav-link">
                <i class="fas fa-dumbbell"></i>
                Exercises
              </a>
            </li>
            <li class="nav-item">
              <a href="#" class="nav-link">
                <i class="fas fa-calendar-alt"></i>
                Schedule
              </a>
            </li>
          </ul>
          <button class="btn btn-outline-secondary" data-toggle="modal" data-target="#signup-modal" type="button">
            <i class="fas fa-user-circle"></i>
            Sign Up
          </button>
        </div>
      </div>
    </nav>

  <c:import url="/user/form-signup.html" />
