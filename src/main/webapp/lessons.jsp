<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://bogdanov.ru/functions" %>
<html>
<head>
  <title>Lessons</title>
  <style>
    .achieved {
      color: green;
    }

    .not-achieved {
      color: red;
    }

    table {
      border-collapse: collapse;
    }

    th, td {
      border: 1px solid black;
      padding: 5px;
    }

  </style>
</head>
<body>

<div>
  <h1>Lessons</h1>

  <table>
    <tr>
      <th>Date</th>
      <th>Description</th>
      <th>Duration</th>
    </tr>

    <c:forEach items="${lessons}" var="lesson">
      <jsp:useBean id="lesson" scope="page" type="ru.bogdanov.learner.model.LessonWithGoal"/>
      <tr class="${lesson.goalAchieved ? '' : 'not-'}achieved">
        <td>${fn:formatDateTime(lesson.startDateTime)}</td>
        <td>${lesson.description}</td>
        <td>${lesson.duration}</td>
      </tr>
    </c:forEach>
  </table>

  <p><a href="index.html">Home</a></p>
</div>

</body>
</html>
