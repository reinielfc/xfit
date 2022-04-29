<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set scope="request" var="__modalTitle"      value="Register"                   />
<c:set scope="request" var="__modalId"         value="register-form-modal"        />

<c:set scope="request" var="__modalForm"       value="/include/form/register.jsp" />
<c:set scope="request" var="__modalFormId"     value="register-form"              />
<c:set scope="request" var="__modalFormAction" value="/register"                  />
<c:set scope="request" var="__modalFormSubmit" value="Register"                   />

<c:import url="/include/form/modal/template.jsp" charEncoding="UTF-8" />
