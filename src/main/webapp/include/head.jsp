<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <!-- favicon -->
    <link rel="apple-touch-icon" sizes="180x180" href='<c:url value="/image/favicon/apple-touch-icon.png" />' />
    <link rel="icon" type="image/png" sizes="32x32" href='<c:url value="/image/favicon/favicon-32x32.png" />' />
    <link rel="icon" type="image/png" sizes="16x16" href='<c:url value="/image/favicon/favicon-16x16.png" />' />
    <link rel="manifest" href='<c:url value="/image/favicon/site.webmanifest" />' />
    <link rel="mask-icon" href='<c:url value="/image/favicon/safari-pinned-tab.svg" />' color="#5bbad5" />
    <link rel="shortcut icon" href='<c:url value="/image/favicon/favicon.ico" />' />
    <meta name="msapplication-TileColor" content="#da532c" />
    <meta name="msapplication-config" content='<c:url value="/image/favicon/browserconfig.xml" />' />
    <meta name="theme-color" content="#ffffff" />

    <!-- TODO: change title based on current page -->
    <title>XFit</title>

    <link
      href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.0/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha512-NZ19NrT58XPK5sXqXnnvtf9T5kLXSzGQlVZL9taZWeTBtXoN3xIfTdxbkQh6QSoJfJgpojRqMfhyqBAAEeiXcA=="
      crossorigin="anonymous"
    />
    <link href="<c:url value='/style/main.css' />" rel="stylesheet" />
    
    <!-- scripts -->
    <script
      src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/js/all.min.js"
      integrity="sha512-yFjZbTYRCJodnuyGlsKamNE/LlEaEAxSUDe5+u61mV8zzqJVFOH7TnULE2/PP/l5vKWpUNnF4VGVkXh3MjgLsg=="
      crossorigin="anonymous"
    ></script>
    <script
      src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"
      integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ=="
      crossorigin="anonymous"
    ></script>
    <script src="<c:url value='/script/main.js' />"></script>
  </head>
  <body>
