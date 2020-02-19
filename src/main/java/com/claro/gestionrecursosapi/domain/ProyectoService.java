package com.claro.gestionrecursosapi.domain;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.claro.gestionrecursosapi.entity.ProyectoEntity;
import com.claro.gestionrecursosapi.repository.IProyectoRepository;

@Service
@Validated
public class ProyectoService implements ICrudService<ProyectoEntity, Integer> {

	@Autowired
	private IProyectoRepository repository;
	
	@Override
	public ProyectoEntity save(ProyectoEntity entity) {
		return repository.save(entity);
	}

	@Override
	public Iterable<ProyectoEntity> saveAll(Iterable<ProyectoEntity> entities) {
		return repository.saveAll(entities);
	}

	@Override
	public Optional<ProyectoEntity> findById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return repository.existsById(id);
	}

	@Override
	public Iterable<ProyectoEntity> findAll() {
		return repository.findAll();
	}

	@Override
	public long count() {
		return repository.count();
	}

	@Override
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}

	@Override
	public void delete(ProyectoEntity entity) {
		repository.delete(entity);
	}

	@Override
	public void deleteAll(Iterable<ProyectoEntity> entities) {
		repository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
	}	
}
