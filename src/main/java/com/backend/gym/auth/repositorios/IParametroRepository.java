package com.backend.gym.auth.repositorios;

import com.backend.gym.auth.modelos.Parametro;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IParametroRepository extends JpaRepository<Parametro, Long>, JpaSpecificationExecutor<Parametro> {

	@Query(value="select p from Parametro p "  
			+" WHERE p.tipo=:tipo")
	public List<Parametro> consultarPorTipo(String tipo);
}
