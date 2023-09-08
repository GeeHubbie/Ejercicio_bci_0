package controller

import exceptions.ErrorMessage
import models.entity.PhoneNumber
import models.entity.Usuario
import request.UsuarioRequest
import spock.lang.Specification
import utilities.TokenUtility
import utilities.Validador

class CrearUsuarioSpec extends Specification{

    void 'Crear un usuario - Caso exitoso - (endpoint: /sign-up)'() {

        given: 'Crear usuario con información enviada, una vez validada'

            Validador validador = new Validador()
            TokenUtility tokenUtil = new TokenUtility()
            UsuarioRequest  ureq = new UsuarioRequest()
            ureq.email = 'user@bci.com'
            ureq.password = 'Password01'
            ureq.nombre = 'Pepe Cortisona'
            PhoneNumber tlf = new PhoneNumber()
            tlf.countryCode = '+56'
            tlf.cityCode = 9
            tlf.number = 71328555
            ureq.userPhones.add(tlf)
            tlf.number = 75251219
            ureq.userPhones.add(tlf)

            Boolean emailValido = validador.emailValidator(ureq.email)

            Boolean pwdValido = validador.passwordValidator(ureq.password)


        when: 'se recibe un request para crear un usuario y los datos recibidos pasan reglas de validación'

            emailValido == true
            pwdValido == true

        then: 'se crea usuario y se responde con registro de usuario y token válido'

            def nuevo = new Usuario()
            nuevo.userId = UUID.randomUUID()
            nuevo.email = 'user@bci.com'
            nuevo.password = 'Password01'
            nuevo.name = 'Pepe Cortisona'
            nuevo.isActive = true
            nuevo.lastLogin = null
            def fono = new PhoneNumber()
            fono.countryCode = '+56'
            fono.cityCode = 9
            fono.number = 71328555
            nuevo.phones.add(fono)
            fono.number = 75251219
            nuevo.phones.add(fono)

            String newToken = tokenUtil.getToken(nuevo)
            HttpStatus == 201 CREATED
    }

    void 'Crear usuario - Caso email inválido - (endpoint: /sign-up)'() {

        given: 'Procesar request de creación de un usuario donde email no cumple criterios de validación'

            def  ureq = new UsuarioRequest()
            ureq.email = 'user@bci'
            ureq.password = 'Password01'
            ureq.nombre = 'Pepe Cortisona'
            def tlf = new PhoneNumber()
            tlf.countryCode = '+56'
            tlf.cityCode = 9
            tlf.number = 71328555
            ureq.userPhones.add(tlf)
            tlf.number = 75251219
            ureq.userPhones.add(tlf)

            // email enviado no cumple reglas de validación
            Validador validador = new Validador()

            Boolean emailValido = validador.emailValidator(ureq.email)

        when: 'email enviado no cumple reglas de validación'

            emailValido == false

        then: 'se rechaza solicitud devolviendo mensaje de error BAD REQUEST'

            ErrorMessage error = new ErrorMessage(333, "INVALID EMAIL ADDRESS")
            HttpStatus == 400 BAD_REQUEST
    }

    void 'Crear usuario - Caso password inválido - (endpoint: /sign-up)'() {

        given: 'Procesar request de creación de un usuario donde el password no cumple criterios de validación'

            def  ureq = new UsuarioRequest()
            ureq.email = 'user@bci.com'
            ureq.password = 'Password'
            ureq.nombre = 'Pepe Cortisona'
            def tlf = new PhoneNumber()
            tlf.countryCode = '+56'
            tlf.cityCode = 9
            tlf.number = 71328555
            ureq.userPhones.add(tlf)
            tlf.number = 75251219
            ureq.userPhones.add(tlf)

            Validador validador = new Validador()
            Boolean pwdValido = validador.passwordValidator(ureq.password)

        when: 'password enviado no cumple reglas de validación'

            pwdValido == false

        then: 'se rechaza solicitud devolviendo mensaje de error BAD REQUEST'

            def ErrorMessage error = new ErrorMessage(444, "INVALID PASSWORD, PASSWORD DOES NOT MATCH CRITERIA")
            HttpStatus == 400 BAD_REQUEST
    }


}
