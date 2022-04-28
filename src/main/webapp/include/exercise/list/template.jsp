<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- params:
__item            : url of exercise list item template to use

---- optional:
__filters         : url of a additional filter buttons
__isForm          : if the list of cards is a form

---- controller:
exerciseList      : list of exercises to display
--%>

<script
  src="https://cdnjs.cloudflare.com/ajax/libs/jquery.isotope/3.0.6/isotope.pkgd.min.js"
  integrity="sha512-Zq2BOxyhvnRFXu0+WE6ojpZLOU2jdnqbrM1hmVdGzyeCa1DgM3X5Q4A/Is9xA1IkbUeDd7755dNNI/PzSf2Pew=="
  crossorigin="anonymous"
></script>

<script src="<c:url value='/script/exercise/filters.js' />"></script>

<c:if test="${user != null}">
  <script src="<c:url value='/script/exercise/buttons.js' />"></script>
</c:if>

<div class="card">
  <div class="card-header">
    <div class="d-flex">
      <!-- list search -->
      <div class="input-group flex-fill m-1">
        <input id="exercise-list-search" class="form-control" type="search" autocomplete="off" placeholder="Search for an exercise..." />
        <span class="input-group-text"><i class="fa fa-search"></i></span>
      </div>

      <div class="dropdown m-1">
        <button id="exercise-list-filters-dropown" class="btn btn-outline-secondary" data-bs-toggle="dropdown">
          <span class="fa-layers fa-fw">
            <i class="fa fa-filter" data-fa-transform="left-6"></i>
            <i class="fa fa-arrow-down-short-wide" data-fa-transform="right-6 flip-h"></i>
          </span>
        </button>

        <!-- sort -->
        <ul class="dropdown-menu dropdown-menu-md-end w-100 w-md-24">
          <li class="dropdown-item-text">
            <div id="exercise-list-sort" class="input-group">
              <div class="input-group-text"><i class="fa fa-arrow-down-wide-short"></i></div>
              <select class="form-select">
                <option>sort by</option>
                <option value="title">&#x1F520; title</option>
                <option value="type">&#x1F7E2; type</option>
                <option value="primary">&#x1F534; primary muscles</option>
                <option value="secondary">&#x1F7E0; secondary muscles</option>
                <option value="equipment">&#x1F535; equipment</option>
              </select>
            </div>
          </li>

          <li><hr class="dropdown-divider" /></li>

          <!-- fliter by type -->
          <li class="dropdown-item-text">
            <div class="exercise-list-filter input-group">
              <div class="input-group-text">
                <span class="fa-layers fa-fw">
                  <i class="fa fa-filter"></i>
                  <i class="fa fa-circle fa-inverse" data-fa-transform="shrink-6 down-1 right-5"></i>
                  <i class="fa fa-circle text-success" data-fa-transform="shrink-8 down-1 right-5"></i>
                </span>
              </div>
              <select class="form-select" data-xf-filter-target=".exercise-type">
                <option value="">filter by type</option>
                <c:forEach items="${exerciseTypesList}" var="type">
                  <option value="${type}">${type}</option>
                </c:forEach>
              </select>
            </div>
          </li>

          <!-- fliter by muscle -->
          <li class="dropdown-item-text">
            <div class="exercise-list-filter input-group">
              <div class="input-group-text">
                <span class="fa-layers fa-fw">
                  <i class="fa fa-filter"></i>
                  <i class="fa fa-circle fa-inverse" data-fa-transform="shrink-6 down-1 right-5"></i>
                  <i class="fa fa-circle text-danger" data-fa-transform="shrink-8 down-1 right-5"></i>
                </span>
              </div>
              <select class="form-select" data-xf-filter-target=".exercise-muscle">
                <option value="">filter by muscle</option>
                <c:forEach items="${muscleList}" var="muscle">
                  <option value="${muscle}">${muscle}</option>
                </c:forEach>
              </select>
            </div>
          </li>

          <!-- filter by equipment -->
          <li class="dropdown-item-text">
            <div class="exercise-list-filter input-group">
              <div class="input-group-text">
                <span class="fa-layers fa-fw">
                  <i class="fa fa-filter"></i>
                  <i class="fa fa-circle fa-inverse" data-fa-transform="shrink-6 down-1 right-5"></i>
                  <i class="fa fa-circle text-primary" data-fa-transform="shrink-8 down-1 right-5"></i>
                </span>
              </div>
              <select class="form-select" data-xf-filter-target=".exercise-equipment">
                <option value="">filter by equipment</option>
                <c:forEach items="${equipmentList}" var="equipmentListItem">
                  <option value="${equipmentListItem}">${equipmentListItem}</option>
                </c:forEach>
              </select>
            </div>
          </li>
        </ul>
      </div>

      <c:if test="${__filters != null}">
        <input type="hidden" name="action" value="favorite">
        <c:import url="${__filters}" charEncoding="UTF-8" />
      </c:if>
    </div>
  </div>
  <div class="card-body overflow-auto vh-100 p-0 bg-light">
    <!-- Exercise List -->
    <ul id="exercise-list" class="list-group list-group-flush">
      <c:forEach items="${exerciseList}" var="exercise">
        <c:set scope="request" var="__exercise" value="${exercise}" />
        <c:set scope="request" var="__exerciseName" value="${__exercise.name}" />
        <c:set scope="request" var="__exerciseId"   value="exercise-${__exerciseName}" />
        <li id="${__exerciseId}" class="exercise list-group-item w-100">
          <c:import url="${__item}" charEncoding="UTF-8" />
        </li>
      </c:forEach>
    </ul>
  </div>
</div>
