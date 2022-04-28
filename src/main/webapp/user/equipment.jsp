<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set scope="request" var="_title"      value="Select your equipment" />
<c:set scope="request" var="_content"    value="/include/form/equipment.jsp" />

<c:set scope="request" var="_formId"     value="equipment-form" />
<c:set scope="request" var="_formAction" value="/settings" />
<c:set scope="request" var="_formSubmit" value='<i class="fa fa-floppy-disk"></i> Save Equipment' />

<c:import url="/template/centered_card_form.jsp" charEncoding="UTF-8" />