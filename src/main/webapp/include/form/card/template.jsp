<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- params:
__cardTitle       : to be displayed in header of card
__cardForm        : url of jsp/html content of the form that will be displayed
__cardFormId      : id of the form
__cardFormAction  : url of servlet that will handle the form

---- defaults
__cardFormSubmit = 'Submit'
__cardFormCancel = 'Cancel'

---- optional
__cardSubtitle           :  subtitle of card
__cardImage              :  image to decorate the card with
__cardFormSubmitAction   :  form action parameter value
--%>

<c:set var="__cardFormSubmit" value="${__cardFormSubmit == null ? 'Submit' : __cardFormSubmit}" />
<c:set var="__cardFormCancel" value="${__cardFormCancel == null ? 'Cancel' : __cardFormCancel}" />

<c:if test="${__cardFormSubmitAction != null}">
  <c:set var="__cardFormSubmitAction" value=' name="action" value="${__cardFormSubmitAction}"' />
</c:if>

<div class="card">
  <div class="card-header">
    <h3>${__cardTitle}</h3>
    <c:if test="${__cardSubtitle != null}">
      <em>${__cardSubtitle}</em>
    </c:if>
  </div>

  <div class="card-body">
    <c:if test="${__cardImage != null}">
      <img src="<c:url value='${__cardImage}' />" class="img-fluid my-1" >
    </c:if>
    
    <form id="${__cardFormId}" action="${__cardFormAction}" method="POST">
      <c:import url="${__cardForm}" charEncoding="UTF-8" />
    </form>
  </div>

  <div class="card-footer d-flex">
    <button class="btn btn-outline-primary ms-auto" type="submit"${__cardFormSubmitAction} form="${__cardFormId}">
      ${__cardFormSubmit}
    </button>
  </div>
</div>
