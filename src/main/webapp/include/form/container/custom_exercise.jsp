<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set scope="request" var="__title"      value="Create a custom exercise"                           />
<c:set scope="request" var="__subtitle"   value="Don't see an exercise in the list? Create it here." />

<c:set scope="request" var="__form"       value="/include/form/custom_exercise.jsp"   />
<c:set scope="request" var="__formId"     value="custom-exercise-form"                />
<c:set scope="request" var="__formAction" value="/exercise"                           />
<c:set scope="request" var="__formSubmit" value='<i class="fa fa-check"></i> Create'  />

<c:import url="/include/form/container/template.jsp" charEncoding="UTF-8" />

<c:import url="/include/form/modal/edit_custom_exercise.jsp" charEncoding="UTF-8" />
