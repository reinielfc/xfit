<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set scope="request" var="__cardTitle"      value="Add to routine"                             />

<c:set scope="request" var="__cardForm"       value="/include/form/add_exercise_to_routine.jsp"  />
<c:set scope="request" var="__cardFormId"     value="add-to-routine"                             />
<c:set scope="request" var="__cardFormAction" value="/planner"                                   />
<c:set scope="request" var="__cardFormSubmit" value='<i class="fa fa-plus"></i> Add'             />

<c:import url="/include/form/card/template.jsp" charEncoding="UTF-8" />
