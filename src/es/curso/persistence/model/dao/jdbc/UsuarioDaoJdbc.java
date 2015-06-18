package es.curso.persistence.model.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import es.curso.model.entity.Usuario;
import es.curso.persistence.model.dao.UsuarioDao;

public class UsuarioDaoJdbc implements UsuarioDao {

	private Connection cx; //para abrir la conexión
	@Override
	public Usuario searchForUserNamePassword(String userName, String password) {
		Usuario usuario = null; //este metodo devuelve Usuario, nos lo creamos y si no lo encuentra devuelve null
		try {
			//abrir conexión
			abrirConexion();
			//2.preparar la sentencia
			PreparedStatement ps = (PreparedStatement) cx.prepareStatement
					("SELECT * FROM USUARIO WHERE userName=? AND password=?");
			//2.1 rellenar los interrogantes
			ps.setString(1, userName); //en el primer interrogante metemos userName
			ps.setString(2, password); //en el segundo interrogante metemos password
			//3.ejecutar la sentencia  (en resultSet)
			//Como es una consulta no hay que hacer commit (que es para confirmar...)
			ResultSet consulta = ps. executeQuery();
			
			if(consulta.next()){
				//traspasar los datos del resultset al usuario
				//establecemos(set) en el objeto usuario lo que cogemos (get) de la base de datos
				usuario = new Usuario(); //parar abrir memoria
				usuario.setId(consulta.getInt("id")); //lo q está entre ()es lo que está en la base de datos
				usuario.setNombres(consulta.getString("nombres"));
				usuario.setApellidos(consulta.getString("apellidos"));
				usuario.setUserName(consulta.getString("userName"));
				usuario.setPassword(consulta.getString("password"));	
			}
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally{
			cerrarConexion();
		}
		
		return usuario;
	}

	private  void abrirConexion(){
		try {    
			Class.forName("com.mysql.jdbc.Driver");
	/* 2. Establecer la conexión 
	 * DriverManager es una clase especializada en crear objeto tipo Connection, no hacemos new
	 * (primer parametro dónde está la base de datos, el segundo el usuario y el tercero la contraseña */
			cx = DriverManager.getConnection("jdbc:mysql://localhost:3306/Tienda",  //añado otro try catch con la ayuda de eclipse
					"rootTienda",
					"rootTienda");
	/* 3. Iniciar el autoCommit en false  es para gestionar TRANSACIONES*/
			cx.setAutoCommit(false); // al tener el autoCommit en falso no se ven si doy de alta uno hasta que hago commit

			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	private void cerrarConexion(){   //para cerrar la conexión
		try {    //hago el try catch con la ayuda de eclipse
			if(cx!=null) // Si fuera diferente a null la cerraria, si fuera null es que nunca se abrió.
			cx.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
		
}
