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
import com.backend.gym.auth.modelos.Auth;
import com.backend.gym.auth.servicios.AuthService;
import static com.backend.gym.auth.Constantes.AUTHCONTROLLER;
import static com.backend.gym.auth.Constantes.LOGCLASS;
import static com.backend.gym.auth.Constantes.LOGMETHOD;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@RestController
@RequestMapping(AUTHCONTROLLER)
public class AuthController {
	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	
    @Autowired
    private AuthService servicio;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> consultar() {
    	logger.info(LOGMETHOD+Thread.currentThread().getStackTrace()[1].getMethodName()+LOGCLASS+this.getClass().getSimpleName());
        List<Auth> usuarios=servicio.consultar();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> obtener(@PathVariable("id") long id) {
    	logger.info(LOGMETHOD+Thread.currentThread().getStackTrace()[1].getMethodName()+LOGCLASS+this.getClass().getSimpleName());
        Optional<Auth> usuario=servicio.obtener(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> crear(@RequestBody @Valid Auth _usuario) {
    	logger.info(LOGMETHOD+Thread.currentThread().getStackTrace()[1].getMethodName()+LOGCLASS+this.getClass().getSimpleName());
        Optional<Auth> usuario=servicio.crear(_usuario);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> actualizar(@RequestBody @Valid Auth _auth) {
    	logger.info(LOGMETHOD+Thread.currentThread().getStackTrace()[1].getMethodName()+LOGCLASS+this.getClass().getSimpleName());
        Optional<Auth> usuario=servicio.actualizar(_auth);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> eliminar(@PathVariable("id") long id)  {
    	logger.info(LOGMETHOD+Thread.currentThread().getStackTrace()[1].getMethodName()+LOGCLASS+this.getClass().getSimpleName());
        servicio.eliminar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @GetMapping(value="/obtenerPorIdentificacionContrasena", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> obtenerPorIdentificacion(@RequestParam("identificacion") String identificacion, @RequestParam("contrasena") String contrasena) {
    	logger.info(LOGMETHOD+Thread.currentThread().getStackTrace()[1].getMethodName()+LOGCLASS+this.getClass().getSimpleName());
        Optional<Auth> auth=servicio.obtenerPorIdentificacionContrasena(identificacion, contrasena);
        return new ResponseEntity<>(auth, HttpStatus.OK);
    }
}
