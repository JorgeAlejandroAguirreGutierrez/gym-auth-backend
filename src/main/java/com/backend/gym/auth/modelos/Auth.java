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
@Table(name = "auth")
public class Auth {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	@NotNull
    @NotEmpty
    @Column(name = "identificacion")
    private String identificacion;
	
	@NotNull
    @NotEmpty
    @Column(name = "contrasena")
	private String contrasena;
	
	@NotNull
    @NotEmpty
    @Column(name = "empresa")
	private String empresa;
	
	@NotNull
    @Column(name="activo")
    private boolean activo;
	
	public Auth() {
		
	}
	
	public Auth(String identificacion, String contrasena, String empresa, boolean activo ) {
		this.identificacion=identificacion;
		this.contrasena=contrasena;
		this.empresa=empresa;
		this.activo=activo;
	}
	
	public long getId() {
		return id;
	}
	
	public String getIdentificacion() {
		return identificacion;
	}
	
	public String getContrasena() {
		return contrasena;
	}
	
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
	public String getEmpresa() {
		return empresa;
	}
	
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	
	public boolean isActivo() {
		return activo;
	}
	
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}
