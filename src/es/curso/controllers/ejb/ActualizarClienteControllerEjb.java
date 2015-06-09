package es.curso.controllers.ejb;

import java.util.ArrayList;

import es.curso.controllers.ActualizarClienteController;
import es.curso.model.entity.Cliente;
import es.curso.persistence.model.dao.ClienteDao;
import es.curso.persistence.model.dao.jdbc.ClienteDaoJdbc;

public class ActualizarClienteControllerEjb implements ActualizarClienteController{



	@Override
	public void actualizar(Cliente cliente) {
	ClienteDao clienteDao = new ClienteDaoJdbc();
	//1. Comprobar si está autorizado para modificar
	clienteDao.update(cliente);
	//enviar email al cliente
	//enviar un email a la seg. social
		
	}
}