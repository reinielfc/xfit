<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set scope="request" var="_title"      value="Register" />
<c:set scope="request" var="_content"    value="/include/form/register.jsp" />

<c:set scope="request" var="_formId"     value="register-form" />
<c:set scope="request" var="_formAction" value="/register" />
<c:set scope="request" var="_formSubmit" value="Register" />

<c:import url="/template/centered_card_form.jsp" charEncoding="UTF-8" />
