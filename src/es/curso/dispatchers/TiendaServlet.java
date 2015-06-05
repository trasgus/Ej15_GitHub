package es.curso.dispatchers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.curso.controllers.ejb.BuscarPorNombreControllerEjb;
import es.curso.controllers.ejb.DarAltaClienteControllerEjb;
import es.curso.controllers.ejb.ListarTodosControllerEjb;
import es.curso.model.entity.Cliente;

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
		switch(action){
			case "altaCliente": //se debe redirigir hacia el formulario altaCliente
				                rd = request.getRequestDispatcher("/archivos_html/altaClienteView.html");
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
	}
		// Si Solicita otro case fuera de Switch mandamos a index
		//si alguien va atrás o adelante por el navegador, se genera una petición get
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getPathInfo().substring(1);
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher rd = null; 
		
		switch(action){
		case"altaCliente": // recuperar los datos tecleados en el formulario
							String nombre = request.getParameter("nombre");
							String apellido = request.getParameter("apellido");
							String dni = request.getParameter("dni");
							Cliente cliente = new Cliente(0,nombre, apellido, dni);
							//invocará al controlador adecuado
							DarAltaClienteControllerEjb controlador=new DarAltaClienteControllerEjb();
							controlador.agregar(cliente);
							rd = request.getRequestDispatcher("/index.html"); //lo redicionamos a index
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
						
			
		}
	}

}
