<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set scope="request" var="__title"      value="Register"                   />

<c:set scope="request" var="__form"       value="/include/form/register.jsp" />
<c:set scope="request" var="__formId"     value="register-form"              />
<c:set scope="request" var="__formAction" value="/register"                  />
<c:set scope="request" var="__formSubmit" value="Register"                   />

<c:import url="/include/form/container/template.jsp" charEncoding="UTF-8" />
