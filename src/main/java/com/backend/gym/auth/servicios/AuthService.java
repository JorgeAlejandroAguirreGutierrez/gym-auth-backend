package com.backend.gym.auth.servicios;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.gym.auth.exception.ModeloNoExistenteException;
import com.backend.gym.auth.modelos.Auth;
import com.backend.gym.auth.repositorios.IAuthRepository;
import static com.backend.gym.auth.Constantes.LOGCLASS;
import static com.backend.gym.auth.Constantes.LOGMETHOD;
import java.util.List;
import java.util.Optional;

@Service
public class AuthService {
	private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private IAuthRepository authRepository;

    /**
     * Consulta un auth por id
     * @param id
     * @return Auth
     */
    public Optional<Auth> obtener(long id) {
    	logger.info(LOGMETHOD+Thread.currentThread().getStackTrace()[1].getMethodName()+LOGCLASS+this.getClass().getSimpleName());
        final Optional<Auth> usuario= authRepository.findById(id);
        return usuario;
    }
    /**
     * Consulta todos los clientes
     * @return List<Usuario>
     */
    public List<Auth> consultar() {
    	logger.info(LOGMETHOD+Thread.currentThread().getStackTrace()[1].getMethodName()+LOGCLASS+this.getClass().getSimpleName());
        final List<Auth> usuarios = authRepository.findAll();
        return usuarios;
    }
    /**
     * Crea un nuevo cliente
     * @param Auth
     * @return Auth 
     */
    public Optional<Auth> crear(Auth auth) {
    	Optional<Auth> authExiste=authRepository.buscarIdentificacion(auth.getIdentificacion());
    	if (authExiste.isPresent()) {
    		return authExiste;
    	}
    	logger.info(LOGMETHOD+Thread.currentThread().getStackTrace()[1].getMethodName()+LOGCLASS+this.getClass().getSimpleName());
    	return Optional.of(authRepository.save(auth));
    }
    /**
     * Actualiza un auth
     * @param Auth
     * @return Optional<Usuario>
     */
    public Optional<Auth> actualizar(Auth usuario) {
    	logger.info(LOGMETHOD+Thread.currentThread().getStackTrace()[1].getMethodName()+LOGCLASS+this.getClass().getSimpleName());
    	return Optional.of(authRepository.save(usuario));
    }
    
    /**
     * Elimina un auth
     * @param id
     */
    public void eliminar(long id) {
    	logger.info(LOGMETHOD+Thread.currentThread().getStackTrace()[1].getMethodName()+LOGCLASS+this.getClass().getSimpleName());
    	authRepository.deleteById(id);
    }
    
    /**
     * Obtiene el auth por la identificacion
     * @return Optional<Auth>
     */
    public Optional<Auth> obtenerPorIdentificacionContrasena(String identificacion, String contrasena) {
    	Optional<Auth> auth=authRepository.obtenerPorIdentificacionContrasena(identificacion, contrasena);
    	if(auth.isEmpty()) {
    		throw new ModeloNoExistenteException();
    	}
    	return auth;
    }	
}
