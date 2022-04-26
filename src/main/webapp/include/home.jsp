<!-- prettier-ignore -->
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Showcase -->
<section class="bg-dark text-light p-5 p-lg-2 pt-lg-5 text-center text-sm-start">
  <div class="container">
    <div class="d-sm-flex align-items-center justify-content-between">
      <div>
        <h1>Track your <span class="text-warning">Workouts</span></h1>
        <p class="lead">Look through our list of exercises and see what is best suited for you.</p>
        <c:if test="${user == null}">
          <p class="lead">Register now to get started!</p>
          <button class="btn btn-primary btn-lg" data-bs-target="#register-form-modal" data-bs-toggle="modal">
            Register
          </button>
        </c:if>
      </div>
      <img class="img-fluid d-none d-md-block w-50" src="<c:url value='/image/undraw/fitness_tracker.svg'/>" alt />
    </div>
  </div>
</section>

<c:if test="${user == null}">
  <c:import url="/include/form/modal/register.jsp" charEncoding="UTF-8" />
</c:if>

<!-- Customize Your Equipment -->
<section class="p-5">
  <div class="container">
    <div class="row align-items-center justify-content-between">
      <div class="col-md">
        <img class="img-fluid" src="<c:url value='/image/undraw/healthy_habit.svg'/>" alt />
      </div>
      <div class="col-md p-5">
        <h2>Use your own Equipment</h2>
        <p class="lead">Have equipment at home? Or at the Gym? Select equipment that is available to you!</p>
        <p>
          XFit allows you to select exercise equipment to match with what you have at home. This helps us filter
          exercises with what is best suited for you.
        </p>
      </div>
    </div>
  </div>
</section>

<!-- Boxes -->
<section class="p-5">
  <div class="container">
    <div class="row row-cols-1 row-cols-md-3 g-4">
      <div class="col">
        <div class="card h-100 bg-dark text-light py-2">
          <div class="card-body text-center">
            <div class="h1">
              <i class="fa fa-laptop" aria-hidden="true"></i>
            </div>
            <h3 class="card-title mb-3">Desktop</h3>
            <p class="card-text">
              At home? XFit provides you service right at your desktop right in the comfort of your home.
            </p>
          </div>
        </div>
      </div>
      <div class="col">
        <div class="card h-100 bg-dark text-light py-2">
          <div class="card-body text-center">
            <div class="h1">
              <i class="fa fa-mobile" aria-hidden="true"></i>
            </div>
            <h3 class="card-title mb-3">Phone</h3>
            <p class="card-text">
              On the go? XFit provides you full mobile accessibility so you can keep track of your routines wherever you
              go!
            </p>
          </div>
        </div>
      </div>
      <div class="col">
        <div class="card h-100 bg-dark text-light py-2">
          <div class="card-body text-center">
            <div class="h1">
              <i class="fa fa-calendar-alt" aria-hidden="true"></i>
            </div>
            <h3 class="card-title mb-3">Schedule</h3>
            <p class="card-text">
              Keep track of your daily workouts with your own customizable weekly routine. Find your exercises for the
              day under Today's workout.
            </p>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>

<!-- Customize Your Routines -->
<!-- #TODO: replace lorem ipsum text -->
<section class="p-5 bg-dark text-light">
  <div class="container">
    <div class="row align-items-center justify-content-between">
      <div class="col-md p-5">
        <h2>Customize your Routines</h2>
        <p class="lead">
          Lorem ipsum dolor sit amet consectetur adipisicing elit. Eaque mollitia esse neque quas cumque, iure amet!
        </p>
        <p>
          Lorem ipsum dolor sit amet consectetur adipisicing elit. Dolorem inventore voluptate quasi ducimus
          perspiciatis deserunt accusantium fugiat labore praesentium, ipsa nulla. Voluptate, molestiae hic rerum
          voluptatem deleniti quos consequatur ea debitis iste.
        </p>
      </div>
      <div class="col-md">
        <img class="img-fluid" src="<c:url value='/image/undraw/healthy_lifestyle.svg'/>" alt />
      </div>
    </div>
  </div>
