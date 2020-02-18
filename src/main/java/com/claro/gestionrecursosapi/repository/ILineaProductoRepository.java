package com.claro.gestionrecursosapi.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.claro.gestionrecursosapi.entity.LineasproductoEntity;
import com.claro.gestionrecursosapi.excepcion.YaExisteExcepcion;

@Repository
public interface ILineaProductoRepository extends CrudRepository<LineasproductoEntity, Integer> {
	
	Optional<LineasproductoEntity> findByIdAndNombre(int id, String nombreProducto) throws YaExisteExcepcion;
	

}
