package controller;

import org.springframework.http.HttpStatus;
import spock.lang.Specification;

class CrearUsuarioTest extends Specification {

    void setup() {

        def Validador validador = Stub(Validador.class)
        def TokenUtility utility = Stub(TokenUtility.class)
    }

    void 'Crear un usuario - Caso exitoso - (endpoint: /sign-up)'() {

        given: 'Crear usuario con información enviada, una vez validada'

            def UsuarioRequest u {
                u.email = 'user@bci.com'
                u.password = 'Password01'
                u.nombre = 'Pepe Cortisona'
                u.userPhones = Arrays.asList(new PhoneNumber("+56", 9, 71328543L), new PhoneNumber("+56", 9, 75251219L))
            }

            validador.emailValidator(u.email) >> true
            validador.passwordValidator(u.password) >> true
            utility.getToken(nuevo) >> newToken

        when: 'se recibe un request para crear un usuario y los datos recibidos pasan reglas de validación'

            validador.emailValidator(u.email) == true
            validador.passwordValidador(u.password) == true

        then: 'se crea usuario y se responde con registro de usuario y token válido'

            Usuario nuevo {
                nuevo.email = 'user@bci.com'
                nuevo.password = 'Password01'
                nuevo.isActive = true
                nuevo.lastLogin = null
                nuevo.nombre = 'Pepe Cortisona'
                nuevo.userPhones = Arrays.asList(new PhoneNumber("+56", 9, 71328543L), new PhoneNumber("+56", 9, 75251219L))
            }
             Token newToken = utility.getToken(nuevo)
             HttpStatus == 201 CREATED
    }

    void 'Crear usuario - Caso email inválido - (endpoint: /sign-up)'() {

        given: 'Procesar request de creación de un usuario donde email no cumple criterios de validación'

            def UsuarioRequest u {
                u.email = 'user@bci'
                u.password = 'Password101'
                u.nombre = 'Pepe Cortisona'
                u.userPhones = Arrays.asList(new PhoneNumber("+56", 9, 71328543L), new PhoneNumber("+56", 9, 75251219L))
            }

            // email enviado no cumple reglas de validación
            validador.emailValidator(u.email) >> false

        when: 'email enviado no cumple reglas de validación'

            validador.emailValidator(u.email) == false

        then: 'se rechaza solicitud devolviendo mensaje de error BAD REQUEST'

         ErrorMessage error {
                                timestamp = new Date()
                                codigo = 333
                                detail = "INVALID EMAIL ADDRESS"
                            }
        HttpStatus == 400 BAD_REQUEST
    }

    void 'Crear usuario - Caso password inválido - (endpoint: /sign-up)'() {

        given: 'Procesar request de creación de un usuario donde el password no cumple criterios de validación'

        def UsuarioRequest u {
            u.email = 'user@bci.com'
            u.password = 'invalid_password'
            u.nombre = 'Pepe Cortisona'
            u.userPhones = Arrays.asList(new PhoneNumber("+56", 9, 71328543L), new PhoneNumber("+56", 9, 75251219L))
        }

        validador.passwordValidador(u.password) >> false

        when: 'password enviado no cumple reglas de validación'

            validador.passwordValidador(u.password) == false

        then: 'se rechaza solicitud devolviendo mensaje de error BAD REQUEST'

        def ErrorMessage error {
            timestamp = new Date()
            codigo = 444
            detail = "INVALID PASSWORD, PASSWORD DOES NOT MATCH CRITERIA"
        }
        HttpStatus == 400 BAD_REQUEST
    }
}


