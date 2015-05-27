package es.curso.persistence.model.dao.jdbc;

import java.util.ArrayList;

import es.curso.model.entity.Cliente;
import es.curso.persistence.model.dao.ClienteDao;

public class ClienteDaoJdbc implements ClienteDao{ 
	private ArrayList<Cliente> clientes;


	public ClienteDaoJdbc() {  // Creo constructor vacio y le meto el arrayList para q se conserve en la memoria y también clientes
		super();
		clientes= new ArrayList<Cliente>();
	}

	//Enlazo lenguaje java con sql
	@Override
	public void create(Cliente cliente) {
		/* van las instrucciones para
		  1. Conectar con la base de datos
		  2. Preparar la sentencia -sql- para agregar
		  3. Ejecutar la sentencia -sql- 
		  4. Cerrar la conexión     */
		
	}

	@Override
	public ArrayList<Cliente> findAll() {
	
		clientes.add(new Cliente(1, "José", "Pérez", "1234D"));
		clientes.add(new Cliente(2, "María", "Cáceres", "65657E"));
		clientes.add(new Cliente(3, "Rosa", "Martínez", "888"));
		clientes.add(new Cliente(4, "Rafaél", "Rodriguez", "888H"));
		clientes.add(new Cliente(5, "Almudena", "García", "76767G"));
		clientes.add(new Cliente(6, "Oscar", "Peñalver", "7373k"));
		clientes.add(new Cliente(7, "Teresa", "Jiménez", "7373R"));
		
		
		
		return clientes;
	}

}
