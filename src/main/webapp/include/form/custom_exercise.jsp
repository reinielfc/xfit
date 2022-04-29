<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 
==== CUSTOM EXERCISE FORM ==============================================================================================
-->

<input type="hidden" class="custom-exercise-name" name="name">

<c:if test="${!(message == null || message.blank)}">
  <c:set var="validationClass" value=" is-invalid" />
  <p class="small mb-3">${message}</p>
</c:if>

<div class="form-group mb-3">
  <label class="form-label" for="custom-exercise-title">Title</label>
  <input  class="custom-exercise-title form-control" name="title" type="text" placeholder="My Custom Exercise" autocomplete="off">
</div>

<div class="form-group mb-3">
  <label class="form-label" for="custom-exercise-description">Description</label>
  <textarea class="custom-exercise-description form-control" name="description" placeholder="Describe your exercise here..." rows="4" autocomplete="off"></textarea>
</div>

<!-- 
==== END CUSTOM EXERCISE FORM ==========================================================================================
-->