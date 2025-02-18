package com.backend.gym.auth.servicios;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.gym.auth.Util;
import com.backend.gym.auth.exception.EmpresaNoExisteException;
import com.backend.gym.auth.exception.ModeloExistenteException;
import com.backend.gym.auth.exception.ModeloNoExistenteException;
import com.backend.gym.auth.modelos.Auth;
import com.backend.gym.auth.modelos.Empresa;
import com.backend.gym.auth.repositorios.IAuthRepository;
import com.backend.gym.auth.repositorios.IEmpresaRepository;

import static com.backend.gym.auth.Constantes.LOGCLASS;
import static com.backend.gym.auth.Constantes.LOGMETHOD;
import java.util.List;
import java.util.Optional;

@Service
public class AuthService {
	private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private IAuthRepository authRepository;
    
    @Autowired
    private IEmpresaRepository empresaRepository;

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
     * Consulta todos los auths
     * @return List<Usuario>
     */
    public List<Auth> consultar() {
    	logger.info(LOGMETHOD+Thread.currentThread().getStackTrace()[1].getMethodName()+LOGCLASS+this.getClass().getSimpleName());
        final List<Auth> usuarios = authRepository.findAll();
        return usuarios;
    }
    /**
     * Crea un nuevo auth
     * @param Auth
     * @return Auth 
     */
    public Optional<Auth> crear(Auth auth) {
    	logger.info(LOGMETHOD+Thread.currentThread().getStackTrace()[1].getMethodName()+LOGCLASS+this.getClass().getSimpleName());
    	Optional<Auth> authExiste=authRepository.buscarIdentificacion(auth.getIdentificacion());
    	if (authExiste.isPresent()) {
    		throw new ModeloExistenteException();
    	}
    	Optional<Empresa> empresa=empresaRepository.buscarEndpoint(auth.getEmpresa().getEndpoint());
    	if (empresa.isEmpty()) {
    		throw new EmpresaNoExisteException();
    	}
    	String contrasena=Util.generarContrasena(auth.getIdentificacion());
    	auth.setContrasena(contrasena);
    	auth.setEmpresa(empresa.get());
    	return Optional.of(authRepository.save(auth));
    }
    /**
     * Actualiza un auth
     * @param Auth
     * @return Optional<Usuario>
     */
    public Optional<Auth> actualizar(Auth auth) {
    	logger.info(LOGMETHOD+Thread.currentThread().getStackTrace()[1].getMethodName()+LOGCLASS+this.getClass().getSimpleName());
    	return Optional.of(authRepository.save(auth));
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
