package com.backend.gym.auth.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "auth")
public class Auth {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;
	
	@NotNull
    @NotEmpty
    @Column(name = "identificacion")
    private String identificacion;
	
	@NotNull
    @Column(name = "contrasena")
	private String contrasena;
	
	@NotNull
    @Column(name="activo")
    private boolean activo;
	
	@ManyToOne
    @JoinColumn(name = "empresa_id", nullable = true)
    private Empresa empresa;
	
	public Auth() {
		
	}
	
	public Auth(String identificacion, String contrasena, boolean activo, Empresa empresa ) {
		this.identificacion=identificacion;
		this.contrasena=contrasena;
		this.activo=activo;
		this.empresa=empresa;
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
	
	public boolean isActivo() {
		return activo;
	}
	
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}
	
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
}
