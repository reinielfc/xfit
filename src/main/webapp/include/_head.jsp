<!-- prettier-ignore -->
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- prettier-ignore -->
<%-- optional:
_title: to be displayed in title of tab
--%>

<c:if test="${_title != null}">
  <c:set var="title" value=" | ${_title}" />
</c:if>

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />

  <!-- Favicon -->
  <link rel="apple-touch-icon" sizes="180x180" href="<c:url value='/image/favicon/apple-touch-icon.png' />" />
  <link rel="icon" type="image/png" sizes="32x32" href="<c:url value='/image/favicon/favicon-32x32.png' />" />
  <link rel="icon" type="image/png" sizes="16x16" href="<c:url value='/image/favicon/favicon-16x16.png' />" />
  <link rel="manifest" href="<c:url value='/image/favicon/site.webmanifest' />" />
  <link rel="mask-icon" href="<c:url value='/image/favicon/safari-pinned-tab.svg' />" color="#5bbad5" />
  <link rel="shortcut icon" href="<c:url value='/image/favicon/favicon.ico' />" />
  <meta name="msapplication-TileColor" content="#da532c" />
  <meta name="msapplication-config" content='<c:url value="/image/favicon/browserconfig.xml" />' />
  <meta name="theme-color" content="#ffffff" />

  <title>XFit${title}</title>

  <!-- Styles -->
  <link
    href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.0/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha512-NZ19NrT58XPK5sXqXnnvtf9T5kLXSzGQlVZL9taZWeTBtXoN3xIfTdxbkQh6QSoJfJgpojRqMfhyqBAAEeiXcA=="
    crossorigin="anonymous"
  />
  <!-- TODO: remove randomizer from css url-->
  <link href="<c:url value='/style/main.css'/>?<%= (int) (Math.random() * 64) + 1 %>" rel="stylesheet" />

  <!-- Script Libraries -->
  <script
    src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/js/all.min.js"
    integrity="sha512-6PM0qYu5KExuNcKt5bURAoT6KCThUmHRewN3zUFNaoI6Di7XJPTMoT6K0nsagZKk2OB4L7E3q1uQKHNHd4stIQ=="
    crossorigin="anonymous"
  ></script>
  <script
    src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"
    integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ=="
    crossorigin="anonymous"
  ></script>
  <script
    src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"
    integrity="sha512-uto9mlQzrs59VwILcLiRYeLKPPbS/bT71da/OEBYEwcdNUk8jYIy+D176RYoop1Da+f9mvkYrmj5MCLZWEtQuA=="
    crossorigin="anonymous"
  ></script>
  <script
    src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui-touch-punch/0.2.3/jquery.ui.touch-punch.min.js"
    integrity="sha512-0bEtK0USNd96MnO4XhH8jhv3nyRF0eK87pJke6pkYf3cM0uDIhNJy9ltuzqgypoIFXw3JSuiy04tVk4AjpZdZw=="
    crossorigin="anonymous"
  ></script>
  <script src="<c:url value='/script/main.js'/>"></script>
</head>

