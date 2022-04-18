package com.backend.gym.auth.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.backend.gym.auth.modelos.Empresa;

@Repository
public interface IEmpresaRepository extends JpaRepository<Empresa, Long>, JpaSpecificationExecutor<Empresa> {
	@Query(value="select e from Empresa e "  
			+" WHERE e.endpoint=:endpoint")
	public Optional<Empresa> buscarEndpoint(String endpoint);
}
