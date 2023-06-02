<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- c:out ; c:forEach etc. -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Formatting (dates) -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mostrar Proyecto</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<!-- change to match your file/naming structure -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/app.js"></script>
<!-- change to match your file/naming structure -->
</head>
<body>

	<div class="container">
		<nav class="navbar bg-body-tertiary">
			<div class="container-fluid">
				<h1>Project Details</h1>
				<a href="/dashboard">Back to Dashboard</a>
			</div>
		</nav>

		<table class="table table-borderless">
			<thead>

			</thead>
			<tbody>
				<tr class="table-active">
					<td colspan="2">Projects:</td>
					<td colspan="2"><c:out value="${proyecto.titulo }"></c:out></td>
				</tr>
				<tr class="table-active">
					<td colspan="2">Description:</td>
					<td colspan="2"><c:out value="${proyecto.description }"></c:out></td>
				</tr>
				<tr class="table-active">
					<td colspan="2">Due Date:</td>
					<fmt:formatDate value="${proyecto.fechaEvento}"
						pattern="MM/dd/yyyy" var="fechaEvento" />
					<td colspan="2"><c:out value="${fechaEvento }"></c:out></td>
				</tr>
			</tbody>
		</table>
		<a href="/projects/${proyecto.id}/tasks"> See tasks! </a> 
		<br>
		<a class=" btn btn-danger" href="/projects/delete/${ proyecto.id }">
			Delete </a>
	</div>

</body>
</html>
