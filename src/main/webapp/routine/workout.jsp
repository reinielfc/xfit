<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set scope="request" var="_title" value="Today's Workout" />
<c:set scope="request" var="_content" value="/include/routine/workout/day.jsp" />
<c:set scope="request" var="_aside" value="/include/routine/workout/description.jsp" />

<c:import url="/template/two_columns.jsp" charEncoding="UTF-8" />