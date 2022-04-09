<%@page contentType="text/html" pageEncoding="UTF-8" %> <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/include/header/normal.jsp" charEncoding="UTF-8" />
<c:import url="/include/navbar.jsp" charEncoding="UTF-8" />

<script
  src="https://cdnjs.cloudflare.com/ajax/libs/jquery.isotope/3.0.6/isotope.pkgd.min.js"
  integrity="sha512-Zq2BOxyhvnRFXu0+WE6ojpZLOU2jdnqbrM1hmVdGzyeCa1DgM3X5Q4A/Is9xA1IkbUeDd7755dNNI/PzSf2Pew=="
  crossorigin="anonymous"
></script>
<script src="<c:url value='/script/exercise-list.js'/>"></script>

<section class="my-3">
  <div class="container">
    <div class="row">
      <div class="col">
        <h1>Exercises</h1>
        <hr class="my-2 d-none d-lg-block" />
      </div>
    </div>
    <div class="row">
      <div class="col-lg-4 m-2 p-0">
        <div class="card">
          <div class="card-header">
            <div class="d-flex flex-wrap flex-md-nowrap m-1">
              <!-- exercise list search -->
              <div class="input-group flex-fill m-1">
                <input id="exercise-list-search" class="form-control" type="search" placeholder="Search..." />
                <span class="input-group-text"><i class="fas fa-search"></i></span>
              </div>

              <div class="d-flex">
                <!-- logged in-->
                <div class="dropdown m-1">
                  <button
                    id="exercise-list-filters-dropown"
                    class="btn btn-outline-secondary"
                    data-bs-toggle="dropdown"
                  >
                    <span class="fa-layers fa-fw">
                      <i class="fa fa-filter" data-fa-transform="left-6"></i>
                      <i class="fa fa-arrow-down-short-wide" data-fa-transform="right-6 flip-h"></i>
                    </span>
                  </button>

                  <ul class="dropdown-menu dropdown-menu-md-end w-100 w-md-24">
                    <li class="dropdown-item-text">
                      <div id="exercise-list-sort" class="input-group">
                        <div class="input-group-text"><i class="fa-solid fa-arrow-down-wide-short"></i></div>
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

                    <li class="dropdown-item-text">
                      <div class="exercise-list-filter input-group">
                        <div class="input-group-text">
                          <span class="fa-layers fa-fw">
                            <i class="fa fa-filter" data-fa-transform="left-3.5"></i>
                            <i class="fa fa-circle text-light" data-fa-transform="shrink-1 right-3.5"></i>
                            <i class="fa fa-circle text-success" data-fa-transform="shrink-3 right-3.5"></i>
                            <i class="fa fa-layers-text" data-fa-transform="shrink-8 right-3.4">&#x1F3AF;</i>
                          </span>
                        </div>
                        <select class="form-select" data-filter-target=".exercise-type">
                          <option value="">filter by type</option>
                          <c:forEach items="${exerciseTypesList}" var="type">
                            <option value="${type}">${type}</option>
                          </c:forEach>
                        </select>
                      </div>
                    </li>

                    <li class="dropdown-item-text">
                      <div class="exercise-list-filter input-group">
                        <div class="input-group-text">
                          <span class="fa-layers fa-fw">
                            <i class="fa fa-filter" data-fa-transform="left-3.5"></i>
                            <i class="fa fa-circle text-light" data-fa-transform="right-3.5"></i>
                            <i class="fa fa-circle text-danger" data-fa-transform="shrink-2 right-3.5"></i>
                            <i class="fa fa-layers-text" data-fa-transform="shrink-7 right-3.4">&#x1F4AA;</i>
                          </span>
                        </div>
                        <select class="form-select" data-filter-target=".exercise-muscle">
                          <option value="">filter by muscle</option>
                          <c:forEach items="${muscleList}" var="muscleListItem">
                            <option value="${muscleListItem}">${muscleListItem}</option>
                          </c:forEach>
                        </select>
                      </div>
                    </li>

                    <li class="dropdown-item-text">
                      <div class="exercise-list-filter input-group">
                        <div class="input-group-text">
                          <span class="fa-layers fa-fw">
                            <i class="fa fa-filter" data-fa-transform="left-3.5"></i>
                            <i class="fa fa-circle text-light" data-fa-transform="shrink-1 right-3.5"></i>
                            <i class="fa fa-circle text-primary" data-fa-transform="shrink-3 right-3.5"></i>
                            <i class="fa fa-dumbbell fa-inverse" data-fa-transform="shrink-8 right-3.5"></i>
                          </span>
                        </div>
                        <select class="form-select" data-filter-target=".exercise-equipment">
                          <option value="">filter by equipment</option>
                          <c:forEach items="${equipmentList}" var="equipmentListItem">
                            <option value="${equipmentListItem}">${equipmentListItem}</option>
                          </c:forEach>
                        </select>
                      </div>
                    </li>
                  </ul>
                </div>

                <div class="m-1">
                  <input
                    class="exercise-list-user-filter btn-check"
                    type="checkbox"
                    value="exercise-equipment-is-available"
                    autocomplete="off"
                    data-filter-target=".exercise-equipment-is-available"
                  />
                  <label class="btn btn-outline-primary" for="exercise-list-equipment-filter">
                    <i class="fas fa-dumbbell"></i>
                  </label>
                </div>

                <div class="m-1">
                  <input
                    class="exercise-list-user-filter btn-check"
                    type="checkbox"
                    value="exercise-is-custom"
                    autocomplete="off"
                    data-filter-target=".exercise.exercise-is-custom"
                  />
                  <label class="btn btn-outline-warning" for="exercise-list-custom-exercise-filter">
                    <i class="fas fa-pen"></i>
                  </label>
                </div>

                <div class="m-1">
                  <input
                    class="exercise-list-user-filter btn-check"
                    type="checkbox"
                    value="exercise-is-favorite"
                    autocomplete="off"
                    data-filter-target=".exercise.exercise-is-favorite"
                  />
                  <label class="btn btn-outline-danger" for="exercise-list-favorite-filter">
                    <i class="fas fa-heart"></i>
                  </label>
                </div>
              </div>
            </div>
          </div>
          <div class="card-body overflow-auto p-0 vh-75 bg-light">
            <c:import url="/include/exercise-list/detailed.jsp" charEncoding="UTF-8" />
          </div>
        </div>
      </div>
    </div>
  </div>
</section>

<c:import url="/include/footer.jsp" charEncoding="UTF-8" />

