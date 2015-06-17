<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link rel="stylesheet" href="css/estilos_formulario.css" />
<title>Login</title>
</head>
<body> -->

<%@ include file="template/header.jsp" %>

<form method="post" action="${pageContext.request.contextPath}/Tienda/login"  >
       
    <h2>LOGIN</h2>
    <label for="userName">Usuario:</label>
    <input type="text" name="userName" id="userName"/>
    <br/> 
    <label for="password">Contraseña:</label>
    <input type="password" name="password" id="password"/>
    <br/> 
    <br/>   
    <input type="submit" name="enviar" value="Login"/>
    <br/>
    <input type="reset" name="reiniciar" value="Reiniciar"/>
  </form>



<%@ include file="template/footer.jsp" %>