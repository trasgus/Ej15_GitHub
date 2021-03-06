package es.curso.dispatchers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.curso.controllers.ActualizarClienteController;
import es.curso.controllers.EliminarController;
import es.curso.controllers.LoginController;
import es.curso.controllers.ejb.ActualizarClienteControllerEjb;
import es.curso.controllers.ejb.BuscarPorIdControllerEjb;
import es.curso.controllers.ejb.BuscarPorNombreControllerEjb;
import es.curso.controllers.ejb.DarAltaClienteControllerEjb;
import es.curso.controllers.ejb.EliminarControllerEjb;
import es.curso.controllers.ejb.ListarTodosControllerEjb;
import es.curso.controllers.ejb.LoginControllerEjb;
import es.curso.model.entity.Cliente;
import es.curso.model.entity.Usuario;

/**
 * Servlet implementation class TiendaServlet
 */

@WebServlet("/Tienda/*")
public class TiendaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public TiendaServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO se recupera en el request
		//en action tengo listarTodos o consultarNombre que son las q tienen servlet
		String action = request.getPathInfo().substring(1);
		request.setCharacterEncoding("UTF-8");
		String titulo = "sin título";
		RequestDispatcher rd; //importo
		                      //Hace rediciona a otras páginas
		HttpSession miSession = request.getSession();
		//preguntar si la petición es login
		if(action.equals("login")){
			//invalida la sesion si el login
			miSession.invalidate();
			//redirigir a login
		   rd = request.getRequestDispatcher("/login.jsp");
           rd.forward(request, response);
		}
		else{
			//en caso contrario hago las otras opciones. Pregunto si la sessión está activa
				//hacer el switch
			
			if(miSession.getAttribute("userName")!=null){
				switch(action){
				//Si tuviesemos Ajax lo mejorariamos, pero dejamos el case login por si alguien con las flechas atrás quiere logearse
					
		//		   case "login":
//						 rd = request.getRequestDispatcher("/login.jsp");
//			             rd.forward(request, response);
//			             break;
//				
					case "altaCliente": //se debe redirigir hacia el formulario altaCliente
						                rd = request.getRequestDispatcher("/jsp/altaClienteView.jsp");
						                // al subir a web el servidor de rutas automaticamente  ???
						                rd.forward(request, response);
						                break;
						
					case "listarTodos":  //se invocará al controllador adecuado que obtendrá todos los clientes
						                 //Esta petición nos redirige a otra página
						//aquí invoco a controller para rececpionar lo que me manda
						ListarTodosControllerEjb todos= new ListarTodosControllerEjb();
						ArrayList<Cliente> clientes=todos.listarTodos();
						request.setAttribute("clientes", clientes); //se va todo el ArrayList en el objeto request
						
						titulo= "Listado general de clientes";
						request.setAttribute("titulo", titulo);
						rd = request.getRequestDispatcher("/jsp/listarTodos.jsp"); //le digo la vista que quiero mostrar
						rd.forward(request, response); //con esta instrucción se redirige a la página /jsp/listarTodos.jsp
						break;
						
					case "buscarPorNombre": //se redirigirá hacia el formulario buscar por nombre,
					                 
						rd = request.getRequestDispatcher("/jsp/buscarPorNombre.jsp");
						rd.forward(request, response);
						break;
						
					case "buscarPorId": //se redirigirá hacia el formulario buscar por id, otro caso de uso
		                
						rd = request.getRequestDispatcher("/jsp/buscarPorId.jsp");
						rd.forward(request, response);
						break;
					
					case "eliminarPorId": //se redirigirá hacia el formulario buscar por id,
		                
						rd = request.getRequestDispatcher("/jsp/eliminarPorId.jsp"); //estamos haciendo una redirección
						rd.forward(request, response);
						break;
					
					case "logout":  //cuando se de clic en logout se debería cerrar la sesión
						miSession.invalidate();
						rd = request.getRequestDispatcher("/login.jsp"); 
						rd.forward(request, response);
						break;
						
			
				
			} //CIERRE DEL SWITCH
				
				} //FIN DEL IF que verifica la sesión
				
			else{
				response.sendRedirect("login");
			}
			
		}
		
		//preguntar primero si la sesión está activa, si está activa le dejamos entrar en 
		//el switch, que pueda navegar. Si está cerrada, que se vaya a login
		
		
