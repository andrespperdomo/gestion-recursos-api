package com.claro.gestionrecursosapi.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the habilidadtipo database table.
 * 
 */
@Entity
@Table(name="habilidadtipo")
@NamedQuery(name="HabilidadtipoEntity.findAll", query="SELECT h FROM HabilidadtipoEntity h")
public class HabilidadtipoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private Timestamp fechacreacion;

	private Timestamp fechamodificacion;

	private String nombre;

	//bi-directional many-to-one association to HabilidadEntity
	@OneToMany(mappedBy="habilidadtipo")
	private List<HabilidadEntity> habilidads;

	public HabilidadtipoEntity() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getFechacreacion() {
		return this.fechacreacion;
	}

	public void setFechacreacion(Timestamp fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public Timestamp getFechamodificacion() {
		return this.fechamodificacion;
	}

	public void setFechamodificacion(Timestamp fechamodificacion) {
		this.fechamodificacion = fechamodificacion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<HabilidadEntity> getHabilidads() {
		return this.habilidads;
	}

	public void setHabilidads(List<HabilidadEntity> habilidads) {
		this.habilidads = habilidads;
	}

	public HabilidadEntity addHabilidad(HabilidadEntity habilidad) {
		getHabilidads().add(habilidad);
		habilidad.setHabilidadtipo(this);

		return habilidad;
	}

	public HabilidadEntity removeHabilidad(HabilidadEntity habilidad) {
		getHabilidads().remove(habilidad);
		habilidad.setHabilidadtipo(null);

		return habilidad;
	}

}