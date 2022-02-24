package com.backend.gym.auth.servicios;

import static com.backend.gym.auth.Constantes.LOGCLASS;
import static com.backend.gym.auth.Constantes.LOGMETHOD;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.backend.gym.auth.modelos.Parametro;
import com.backend.gym.auth.repositorios.IParametroRepository;

@Service
public class ParametroService {
	private static final Logger logger = LoggerFactory.getLogger(ParametroService.class);

    @Autowired
    private IParametroRepository parametroRepository;

    /**
     * Consulta el parametro por id
     * @param id
     * @return Parametro
     */
    public Optional<Parametro> obtener(long id) {
    	logger.info(LOGMETHOD+Thread.currentThread().getStackTrace()[1].getMethodName()+LOGCLASS+this.getClass().getSimpleName());
        final Optional<Parametro> parametro= parametroRepository.findById(id);
        return parametro;
    }
    /**
     * Consulta todos los parametros
     * @return List<Parametro>
     */
    public List<Parametro> consultar() {
    	logger.info(LOGMETHOD+Thread.currentThread().getStackTrace()[1].getMethodName()+LOGCLASS+this.getClass().getSimpleName());
        final List<Parametro> parametros = parametroRepository.findAll();
        return parametros;
    }
    /**
     * Crea un nuevo parametro
     * @param Parametro
     * @return Optional<Parametro> 
     */
    public Optional<Parametro> crear(Parametro producto) {
    	logger.info(LOGMETHOD+Thread.currentThread().getStackTrace()[1].getMethodName()+LOGCLASS+this.getClass().getSimpleName());
    	producto.setActivo(true);
    	return Optional.of(parametroRepository.save(producto));
    }
    /**
     * Actualiza un Parametro
     * @param Parametro
     * @return Optional<Parametro>
     */
    public Optional<Parametro> actualizar(Parametro parametro) {
    	logger.info(LOGMETHOD+Thread.currentThread().getStackTrace()[1].getMethodName()+LOGCLASS+this.getClass().getSimpleName());
    	return Optional.of(parametroRepository.save(parametro));
    }
    
    /**
     * Elimina un parametro
     * @param id
     */
    public void eliminar(long id) {
    	logger.info(LOGMETHOD+Thread.currentThread().getStackTrace()[1].getMethodName()+LOGCLASS+this.getClass().getSimpleName());
    	parametroRepository.deleteById(id);
    }
    
    /**
     * Consulta los parametros por tipo
     * @return List<Parametro>
     */
    public List<Parametro> consultarPorTipo(String tipo) {
    	logger.info(LOGMETHOD+Thread.currentThread().getStackTrace()[1].getMethodName()+LOGCLASS+this.getClass().getSimpleName());
    	return  parametroRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
		    List<Predicate> predicates = new ArrayList<>();
		    if (tipo!=null && !tipo.equals("")) {
		        predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("tipo"), tipo)));
		        predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("activo"), true)));
		        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
		    }
		    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("id"), 0)));
		    return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
		});
    }
    
    /**
     * Consulta los parametros por titulo y tipo
     * @return List<Parametro>
     */
    public List<Parametro> consultarPorTituloTipo(String titulo, String tipo) {
    	logger.info(LOGMETHOD+Thread.currentThread().getStackTrace()[1].getMethodName()+LOGCLASS+this.getClass().getSimpleName());
    	return  parametroRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
		    List<Predicate> predicates = new ArrayList<>();
		    if (titulo != null && !titulo.equals("") && tipo!=null && !tipo.equals("")) {
		    	predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("titulo"), titulo)));
		        predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("tipo"), tipo)));
		        predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("activo"), true)));
		        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
		    }
		    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("id"), 0)));
		    return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
		});
    }
    
    /**
     * AObtener una imagen
     * @param String nombre
     * @return Optional<byte[]>
     */
    public Optional<byte[]> obtenerImagen(String nombre) {
    	logger.info(LOGMETHOD+Thread.currentThread().getStackTrace()[1].getMethodName()+LOGCLASS+this.getClass().getSimpleName());
    	File resource;
		try {
			resource = new ClassPathResource(nombre).getFile();
			byte[] imagen= Files.readAllBytes(resource.toPath());
	    	return Optional.of(imagen);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Optional.empty();
		}
    }
}
