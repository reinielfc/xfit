<!-- prettier-ignore -->
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- prettier-ignore -->
<%-- params:
_title    : to be displayed in header of page as well as title of tab
_content  : url of main jsp/html content of the template

---- defaults:
_mainColumnWidth = 8

---- optional:
_isForm     : surround page with form
_formAction : page form action
_titleAside : url of jsp/html content to display alongside the title
_aside      : url of jsp/html content to display alongside the main content
--%>

<!-- prettier-ignore -->
<c:set var="_mainColumnWidth" value="${_mainColumnWidth == null ? '8' : _mainColumnWidth}" />

<!DOCTYPE html>
<html lang="en">
  <c:import url="/include/_head.jsp" charEncoding="UTF-8" />
  
  <!-- #### ${_title} #### -->
  <body class="has-navbar d-flex flex-column min-vh-100">
    <c:import url="/include/navbar.jsp" charEncoding="UTF-8" />

    <section class="container my-3">
      <header class="row g-3">
        <div class="col-lg-${_mainColumnWidth}">
          <h1>${_title}</h1>
        </div>
        <div class="col">
          <c:if test="${_titleAside != null}">
            <c:import url="${_titleAside}" charEncoding="UTF-8" />
          </c:if>
        </div>
      </header>

      <c:if test="${_isForm}">
        <form action='<c:url value="${_formAction}" />' method="POST" >
      </c:if>
        <div class="row g-3">
          <div class="col-lg-${_mainColumnWidth}">
            <c:import url="${_content}" charEncoding="UTF-8" />
          </div>
          
          <div class="col">
            <c:if test="${_aside != null}">
              <c:import url="${_aside}" charEncoding="UTF-8" />
            </c:if>
          </div>
        </div>
      <c:if test="${_isForm}">
        </form>
      </c:if>
    </section>

    <c:import url="/include/footer.jsp" charEncoding="UTF-8" />
  </body>
</html>

