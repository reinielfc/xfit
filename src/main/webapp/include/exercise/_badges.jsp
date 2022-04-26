<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- params:
__exercise  : exercise to display the badges of
--%>

<!-- type -->
<span class="exercise-type badge rounded-pill bg-success" data-xf-value="${__exercise.type}">&#x1F3AF; ${__exercise.type}</span>

<!-- primary muscles -->
<c:forEach items="${__exercise.primaryMuscles}" var="muscle">
  <span class="exercise-muscle exercise-muscle-primary badge rounded-pill bg-danger" data-xf-value="${muscle.name}">&#x1F4AA; ${muscle.name}</span>
</c:forEach>

<!-- secondary muscles -->
<c:forEach items="${__exercise.secondaryMuscles}" var="muscle">
  <span class="exercise-muscle exercise-muscle-secondary badge rounded-pill bg-warning text-dark" data-xf-value="${muscle.name}">${muscle.name}</span>
</c:forEach>

<!-- equipment -->
<c:forEach items="${__exercise.equipment}" var="equipment">
  <span class="exercise-equipment badge rounded-pill bg-primary" data-xf-value="${equipment.name}">
    <i class="fas fa-dumbbell"></i>
    ${equipment.name}
  </span>
</c:forEach>
