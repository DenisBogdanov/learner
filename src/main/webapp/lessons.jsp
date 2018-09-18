<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://bogdanov.ru/functions" %>
<html>
<head>
  <title>Lessons</title>
  <link rel="stylesheet" href="css/style.css">
</head>
<body>

<div>
  <h1>Lessons</h1>

  <form method="post" action="lessons?action=filter">

    <table>
      <tr>
        <td><label for="startDate">From Date:</label></td>
        <td><input type="date" name="startDate" value="${param.startDate}" id="startDate"></td>
      </tr>
      <tr>
        <td><label for="endDate">To Date:</label></td>
        <td><input type="date" name="endDate" value="${param.endDate}" id="endDate"></td>
      </tr>
      <tr>
        <td><label for="startTime">From Time:</label></td>
        <td><input type="time" name="startTime" value="${param.startTime}" id="startTime"></td>
      </tr>
      <tr>
        <td><label for="endTime">To Time:</label></td>
        <td><input type="time" name="endTime" value="${param.endTime}" id="endTime"></td>
      </tr>
      <tr>
        <td>
          <button type="submit">Filter</button>
        </td>
      </tr>
    </table>
  </form>
  <hr/>

  <a href="lessons?action=create">Add Lesson</a>

  <table>
    <tr>
      <th>Date</th>
      <th>Description</th>
      <th>Duration</th>
      <th></th>
      <th></th>
    </tr>

    <c:forEach items="${lessons}" var="lesson">
      <jsp:useBean id="lesson" scope="page" type="ru.bogdanov.learner.to.LessonWithGoal"/>
      <tr data-goalAchieved="${lesson.goalAchieved}">
        <td>${fn:formatDateTime(lesson.startDateTime)}</td>
        <td>${lesson.description}</td>
        <td>${lesson.duration}</td>
        <td><a href="lessons?action=update&id=${lesson.id}">Update</a></td>
        <td><a href="lessons?action=delete&id=${lesson.id}">Delete</a></td>
      </tr>
    </c:forEach>
  </table>

  <p><a href="index.html">Home</a></p>
</div>

</body>
</html>
