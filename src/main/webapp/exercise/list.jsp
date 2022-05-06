<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set scope="request" var="_title"   value="Exercises"                           />
<c:set scope="request" var="_content" value="/include/exercise/list/template.jsp" />

<c:choose>
  <c:when test="${user == null}">
    <c:set scope="request" var="__item"     value="/include/exercise/list/_item.jsp"              />

    <c:set scope="request" var="_aside"     value="/include/form/container/register.jsp"          />
    <c:set scope="request" var="__subtitle" value="Register to add an exercise to your routine."  />
  </c:when>
  <c:otherwise>
    <c:set scope="request" var="__filters"    value="/include/exercise/user/list/filters_with_save_button.jsp"      />
    <c:set scope="request" var="__item"       value="/include/exercise/user/list/item/template.jsp" />
    <c:set scope="request" var="__itemAside"  value="/include/exercise/user/list/item/_buttons.jsp" />
    
    <c:set scope="request" var="__formSubmitAction" value="create" />

    <c:set scope="request" var="_aside"     value="/include/form/container/custom_exercise.jsp"     />
  </c:otherwise>
</c:choose>

<c:set scope="request" var="__image" value="/image/undraw/fitness_stats.svg" />

<c:import url="/template/two_columns.jsp" charEncoding="UTF-8" />
