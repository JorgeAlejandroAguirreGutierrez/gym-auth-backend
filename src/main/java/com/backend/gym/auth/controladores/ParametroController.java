package com.backend.gym.auth.controladores;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.gym.auth.modelos.Parametro;
import com.backend.gym.auth.servicios.ParametroService;

import static com.backend.gym.auth.Constantes.LOGCLASS;
import static com.backend.gym.auth.Constantes.LOGMETHOD;
import static com.backend.gym.auth.Constantes.PARAMETROCONTROLLER;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@RestController
@RequestMapping(PARAMETROCONTROLLER)
@Validated
public class ParametroController {
	private static final Logger logger = LoggerFactory.getLogger(ParametroController.class);
	
    @Autowired
    private ParametroService servicio;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> consultar() {
    	logger.info(LOGMETHOD+Thread.currentThread().getStackTrace()[1].getMethodName()+LOGCLASS+this.getClass().getSimpleName());
        List<Parametro> parametros=servicio.consultar();
        return new ResponseEntity<>(parametros, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> obtener(@PathVariable("id") long id) {
    	logger.info(LOGMETHOD+Thread.currentThread().getStackTrace()[1].getMethodName()+LOGCLASS+this.getClass().getSimpleName());
        Optional<Parametro> parametro=servicio.obtener(id);
        return new ResponseEntity<>(parametro, HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> crear(@RequestBody @Valid Parametro _parametro) {
    	logger.info(LOGMETHOD+Thread.currentThread().getStackTrace()[1].getMethodName()+LOGCLASS+this.getClass().getSimpleName());
        Optional<Parametro> producto=servicio.crear(_parametro);
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> actualizar(@RequestBody @Valid Parametro _parametro) {
    	logger.info(LOGMETHOD+Thread.currentThread().getStackTrace()[1].getMethodName()+LOGCLASS+this.getClass().getSimpleName());
        Optional<Parametro> parametro=servicio.actualizar(_parametro);
        return new ResponseEntity<>(parametro, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> eliminar(@PathVariable("id") long id)  {
    	logger.info(LOGMETHOD+Thread.currentThread().getStackTrace()[1].getMethodName()+LOGCLASS+this.getClass().getSimpleName());
        servicio.eliminar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @GetMapping(value = "/consultarPorTipo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> consultarPorTipo(@RequestParam("tipo") String tipo) {
    	logger.info(LOGMETHOD+Thread.currentThread().getStackTrace()[1].getMethodName()+LOGCLASS+this.getClass().getSimpleName());
        List<Parametro> parametros=servicio.consultarPorTipo(tipo);
        return new ResponseEntity<>(parametros, HttpStatus.OK);
    }
    
    @GetMapping(value = "/consultarPorTituloTipo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> consultarPorTituloTipo(@RequestParam("titulo") String titulo, @RequestParam("tipo") String tipo) {
    	logger.info(LOGMETHOD+Thread.currentThread().getStackTrace()[1].getMethodName()+LOGCLASS+this.getClass().getSimpleName());
        List<Parametro> parametros=servicio.consultarPorTituloTipo(titulo, tipo);
        return new ResponseEntity<>(parametros, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/obtenerImagen")
    public HttpEntity<?> obtenerImagen(@RequestParam("nombre") String nombre) {
    	logger.info(LOGMETHOD+Thread.currentThread().getStackTrace()[1].getMethodName()+LOGCLASS+this.getClass().getSimpleName());
        Optional<byte[]> imagen= servicio.obtenerImagen(nombre);
        if (!imagen.isEmpty()) {
        	HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            headers.setContentLength(imagen.get().length);
            return new HttpEntity<byte[]>(imagen.get(), headers);
        }
        return new HttpEntity<>(null);
    }
}
