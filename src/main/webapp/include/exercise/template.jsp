<!-- prettier-ignore -->
<%@ page contentType="text/html" pageEncoding="UTF-8" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- params:
__exercise  : exercise to display the details of
__badges    : url of badges template to use
--%>

<hr class="my-2 d-none d-lg-block" />

<div class="text-muted d-flex small">
  <!-- prettier-ignore -->
  <em class="me-auto">This content is available under a free license.</em>

  <a class="text-muted text-decoration-none me-1" href="https://github.com/everkinetic/data/blob/main/LICENSE.md">
    <i class="fa fa-balance-scale"></i>
    CC-BY-SA-4.0
  </a>

  <a class="text-muted text-decoration-none" href="https://github.com/everkinetic">
    <i class="fab fa-github"></i>
    Everkinetic
  </a>
</div>

<div class="d-block my-2">
  <c:import url="${__badges}" charEncoding="UTF-8" />
</div>

<!-- primer -->
<p class="lead mb-1">${__exercise.primer}</p>

<c:set var="exerciseStepsList" value="${__exercise.stepsList}" />
<c:if test="${!(exerciseStepsList == null || exerciseStepsList.isEmpty())}">
  <!-- steps list -->
  <ul>
    <c:forEach items="${exerciseStepsList}" var="step">
      <li>${step}</li>
    </c:forEach>
  </ul>
</c:if>

<c:set var="exerciseTipsList" value="${__exercise.tipsList}" />
<c:if test="${!(exerciseTipsList == null || exerciseTipsList.isEmpty())}">
  <!-- tips -->
  <h3>Tips</h3>
  <ul>
    <c:forEach items="${exerciseTipsList}" var="tip">
      <li>${tip}</li>
    </c:forEach>
  </ul>
</c:if>

<!-- images -->
<div class="my-2 d-flex flex-wrap flex-md-nowrap p-1">
  <c:forEach items="${__exercise.exerciseImagesById}" var="image">
    <div class="card h-100 m-1">
      <div class="card-header text-capitalize">${image.exerciseState}</div>
      <div class="h-100 p-1">
        <!-- prettier-ignore -->
        <img class="card-img-top" src="<c:url value='/img/${image.exerciseState}.png?of=exercise&name=${__exercise.name}&state=${image.exerciseState}'/>" />
      </div>
    </div>
  </c:forEach>
</div>

<c:set var="exerciseLinksList" value="${__exercise.linksList}" />
<c:if test="${!(exerciseLinksList == null || exerciseLinksList.isEmpty())}">
  <!-- tips -->
  <h3>References</h3>
  <ul>
    <c:forEach items="${exerciseLinksList}" var="reference">
      <li>${reference}</li>
    </c:forEach>
  </ul>
</c:if>
