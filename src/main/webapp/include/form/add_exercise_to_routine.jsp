<!-- prettier-ignore -->
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- prettier-ignore -->
<%-- controller:
exercise  : exercise to add to routine
--%>

<!-- 
==== ADD TO ROUTINE FORM ===============================================================================================
-->
<input type="hidden" name="action" value="add">
<input type="hidden" name="exerciseName" value="${exercise.name}">

<label class="form-label" class="form-label" for="routine-day">Pick a day of the week:</label>
<div class="form-group mb-3">
  <div class="input-group">
    <div class="input-group-text"><i class="fa fa-calendar"></i></div>
    <select id="routine-day" class="form-select" name="day">
      <option value="sun">Sunday</option>
      <option value="mon">Monday</option>
      <option value="tue">Tuesday</option>
      <option value="wed">Wednesday</option>
      <option value="thu">Thursday</option>
      <option value="fri">Friday</option>
      <option value="sat">Saturday</option>
    </select>
  </div>
</div>

<label class="form-label">How many sets and reps you are going to do:</label>
<div class="row mb-3">
  <div class="col">
    <div class="input-group">
      <span class="input-group-text px-2"><i class="fa fa-bars"></i></span>
      <input class="form-control" type="number" name="sets" value="5" placeholder="5" min="0" max="512" />
    </div>
  </div>
  
  <div class="col">
    <div class="input-group">
      <span class="input-group-text px-2"><i class="fa fa-arrow-rotate-right"></i></span>
      <input class="form-control" type="number" name="reps" value="10" placeholder="10" min="0" max="512" />
    </div>
  </div>
</div>

<label class="form-label">Weight to lift (if any):</label>
<div class="form-group mb-3">
  <div class="input-group">
    <span class="input-group-text px-2"><i class="fa fa-bars"></i></span>
    <input class="form-control" type="number" name="weight" value="0" placeholder="25" min="0" max="4096" />
    <span class="input-group-text px-2">lbs</span>
  </div>
</div>

<!-- 
==== END ADD TO ROUTINE FORM ===========================================================================================
-->