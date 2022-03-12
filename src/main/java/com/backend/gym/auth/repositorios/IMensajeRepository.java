package com.backend.gym.auth.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.backend.gym.auth.modelos.Mensaje;

@Repository
public interface IMensajeRepository extends JpaRepository<Mensaje, Long>, JpaSpecificationExecutor<Mensaje> {
	@Query(value="select m from Mensaje m "
			+" WHERE m.correo=:correo")
	public Optional<Mensaje> obtenerPorCorreo(String correo);
}
