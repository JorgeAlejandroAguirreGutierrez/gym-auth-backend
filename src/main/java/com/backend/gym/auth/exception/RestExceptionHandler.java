package com.backend.gym.auth.exception;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.backend.gym.auth.Constantes;


public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
    public final ResponseEntity<RestExceptionMessage> handleAllExceptions(Exception ex, WebRequest req) {
		List<String> errors=new ArrayList<String>();
		errors.add(ex.getLocalizedMessage());
		errors.add(ex.getMessage());
		errors.add(ex.toString());
		RestExceptionMessage exceptionResponse = new RestExceptionMessage(Constantes.error_codigo_generico,
			ex.getMessage(),
	        errors
        );
		System.out.println(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@ExceptionHandler(ModeloExistenteException.class)
	public final ResponseEntity<RestExceptionMessage> handleModeloExistenteException(
	    ModeloExistenteException ex, WebRequest req) {
		RestExceptionMessage exceptionResponse = new RestExceptionMessage(Constantes.error_codigo_modelo_existente,
	        ex.getMessage(),
	        null
	    );
	    return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ModeloNoExistenteException.class)
	public final ResponseEntity<RestExceptionMessage> handleModeloNoExistenteException(
	    ModeloNoExistenteException ex, WebRequest req) {
		RestExceptionMessage exceptionResponse = new RestExceptionMessage(Constantes.error_codigo_modelo_no_existente,
	        ex.getMessage(),
	        null
	    );
	    return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(SuscripcionInvalidaException.class)
	public final ResponseEntity<RestExceptionMessage> handleSuscripcionInvalidaException(
			SuscripcionInvalidaException ex, WebRequest req) {
		RestExceptionMessage exceptionResponse = new RestExceptionMessage(Constantes.error_codigo_suscripcion_invalida,
	        ex.getMessage(),
	        null
	    );
	    return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(SesionInvalidaException.class)
	public final ResponseEntity<RestExceptionMessage> handleSesionInvalidaException(
			SesionInvalidaException ex, WebRequest req) {
		RestExceptionMessage exceptionResponse = new RestExceptionMessage(Constantes.error_codigo_sesion_invalida, ex.getMessage(),null);
	    return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(EmpresaNoExisteException.class)
	public final ResponseEntity<RestExceptionMessage> handleEmpresaNoExisteException(
	    EmpresaNoExisteException ex, WebRequest req) {
		RestExceptionMessage exceptionResponse = new RestExceptionMessage(Constantes.error_codigo_empresa_no_existe,
	        ex.getMessage(),
	        null
	    );
	    return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        for(FieldError field : ex.getBindingResult().getFieldErrors()) {
            errors.add(field.getField());
        }
        RestExceptionMessage exceptionResponse = new RestExceptionMessage(Constantes.error_codigo_datos_invalidos, ex.getMessage(),errors);
	    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
	
	@Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		RestExceptionMessage exceptionResponse = new RestExceptionMessage(Constantes.error_codigo_datos_invalidos, ex.getMessage(),null);
	    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
