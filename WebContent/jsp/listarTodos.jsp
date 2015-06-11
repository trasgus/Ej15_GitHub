
<%@page import="es.curso.model.entity.Cliente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../template/header.jsp" %>  <!-- include es para incluir un trozo de código (en este caso el header.jsp) -->
<script type="text/javascript">
	function enviar(boton) {
		var formulario = document.getElementById("formul"
				+ boton.name.substring(3));
		//alteramos el action de acuerdo al botón pulsado
		if (boton.value == "Actualizar")
			formulario.action = "${pageContext.request.contextPath}/Tienda/actualizar";

		if (boton.value == "Eliminar")
			formulario.action = "${pageContext.request.contextPath}/Tienda/eliminarPorId";

		if (boton.value == "Editar")
			formulario.action = "${pageContext.request.contextPath}/Tienda/listarTodos";
		formulario.submit();

	}
</script>









	<h1><%=request.getAttribute("titulo")%></h1>
	<!--recuperamos el titulo puesto antes en tiendaServlet con código java  -->
	<!--las jsp mezclan código html y java  -->
	<!-- Tabla html dinámicamente -->
	<!-- si pongo el Local y doy autocompletar con la ayuda de eclipse, ya me importa lo que necesita
    < %=  > es para imprimir
    < % > significa que es código java 
    < %!   %>-->
	<span><%=LocalDateTime.now()%></span>
	<span> Valor enviado desde el servlet Tienda IVA: </span>
	<!-- Valor para recuperar el request ponemos (Integer)así le damos la vuelta, pero
    no haría falta, pq la clase Integer tiene implicito el método toString. Lo lanzo desde 
    el index y me sale arriba el iva 21 -->
	<span> <%=(Integer) request.getAttribute("iva")%></span>
	<%
		ArrayList<Cliente> clientes = (ArrayList<Cliente>) request
				.getAttribute("clientes");
	%>
	<!--recuperamos. Hemos importado el ArrayList y Clientes para recepcionar
      lo q te manda el controlador, lo que va entre (ArrayList<Cliente>) es un cast pq 
      es de tipo objeto y lo convertimos a tipo ArrayList de cliente-->
	<table>
		<tr>
			<th>ID:</th>
			<th>Nombre</th>
			<th>Apellido</th>
			<th>DNI</th>
		</tr>
		<%
			for (Cliente c : clientes) {
		%>
		<!-- ABRO EL FOR  El for es para recorrer el ArrayList pq la tabla es dinámica -->

		<form id="formul<%=c.getId()%>" action="#" method="post"
			onsubmit="return false;">
			<%--         <form action="${pageContext.request.contextPath}/Tienda/eliminarPorId" method="post">
 Hay una forma actual más fácil que con for action pero no en internet exploret 10 --%>
			<tr id="<%=c.getId()%>">
			<%-- 	<td><input type="text" name="id" value="<%=c.getId()%>"></td> 
			Esto era antes cuando se veia el botón del id, ahora lo hemos puesto hidden --%>
				<td><input type="hidden" name="id" value="<%=c.getId()%>"></td>
				<td><input type="text" name="nombres"
					value="<%=c.getNombres()%>" /></td>
				<!-- con la referencia c imprime Nombres -->
				<td><input type="text" name="apellidos"
					value="<%=c.getApellidos()%>" /></td>
				<td><input type="text" name="dni" value="<%=c.getDni()%>" /></td>
				<!--Aquí iría un formulario <form method= "post"> -->
				<td><input id="btnSinBordes" type="submit" value="Eliminar"
					name="btn<%=c.getId()%>" onclick="enviar(this)" /></td>
				<!-- Este ultimo botón es para diferenciar un eliminar de otro , y de ahí va al controlador de eliminar -->
				<td><input id="btnSinBordes" type="submit" value="Actualizar"
					name="btn<%=c.getId()%>" onclick="enviar(this);" />
				<td><input id="btnSinBordes" type="submit" value="Editar"
					name="btn<%=c.getId()%>" onclick="enviar(this);" />
			</tr>
		</form>


		<%
			}
		%>
		<!-- CIERRO EL FOR . Cierra la } de esa manera pq es jsp-->


		<%--   Imprimo la variable clientes, imprimiría todo el ArrayList   <%= clientes %>   
          este es el estilo de comentario de jsp --%>
		<!-- Las siguiente tr se construyen dinámicamente, usando instrucciones java embebidas
      la cabecera que es la anterior no es dinámica, es fija -->
	</table>
	<%@ include file="../template/footer.jsp" %>