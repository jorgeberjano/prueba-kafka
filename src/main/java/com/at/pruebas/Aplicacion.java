package com.at.pruebas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author jberjano
 */
@SpringBootApplication
public class Aplicacion {
    
    @Autowired
    private ProductorConsumidor productorConsumidor;

    public static void main(String[] args) {
        SpringApplication.run(Aplicacion.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
//            productorConsumidor.crearProductor();
//            productorConsumidor.crearConsumidor();
            
//            productorConsumidor.producirMensajes();
//            productorConsumidor.consumirMensajes();
        };
    }

}
