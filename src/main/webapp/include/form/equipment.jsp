<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row row-cols-auto justify-content-center g-2">
  <c:forEach var="i" begin="0" end="23">
    <div class="col">
      <input id="equipment-${i}" class="btn-check" type="checkbox" autocomplete="off" />
      <label class="btn btn-outline-primary p-2 h-100" for="equipment-${i}" style="width: 10rem">
        <img src="https://dummyimage.com/512x512/343a40/ffffff" alt="" class="rounded img-fluid" />
        <strong class="m-1 d-block">equipment-${i}</strong>
      </label>
    </div>
  </c:forEach>
</div>
