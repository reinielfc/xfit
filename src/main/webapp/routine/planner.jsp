<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set scope="request" var="_title"           value="Planner"                           />
<c:set scope="request" var="_content"         value="/include/routine/planner/card.jsp" />
<c:set scope="request" var="_mainColumnWidth" value="7"                                 />

<c:set scope="request" var="_aside"     value="/include/exercise/list/template.jsp" />
<c:set scope="request" var="__item"     value="/include/exercise/user/list/item/template.jsp"    />
<c:set scope="request" var="__itemAside"  value="/include/exercise/user/list/item/_buttons.jsp" />
<c:set scope="request" var="__filters"  value="/include/exercise/user/list/filters.html" />

<c:import url="/template/two_columns.jsp" charEncoding="UTF-8" />
