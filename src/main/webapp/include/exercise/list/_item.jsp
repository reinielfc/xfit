<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- params:
__exercise  : exercise to display
--%>

<div class="d-flex w-100">
  <div class="flex-fill">
    <div class="d-block">
      <!-- prettier-ignore -->
      <a href="<c:url value='/exercise?name=${__exercise.name}' />" class="exercise-title h5 fw-bold text-decoration-none link-dark link-hover-primary">${__exercise.title}</a>

      <!-- badges -->
      <div class="d-block my-2">
        <c:import url="/include/exercise/_badges.jsp" charEncoding="UTF-8" />
      </div>

      <p class="my-1">${__exercise.primer}</p>
    </div>
  </div>
</div>
