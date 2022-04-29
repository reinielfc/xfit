<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- params:
_title      : to be displayed in header of card as well as title of tab
_content    : url of jsp/html content of the template, in this case the form that will be displayed
_formAction : url of servlet that will handle the form

---- defaults:
_formId     = 'centered-card-form'
_formSubmit = 'Submit'
_formCancel = 'Cancel'

---- optional:
_formSubmitAction : form action parameter value
--%>

<c:set var="_formId"     value="${_formId     == null ? 'centered-card-form' : _formId}"     />
<c:set var="_formSubmit" value="${_formSubmit == null ? 'Submit'             : _formSubmit}" />
<c:set var="_formCancel" value="${_formCancel == null ? 'Cancel'             : _formCancel}" />

<c:if test="${_formSubmitAction != null}">
  <c:set var="_formSubmitAction" value=' name="action" value="${_formSubmitAction}"' />
</c:if>

<!DOCTYPE html>
<html lang="en">
  <c:import url="/include/_head.jsp" charEncoding="UTF-8" />

  <!-- #### ${_title} #### -->
  <body class="d-flex flex-column bg-dark overflow-hidden"> <!-- TODO: classes taken out: flex-column min-vh-100  -->
    <section class="my-auto">
      <div class="row justify-content-center p-1 my-5">
        <img src="<c:url value='/image/logo.svg' />" class="p-0" alt="XFit Logo" width="128" height="128" />
      </div>

      <div class="row justify-content-center">
        <div class="card card-md p-0">
          <header class="card-header bg-white">
            <h4>${_title}</h4>
          </header>

          <div class="card-body">
            <form action="<c:url value='${_formAction}' />" method="POST" id="${_formId}">
              <c:import url="${_content}" charEncoding="UTF-8" />
            </form>
          </div>

          <div class="card-footer bg-white d-flex align-items-end">
            <a href="<c:url value='/' />" class="btn btn-secondary ms-auto m-1" type="button">
              ${_formCancel}
            </a>

            <button class="btn btn-primary m-1" type="submit"${_formSubmitAction} form="${_formId}">
              ${_formSubmit}
            </button>
          </div>
        </div>
      </div>

      <div class="row text-center my-5">
        <p class="small text-muted p-0">Copyright &copy; 2022 Group3</p>
      </div>
    </section>

    <c:import url="/include/foot.html" charEncoding="UTF-8" />
  </body>
</html>

