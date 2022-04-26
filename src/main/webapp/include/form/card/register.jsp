<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set scope="request" var="__cardTitle"      value="Register"                   />

<c:set scope="request" var="__cardForm"       value="/include/form/register.jsp" />
<c:set scope="request" var="__cardFormId"     value="register-form"              />
<c:set scope="request" var="__cardFormAction" value="/register"                  />
<c:set scope="request" var="__cardFormSubmit" value="Register"                   />

<c:import url="/include/form/card/template.jsp" charEncoding="UTF-8" />
