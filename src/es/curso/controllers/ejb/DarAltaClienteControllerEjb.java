package es.curso.controllers.ejb;

import es.curso.controllers.DarAltaClienteController;
import es.curso.model.entity.Cliente;
import es.curso.persistence.model.dao.ClienteDao;
import es.curso.persistence.model.dao.jdbc.ClienteDaoJdbc;

public class DarAltaClienteControllerEjb  implements DarAltaClienteController{

	// al poner implements add los métodos
	
	
	// Me creo un atributo
	private ClienteDao clienteDao; //hago el import
	@Override
	public void agregar(Cliente cliente) {
		
	/* Aquí tendríamos que poner la lógica del negocio para agregar un cliente
		1. Verificar datos en Hacienda
		2. Agregarlo----> llamar a la capa DAO que es la que se encarga de dar de alta
		3. Enviar email al jefe de Obra
		4. Enviar un email al cliente.     */
		
		clienteDao = new ClienteDaoJdbc(); //importo 
		clienteDao.create(cliente);
		
	}

}
