package com.backend.gym.auth;
import org.springframework.stereotype.Component;

@Component
public class Util {
	
	public static String generarContrasena(String identificacion){
        String codigo = Constantes.GIMNASIO+identificacion;
        return codigo;
    }
}
