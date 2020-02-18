package com.claro.gestionrecursosapi.domain.imp;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.gestionrecursosapi.domain.ILineasProductoService;
import com.claro.gestionrecursosapi.entity.LineasproductoEntity;
import com.claro.gestionrecursosapi.excepcion.DataIncorrectaExcepcion;
import com.claro.gestionrecursosapi.excepcion.NoExisteExcepcion;
import com.claro.gestionrecursosapi.excepcion.YaExisteExcepcion;
import com.claro.gestionrecursosapi.repository.ILineaProductoRepository;

@Service
@Transactional
public class LineaProductoService implements ILineasProductoService {

	@Autowired
	private ILineaProductoRepository repository;

	@Override
	public LineasproductoEntity crear(LineasproductoEntity entity) throws YaExisteExcepcion, DataIncorrectaExcepcion {
		if (entity.isIncorrectData()) {
			throw new DataIncorrectaExcepcion("Todos los campos son obligatorios");
			// } else if (repository.findById(entity.getId()).isPresent()) {
		} else if (repository.findByIdAndNombre(entity.getId(), entity.getNombre()).isPresent()) {
			// } else if
			// (repository.findFirstByCodtipodocumentoAndNumerodocumento(entity.getCodtipodocumento(),
			// entity.getNumerodocumento()) != null) {
			throw new YaExisteExcepcion("Ya existe una persona con ese numero de documento");
		} else {
			return repository.save(entity);
		}
	}

	@Override
	public LineasproductoEntity actualizar(int id, LineasproductoEntity entity)
			throws DataIncorrectaExcepcion, YaExisteExcepcion, NoExisteExcepcion {

		if (entity.isIncorrectData()) {
			throw new DataIncorrectaExcepcion("Todos los campos son obligatorios");
		} else {
			LineasproductoEntity listaProductoEntityBuscada = buscarPorId(id);
			listaProductoEntityBuscada.setNombre(entity.getNombre());
			listaProductoEntityBuscada.setFechamodificacion(new Timestamp(new Date().getTime()));
			return repository.save(listaProductoEntityBuscada);
		}

	}

	@Override
	public LineasproductoEntity buscarPorId(int id) throws NoExisteExcepcion {
		Optional<LineasproductoEntity> entity = repository.findById(id);

		if (entity.isPresent()) {
			return entity.get();
		} else {
			throw new NoExisteExcepcion("No se encontro ningún proveedor con el Id: " + id);
		}
	}

	@Override
	public Iterable<LineasproductoEntity> buscarTodos() throws NoExisteExcepcion {
		Iterable<LineasproductoEntity> lineasEntity = repository.findAll();
		if (((Collection<?>) lineasEntity).size() > 0) {
			return lineasEntity;
		} else {
			throw new NoExisteExcepcion("No se encontraron resultados");
		}
	}

	@Override
	public boolean eliminar(int id) throws NoExisteExcepcion {
		if (repository.findById(id).isPresent()) {
			repository.deleteById(id);
			return true;
		} else {
			throw new NoExisteExcepcion("No se encontro ningún proveedor con el Id: " + id);
		}
	}

}
