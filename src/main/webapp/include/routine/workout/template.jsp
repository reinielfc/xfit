<!-- prettier-ignore -->
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- prettier-ignore -->
<%-- controller:
planList  : list of plans
--%>

<script src="<c:url value='/script/routine/workout.js' />"></script>

<div class="card">
  <div class="card-body max-vh-100 bg-light p-0 my-2">
    <ol class="list-group list-group-flush list-group-numbered overflow-auto">
      <c:forEach items="${planList}" var="plan">
        <c:set var="exercise" value="${plan.exerciseByExerciseId}" />

        <li class="list-group-item d-flex w-100">
          <div class="ms-2 flex-fill d-flex">
            <div class="flex-fill py-0 p-2">
              <div class="d-block">
                <div class="d-flex">
                  <!-- prettier-ignore -->
                  <a href="#exercise-details-${plan.position}-${exercise.name}" class="tab-link h5 fw-bold text-decoration-none link-dark link-hover-primary mb-2" data-bs-toggle="tab">
                    ${exercise.title}
                  </a>
                </div>

                <div class="d-flex text-muted me-auto mb-1">
                  <div class="m-1">
                    <i class="fa fa-bars"></i>
                    &times;
                    <span class="sets-count">${plan.sets}</span>
                  </div>

                  <div class="m-1">
                    <i class="fa fa-arrow-rotate-right"></i>
                    &times;
                    <span class="reps-count">${plan.reps}</span>
                  </div>

                  <div class="m-1">
                    <i class="fa fa-weight-hanging"></i>
                    <span class="weight-amount">${plan.sets}</span> lbs
                  </div>
                </div>

                <p class="m-0">${exercise.primer}</p>
              </div>
            </div>
            <div class="d-flex align-items-center px-2">
              <!-- prettier-ignore -->
              <input id="plan-${exercise.name}" class="btn-check" type="checkbox" data-xf-target="" name="" autocomplete="off" />
              <label for="plan-${exercise.name}" class="btn btn-outline-primary text-nowrap">
                <i class="fa fa-check"></i>
                <span class="d-none d-md-inline"> Done </span>
              </label>
            </div>
          </div>
        </li>
      </c:forEach>
    </ol>
  </div>
  <div class="card-footer d-flex">
    <button class="btn btn-outline-primary ms-auto" type="submit" form="TODO">
      <i class="fa fa-bottle-water"></i>
      Done for the day!
    </button>
  </div>
</div>

