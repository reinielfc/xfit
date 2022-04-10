<%@page contentType="text/html" pageEncoding="UTF-8" %> <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- sign in -->
<span class="navbar-text px-md-3 d-none d-md-block">Have an account?</span>
<ul class="navbar-nav">
  <li class="nav-item dropdown">
    <button id="signin-dropdown-button" class="btn btn-outline-secondary" data-bs-toggle="dropdown" type="button">
      Sign in
    </button>

    <form id="signin-dropdown-form" class="dropdown-menu dropdown-menu-md-end mt-2 w-100 w-md-24" action="signin" method="post">

      <!-- email -->
      <div class="form-group dropdown-item-text">
        <label for="email">Email</label>
        <div class="input-group">
          <input
            id="signin-email"
            class="form-control"
            name="email"
            type="email"
            placeholder="jsmith@example.com"
            required
          />
          <span class="input-group-text"><i class="fa fa-envelope"></i></span>
        </div>
      </div>

      <!-- password -->
      <div class="form-group dropdown-item-text">
        <label for="signin-password">Password</label>
        <div class="input-group">
          <input
            id="signin-password"
            class="form-control"
            name="password"
            type="password"
            placeholder="********"
            required
          />
          <span class="input-group-text"><i class="fa fa-lock"></i></span>
        </div>
        <a class="text-decoration-none" href="#"><small>Forgot password?</small></a>
      </div>
      <div class="dropdown-item-text">
        <div class="form-check">
          <input id="remember-me" class="form-check-input" name="remember-me" type="checkbox" />
          <label class="form-check-label" for="remember-me">Remember me</label>
        </div>
      </div>
      <div class="dropdown-divider"></div>
      <div class="dropdown-item-text d-flex flex-wrap">
        <button class="btn btn-outline-primary me-auto" data-bs-target="#create-account-modal" data-bs-toggle="modal">
          Register
        </button>
        <button class="btn btn-primary" type="submit">Sign in</button>
      </div>
    </form>
  </li>
</ul>
