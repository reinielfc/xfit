<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set scope="request" var="__modalTitle" value='<i class="fa fa-pen-to-square"></i> Edit exercise' />
<c:set scope="request" var="__modalId"    value="custom-exercise-edit-form-modal"                   />

<c:set scope="request" var="__modalForm"       value="/include/form/custom_exercise.jsp"  />
<c:set scope="request" var="__modalFormId"     value="custom-exercise-edit-form"          />
<c:set scope="request" var="__modalFormAction" value="/exercise"                          />

<c:set scope="request" var="__modalFormSubmit" value='<i class="fa fa-floppy-disk"></i> Save' />

<c:set scope="request" var="__modalFormDanger"        value='<i class="fa fa-trash-can"></i> Delete'              />
<c:set scope="request" var="__modalFormDangerAction"  value="delete"                                              />
<c:set scope="request" var="__modalFormDangerMessage" value="Are you sure that you want to delete this exercise?" />

<c:import url="/include/form/modal/template.jsp" charEncoding="UTF-8" />
