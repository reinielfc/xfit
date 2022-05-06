<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set scope="request" var="_title"            value="Verify Your Email" />
<c:set scope="request" var="_content"          value="/include/form/verify.jsp" />

<c:set scope="request" var="_formId"           value="verify-email-form" />
<c:set scope="request" var="_formAction"       value="/register" />
<c:set scope="request" var="_formSubmitAction" value="verify" />

<c:import url="/template/centered_card_form.jsp" charEncoding="UTF-8" />
