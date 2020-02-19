package com.claro.gestionrecursosapi.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


/**
 * The persistent class for the proyecto database table.
 * 
 */
@Entity
@Table(name="proyecto")
public class ProyectoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;	
	private Integer codproyectotipo;
	private Integer codpresupuesto;
	private String codigoproyecto;
	private String nombre;
	private String descripcion;
	private byte prioritario;
	private Date fechainicio;
	private Date fechafin;
	@CreationTimestamp
	@Column(updatable = false)
	private Date fechacreacion;
	@UpdateTimestamp
	private Date fechamodificacion;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCodproyectotipo() {
		return codproyectotipo;
	}
	public void setCodproyectotipo(Integer codproyectotipo) {
		this.codproyectotipo = codproyectotipo;
	}
	public Integer getCodpresupuesto() {
		return codpresupuesto;
	}
	public void setCodpresupuesto(Integer codpresupuesto) {
		this.codpresupuesto = codpresupuesto;
	}
	public String getCodigoproyecto() {
		return codigoproyecto;
	}
	public void setCodigoproyecto(String codigoproyecto) {
		this.codigoproyecto = codigoproyecto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public byte getPrioritario() {
		return prioritario;
	}
	public void setPrioritario(byte prioritario) {
		this.prioritario = prioritario;
	}
	public Date getFechafin() {
		return fechafin;
	}
	public void setFechafin(Date fechafin) {
		this.fechafin = fechafin;
	}
	public Date getFechainicio() {
		return fechainicio;
	}
	public void setFechainicio(Date fechainicio) {
		this.fechainicio = fechainicio;
	}
	public Date getFechacreacion() {
		return fechacreacion;
	}
	public void setFechacreacion(Date fechacreacion) {
		this.fechacreacion = fechacreacion;
	}
	public Date getFechamodificacion() {
		return fechamodificacion;
	}
	public void setFechamodificacion(Date fechamodificacion) {
		this.fechamodificacion = fechamodificacion;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}	

}