</section>

<!-- FAQ -->
<!-- #TODO: replace lorem ipsum text -->
<section class="p-5">
  <div class="container">
    <h2 class="text-center mb-4">Frequently Asked Questions</h2>
    <div id="questions" class="accordion accordion-flush">
      <!-- item 1-->
      <div class="accordion-item">
        <h2 class="accordion-header">
          <button
            class="accordion-button collapsed"
            data-bs-target="#question-one"
            data-bs-toggle="collapse"
            type="button"
          >
            Lorem ipsum dolor sit amet consectetur adipisicing elit?
          </button>
        </h2>
        <div id="question-one" class="accordion-collapse collapse" data-bs-parent="#questions">
          <div class="accordion-body">
            Lorem ipsum dolor sit amet consectetur adipisicing elit. Dolores, eum impedit quisquam porro est iusto
            fugiat consectetur autem dolore quos nihil aperiam corrupti commodi illum eligendi doloremque non cupiditate
            consequatur, voluptatem neque ullam eaque unde. Aliquam, eaque hic dignissimos laborum culpa fugiat
            necessitatibus distinctio expedita eveniet mollitia ullam vel voluptatem quo et, illum numquam impedit.
            Atque fugiat facere corporis expedita saepe odio possimus perferendis?
          </div>
        </div>
      </div>

      <!-- item 2 -->
      <div class="accordion-item">
        <h2 class="accordion-header">
          <button
            class="accordion-button collapsed"
            data-bs-target="#question-two"
            data-bs-toggle="collapse"
            type="button"
          >
            Officiis alias aliquid porro commodi cumque nobis eligendi?
          </button>
        </h2>
        <div id="question-two" class="accordion-collapse collapse" data-bs-parent="#questions">
          <div class="accordion-body">
            Lorem ipsum dolor sit amet consectetur adipisicing elit. Inventore, quibusdam blanditiis voluptate
            cupiditate aspernatur excepturi sapiente similique debitis ullam nulla dolorem error ipsa amet sit nihil eos
            maxime, at totam eligendi ea cum doloremque! Quas, quam fugiat! Suscipit odio a maiores praesentium quaerat
            quo minus delectus, at deleniti animi voluptatibus, tenetur itaque laudantium earum iure error possimus,
            harum perferendis culpa sunt unde quia. Modi?
          </div>
        </div>
      </div>

      <!-- item 3 -->
      <div class="accordion-item">
        <h2 class="accordion-header">
          <button
            class="accordion-button collapsed"
            data-bs-target="#question-three"
            data-bs-toggle="collapse"
            type="button"
          >
            Reiciendis odio sint veniam praesentium sequi, modi perferendis?
          </button>
        </h2>
        <div id="question-three" class="accordion-collapse collapse" data-bs-parent="#questions">
          <div class="accordion-body">
            Lorem ipsum dolor, sit amet consectetur adipisicing elit. Doloribus ad a harum ab adipisci quo vero magnam
            necessitatibus aut laborum neque inventore placeat consectetur minus molestias explicabo quia voluptatibus,
            sapiente est exercitationem velit porro fugiat dignissimos? Expedita, molestiae vel debitis iste officia
            error harum voluptatibus facere, quidem sequi, inventore non incidunt voluptatem aliquam deleniti est
            quisquam veniam nemo ea assumenda? Distinctio aliquid deleniti expedita.
          </div>
        </div>
      </div>
    </div>
  </div>
</section>

