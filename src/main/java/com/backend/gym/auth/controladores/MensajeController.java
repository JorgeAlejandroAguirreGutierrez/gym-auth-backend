package com.backend.gym.auth.controladores;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.backend.gym.auth.modelos.Mensaje;
import com.backend.gym.auth.servicios.MensajeService;

import static com.backend.gym.auth.Constantes.MENSAJECONTROLLER;
import static com.backend.gym.auth.Constantes.LOGCLASS;
import static com.backend.gym.auth.Constantes.LOGMETHOD;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@RestController
@RequestMapping(MENSAJECONTROLLER)
public class MensajeController {
	private static final Logger logger = LoggerFactory.getLogger(MensajeController.class);
	
    @Autowired
    private MensajeService servicio;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> consultar() {
    	logger.info(LOGMETHOD+Thread.currentThread().getStackTrace()[1].getMethodName()+LOGCLASS+this.getClass().getSimpleName());
        List<Mensaje> mensajes=servicio.consultar();
        return new ResponseEntity<>(mensajes, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> obtener(@PathVariable("id") long id) {
    	logger.info(LOGMETHOD+Thread.currentThread().getStackTrace()[1].getMethodName()+LOGCLASS+this.getClass().getSimpleName());
        Optional<Mensaje> mensaje=servicio.obtener(id);
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> crear(@RequestBody @Valid Mensaje _mensaje) {
    	logger.info(LOGMETHOD+Thread.currentThread().getStackTrace()[1].getMethodName()+LOGCLASS+this.getClass().getSimpleName());
        Optional<Mensaje> mensaje=servicio.crear(_mensaje);
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> actualizar(@RequestBody @Valid Mensaje _mensaje) {
    	logger.info(LOGMETHOD+Thread.currentThread().getStackTrace()[1].getMethodName()+LOGCLASS+this.getClass().getSimpleName());
        Optional<Mensaje> mensaje=servicio.actualizar(_mensaje);
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> eliminar(@PathVariable("id") long id)  {
    	logger.info(LOGMETHOD+Thread.currentThread().getStackTrace()[1].getMethodName()+LOGCLASS+this.getClass().getSimpleName());
        servicio.eliminar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    } 
    
    @GetMapping(value="/crearMensaje", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> obtenerPorIdentificacion(@RequestParam("nombre") String nombre, @RequestParam("correo") String correo, @RequestParam("observacion") String observacion) {
    	logger.info(LOGMETHOD+Thread.currentThread().getStackTrace()[1].getMethodName()+LOGCLASS+this.getClass().getSimpleName());
        Optional<Mensaje> mensaje=servicio.crearMensaje(nombre, correo, observacion);
        if (mensaje.isPresent()) {
        	return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
        	return new ResponseEntity<>(false, HttpStatus.OK);
        }
    }
}
