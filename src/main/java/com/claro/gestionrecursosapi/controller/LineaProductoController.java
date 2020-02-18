package com.claro.gestionrecursosapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.claro.gestionrecursosapi.domain.ILineasProductoService;
import com.claro.gestionrecursosapi.entity.LineasproductoEntity;
import com.claro.gestionrecursosapi.excepcion.DataIncorrectaExcepcion;
import com.claro.gestionrecursosapi.excepcion.NoExisteExcepcion;
import com.claro.gestionrecursosapi.excepcion.YaExisteExcepcion;
import com.claro.gestionrecursosapi.model.RespuestaBase;
import com.claro.gestionrecursosapi.model.RespuestaCustomizada;

@RestController
@RequestMapping("/api/v1/lineaproducto")
public class LineaProductoController {
	
	@Autowired
	ILineasProductoService service;
	
	@GetMapping
	public ResponseEntity<RespuestaBase> buscarTodo() {
		try {
			Iterable<LineasproductoEntity> lineaProducto = service.buscarTodos();
			RespuestaCustomizada<Iterable<LineasproductoEntity>> respuesta = new RespuestaCustomizada<>();

			respuesta.setCodigoEstatus(HttpStatus.OK.value());
			respuesta.setMensaje("Consulta exitosa");
			respuesta.setData(lineaProducto);
			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
		} catch (NoExisteExcepcion e) {
			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<RespuestaBase> buscarPorId(@PathVariable int id) {
		try {
			LineasproductoEntity lineasEntity = service.buscarPorId(id);
			RespuestaCustomizada<LineasproductoEntity> respuesta = new RespuestaCustomizada<>();
			respuesta.setCodigoEstatus(HttpStatus.OK.value());
			respuesta.setMensaje("Consulta exitosa");
			respuesta.setData(lineasEntity);
			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
		} catch (NoExisteExcepcion e) {
			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<RespuestaBase> crear(@RequestBody LineasproductoEntity entity) {
		try {
			LineasproductoEntity lineasEntity = service.crear(entity);
			RespuestaCustomizada<LineasproductoEntity> respuesta = new RespuestaCustomizada<>();
			respuesta.setCodigoEstatus(HttpStatus.CREATED.value());
			respuesta.setMensaje("Persona creada");
			respuesta.setData(lineasEntity);
			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.CREATED);
		} catch (YaExisteExcepcion | DataIncorrectaExcepcion e) {
			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.CONFLICT.value(), e.getMessage());
			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.CONFLICT);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<RespuestaBase> actualizar(@PathVariable int id, @RequestBody LineasproductoEntity entity) {
		try {
			LineasproductoEntity lineasEntity = service.actualizar(id, entity);
			RespuestaCustomizada<LineasproductoEntity> respuesta = new RespuestaCustomizada<>();
			respuesta.setCodigoEstatus(HttpStatus.OK.value());
			respuesta.setMensaje("Persona actualizada");
			respuesta.setData(lineasEntity);
			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
		} catch (DataIncorrectaExcepcion e) {
			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.CONFLICT.value(), e.getMessage());
			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.CONFLICT);
		} catch (NoExisteExcepcion e) {
			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
		}
		catch (YaExisteExcepcion e) {
			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<RespuestaBase> eliminar(@PathVariable int id) {

		try {
			boolean isTrue = service.eliminar(id);
			RespuestaBase respuesta = new RespuestaBase();
			respuesta.setCodigoEstatus(HttpStatus.OK.value());
			respuesta.setMensaje("Se elimino registro");
			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
		} catch (NoExisteExcepcion e) {
			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
		}

	}


}