<!-- Our Team -->
<!-- #TODO: replace lorem ipsum text -->
<section class="p-5 bg-primary">
  <div class="container">
    <h2 class="text-center text-white">Our Team</h2>
    <p class="lead text-center text-white mb-5">
      Lorem ipsum dolor sit amet consectetur adipisicing elit. Amet, aut. Voluptatem doloribus porro non vel culpa!
    </p>
    <div class="row g-4">
      <!-- Reiniel Fernandez -->
      <div class="col-md-6 col-lg-3">
        <div class="card h-100 bg-light">
          <div class="card-body text-center">
            <img class="rounded-circle mb-3" src="https://randomuser.me/api/portraits/men/20.jpg" alt />
            <h3 class="card-title mb-3">Reiniel Fernandez</h3>
            <p class="card-text">
              Lorem ipsum dolor sit amet consectetur adipisicing elit. Magni, distinctio saepe impedit quibusdam cumque
              sapiente odit?
            </p>
            <a href="#"><i class="fa-brands fa-twitter h1" aria-hidden="true"></i></a>
            <a href="#"><i class="fa-brands fa-linkedin h1" aria-hidden="true"></i></a>
            <a href="#"><i class="fa-brands fa-github h1" aria-hidden="true"></i></a>
          </div>
        </div>
      </div>

      <!-- Javier Herdocia -->
      <div class="col-md-6 col-lg-3">
        <div class="card h-100 bg-light">
          <div class="card-body text-center">
            <img class="rounded-circle mb-3" src="https://randomuser.me/api/portraits/men/86.jpg" alt />
            <h3 class="card-title mb-3">Javier Herdocia</h3>
            <p class="card-text">
              Lorem ipsum dolor sit amet consectetur adipisicing elit. Atque totam repellendus, voluptatem ipsa in
              dolores? Cumque!
            </p>
            <a href="#"><i class="fa-brands fa-twitter h1" aria-hidden="true"></i></a>
            <a href="#"><i class="fa-brands fa-linkedin h1" aria-hidden="true"></i></a>
            <a href="#"><i class="fa-brands fa-github h1" aria-hidden="true"></i></a>
          </div>
        </div>
      </div>

      <!-- Mario Orellana -->
      <div class="col-md-6 col-lg-3">
        <div class="card h-100 bg-light">
          <div class="card-body text-center">
            <img class="rounded-circle mb-3" src="https://randomuser.me/api/portraits/men/37.jpg" alt />
            <h3 class="card-title mb-3">Mario Orellana</h3>
            <p class="card-text">
              Lorem ipsum dolor sit amet consectetur adipisicing elit. Delectus, ducimus harum? Distinctio excepturi
              aliquid odio quo.
            </p>
            <a href="#"><i class="fa-brands fa-twitter h1" aria-hidden="true"></i></a>
            <a href="#"><i class="fa-brands fa-linkedin h1" aria-hidden="true"></i></a>
            <a href="#"><i class="fa-brands fa-github h1" aria-hidden="true"></i></a>
          </div>
        </div>
      </div>

      <!-- Gustavo Zapata -->
      <div class="col-md-6 col-lg-3">
        <div class="card h-100 bg-light">
          <div class="card-body text-center">
            <img class="rounded-circle mb-3" src="https://randomuser.me/api/portraits/men/35.jpg" alt />
            <h3 class="card-title mb-3">Gustavo Zapata</h3>
            <p class="card-text">
              Lorem ipsum dolor sit amet consectetur adipisicing elit. Magni, ex atque tempore laborum officia
              reprehenderit ipsa!
            </p>
            <a href="#"><i class="fa-brands fa-twitter h1" aria-hidden="true"></i></a>
            <a href="#"><i class="fa-brands fa-linkedin h1" aria-hidden="true"></i></a>
            <a href="#"><i class="fa-brands fa-github h1" aria-hidden="true"></i></a>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>

<!--Contact-->
<!-- #TODO: replace lorem ipsum text -->
<section class="p-5">
  <div class="container">
    <div class="row g-4">
      <div class="col-md">
        <h2 class="text-center mb-4">Contact Info</h2>
        <ul class="list-group lsit-group-flush lead">
          <li class="list-group-item">
            <i class="fa fa-map-marker me-1" aria-hidden="true"></i>
            50 Main St, Miami FL
          </li>
          <li class="list-group-item">
            <i class="fa fa-phone me-1" aria-hidden="true"></i>
            (305) 555-5555
          </li>
          <li class="list-group-item">
            <i class="fa fa-envelope me-1" aria-hidden="true"></i>
            email@example.com
          </li>
        </ul>
      </div>
      <div class="col-md h-auto bg-warning text-center p-5 rounded"></div>
    </div>
  </div>
</section>
