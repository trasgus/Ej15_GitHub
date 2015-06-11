<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../template/header.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Buscar por nombre</title>
 <link rel="stylesheet" href="../css/estilos_formulario.css" />
</head>
<body>


<form method="post" action="${pageContext.request.contextPath}/Tienda/buscarPorNombre" name="buscarPorNombre"> 
<!-- enlace con # se queda en la misma página -->
    <fieldset>
    <label>Nombre:</label>
    <input type="text" name="nombre" id="nombre"/>
    <input type="submit" name="enviar" value="Enviar"/>
    <input type="reset" name="reiniciar" value="Reiniciar"/>
    </fieldset>
</form>

<%@ include file="../template/footer.jsp" %>