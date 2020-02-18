package com.claro.gestionrecursosapi.domain;


import com.claro.gestionrecursosapi.entity.LineasproductoEntity;
import com.claro.gestionrecursosapi.excepcion.DataIncorrectaExcepcion;
import com.claro.gestionrecursosapi.excepcion.NoExisteExcepcion;
import com.claro.gestionrecursosapi.excepcion.YaExisteExcepcion;

public interface ILineasProductoService  {


	LineasproductoEntity crear(LineasproductoEntity entity) throws YaExisteExcepcion, DataIncorrectaExcepcion;

	LineasproductoEntity actualizar(int id, LineasproductoEntity entity) throws YaExisteExcepcion,
	DataIncorrectaExcepcion,NoExisteExcepcion;
	
	LineasproductoEntity buscarPorId(int id) throws NoExisteExcepcion;
	
	Iterable<LineasproductoEntity> buscarTodos() throws NoExisteExcepcion;
	
	boolean eliminar(int id) throws NoExisteExcepcion;
}
