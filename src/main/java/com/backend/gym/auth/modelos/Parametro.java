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
@Table(name = "parametro")
public class Parametro {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
    @Column(name = "titulo")
    private String titulo;
	
	@NotNull
    @NotEmpty
    @Column(name = "valor")
    private String valor;
	
	@NotNull
    @NotEmpty
    @Column(name = "tipo")
    private String tipo;
	
    @Column(name = "activo")
    private boolean activo;
	
	public Parametro() {
		
	}
	
	public Parametro(String titulo, String valor, String tipo, boolean activo) {
		this.titulo=titulo;
		this.valor=valor;
		this.tipo=tipo;
		this.activo=activo;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public String getValor() {
		return valor;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}
