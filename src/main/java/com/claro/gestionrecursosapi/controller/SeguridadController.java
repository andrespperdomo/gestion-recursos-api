package com.claro.gestionrecursosapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.claro.gestionrecursosapi.application.SeguridadApplication;
import com.claro.gestionrecursosapi.entity.UsuarioEntity;

@RestController
@RequestMapping("/api/v1/seguridad")
public class SeguridadController {

	@Autowired
	private SeguridadApplication service;
	 
	@PostMapping
	public Integer POST(@RequestBody UsuarioEntity entity) {
		return service.iniciarSesion(entity);
	}   
	
	@GetMapping
	public UsuarioEntity GET(@RequestParam String usuario) {
		return service.findByUsuario(usuario);
	}
	
}
