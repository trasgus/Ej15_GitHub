
<%@page import="es.curso.model.entity.Cliente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Listado de Cliente</title>
 <link rel="stylesheet" href="../css/estilos_formulario.css" />
</head>
<body>
<h1><%=request.getAttribute("titulo") %></h1><!--recuperamos el titulo puesto antes en tiendaServlet con código java  -->
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
    <span> <%= (Integer)request.getAttribute("iva") %></span> 
    <% ArrayList<Cliente> clientes= (ArrayList<Cliente>) request.getAttribute("clientes"); %>
     <!--recuperamos. Hemos importado el ArrayList y Clientes para recepcionar
      lo q te manda el controlador, lo que va entre (ArrayList<Cliente>) es un cast pq 
      es de tipo objeto y lo convertimos a tipo ArrayList de cliente-->
    <table>
        <tr>
            <th>ID:</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>DNI:</th>
        </tr>
 <!-- Las siguiente tr se construyen dinámicamente, usando instrucciones java embebidas
      la cabecera que es la anterior no es dinámica, es fija -->
    </table>
    

</body>
</html>