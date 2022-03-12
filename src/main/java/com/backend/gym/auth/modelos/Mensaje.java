package com.backend.gym.auth.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
}
