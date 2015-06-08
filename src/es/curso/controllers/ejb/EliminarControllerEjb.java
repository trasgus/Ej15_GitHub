package es.curso.controllers.ejb;

import es.curso.controllers.EliminarController;
import es.curso.persistence.model.dao.ClienteDao;
import es.curso.persistence.model.dao.jdbc.ClienteDaoJdbc;

public class EliminarControllerEjb implements EliminarController {

	@Override
	public void eliminar(int id) {
		//como este controller se puede comun con DAO voy a capa Dao así....
		ClienteDao clienteDao = new ClienteDaoJdbc();
		//verificar si no tiene deudas por ejemplo.... Es la lógica del negocio
		clienteDao.delete(id);
		//mandarle un correo al usuario diciendole q fue dado de baja
		
	}

}
