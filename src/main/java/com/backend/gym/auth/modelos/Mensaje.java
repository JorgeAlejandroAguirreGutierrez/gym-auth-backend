package com.backend.gym.auth.modelos;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "mensaje")
public class Mensaje {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	@NotNull
    @NotEmpty
    @Column(name = "nombre")
    private String nombre;
	
	@NotNull
    @NotEmpty
    @Column(name = "correo")
	private String correo;
	
	@NotNull
    @NotEmpty
    @Column(name = "observacion")
	private String observacion;
	
	@CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "fecha_creacion")
    private Timestamp fechaCreacion;
	
	public Mensaje() {
		
	}
	
	public Mensaje(String nombre, String correo, String observacion) {
		this.nombre=nombre;
		this.correo=correo;
		this.observacion=observacion;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getCorreo() {
		return correo;
	}
	
	public String getObservacion() {
		return observacion;
	}
	
	public Timestamp getFechaCreacion() {
		return fechaCreacion;
	}
}
