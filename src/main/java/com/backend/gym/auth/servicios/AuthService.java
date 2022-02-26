package com.backend.gym.auth.servicios;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import com.backend.gym.auth.exception.ModeloExistenteException;
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
    private IAuthRepository usuarioRepository;

    /**
     * Consulta el cliente por id
     * @param id
     * @return Cliente
     */
    public Optional<Auth> obtener(long id) {
    	logger.info(LOGMETHOD+Thread.currentThread().getStackTrace()[1].getMethodName()+LOGCLASS+this.getClass().getSimpleName());
        final Optional<Auth> usuario= usuarioRepository.findById(id);
        return usuario;
    }
    /**
     * Consulta todos los clientes
     * @return List<Usuario>
     */
    public List<Auth> consultar() {
    	logger.info(LOGMETHOD+Thread.currentThread().getStackTrace()[1].getMethodName()+LOGCLASS+this.getClass().getSimpleName());
        final List<Auth> usuarios = usuarioRepository.findAll();
        return usuarios;
    }
    /**
     * Crea un nuevo cliente
     * @param Auth
     * @return Usuario 
     */
    public Optional<Auth> crear(Auth usuario) {
    	Optional<Auth> usuarioExiste=usuarioRepository.buscarIdentificacion(usuario.getIdentificacion());
    	if (usuarioExiste.isPresent()) {
    		return usuarioExiste;
    	}
    	logger.info(LOGMETHOD+Thread.currentThread().getStackTrace()[1].getMethodName()+LOGCLASS+this.getClass().getSimpleName());
    	return Optional.of(usuarioRepository.save(usuario));
    }
    /**
     * Actualiza un usuario
     * @param Auth
     * @return Optional<Usuario>
     */
    public Optional<Auth> actualizar(Auth usuario) {
    	logger.info(LOGMETHOD+Thread.currentThread().getStackTrace()[1].getMethodName()+LOGCLASS+this.getClass().getSimpleName());
    	return Optional.of(usuarioRepository.save(usuario));
    }
    
    /**
     * Elimina un cliente
     * @param id
     */
    public void eliminar(long id) {
    	logger.info(LOGMETHOD+Thread.currentThread().getStackTrace()[1].getMethodName()+LOGCLASS+this.getClass().getSimpleName());
    	usuarioRepository.deleteById(id);
    }
    
    /**
     * Obtiene el  usuario por la identificacion
     * @return Optional<Usuario>
     */
    public Optional<Auth> obtenerPorIdentificacionContrasena(String identificacion, String contrasena) {
    	Optional<Auth> auth=usuarioRepository.obtenerPorIdentificacionContrasena(identificacion, contrasena);
    	if(auth.isEmpty()) {
    		throw new ModeloNoExistenteException();
    	}
    	return auth;
    }    
}
