<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- params:
__modalTitle      :   to be displayed in header of modal
__modalId         :   id of modal to toggle it by
__modalForm       :   url of jsp/html content of the form that will be displayed
__modalFormId     :   id of the form
__modalFormAction :   url of servlet that will handle the form

---- defaults
__modalFormSubmit = 'Submit'
__modalFormCancel = 'Cancel'

---- optional
__modalFormSubmitAction   :   form action parameter value
__modalFormDanger         :   form danger button
__modalFormDangerAction   :   form danger button action parameter value
__modalFormDangerMessage  :   form danger button message
--%>

<c:set var="__modalFormSubmit" value="${__modalFormSubmit == null ? 'Submit' : __modalFormSubmit}" />
<c:set var="__modalFormCancel" value="${__modalFormCancel == null ? 'Cancel' : __modalFormCancel}" />

<c:if test="${__modalFormSubmitAction != null}">
  <c:set var="__modalFormSubmitAction" value=' name="action" value="${__modalFormSubmitAction}"' />
</c:if>

<div id="${__modalId}" class="modal" role="dialog" tabindex="-1">
  <div class="modal-dialog modal-fullscreen-md-down" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">${__modalTitle}</h4>
      </div>
      <div class="modal-body">
        <form id="${__modalFormId}" action="<c:url value='${__modalFormAction}' />" method="POST">
          <c:import url="${__modalForm}" charEncoding="UTF-8" />
        </form>
      </div>
      <div class="modal-footer">
        <c:if test="${__modalFormDanger != null}">
          <c:if test="${__modalFormDangerAction != null}">
            <c:set var="__modalFormDangerAction" value=' name="action" value="${__modalFormDangerAction}"' />
          </c:if>

          <button class="btn btn-outline-danger me-auto" type="submit"${__modalFormDangerAction} class="danger" form="${__modalFormId}" data-danger-message="${__modalFormDangerMessage}">
            ${__modalFormDanger}
          </button>
        </c:if>
         
        <button class="btn btn-secondary" data-bs-dismiss="modal" form="${__modalFormId}" type="reset">
          ${__modalFormCancel}
        </button>
        <button class="btn btn-primary" type="submit"${__modalFormSubmitAction} form="${__modalFormId}">
          ${__modalFormSubmit}
        </button>
      </div>
    </div>
  </div>
</div>
