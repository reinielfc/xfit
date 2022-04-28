<!-- prettier-ignore -->
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set scope="request" var="_title" value='<span class="text-danger"><i class="fa fa-magnifying-glass"></i>?</span> 404 Error' />
<c:set scope="request" var="_content" value="/include/error/404.jsp" />

<c:import url="/template/two_columns.jsp" charEncoding="UTF-8" />
