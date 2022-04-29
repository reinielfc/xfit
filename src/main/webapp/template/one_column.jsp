<!-- prettier-ignore -->
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- prettier-ignore -->
<%-- params:
_title      :   to be displayed in title of tab
_content    :   url of jsp/html content of the template
--%>

<!DOCTYPE html>
<html lang="en">
  <c:import url="/include/_head.jsp" charEncoding="UTF-8" />

  <!-- ### ${_title} ### -->
  <body class="has-navbar d-flex flex-column min-vh-100">
    <c:import url="/include/navbar.jsp" charEncoding="UTF-8" />

    <c:import url="${_content}" charEncoding="UTF-8" />

    <c:import url="/include/footer.jsp" charEncoding="UTF-8" />
  </body>
</html>
