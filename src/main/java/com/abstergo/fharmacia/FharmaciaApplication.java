package com.abstergo.fharmacia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FharmaciaApplication {

    public static void main(String[] args) {
        SpringApplication.run(FharmaciaApplication.class, args);

        System.out.println("Olá Mundo Astergo");
    }

}
