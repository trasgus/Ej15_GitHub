package es.curso.persistence.model.dao.jdbc;

import es.curso.model.entity.Cliente;
import es.curso.persistence.model.dao.ClienteDao;

public class ClienteDaoJdbc implements ClienteDao{

	
	//Enlazo lenguaje java con sql
	@Override
	public void create(Cliente cliente) {
		/* van las instrucciones para
		  1. Conectar con la base de datos
		  2. Preparar la sentencia -sql- para agregar
		  3. Ejecutar la sentencia -sql- 
		  4. Cerrar la conexión     */
		
	}

}
