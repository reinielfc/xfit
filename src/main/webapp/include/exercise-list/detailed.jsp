<%@page contentType="text/html" pageEncoding="UTF-8" %> <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" session="true" %>

<!-- exercise list detailed -->
<ul id="exercise-list" class="list-group list-group-flush">
  <c:forEach items="${exerciseList}" var="exercise">
    <li class="exercise list-group-item w-100">
      <div class="d-flex">
        <div class="me-auto">
          <a
            href='<c:url value="/exercise?name=${exercise.name}" />'
            class="exercise-title h5 fw-bold text-decoration-none link-dark link-hover-primary"
            >${exercise.title}</a
          >
          <div class="d-block">
            <span class="exercise-type badge rounded-pill bg-success" data-field-value="${exercise.type}"
              >&#x1F3AF; ${exercise.type}</span
            >
            <c:forEach items="${exercise.primaryMuscles}" var="muscle">
              <span
                class="exercise-muscle exercise-primary badge rounded-pill bg-danger"
                data-field-value="${muscle.name}"
                >&#x1F4AA; ${muscle.name}</span
              >
            </c:forEach>

            <c:forEach items="${exercise.secondaryMuscles}" var="muscle">
              <span
                class="exercise-muscle exercise-secondary badge rounded-pill bg-warning text-dark"
                data-field-value="${muscle.name}"
                >${muscle.name}</span
              >
            </c:forEach>
            <c:forEach items="${exercise.equipment}" var="equipment">
              <span class="exercise-equipment badge rounded-pill bg-primary" data-field-value="${equipment.name}"
                ><i class="fas fa-dumbbell"></i> ${equipment.name}</span
              >
            </c:forEach>
          </div>
          <p class="my-1">${exercise.primer}</p>
        </div>
        <div class="d-flex align-items-center">
          <div class="m-1">
            <input class="btn-check" type="checkbox" value="exercise-is-favorite" autocomplete="off" />
            <label class="btn btn-outline-danger hidden-button" for="exercise-list-favorite-filter">
              <i class="fa fa-heart"></i>
            </label>
          </div>
        </div>
      </div>
    </li>
  </c:forEach>
</ul>

