<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- controller:
planList  : list of plans
--%>

<ul class="list-group">
  <c:forEach items="${planList}" var="plan">
    <c:set var="exercise" value="${plan.exercise}" />

    <li class="list-group-item w-100">
      <div class="d-flex w-100">
        <div class="flex-fill">
          <div class="d-block">
            <button class="btn btn-link h5 fw-bold text-decoration-none link-dark link-hover-primary" data-bs-toggle="collapse" data-bs-target="#exercise-details-${exercise.name}">
              ${exercise.title}
            </button>

            <p class="my-1">${exercise.primer}</p>
          </div>
          <div class="d-block">
            <p></p>
          </div>
          
        </div>
      </div>
    </li>
  </c:forEach>
</ul>