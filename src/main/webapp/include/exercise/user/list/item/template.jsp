<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- params:
__exercise      : exercise to display
__exerciseName  : used in title link
__itemAside     : url of content to display on the right side of list item

---- optional:
__exerciseId    : 
__itemAdd       : additional content to display in list item
--%>

<c:set scope="request" var="__exerciseIsFavorite" value="${user.hasExerciseAsFavorite(__exercise)}" />
<c:set scope="request" var="__exerciseIsCustom"   value="${__exercise.userByUserId != null}" />

<div class="d-flex w-100">
  <div class="flex-fill">
    <div class="d-block">
      <c:choose>
        <c:when test="${__exerciseIsCustom}">
          <h5 class="exercise-title fw-bold">${__exercise.title}</h5>
        </c:when>
        <c:otherwise>
          <a href="<c:url value='/exercise?name=${__exerciseName}' />" class="exercise-title h5 fw-bold text-decoration-none link-dark link-hover-primary">${__exercise.title}</a>
        </c:otherwise>
      </c:choose>

      <div class="exercise-badges d-block my-2">
        <c:import url="/include/exercise/user/_badges.jsp" charEncoding="UTF-8" />
      </div>

      <p class="exercise-primer my-1">${__exercise.primer}</p>
    </div>

    <c:if test="${__itemAdd != null}">
      <c:import url="${__itemAdd}" charEncoding="UTF-8" />
    </c:if>
  </div>

  <c:import url="${__itemAside}" charEncoding="UTF-8" />
</div>
