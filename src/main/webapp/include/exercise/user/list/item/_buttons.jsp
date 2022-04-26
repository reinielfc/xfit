<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- params:
__exerciseId          :
__exerciseName        :
__exerciseIsFavorite  : if exercise is in the user's favorites
__exerciseIsCustom    : if exercise was made by the user
--%>

<c:set var="__exerciseIsFavorite" value="${__exerciseIsFavorite ? 'checked' : ''}" />

<c:if test="${__exerciseIsCustom}">
  <div class="d-flex align-items-center px-2">
    <button class="custom-exercise-edit-btn btn btn-outline-dark m-1 text-nowrap" type="button" data-xf-target="#${__exerciseId}" data-bs-toggle="modal" data-bs-target="#custom-exercise-edit-form-modal">
      <i class="fa fa-pen-to-square"></i>
      <span class="d-none d-md-inline">
        Edit
      </span>
    </button>
  </div>
</c:if>


<div class="d-flex align-items-center px-2">
  <input id="exercise-${__exerciseId}-is-favorite-btn" class="exercise-is-favorite-btn btn-check" type="checkbox" data-xf-target="#${__exerciseId}" name="exercise[${__exerciseName}][isFavorite]" autocomplete="off" ${__exerciseIsFavorite}/>
  <label for="exercise-${__exerciseId}-is-favorite-btn" class="btn btn-outline-danger text-nowrap">
    <i class="fa fa-heart"></i>
    <span class="d-none d-md-inline">
      Favorite
    </span>
  </label>
</div>
