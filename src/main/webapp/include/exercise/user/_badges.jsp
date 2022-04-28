<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- params:
__exercise            : exercise to get the badges from
__exerciseIsFavorite  : if exercise is in the user's favorites
__exerciseIsCustom    : if exercise was made by the user
--%>

<!-- is favorite -->
<span class="exercise-is-favorite badge bg-danger fade ${__exerciseIsFavorite ? 'show' : 'hide d-none'}" data-xf-value="${__exerciseIsFavorite}">
  <i class="fa fa-heart"></i> favorite
</span>

<c:choose>
  <c:when test="${__exerciseIsCustom}">
    <!-- is custom -->
    <span class="exercise-is-custom badge bg-warning text-dark" data-xf-value="true">
      <i class="fa fa-pen"></i> custom
    </span>
  </c:when>
  <c:otherwise>
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
      <c:set var="equipmentIsOwned" value="${equipment.isOwnedBy(user)}"/>
      <span class="exercise-equipment badge rounded-pill bg-primary" data-xf-value="${equipment.name}">
        <i class="fas fa-dumbbell"></i>
        ${equipment.name}
        <c:if test="${equipmentIsOwned}">
          <span class="user-owns-equipment" data-xf-value="true">
            <i class="fa fa-check"></i>
          </span>
        </c:if>
      </span>
    </c:forEach>
  </c:otherwise>
</c:choose>