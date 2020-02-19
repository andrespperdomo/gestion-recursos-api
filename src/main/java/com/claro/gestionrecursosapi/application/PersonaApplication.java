package com.claro.gestionrecursosapi.application;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.gestionrecursosapi.domain.IPersonaService;
import com.claro.gestionrecursosapi.domain.UsuarioRolEnum;
import com.claro.gestionrecursosapi.domain.UsuarioService;
import com.claro.gestionrecursosapi.entity.PersonaEntity;
import com.claro.gestionrecursosapi.entity.UsuarioEntity;
import com.claro.gestionrecursosapi.excepcion.DataIncorrectaExcepcion;
import com.claro.gestionrecursosapi.excepcion.YaExisteExcepcion;
import com.google.common.hash.Hashing;

@Service
public class PersonaApplication {
 
	@Autowired
	private IPersonaService personaService;    
	@Autowired
	private UsuarioService usuarioService;
	
	public PersonaEntity save(PersonaEntity entity) throws YaExisteExcepcion, DataIncorrectaExcepcion {
		
		// Se crea la persona
		entity = personaService.crear(entity);
		
		// Se crea el usuario por defecto
		UsuarioEntity usuarioEntity = new UsuarioEntity();
		usuarioEntity.setCodpersona(entity.getId());
		usuarioEntity.setUsuario(entity.getNumerodocumento().toString());
		usuarioEntity.setNombre(String.valueOf(entity.getNombre1() + " " + entity.getApellido1()));
		usuarioEntity.setClave(encriptarTexto(entity.getNumerodocumento().toString()));
		usuarioEntity.setCodusuariorol(UsuarioRolEnum.USUARIO.getValue());
		usuarioEntity.setEstado("A");
		usuarioService.save(usuarioEntity);		
		
		return entity;
	}
	
	private String encriptarTexto(String texto) {
		return Hashing.sha256().hashString(texto, StandardCharsets.UTF_8).toString();
	}
	
	
}
