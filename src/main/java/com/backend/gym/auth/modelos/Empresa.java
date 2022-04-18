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
@Table(name = "empresa")
public class Empresa {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	@NotNull
    @Column(name = "nombre")
    private String nombre;
	
	@NotNull
    @NotEmpty
    @Column(name = "endpoint")
	private String endpoint;
	
	@NotNull
    @Column(name="activo")
    private boolean activo;
	
	public Empresa() {
		
	}
	
	public Empresa(String nombre, String endpoint) {
		this.nombre=nombre;
		this.endpoint=endpoint;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getEndpoint() {
		return endpoint;
	}
	
	public boolean isActivo() {
		return activo;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
	
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}
