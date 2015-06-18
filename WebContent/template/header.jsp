<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href= "${pageContext.request.contextPath}/css/estilos_formulario.css"/>
<title>Insert title here</title>
</head>
<body>

<!--  Para navegar por el menú y con flechas atrás adelante -->
<%
response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1. //para borrar la cache desde aqui, con código
response.setHeader("Cache-Control", "no-store");//Directs caches not to store the
response.setDateHeader("Expires", 0); //causes the proxy cache to see the page as 
response.setHeader("Pragma", "no-cache"); //HTTP 1.0.backward compatibility
%>


    <header>
    <% HttpSession miSession = request.getSession(); %> <!-- hago referencia y almaceno lo que tengo -->
    <% if(miSession.getAttribute("userName")!=null){ %> <!--  la manera de preguntar si el atributo está o no está -->
    	
  

    <!-- Este es igual que lo de abajo  <a href="/Ej15_GitHub/Tienda/altaCliente">ALTA CLIENTE</a> --> 
    <a href="${pageContext.request.contextPath}/Tienda/altaCliente">ALTA CLIENTE</a>
    <br/>
    <a href="${pageContext.request.contextPath}/Tienda/listarTodos">LISTAR TODOS</a> 
   
    <br/>
    <a href="${pageContext.request.contextPath}/Tienda/buscarPorNombre">BUSCAR POR NOMBRE</a>
    <br/>
      <a href="${pageContext.request.contextPath}/Tienda/buscarPorId">BUSCAR POR ID</a>
     <br/>
   
    <a href="${pageContext.request.contextPath}/Tienda/eliminarPorId">ELIMINAR CLIENTE POR ID</a>
     <br/>
    <a href="${pageContext.request.contextPath}/Tienda/listarTodos">EDITAR</a>
      <br/>
    <a href="${pageContext.request.contextPath}/Tienda/logout">LOGOUT</a> <!-- estás dentro y te da la opción de salir -->
      <br/>
      <p> Usuario: <%=miSession.getAttribute("userName") %></p> <!-- está dentro del if pq ya está logeado. Dentro del código java si empiezo con = no termina en ;-->
      <p> !Hola! <%=miSession.getAttribute("nombreCompleto") %></p> <!-- mete código java pq coge un valor por medio del get y es código java -->
      <p> Máximo periodo de Inactividad: <%=miSession.getMaxInactiveInterval() %> segundos </p> <!-- Periodo máximo de inactividad -->
    </header>
    
     <% }else  
        { %>
      <a href="${pageContext.request.contextPath}/Tienda/login">LOGIN</a> <!-- estas fuera y te da la opción de entrar -->
      <% } %>