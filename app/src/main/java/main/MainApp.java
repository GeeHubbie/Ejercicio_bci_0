package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApp {

    public String saluda() {
        return "Hola Nuevo Mundo!";
    }

    public static void main(String[] args) {

        SpringApplication.run(MainApp.class, args);

        System.out.println("-----> Servidor ARRIBA atendiendo requests ...");
    }
}
