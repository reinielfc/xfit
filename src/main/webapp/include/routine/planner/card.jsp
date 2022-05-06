<!-- prettier-ignore -->
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- prettier-ignore -->
<%-- controller:
today               : today's day of the week
planListByDayMap    : plans mapped by day, sorted by position
--%>


<script src="<c:url value='/script/routine/planner.js' />"></script>

<c:set var="days" value="sun,mon,tue,wed,thu,fri,sat" />

<div class="card p-0">
  <form id="planner" action="<c:url value='/planner' />" method="POST">
    <input type="hidden" name="action" value="update">
    <div class="card-header">
      <div class="card-header-tabs nav nav-tabs d-flex justify-content-around" role="tablist">
        <c:forTokens items="${days}" delims="," var="day">

          <button class="nav-link${today.equals(day) ? ' active' : ''}" type="button" data-bs-toggle="tab" data-bs-target="#nav-${day}" role="tab">
            ${fn:toUpperCase(day)}
          </button>
        </c:forTokens>

        <button class="btn btn-primary m-2">
          <i class="fa fa-floppy-disk"></i>
          Save
        </button>
      </div>
    </div>

    <div class="card-body overflow-auto vh-100 p-0 bg-light">
      <div class="tab-content">
        <c:forTokens items="${days}" delims="," var="day">
          <c:if test="${today.equals(day)}">
            <c:set var="active" value=" active show" />
          </c:if>

          <div id="nav-${day}" class="day tab-pane fade p-0${active}" role="tabpanel" data-xf-day="${day}">
            <!-- Plan List -->
            <ol class="plan-list sortable list-group list-group-numbered list-group-flush">
              <c:forEach items="${planListByDayMap[day]}" var="plan">
                <c:set scope="request" var="__day" value="${day}" />
                <c:set scope="request" var="__plan" value="${plan}" />
                <c:set scope="request" var="__itemAside" value="/include/routine/planner/_add_button.jsp" />

                <c:import url="/include/routine/planner/_item.jsp" charEncoding="UTF-8" />
              </c:forEach>
            </ol>
          </div>
        </c:forTokens>
      </div>
    </div>
  </form>
</div>

