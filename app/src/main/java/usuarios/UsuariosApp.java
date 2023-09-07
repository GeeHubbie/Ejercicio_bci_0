package usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UsuariosApp {

    public static void main(String[] args){
        SpringApplication.run(UsuariosApp.class, args);
        System.out.println("Corriendo con Spring");
    }
}
