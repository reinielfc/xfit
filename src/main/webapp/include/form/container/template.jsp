<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- params:
__title       : to be displayed in header of form
__form        : url of jsp/html content of the form that will be displayed
__formId      : id of the form
__formAction  : url of servlet that will handle the form

---- defaults
__formSubmit = 'Submit'
__formCancel = 'Cancel'

---- optional
__subtitle           :  subtitle of card
__image              :  image to decorate the form with
__formSubmitAction   :  form action parameter value
--%>

<c:set var="__formSubmit" value="${__formSubmit == null ? 'Submit' : __formSubmit}" />
<c:set var="__formCancel" value="${__formCancel == null ? 'Cancel' : __formCancel}" />

<c:if test="${__formSubmitAction != null}">
  <c:set var="__formSubmitAction" value=' name="action" value="${__formSubmitAction}"' />
</c:if>

<section class="container-fluid">
  <header class="mb-4">
    <h3>${__title}</h3>
    <em>${__subtitle}</em>
    <hr class="my-2" />
  </header>
  
  <section class="container mb-3">
    <c:if test="${__image != null}">
      <img src="<c:url value='${__image}' />" class="img-fluid mb-3" >
    </c:if>
    
    <form id="${__formId}" action="<c:url value='${__formAction}' />" method="POST">
      <c:import url="${__form}" charEncoding="UTF-8" />
    </form>
  </section>
  
  <footer class="d-flex">
    <button class="btn btn-outline-primary ms-auto" type="submit"${__formSubmitAction} form="${__formId}">
      ${__formSubmit}
    </button>
  </footer>
</section>
