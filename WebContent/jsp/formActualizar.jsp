<%@page import="es.curso.model.entity.Cliente"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../template/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Formulario Actualizar</title>
</head>
<body> <% ArrayList<Cliente> clientes= (ArrayList<Cliente>) request.getAttribute("clientes"); 
          Cliente c = clientes.get(0);%>

     
     
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
<%@ include file="../template/footer.jsp" %>
