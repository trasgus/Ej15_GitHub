package es.curso.controllers.ejb;

import java.util.ArrayList;

import es.curso.controllers.ListarTodosController;
import es.curso.model.entity.Cliente;
import es.curso.persistence.model.dao.jdbc.ClienteDaoJdbc;


public class ListarTodosControllerEjb implements ListarTodosController{
   
	
	//Me cro objeto de clase cliente dao lo hacemos así para tener acceso
	//para no tanto new Singleton 
	@Override
	public ArrayList<Cliente> listarTodos() {
		//me creo un objeto de la clase ClienteDao.jdbc
		ClienteDaoJdbc daoCliente = new ClienteDaoJdbc();
		//invoco al método findAll del objeto dao, esto retorna un ArrayList
		ArrayList<Cliente> clientes = daoCliente.findAll();
		
		//findAll retorna ArrayList al controlador lpero este a su vez al Servlet
		//podría haber más instrucciones aquí
		
		return clientes;
	}
		
	}

