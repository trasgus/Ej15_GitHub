package es.curso.persistence.model.dao;

import java.util.ArrayList;

import es.curso.model.entity.Cliente;



public interface ClienteDao {  //he quitado class y he puesto interface
//Por cada entity q creemos (clases q tendrán reflejo en la base de datos), creamos un DAO
	
	public void create(Cliente cliente);//creo método abstracto  y me importo la clase Cliente
	
	public ArrayList<Cliente> findAll();  //importo
	public ArrayList<Cliente> searchByName(String name);
	public void update (Cliente cliente);
	public void delete (Integer id);

	public ArrayList<Cliente> searchById(String idABuscar);
	public ArrayList<Cliente> actualizarById(String idABuscar);
}
