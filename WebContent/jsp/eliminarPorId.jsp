<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Formulario para Buscar por Id y Borrar</title>
</head>
<body>

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