<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- params:
__exerciseId          : exercise id
__exerciseName        : exercise name
__exerciseIsFavorite  : if exercise is in the user's favorites
__exerciseIsCustom    : if exercise was made by the user
--%>

<c:set var="__favoriteCheck" value="${__exerciseIsFavorite ? 'checked' : ''}" />

<c:if test="${__exerciseIsCustom}">
  <div class="d-flex align-items-center px-2">
    <button class="custom-exercise-edit-btn btn btn-outline-dark m-1 text-nowrap" type="button" data-xf-parent="#${__exerciseId}" data-bs-toggle="modal" data-bs-target="#custom-exercise-edit-form-modal">
      <i class="fa fa-pen-to-square"></i>
      <span class="d-none d-md-inline">
        Edit
      </span>
    </button>
  </div>
</c:if>


<div class="d-flex align-items-center px-2">
  <input class="input-hidden" type="hidden" data-xf-name="exercise[${__exerciseName}][isFavorite]" data-xf-value="${__exerciseIsFavorite}">
  <input id="${__exerciseId}-is-favorite-check" class="exercise-is-favorite-check btn-check" type="checkbox" data-xf-parent="#${__exerciseId}" autocomplete="off" ${__favoriteCheck}/>
  <label for="${__exerciseId}-is-favorite-check" class="btn btn-outline-danger text-nowrap">
    <i class="fa fa-heart"></i>
    <span class="d-none d-md-inline">
      Favorite
    </span>
  </label>
</div>