//		
		
		// Si Solicita otro case fuera de Switch mandamos a index
		//si alguien va atrás o adelante por el navegador, se genera una petición get
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getPathInfo().substring(1);
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher rd; 
		
		if(request.getSession().getAttribute("userName")!=null){ //dame la sesión y el atributo userName, si no es null, entro en el Switch
		switch(action){
			
		case"altaCliente": // recuperar los datos tecleados en el formulario
							String nombre = request.getParameter("nombre");
							String apellido = request.getParameter("apellido");
							String dni = request.getParameter("dni");
							Cliente cliente = new Cliente(0,nombre, apellido, dni);
							//invocará al controlador adecuado
							DarAltaClienteControllerEjb controlador=new DarAltaClienteControllerEjb();
							controlador.agregar(cliente);
							rd = request.getRequestDispatcher("/index.jsp"); //lo redicionamos a index
							rd.forward(request, response);
							break;
		
		case "buscarPorNombre":
							// recuperar la cadena tecleada en el formulario
							String cadenaNombre = request.getParameter("nombre");
							
							// llamar al controlador adecuado
							BuscarPorNombreControllerEjb controladorBusqueda = new BuscarPorNombreControllerEjb();
							ArrayList<Cliente> resultado = controladorBusqueda.buscarPorNombre(cadenaNombre);
							// meter en el request el arraylist de respuesta
							request.setAttribute("clientes", resultado);
							
							// mandarle un titulo diferente
							request.setAttribute("titulo", "Búsqueda por " + cadenaNombre);
							
							// y redirigir hacia el jsp ListarTodos
							rd = request.getRequestDispatcher("/jsp/listarTodos.jsp");
							rd.forward(request, response);
														
							break;
		case "buscarPorId":
			// recuperar la cadena tecleada en el formulario
			String idABuscar = request.getParameter("idBuscar");
			
			// llamar al controlador adecuado
			BuscarPorIdControllerEjb contBuscarPorId = new BuscarPorIdControllerEjb();
			ArrayList<Cliente> result = contBuscarPorId.buscarPorId(idABuscar);
			// meter en el request el arraylist de respuesta
			request.setAttribute("clientes", result);
			
			// mandarle un titulo diferente
			request.setAttribute("titulo", "Búsqueda por " + idABuscar);
			
			// y redirigir hacia el jsp ListarTodos
			rd = request.getRequestDispatcher("/jsp/formActualizar.jsp");
			rd.forward(request, response);
										
			break;
						
							
							
							
		case "eliminarPorId": //estamos en la petición post, alguien tecleo en el formulario y quiere borrar
			
			//Recuperar el id tecleado, en el formulario
			int id = Integer.parseInt(request.getParameter("id"));
			//tenemos que llamar al controlador adecuado
			EliminarController eliminarEjb = new EliminarControllerEjb();
			eliminarEjb.eliminar(id);
			
			//Aquí en vez de rquest ya está hecho arriba comento 2 lineas y pongo:
			response.sendRedirect("listarTodos");//es como volver a la hacer la petición get	
			//ese listar todos, sería lo mismo que poner ("Ej15_GitHub/Tienda/listarTodos")
			break;
			
		case "actualizar": //recuperar los datos del formulario
			int idCliente =  Integer.parseInt(request.getParameter("id"));
		    String nombresCliente = request.getParameter("nombres");
		    String apellidosCliente = request.getParameter("apellidos");
			String dniCliente = request.getParameter("dni");
			Cliente clienteAct = new Cliente(idCliente,nombresCliente,apellidosCliente, dniCliente);
			ActualizarClienteController actualizarEjb = new ActualizarClienteControllerEjb();

		//	ActualizarClienteControllerEjb controladorAct=new ActualizarClienteControllerEjb();
			actualizarEjb.actualizar(clienteAct);
			response.sendRedirect("listarTodos");
			
			
		//	rd = request.getRequestDispatcher("/listarTodos.jsp"); 
		//	rd.forward(request, response);
			break;
							
			
		} //cierre del swich
		} //cierre del if (parte verdadera)
		
		else{
			if(action.equals("login")){
			
				String userName = request.getParameter("userName");
				String password = request.getParameter("password");
					//invocar al controlador adecuado,
				LoginController loginController = new LoginControllerEjb();
				Usuario usuario = loginController.login(userName, password);
			       //que le encargamos que nos diga si el usuario existe o no en la base de datos
				   //si el usuario existe meter los datos en ese usuario en la sesión
				if(usuario!=null){ //si usuario es diferente de null se mete en la sesión
					//HttpSession para guardar en una sesión los datos del usuario, es una variable
					HttpSession session = request.getSession(false); //para cerrar la sesión que estuviera abierta
					session.invalidate(); //invalidamos la sesión
					session = request.getSession(true); //vuelvo a crear la sesión
					session.setMaxInactiveInterval(30); // periodo de inactividad iniciado, a los 30 segundo se cerraria la sesión
					
					//aqui tengo que rellenar los datos del usuario
					String nombreCompleto = usuario.getNombres() + " " + usuario.getApellidos();
					session.setAttribute("nombreCompleto", nombreCompleto);
					session.setAttribute("userName", usuario.getUserName());
					//al encontrar usuario ya puede ver el menu de index
					rd = request.getRequestDispatcher("/index.jsp"); //una vez ejecutado se va al index
					rd.forward(request, response);
					
				}else{
					//si el usuario no existe redirigir hacia login otra vez
					response.sendRedirect("login");	
				}  
				
			} //fin del caso login
			
		} //fin del else por si quiere loggearse
		
	} // cierre método dopost

} //fin de la clase