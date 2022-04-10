<%@page contentType="text/html" pageEncoding="UTF-8" %> <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/include/header/normal.jsp" charEncoding="UTF-8" />
<c:import url="/include/navbar.jsp" charEncoding="UTF-8" />

<section class="my-3">
  <div class="container">
    <div class="row">
      <div class="col-lg-8">
        <h2>${exercise.title}</h2>
        <hr class="my-2" />
      </div>
    </div>
    <div class="row">
      <div class="col-lg-4">
        <div class="text-muted d-flex small">
          <em class="me-auto">This content is available under a free license.</em>
          <a
            class="text-muted text-decoration-none me-1"
            href="https://github.com/everkinetic/data/blob/main/LICENSE.md"
          >
            <i class="fa fa-balance-scale"></i>
            CC-BY-SA-4.0
          </a>
          <a class="text-muted text-decoration-none" href="https://github.com/everkinetic">
            <i class="fab fa-github" aria-hidden="true"></i>
            Everkinetic
          </a>
        </div>
        <div class="d-block my-2">
          <span class="exercise-type badge rounded-pill bg-success">&#x1F3AF; ${exercise.type}</span>
          <c:forEach items="${exercise.primaryMuscles}" var="muscle">
            <span class="exercise-primary badge rounded-pill bg-danger">&#x1F4AA; ${muscle.name}</span>
          </c:forEach>
          <c:forEach items="${exercise.secondaryMuscles}" var="muscle">
            <span class="exercise-secondary badge rounded-pill bg-warning text-dark">${muscle.name}</span>
          </c:forEach>
          <c:forEach items="${exercise.equipment}" var="equipment">
            <span class="exercise-equipment badge rounded-pill bg-primary">
              <i class="fas fa-dumbbell"></i> ${equipment.name}
            </span>
          </c:forEach>
        </div>
        <p class="lead mb-1">${exercise.primer}</p>
        <ul>
          <c:forEach items="${exercise.stepsList}" var="step">
            <li>${step}</li>
          </c:forEach>
        </ul>

        <div class="my-2 d-flex flex-wrap flex-md-nowrap p-1">
          <c:forEach items="${exercise.exerciseImagesById}" var="image">
            <div class="card h-100 m-1">
              <div class="card-header text-capitalize">${image.exerciseState}</div>
              <div class="h-100 p-1">
                <img
                  class="card-img-top"
                  src="<c:url value='/img/${image.exerciseState}.png?exercise=${exercise.name}&state=${image.exerciseState}'/>"
                />
              </div>
            </div>
          </c:forEach>
        </div>

        <c:if test="${!exercise.tipsList.isEmpty()}">
          <h3>Tips</h3>
          <ul>
            <c:forEach items="${exercise.tipsList}" var="tip">
              <li>${tip}</li>
            </c:forEach>
          </ul>
        </c:if>
      </div>
    </div>
  </div>
</section>

<c:import url="/include/footer.jsp" charEncoding="UTF-8" />

