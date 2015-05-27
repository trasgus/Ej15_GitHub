package es.curso.dispatchers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		switch(action){
			case "listarTodos":  //se invocará al controllador adecuado que obtendrá todos los clientes
				                 //Esta petición nos redirige a otra página
				//aquí invoco a controller para rececpionar lo que me manda
				ListarTodosControllerEjb todos= new ListarTodosControllerEjb();
				ArrayList<Cliente> clientes=todos.listarTodos();
				request.setAttribute("clientes", clientes); //se va todo el ArrayList en el objeto request
				todos.listarTodos(); //este método devuelve un ArrayList de cliente
				titulo= "Listado general de clientes";
				break;
				
			case "buscarPorNombre": //se invocará al controlador que haga la consulta por nombre,
				                   //que obtendrá solo los clientes que coincidan con el nombre buscado
				                  //esta petición redirige a otra página
				titulo= "Resultado de la búsqueda por nombre";
				break;
		}
		
		//tengo que redirigir hacia una vista jsp para mostrar los clientes
		RequestDispatcher rd; //importo
		//de alguna manera hay que enviarle a la vista el resultado de la consulta a la base de datos
		rd = request.getRequestDispatcher("/jsp/listarTodos.jsp"); //le digo la vista que quiero mostrar
		request.setAttribute("titulo", titulo);
		request.setAttribute("iva", new Integer(21)); 
		//meto el dato en el request con setAttribute y el new Integer es pq el dato tiene q ser tipo object
		
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getPathInfo().substring(1);
		request.setCharacterEncoding("UTF-8");
		
		switch(action){
		case"altaCliente": // recuperar los datos tecleados en el formulario
							String nombre = request.getParameter("nombre");
							String apellido = request.getParameter("apellido");
							String dni = request.getParameter("dni");
							Cliente cliente = new Cliente(0,nombre, apellido, dni);
							//invocará al controlador adecuado
							DarAltaClienteControllerEjb controlador=new DarAltaClienteControllerEjb();
							controlador.agregar(cliente);
							break;
			
			
			
			
		}
	}

}
