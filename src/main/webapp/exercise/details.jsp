<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set scope="request" var="_title"   value="${exercise.title}"               />
<c:set scope="request" var="_content" value="/include/exercise/template.jsp"  />

<c:set scope="request" var="__exercise" value="${exercise}" />

<c:choose>
  <c:when test="${user == null}">
    <c:set scope="request" var="__badges"       value="/include/exercise/_badges.jsp" />
    <c:set scope="request" var="_aside"         value="/include/form/card/register.jsp" />
    <c:set scope="request" var="__cardSubtitle" value='Register to add "${_title}" to your routine.' />
  </c:when>
  <c:otherwise>
    <c:set scope="request" var="__badges" value="/include/exercise/user/_badges.jsp" />
    <c:set scope="request" var="_aside"   value="/include/form/card/add_exercise_to_routine.jsp" />
  </c:otherwise>
</c:choose>

<c:set scope="request" var="__cardImage" value="/image/undraw/personal_trainer.svg" />

<c:import url="/template/two_columns.jsp" charEncoding="UTF-8" />
