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
<title>New Task</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<!-- change to match your file/naming structure -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/app.js"></script>
<!-- change to match your file/naming structure -->
</head>
<body>

	<div class="container row">
		<div class="col-8">
		<h1> Project: <c:out value="${proyecto.titulo }"></c:out></h1>
		<p> Project Lead: <c:out value="${proyecto.lider.nombre }"></c:out></p>
			<form:form  method="post" action="/project/${proyecto.id}/tarea" modelAttribute="task">
				<div class="form-group row mb-3">
					<label for="titulo" class="col-sm-3 col-form-label">add a
						task ticket for this team:</label>
					<div class="col-sm-9">
						<form:textarea path="tarea" class="form-control" id="titulo"></form:textarea>
						<div class="invalid-feedback">
							<form:errors path="tarea" class="text-danger" />
						</div>
					</div>
				</div>
				<button>submit</button>
			</form:form>
		</div>
		<div class="row">
<%-- 		<c:forEach items="${ proyectos_asignados }" var="proyecto"> --%>
<!-- 		<h6> fecha</h6> -->
<!-- 		<p> task</p> -->
<%-- 		</c:forEach> --%>
		 
		</div>
	</div>

</body>
</html>
