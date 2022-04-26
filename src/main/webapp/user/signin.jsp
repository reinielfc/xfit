<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set scope="request" var="_title"      value="Sign In" />
<c:set scope="request" var="_content"    value="/include/form/signin.jsp" />

<c:set scope="request" var="_formId"     value="signin-form" />
<c:set scope="request" var="_formAction" value="/signin" />
<c:set scope="request" var="_formSubmit" value="Sign In" />

<c:import url="/template/centered_card_form.jsp" charEncoding="UTF-8" />
