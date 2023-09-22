package main.utilities;

import org.springframework.stereotype.Component;

import java.util.function.Predicate;
import java.util.regex.Pattern;

@Component
public class Validador {

    public Boolean emailValidator (String email) {

        Predicate<String> validaEmail = x -> Pattern.matches(x, "^[a-zA-Z]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,}$");

        return validaEmail.test(email);
    }

    public Boolean passwordValidator (String pwd) {

        Predicate<String> validaPwd = x -> Pattern.matches(pwd, "^(?=[^A-Z]*[A-Z])(?=\\D*\\d){2}\\w{8,12}$");

        return validaPwd.test(pwd);

    }

}
