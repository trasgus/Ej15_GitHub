<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ include file="../template/header.jsp" %> 



    <h1>Cliente</h1>
    <section id="contenedor">
        <section id="formulario"> 
        <!-- De momento ponemos el nombre del proyecto en el action delante de Tienda -->
            <form name="altaColegio" action="/Ej15_GitHub/Tienda/altaCliente" method="post" enctype="text/html">

                <h2>Alta Tienda</h2>
                <div class="texto">

                    <label for="nombre" class="mano">Nombre:</label>
                </div>
                <div class="elemento">

                    <input type="text" id="nombre" name="nombre" autofocus required />

                </div>
                <div class="elemento">
                    <label for="apellido" class="mano">Apellido:</label>
                </div>
                <div class="elemento">

                    <input type="text" id="apellido" name="apellido" autofocus required />

                </div>
                
                <div class="texto">
                    <br />
                    <br /> <label for="dni" class="mano">DNI:</label>
                    <div class="elemento">
                        <input type="text" id="dni" name="dni" required />
                    </div>
                </div>
                
                    
                        <br />
                        
                    <input type="hidden" name="id" id="id"/>
                    
                    <br />
                        <input type="submit" id="enviar" class="mano" value="enviar"
                          class="mano" /> 
                        <input type="reset" id="borrar" class="mano" value="borrar"
                          class="mano" />
                        <!--aqui le ponemos tambien la class="mano" para que me salga la mano luego al poner el cursor encima, lo hemos hecho en el css .mano{  cursor: pointer-->
            </form>

        </section>
    </section>
        <%@ include file="../template/footer.jsp" %>