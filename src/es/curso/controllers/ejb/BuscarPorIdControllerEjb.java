package es.curso.controllers.ejb;

import java.util.ArrayList;

import es.curso.controllers.BuscarPorIdController;
import es.curso.model.entity.Cliente;
import es.curso.persistence.model.dao.ClienteDao;
import es.curso.persistence.model.dao.jdbc.ClienteDaoJdbc;

public class BuscarPorIdControllerEjb implements BuscarPorIdController{

	@Override
	public ArrayList<Cliente> buscarPorId(String idABuscar) {
		ClienteDao clienteDao = new ClienteDaoJdbc();
		return clienteDao.searchById(idABuscar);
		
		
	}

}
