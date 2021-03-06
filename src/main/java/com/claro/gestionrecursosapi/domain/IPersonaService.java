package com.claro.gestionrecursosapi.domain;

import com.claro.gestionrecursosapi.entity.PersonaEntity;
import com.claro.gestionrecursosapi.excepcion.DataIncorrectaExcepcion;
import com.claro.gestionrecursosapi.excepcion.NoExisteExcepcion;
import com.claro.gestionrecursosapi.excepcion.YaExisteExcepcion;

public interface IPersonaService {

	PersonaEntity crear(PersonaEntity entity) throws YaExisteExcepcion, DataIncorrectaExcepcion;
	
	PersonaEntity actualizar(int id, PersonaEntity personaEntity)
			throws DataIncorrectaExcepcion, NoExisteExcepcion;
	
	public PersonaEntity buscarPorId(int id) throws NoExisteExcepcion;
	
	public PersonaEntity buscarPorCorreo(String correo) throws NoExisteExcepcion;
	
	public Iterable<PersonaEntity> buscarTodos() throws NoExisteExcepcion;
	
	public boolean eliminar(int id) throws NoExisteExcepcion;
	
	
	
}

