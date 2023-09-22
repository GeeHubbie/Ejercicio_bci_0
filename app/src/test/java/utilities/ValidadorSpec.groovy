package utilities

import main.utilities.Validador
import spock.lang.Specification
import spock.lang.Unroll
import sun.security.util.Password

class ValidadorSpec extends Specification{

    @Unroll
    def 'El resultado de ejecutar emailValidator sobre el email: #inputEmail es: #resultadoEsperado'() {

        given: 'Dada una dirección de correo, evaluar si cumple las reglas de validación definidas'
        Validador juez = new Validador()
            Boolean resultado
        when: 'se evalúa la dirección de correo recibida como entrada'
             resultado = juez.emailValidator(inputEmail)
        then:
            resultado == resultadoEsperado
        where:
        inputEmail          | resultadoEsperado
        gee@bci.com         |   true
        gee@bci             |   false
        un4@bci.com         |   false
        3.1416              |   false
        GeeCampos@bci.co    |   true
    }

    @Unroll
    def 'Resultados del validador de password: #inputpwd --> #resultadoEsperado'()
    {
        given: 'determinar si un password cumple los criterios establecidos'
            Validador juez = new Validador()
            Boolean resultado
        when:
            resultado = juez.passwordValidator(inputpwd)
        then:
            resultado == resultadoEsperado
        where:
            inputpwd            |   resultadoEsperado
            Pas5w0rd            |   true
            password            |   false
            Passw0rd            |   false
            minusculas          |   false
            Password            |   false
            Muchos999999        |   true
            Demasiados2023      |   false
    }
}
