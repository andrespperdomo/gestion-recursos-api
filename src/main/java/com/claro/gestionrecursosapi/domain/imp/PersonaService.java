package com.claro.gestionrecursosapi.domain.imp;

import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.gestionrecursosapi.domain.IPersonaService;
import com.claro.gestionrecursosapi.entity.PersonaEntity;
import com.claro.gestionrecursosapi.excepcion.DataIncorrectaExcepcion;
import com.claro.gestionrecursosapi.excepcion.NoExisteExcepcion;
import com.claro.gestionrecursosapi.excepcion.YaExisteExcepcion;
import com.claro.gestionrecursosapi.repository.IPersonaRepository;

@Service
@Transactional
public class PersonaService implements IPersonaService {

	@Autowired
	private IPersonaRepository repository;

	@Override
	public PersonaEntity crear(PersonaEntity entity) throws YaExisteExcepcion, DataIncorrectaExcepcion {
		if (entity.isIncorrectData()) {
			throw new DataIncorrectaExcepcion("Todos los campos son obligatorios");
		//} else if (repository.findById(entity.getId()).isPresent()) {
		} else if (repository.findByIdAndCodtipodocumento(entity.getId(), entity.getCodtipodocumento()).isPresent()) {
		//} else if (repository.findFirstByCodtipodocumentoAndNumerodocumento(entity.getCodtipodocumento(), entity.getNumerodocumento()) != null) {
			throw new YaExisteExcepcion("Ya existe una persona con ese numero de documento");
		} else {
			return repository.save(entity);
		}
	}

	@Override
	public PersonaEntity actualizar(int id, PersonaEntity personaEntity)
			throws DataIncorrectaExcepcion, NoExisteExcepcion {
		if (personaEntity.isIncorrectData()) {
			throw new DataIncorrectaExcepcion("Todos los campos son obligatorios");
		} else {
			PersonaEntity personaEntityBuscada = buscarPorId(id);
			personaEntityBuscada.setNombre1(personaEntity.getNombre1());
			personaEntityBuscada.setApellido1(personaEntity.getApellido1());
			personaEntityBuscada.setNombre2(personaEntity.getNombre2());
			personaEntityBuscada.setApellido2(personaEntity.getApellido2());
			personaEntityBuscada.setTelefono(personaEntity.getTelefono());
			personaEntityBuscada.setCorreo(personaEntity.getCorreo());
			personaEntityBuscada.setDireccionresidencia(personaEntity.getDireccionresidencia());
			personaEntityBuscada.setGenero(personaEntity.getGenero());
			personaEntityBuscada.setFechanacimiento(personaEntity.getFechanacimiento());
			return repository.save(personaEntityBuscada);
		}

	}

	@Override
	public PersonaEntity buscarPorId(int id) throws NoExisteExcepcion {
		Optional<PersonaEntity> personaEntity = repository.findById(id);
		if (personaEntity.isPresent()) {
			return personaEntity.get();
		} else {
			throw new NoExisteExcepcion("No existe una persona con ese ID");
		}
	}

	@Override
	public PersonaEntity buscarPorCorreo(String correo) throws NoExisteExcepcion {
		PersonaEntity personaEntity = repository.findByCorreo(correo);
		if (personaEntity == null) {
			throw new NoExisteExcepcion("No existe una persona asociada a ese correo");
		} else {
			return personaEntity;
		}
	}

	@Override
	public Iterable<PersonaEntity> buscarTodos() throws NoExisteExcepcion {
		Iterable<PersonaEntity> resultadoConsulta = repository.findAll();
		if (((Collection<?>) resultadoConsulta).size() > 0) {
			return resultadoConsulta;
		} else {
			throw new NoExisteExcepcion("No hay resultados");
		}
	}

	@Override
	public boolean eliminar(int id) throws NoExisteExcepcion {
		
		Optional<PersonaEntity> personaEntity = repository.findById(id);

		if (personaEntity == null) {
			throw new NoExisteExcepcion("No existe una persona asociada a ese ID");
		} else {
			return true;
		}

	}

}
