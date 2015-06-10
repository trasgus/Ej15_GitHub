<%@page import="es.curso.model.entity.Cliente"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Formulario Actualizar</title>
</head>
<body> <% ArrayList<Cliente> clientes= (ArrayList<Cliente>) request.getAttribute("clientes"); 
          Cliente c = clientes.get(0);%>
   <a href="/Ej15_GitHub/Tienda/altaCliente">ALTA CLIENTE</a> <!-- Con esta ruta hacemos que se vaya al Servlet -->
    <br/>
    <a href="/Ej15_GitHub/Tienda/listarTodos">LISTAR TODOS</a> <!--nombre Proyecto/Servlet  -->
    <!-- con# (se quedaria en la página donde está) 
    con esta solicitud va al Servlet -->
    <br/>
    <a href="/Ej15_GitHub/Tienda/buscarPorNombre">BUSCAR POR NOMBRE</a>
    <br/>
      <a href="/Ej15_GitHub/Tienda/buscarPorId">BUSCAR POR ID</a>
     <br/>
    <!-- Esto es enlace a get pero al DAR A BORRAR es petición post -->
    <a href="/Ej15_GitHub/Tienda/eliminarPorId">ELIMINAR CLIENTE POR ID</a>
     <br/>
     <a href="/Ej15_GitHub/Tienda/editar">EDITAR</a>
     
     
    <form method="post" action="${pageContext.request.contextPath}/Tienda/actualizar" name="actualizar"> 
<!-- enlace con # se queda en la misma página -->
    <fieldset>
        <label>id:</label>
     <input type="hidden" name="id" id="id" value="<%= c.getId()%>"/>
       <br/>
    <label>Nombre:</label>
     <input type="text" name="nombre" id="nombre" value="<%= c.getNombres()%>"/>
       <br/>
    <label>Apellidos:</label>
      <input type="text" name="apellidos" id="apellidos" value= "<%= c.getApellidos()%>"/>
        <br/>
    <label>DNI:</label>
      <input type="text" name="dni" id="dni" value= "<%= c.getDni()%>"/>
        <br/>
    <input type="submit" name="enviar" value="Actualizar"/>
    <input type="reset" name="reiniciar" value="Reiniciar"/>
    </fieldset>
</form>

</body>
</html>