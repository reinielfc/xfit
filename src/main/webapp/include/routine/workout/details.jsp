<!-- prettier-ignore -->
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- prettier-ignore -->
<%-- controller:
planList  : list of plans
--%>

<c:set scope="request" var="__badges" value="/include/exercise/user/_badges.jsp" />

<div id="exercise-details" class="tab-content">
  <c:forEach items="${planList}" var="plan">
    <c:set scope="request" var="__exercise" value="${plan.exerciseByExerciseId}" />
    <c:set scope="request" var="__exerciseIsFavorite" value="${__exercise.isFavoritedBy(user)}" />
    <c:set scope="request" var="__exerciseIsCustom" value="${__exercise.userByUserId != null}" />

    <div class="tab-pane fade" id="exercise-details-${plan.position}-${__exercise.name}" role="tabpanel">
      <c:choose>
        <c:when test="${__exerciseIsCustom}">
          <h2 class="exercise-title fw-bold">${__exercise.title}</h2>
        </c:when>
        <c:otherwise>
          <a href="<c:url value='/exercise?name=${__exerciseName}' />" class="exercise-title h2 text-decoration-none link-dark link-hover-primary">${__exercise.title}</a>
        </c:otherwise>
      </c:choose>

      <c:import url="/include/exercise/template.jsp" charEncoding="UTF-8" />
    </div>
  </c:forEach>
</div>

