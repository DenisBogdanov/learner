<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>Add Lesson</title>
  <style>
    table {
      border-collapse: collapse;
    }

    th, td {
      padding: 5px;
    }
  </style>
</head>

<body>

<div>
  <h1>${param.action == 'create' ? 'Create meal' : 'Edit meal'}</h1>
  <hr>
  <jsp:useBean id="lesson" type="ru.bogdanov.learner.model.Lesson" scope="request"/>
  <form method="post" action="lessons">
    <input type="hidden" name="id" value="${lesson.id}">

    <table>
      <tr>
        <td><label for="startDateTime">DateTime:</label></td>
        <td><input type="datetime-local" value="${lesson.startDateTime}" name="startDateTime" id="startDateTime"
                   required></td>
      </tr>
      <tr>
        <td><label for="description">Description:</label></td>
        <td><input type="text" value="${lesson.description}" size=40 name="description" id="description" required></td>
      </tr>
      <tr>
        <td><label for="duration">Duration:</label></td>
        <td><input type="number" value="${lesson.duration}" name="duration" id="duration" id="duration" required></td>
      </tr>
      <tr>
        <td>
          <button type="submit">Save</button>
        </td>
      </tr>
      <tr>
        <td>
          <button onclick="window.history.back()" type="button">Cancel</button>
        </td>
      </tr>
    </table>

  </form>

  <p><a href="index.html">Home</a></p>
</div>

</body>
</html>
