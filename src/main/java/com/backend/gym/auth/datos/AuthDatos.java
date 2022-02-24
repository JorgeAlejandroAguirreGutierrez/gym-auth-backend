package com.backend.gym.auth.datos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.backend.gym.auth.repositorios.IAuthRepository;
import com.backend.gym.auth.modelos.Auth;

@Component
@Order(1)
public class AuthDatos implements ApplicationRunner {
	@Autowired
    private IAuthRepository rep;

    @Override
    public void run(ApplicationArguments args) throws Exception {
    	Optional<Auth> ant=rep.findById((long) 1);
        if (!ant.isPresent()) {
            List<Auth> auths= new ArrayList<>();
            auths.add(new Auth("15956607", "admin123", "empresa1", true));
            rep.saveAll(auths);
        }
    }
}
