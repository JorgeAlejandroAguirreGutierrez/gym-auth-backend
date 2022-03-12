package com.backend.gym.auth.servicios;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.gym.auth.exception.ModeloExistenteException;
import com.backend.gym.auth.modelos.Mensaje;
import com.backend.gym.auth.repositorios.IMensajeRepository;
import static com.backend.gym.auth.Constantes.LOGCLASS;
import static com.backend.gym.auth.Constantes.LOGMETHOD;
import java.util.List;
import java.util.Optional;

@Service
public class MensajeService {
	private static final Logger logger = LoggerFactory.getLogger(MensajeService.class);
    
    @Autowired
    private IMensajeRepository mensajeRepository;

    /**
     * Consulta un mensaje por id
     * @param id
     * @return Optional<Mensaje>
     */
    public Optional<Mensaje> obtener(long id) {
    	logger.info(LOGMETHOD+Thread.currentThread().getStackTrace()[1].getMethodName()+LOGCLASS+this.getClass().getSimpleName());
        final Optional<Mensaje> mensaje= mensajeRepository.findById(id);
        return mensaje;
    }
    /**
     * Consulta todos los clientes
     * @return List<Mensaje>
     */
    public List<Mensaje> consultar() {
    	logger.info(LOGMETHOD+Thread.currentThread().getStackTrace()[1].getMethodName()+LOGCLASS+this.getClass().getSimpleName());
        final List<Mensaje> mensajes = mensajeRepository.findAll();
        return mensajes;
    }
    /**
     * Crea un nuevo mensaje
     * @param Mensaje
     * @return Mensaje 
     */
    public Optional<Mensaje> crear(Mensaje mensaje) {
    	Optional<Mensaje> mensajeExiste=mensajeRepository.obtenerPorCorreo(mensaje.getCorreo());
    	if (mensajeExiste.isPresent()) {
    		throw new ModeloExistenteException();
    	}
    	logger.info(LOGMETHOD+Thread.currentThread().getStackTrace()[1].getMethodName()+LOGCLASS+this.getClass().getSimpleName());
    	return Optional.of(mensajeRepository.save(mensaje));
    }
    /**
     * Actualiza un mensaje
     * @param Auth
     * @return Optional<Usuario>
     */
    public Optional<Mensaje> actualizar(Mensaje mensaje) {
    	logger.info(LOGMETHOD+Thread.currentThread().getStackTrace()[1].getMethodName()+LOGCLASS+this.getClass().getSimpleName());
    	return Optional.of(mensajeRepository.save(mensaje));
    }
    
    /**
     * Elimina un mensaje
     * @param id
     */
    public void eliminar(long id) {
    	logger.info(LOGMETHOD+Thread.currentThread().getStackTrace()[1].getMethodName()+LOGCLASS+this.getClass().getSimpleName());
    	mensajeRepository.deleteById(id);
    }
    
    /**
     * Crea un nuevo mensaje
     * @param Mensaje
     * @return Mensaje 
     */
    public Optional<Mensaje> crearMensaje(String nombre, String correo, String observacion) {
    	Mensaje mensaje=new Mensaje(nombre, correo, observacion);
    	Optional<Mensaje> mensajeExiste=mensajeRepository.obtenerPorCorreo(mensaje.getCorreo());
    	if (mensajeExiste.isPresent()) {
    		throw new ModeloExistenteException();
    	}
    	logger.info(LOGMETHOD+Thread.currentThread().getStackTrace()[1].getMethodName()+LOGCLASS+this.getClass().getSimpleName());
    	return Optional.of(mensajeRepository.save(mensaje));
    }
}
