package es.curso.persistence.model.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.curso.model.entity.Cliente;
import es.curso.persistence.model.dao.ClienteDao;

public class ClienteDaoJdbc implements ClienteDao{ 


	private Connection cx;  //He creado un atributo tipo Connetion. 

	public ClienteDaoJdbc() {  // Creo constructor vacio y le meto el arrayList para q se conserve en la memoria y también clientes
		super();
	
	}

	//Enlazo lenguaje java con sql
	@Override
	public void create(Cliente cliente) {  // en este objeto cliente viene desde form atraveseando capas y recuperar en prepStatement
		try {
			/* van las instrucciones para
			  1. Conectar con la base de datos */
				abrirConexion();
/*    2. Preparar la sentencia -sql- para agregar 
        PreparedStatement me permite  agregar código sql dentro de Java
        importo y hago try cath señalando el bloque con sorround with try/catch block */ 
				
				PreparedStatement ps = cx.prepareStatement("INSERT INTO CLIENTE VALUES(?,?,?,?)"); 
			/*  Si se cae la conexión aquí y or una u otra razón la conexión se queda abierta se Cerrar siempre
			   cuatro INSERT incluido. En heidi values (id, nombre, apellidos, dni) 
     a mano en java seria values (cliente.getId(), cliente.getNombre(), Cliente.getApellido(), Cliente.getDni() */
				/* 2.1 Insertar los datos del cliente en los ? */
				
				ps.setInt(1, 0);//hay que saber que tipos de datos tenemos si son int, 
				               //Strin para poner el método, le pongo 0 pq lo tengo q cree autonumerico el id
				ps.setString(2,  cliente.getNombres());
				ps.setString(3, cliente.getApellidos());
				ps.setString(4, cliente.getDni());
/*	  3. Ejecutar la sentencia -sql- */
				
				ps.executeUpdate(); 
				/*  Es lo mismo que dar al play en heidi(cliente ligero) 
				   Se usa para las instrucciones sql como: insert, delete, update
				   Esta instrucción devulve como resultado el número de registros (o filas) afectadas.
	  3.1 Hacer el commit 
	                   */
				

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally{
     /*	  4. Cerrar la conexión   */
			cerrarConexion();
		}
		
	}

	@Override
	public ArrayList<Cliente> findAll() {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	   try {
		/* 1.Abrir la conexión */
			abrirConexion();
		    
			/*  2.Preparar las sentencias */
			PreparedStatement ps = cx.prepareStatement("SELECT * FROM CLIENTE");
			/*  3.Ejecutar la sentencia ..... */
			ResultSet consulta = ps.executeQuery(); //importo el segundo ResulSet (el de java sql)
			/* Hacemos un método while(consulta.next()) es la manera en la que lo recorremos 
			   Lo que viene de la tabla, lo recorremos y lo pasamos a nuestro ArrayList */
		    /*  3.1 Traspasar los datos de la respuesta al arrayList */
			while(consulta.next()){
				Cliente clienteTemporal = new Cliente(); //me creo un objeto clienteTemporal 
				// aquí habría un código para traspasar la consulta (ResultSet) hacia clienteTemporal
				clienteTemporal.setId(consulta.getInt("id")); 
				//los objetos se rellenan con los métodos set
				// lo que está entre comillas corresponde al nombre del atributo en la base de datos
				clienteTemporal.setNombres(consulta.getString("nombres")); //tiene que poner lo mismo q en la tabla de la bbdd
				clienteTemporal.setApellidos(consulta.getString("apellidos"));
				clienteTemporal.setDni(consulta.getString("dni"));
			
				clientes.add(clienteTemporal); //lo adicionamos al ArrayList	
			}
				
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
		/*  4.Cerrar la conexión */
		finally{
			cerrarConexion();	
		}
			
		return clientes;
	}
	/*  Me creo dos métodos para abrir y cerrar conexión.
	 * 1. Lo primero es determinar
	 	y validar si tengo el driver o conector (de mysql)
	   	Con la ayuda de eclipse lo encierro en un try catch */
	private  void abrirConexion(){
		try {    
			Class.forName("com.mysql.jdbc.Driver");
	/* 2. Establecer la conexión 
	 * DriverManager es una clase especializada en crear objeto tipo Connection, no hacemos new
	 * (primer parametro dónde está la base de datos, el segundo el usuario y el tercero la contraseña */
			cx = DriverManager.getConnection("jdbc:mysql://localhost:3306/Tienda",  //añado otro try catch con la ayuda de eclipse
					"rootTienda",
					"rootTienda");
	/* 3. Iniciar el autoCommit en false */
		//	cx.setAutoCommit(false);

			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	private void cerrarConexion(){
		try {    //hago el try catch con la ayuda de eclipse
			if(cx!=null) // Si fuera diferente a null la cerraria, si fuera null es que nunca se abrió.
			cx.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
   
	@Override
	public ArrayList<Cliente> searchByName(String name) {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		try {
			// 1.Establecer la conexion con la bd
			abrirConexion();
			//  2. Preparar la sentencia sql parametrizada
			PreparedStatement ps = cx.prepareStatement("SELECT * FROM CLIENTE WHERE nombres LIKE ?");
			
			//  2.1 Especificar lo que va en ?
			ps.setString(1, "%" + name + "%");
			// 3. ejecutar la query
			ResultSet resultado = ps.executeQuery(); // como el play de Heidi
			// 3.1 Pasar los datos que vienen de la bbdd (ResultSet) hacia el ArrayList<Cliente>
			while(resultado.next()){
				Cliente c = new Cliente();
//				c.setId(id);
//				c.setNombres(nombres);
//				c.setApellidos(apellidos);
//				c.setDni(dni);
				c.setId(resultado.getInt("id"));
				c.setNombres(resultado.getString("nombres"));//otra forma es darle el numeral pero no se recom
				c.setApellidos(resultado.getString("apellidos"));
				c.setDni(resultado.getString("dni"));
				clientes.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
		// 4.Cerrar la conexion (con el finally siempre)
			cerrarConexion();
		}
		return clientes;
		
	}
	 @Override
		public void update(Cliente cliente){
		//	ArrayList<Cliente> clientes = new ArrayList<Cliente>();
			try {
				// 1.Establecer la conexion con la bd
				abrirConexion();
				//  2. Preparar la sentencia sql parametrizada
				
				PreparedStatement ps = cx.prepareStatement("UPDATE CLIENTE SET NOMBRES=?, APELLIDOS=?, DNI=? WHERE ID=? ");
				//  2.1 Especificar lo que va en ?
				ps.setString(1, cliente.getNombres());
				ps.setString(2, cliente.getApellidos());
				ps.setString(3, cliente.getDni());
				ps.setInt(4,  cliente.getId());
				// 3. ejecutar la sentencia
			    ps.executeUpdate(); // como el play de Heidi
			
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
			// 4.Cerrar la conexion (con el finally siempre)
				cerrarConexion();
			}
			
		}
		@Override
		public void delete (Integer id){
			
			try {
				//establecer conexión
				abrirConexion();
				//2.preparar las sentencias
				PreparedStatement ps= cx.prepareStatement("DELETE FROM CLIENTE WHERE ID=?");
					//2.1 Especificar lo que va en ?
				ps.setInt(1, id); //en el interrogante uno poner lo que pone en el id
				//3.Ejecutar LA SENTENCIA
				ps.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				//4.Cerrar la conexión
				cerrarConexion();
			}
			
		}

		@Override
		public ArrayList<Cliente> searchById(String idABuscar) {
			ArrayList<Cliente> clientes = new ArrayList<Cliente>();
			try {
				// 1.Establecer la conexion con la bd
				abrirConexion();
				//  2. Preparar la sentencia sql parametrizada
				
				PreparedStatement ps = cx.prepareStatement("SELECT * FROM CLIENTE WHERE id LIKE ?");
				//  2.1 Especificar lo que va en ?
				ps.setString(1, idABuscar );
				// 3. ejecutar la query
				ResultSet resultado = ps.executeQuery(); // como el play de Heidi
				// 3.1 Pasar los datos que vienen de la bbdd (ResultSet) hacia el ArrayList<Cliente>
				while(resultado.next()){
					Cliente c = new Cliente();
//					c.setId(id);
//					c.setNombres(nombres);
//					c.setApellidos(apellidos);
//					c.setDni(dni);
					c.setId(resultado.getInt("id"));
					c.setNombres(resultado.getString("nombres"));//otra forma es darle el numeral pero no se recom
					c.setApellidos(resultado.getString("apellidos"));
					c.setDni(resultado.getString("dni"));
					clientes.add(c);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
			// 4.Cerrar la conexion (con el finally siempre)
				cerrarConexion();
			}
			return clientes;
			
			
		}

		@Override
		public ArrayList<Cliente> actualizarById(String idABuscar) {
			ArrayList<Cliente> clientes = new ArrayList<Cliente>();
			try {
				// 1.Establecer la conexion con la bd
				abrirConexion();
				//  2. Preparar la sentencia sql parametrizada
				
				PreparedStatement ps = cx.prepareStatement("UPDATE * FROM CLIENTE WHERE id LIKE ?");
				//  2.1 Especificar lo que va en ?
				ps.setString(1, idABuscar );
				// 3. ejecutar la query
				ResultSet resultado = ps.executeQuery(); // como el play de Heidi
				// 3.1 Pasar los datos que vienen de la bbdd (ResultSet) hacia el ArrayList<Cliente>
				while(resultado.next()){
					Cliente c = new Cliente();
//					c.setId(id);
//					c.setNombres(nombres);
//					c.setApellidos(apellidos);
//					c.setDni(dni);
					c.setId(resultado.getInt("id"));
					c.setNombres(resultado.getString("nombres"));//otra forma es darle el numeral pero no se recom
					c.setApellidos(resultado.getString("apellidos"));
					c.setDni(resultado.getString("dni"));
					clientes.add(c);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
			// 4.Cerrar la conexion (con el finally siempre)
				cerrarConexion();
			}
			return clientes;
			
		}

}
