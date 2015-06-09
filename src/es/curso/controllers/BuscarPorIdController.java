package es.curso.controllers;

import java.util.ArrayList;

import es.curso.model.entity.Cliente;

public interface BuscarPorIdController {

	public ArrayList<Cliente> buscarPorId(String idABuscar);
}
