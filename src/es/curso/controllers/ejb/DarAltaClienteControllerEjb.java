package es.curso.controllers.ejb;

import es.curso.controllers.DarAltaClienteController;
import es.curso.model.entity.Cliente;

public class DarAltaClienteControllerEjb  implements DarAltaClienteController{

	// al poner implements add los métodos
	
	@Override
	public void agregar(Cliente cliente) {
		
	/* Aquí tendríamos que poner la lógica del negocio para agregar un cliente
		1. Verificar datos en Hacienda
		2. Agregarlo----> llamar a la capa DAO que es la que se encarga de dar de alta
		3. Enviar email al jefe de Obra
		4. Enviar un email al cliente.     */
	}

}
