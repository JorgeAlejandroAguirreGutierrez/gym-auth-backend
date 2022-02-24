package com.backend.gym.auth.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.backend.gym.auth.modelos.Auth;

@Repository
public interface IAuthRepository extends JpaRepository<Auth, Long>, JpaSpecificationExecutor<Auth> {
	@Query(value="select a from Auth a "  
			+" WHERE a.identificacion=:identificacion AND a.contrasena=:contrasena")
	public Optional<Auth> obtenerPorIdentificacionContrasena(String identificacion, String contrasena);
	
	@Query(value="select a from Auth a "  
			+" WHERE a.identificacion=:identificacion")
	public Optional<Auth> buscarIdentificacion(String identificacion);
}
