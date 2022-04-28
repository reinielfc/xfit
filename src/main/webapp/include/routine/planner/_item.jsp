<!-- prettier-ignore -->
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- prettier-ignore -->
<%-- params:
__day   : day of week
__plan  : plan
--%>

<c:set var="planId" value="plan-${__day}-${__plan.position}" />

<li id="${planId}" class="plan list-group-item d-flex w-100 pe-0">
  <div class="ms-2 flex-fill">

    <div class="d-flex w-100">
      <div class="flex-fill">
        <div class="d-block">
          <c:set scope="request" var="__exercise" value="${__plan.exerciseByExerciseId}" />

          <!-- prettier-ignore -->
          <a href="<c:url value='/exercise?name=${__exercise.name}' />" class="exercise-title h5 fw-bold text-decoration-none link-dark link-hover-primary">${__exercise.title}</a>

          <!-- badges -->
          <div class="d-block my-2">
            <c:import url="/include/exercise/user/_badges.jsp" charEncoding="UTF-8" />
          </div>

          <p class="my-1">${__exercise.primer}</p>
        </div>

        <div class="d-flex small text-muted me-auto">
          <div class="m-1">
            <i class="fa fa-bars"></i>
            &times;
            <span class="sets-count">${__plan.sets}</span>
          </div>

          <div class="m-1">
            <i class="fa fa-arrow-rotate-right"></i>
            &times;
            <span class="reps-count">${__plan.reps}</span>
          </div>

          <div class="m-1">
            <i class="fa fa-weight-hanging"></i>
            <span class="weight-amount">${__plan.sets}</span> lbs
          </div>
        </div>
      </div>

      <div class="d-flex align-items-center p-1">
        <!-- prettier-ignore -->
        <button class="btn btn-outline-dark m-1 text-nowrap" type="button" data-xf-collapse-group=".plan-form" data-bs-toggle="collapse" data-bs-target="#${planId}-form">
            <i class="fa fa-pen-to-square"></i>
            Edit
        </button>
      </div>

      <div class="sortable-item-handle d-flex align-items-center p-3">
        <i class="fa fa-grip-lines-vertical"></i>
      </div>
    </div>

    <div id="${planId}-form" class="plan-form collapse">
      <div class="p-1 me-2">
        <div class="d-flex flex-row">
          <div class="ms-0">
            <button class="btn btn-outline-danger m-1" type="button">
              <i class="fa fa-trash-can"></i>
            </button>
          </div>

          <%-- plan mapping format: plan[day][position][parameterName] --%>
          <c:set var="planMapping" value="plan[${__day}][${__plan.position}]" />

          <input type="hidden" name="${planMapping}[action]" value="" />
          <input type="hidden" name="${planMapping}[exerciseName]" value="${__exercise.name}" />
          <input type="hidden" name="${planMapping}[position]" value="${__plan.position}" />

          <div class="input-group mx-1">
            <span class="input-group-text px-2"><i class="fa fa-bars"></i></span>
            <!-- prettier-ignore -->
            <input class="form-control" type="number" name="${planMapping}[sets]" value="${__plan.sets}" data-xf-bind="#${planId} .sets-count" placeholder="5" min="0" max="512" />
            <span class="input-group-text px-2">sets</span>
          </div>

          <div class="input-group mx-1">
            <span class="input-group-text px-2"><i class="fa fa-arrow-rotate-right"></i></span>
            <!-- prettier-ignore -->
            <input class="form-control" type="number" name="${planMapping}[reps]" value="${__plan.reps}" data-xf-bind="#${planId} .reps-count" placeholder="10" min="0" max="512" />
            <span class="input-group-text px-2">reps</span>
          </div>

          <div class="input-group mx-1">
            <span class="input-group-text px-2"><i class="fa fa-weight-hanging"></i></span>
            <!-- prettier-ignore -->
            <input class="form-control" type="number" name="${planMapping}[weight]" value="${__plan.weight}" data-xf-bind="#${planId} .weight-amount" placeholder="25" min="0" max="4096" />
            <span class="input-group-text px-2">lbs</span>
          </div>
        </div>
      </div>
    </div>
        
  </div>
</li>
