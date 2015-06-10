<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Formulario para Buscar por Id y Borrar</title>
</head>
<body>
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



<form method="post" action="${pageContext.request.contextPath}/Tienda/eliminarPorId" name="eliminarPorId"> 
<!-- enlace con # se queda en la misma página -->
    <fieldset>
    <label>ID:</label>
    <input type="text" name="id" id="id"/>
    <input type="submit" name="iliminar" value="ELIMINAR"/>
    <input type="reset" name="reiniciar" value="Reiniciar"/>
    </fieldset>
</form>

</body>
</html>