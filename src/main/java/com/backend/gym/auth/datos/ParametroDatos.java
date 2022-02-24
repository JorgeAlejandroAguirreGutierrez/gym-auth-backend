package com.backend.gym.auth.datos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.backend.gym.auth.modelos.Parametro;
import com.backend.gym.auth.repositorios.IParametroRepository;

@Component
@Order(1)
public class ParametroDatos implements ApplicationRunner {
	@Autowired
    private IParametroRepository rep;

    @Override
    public void run(ApplicationArguments args) throws Exception {
    	Optional<Parametro> ant=rep.findById((long) 1);
        if (!ant.isPresent()) {
            List<Parametro> parametros= new ArrayList<>();
            //INICIO
            parametros.add(new Parametro("", "inicio.jpg", "INICIO", true));
            //FONDO INICIO SESION
            parametros.add(new Parametro("", "fondoiniciosesion.jpg", "FONDOINICIOSESION", true));
            //DESCRIPCION SLIDE
            parametros.add(new Parametro("", "Bienvenido a Gym-Up!_Gestionar tu gimnasio nunca había sido tan facil", "CONCEPTOSLIDE", true));
            parametros.add(new Parametro("", "Centralización_de la información de tus usuarios", "CONCEPTOSLIDE", true));
            parametros.add(new Parametro("", "Muestrale la evolución_del plan de entrenamiento a tus clientes", "CONCEPTOSLIDE", true));
            parametros.add(new Parametro("", "Reduce los tiempos_en la gestión de tus usuarios", "CONCEPTOSLIDE", true));
            //MEDIDAS
            parametros.add(new Parametro("KILOGRAMOS", "KG", "MEDIDAPESO", true));
            parametros.add(new Parametro("LIBRAS", "LB", "MEDIDAPESO", true));
            parametros.add(new Parametro("SEGUNDOS", "SEG", "MEDIDATIEMPO", true));
            parametros.add(new Parametro("MINUTOS", "MIN", "MEDIDATIEMPO", true));
            parametros.add(new Parametro("HORAS", "HORAS", "MEDIDATIEMPO", true));
            rep.saveAll(parametros);
        }
    }
}
