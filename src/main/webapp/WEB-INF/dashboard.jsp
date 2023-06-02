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
<title>Dashboard</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<!-- change to match your file/naming structure -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/app.js"></script>
<!-- change to match your file/naming structure -->
</head>
<body>

	<div class="container">
		<h1>Welcome, ${ usuario.nombre }</h1>
		<div class="text-end">
			<a href="/logout">Logout</a> <a class="btn btn-primary"
				href="/projects/new">New Project</a>
		</div>
		<hr />
		<h3>All Projects</h3>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Project</th>
					<th>Lead</th>
					<th>Due Date</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ noasignados }" var="proyecto">
					<tr>
						<c:if test="${proyecto.lider.id != usuario.id }">
						<td><a href="/projects/${ proyecto.id }">${ proyecto.titulo }</a></td>
						<td>${ proyecto.lider.nombre }</td>
						<td><fmt:formatDate value="${proyecto.fechaEvento}"
								pattern="MMMM dd" var="fechaEvento" /> <c:out
								value="${fechaEvento}"></c:out></td>
						<td>
						<a href="/project/${ proyecto.id }/${usuario.id }/join">Join Team</a>
						</td>
						</c:if>
						
						
<%-- 						<c:choose> --%>
<%-- 								<c:when test="${ proyecto.lider.id == usuario.id }"> --%>
<%-- 									<a href="/projects/edit/${ proyecto.id }">Edit</a> | --%>
<%-- 									<form class="delete-form" action="/events/${ proyecto.id }" --%>
<%-- 										method="post"> --%>
<!-- 										<input type="hidden" name="_method" value="delete" /> -->
<!-- 										<button>Borrar</button> -->
<%-- 									</form> --%>
<%-- 								</c:when> --%>
<%-- 								<c:otherwise> --%>
<%-- 									<c:choose> --%>
<%-- 										<c:when test="${ proyecto.compas.contains(usuario) }"> --%>
<%-- 											<a href="/project/${ proyecto.id }/${usuario.id }/cancel"> --%>
<!-- 												Leave Team</a> -->
<%-- 										</c:when> --%>

<%-- 										<c:otherwise> --%>
<%-- 											<a href="/project/${ proyecto.id }/${usuario.id }/join"> --%>
<!-- 												Team</a> -->
<%-- 										</c:otherwise> --%>

<%-- 									</c:choose> --%>
<%-- 								</c:otherwise> --%>
<%-- 							</c:choose> --%>
							
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<hr class="text-primary">

		<h3>Your projects</h3>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Project</th>
					<th>Team Lead</th>
					<th>Due Date</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ proyectos_asignados }" var="proyecto">
					<tr>
						<td><a href="/projects/${ proyecto.id }">${ proyecto.titulo }</a></td>
						<td>${ proyecto.lider.nombre }</td>
						<td><fmt:formatDate value="${proyecto.fechaEvento}"
								pattern="MMMM dd, yyyy" var="fechaEvento" /> ${fechaEvento}</td>
						<td><c:choose>
								<c:when test="${ proyecto.lider.id == usuario.id }">
									<a href="/projects/edit/${ proyecto.id }">Edit</a> | 
 									
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${ proyecto.lider.id != usuario.id }"> 
											<a href="/project/${ proyecto.id }/${usuario.id }/cancel">
												Leave Team</a>
										</c:when>
										<c:otherwise>
										</c:otherwise>

									</c:choose>
								</c:otherwise>
							</c:choose></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<hr />




	</div>



</body>
</html>
