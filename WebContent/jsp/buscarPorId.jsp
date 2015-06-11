<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../template/header.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../css/estilos_formulario.css" />
<title>Buscar por Id Para Actualizar</title>
</head>
<body>
 

<form method="post" action="${pageContext.request.contextPath}/Tienda/buscarPorId" name="buscarPorId"> 
<!-- enlace con # se queda en la misma página -->
    <fieldset>
    <label>ID:</label>
    <input type="text" name="idBuscar" id="idBuscar"/>
    <input type="submit" name="enviar" value="BUSCAR"/>
    <input type="reset" name="reiniciar" value="Reiniciar"/>
    </fieldset>
</form>

<%@ include file="../template/footer.jsp" %>