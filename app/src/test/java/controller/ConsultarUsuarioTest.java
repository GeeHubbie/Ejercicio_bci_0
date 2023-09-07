package controller;

import service.UsuarioServiceImpl;
import spock.lang.Specification;

class ConsultarUsuarioTest extends Specification {

    public void setup() {

        def userId = "45bcf2ac-f68b-4a85-9d47-f87ec72ad10a"
        def token = 'dummyToken'
        def TokenUtility utility = Stub(TokenUtility.class)
        def Usuario nuevo {
                            email = 'user@dominio.com'
                            password = 'Password01'
                            nombre = 'Pepe Cortisona'
                            isActive = true
                            lastLogin = null
                            userPhones = Arrays.asList(new PhoneNumber("+56", 9, 71328543L), new PhoneNumber("+56", 9, 75251219L))
                        }
        def UsuarioServiceImpl servicio = Stub(UsuarioServiceImpl.class)
        def Optional<Usuario> optionalUser
    }

    void 'Consultar un usuario por ID - Caso exitoso'() {

        given: 'un id de un usuario que existe y un Token válido (JWT)'

            servicio.getUsuarioById(userId) >> Optional(nuevo)

        when: 'consultamos la información del usuario con ID="45bcf2ac-f68b-4a85-9d47-f87ec72ad10a" '

            utility.isTokenValid(token) == true
            Optional(nuevo).isPresent == true


        then: 'se devuelve la información del usuario con ese id'

            Usuario aRetornar = nuevo
            Token token = utility.getToken(nuevo)
            HttpStatus == 200 OK
    }

    void 'Consultar un usuario por ID - Caso token inválido'() {

        given: 'un id de usuario y un Token inválido (JWT)'

            def Boolean isTokenValid = utility.isTokenValid(token)

        when: 'consultamos la información del usuario enviando un token inválido (endpoint: login/{userId})'

            isTokenValid == false

        then: 'se devuelve BAD REQUEST con ERROR código 111, msj = INVALID TOKEN'

            def e = thrown(InvalidTokenException)

            return HttpStatus == HttpStatus.BAD_REQUEST
            ErrorMessage error {
                timestamp = new Date()
                codigo = 111
                detail = "INVALID TOKEN"
            }
    }

    void 'Consultar un usuario por ID - Caso usuario no existe'() {

        given: 'No existe un usuario con id suministrado '

        servicio.getUsuarioById(userId) >> optionalUser
        utility.isTokenValid(token) == true

        when: 'No existe un usuario con id suministrado (endpoint: login/{userId})'

            optionalUser.isPresent() == false

        then: 'se devuelve HttpStaus NOT FOUND'

            HttpStatus == HttpStatus.NOT_FOUND

    }

}
