<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/include/exercise/user/list/filters.html" charEncoding="UTF-8" />

<input type="hidden" name="action" value="favorite" form="exercise-list-form">

<button class="btn btn-primary m-1 text-nowrap" type="submit" form="exercise-list-form">
  <i class="fa fa-floppy-disk"></i> Save
</button>
