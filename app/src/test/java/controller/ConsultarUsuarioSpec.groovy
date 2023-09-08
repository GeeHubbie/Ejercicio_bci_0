package controller

import exceptions.ErrorMessage
import models.entity.PhoneNumber
import models.entity.Usuario
import spock.lang.Specification
import utilities.TokenUtility
import service.IUsuarioService
import spock.mock.MockingApi

class ConsultarUsuarioSpec extends Specification{

    def  setup() {

        def userId = "45bcf2ac-f68b-4a85-9d47-f87ec72ad10a"
        def token = 'dummyToken'

        def nuevo = new Usuario()
        nuevo.userId = UUID.fromString(userId)
        nuevo.email = 'user@dominio.com'
        nuevo.password = 'Password01'
        nuevo.name = 'Pepe Cortisona'
        nuevo.isActive = true
        nuevo.lastLogin = null
        def tlf = new PhoneNumber()
        tlf.countryCode = '+56'
        tlf.cityCode = 9
        tlf.number = 71328555
        nuevo.phones.add(tlf)
        tlf.number = 75251219
        nuevo.phones.add(tlf)
        TokenUtility utility = Stub()
        IUsuarioService servicio = Stub()
        Optional<Usuario> optionalUser
    }

    def 'Consultar un usuario por ID - Caso exitoso'() {

        given: 'un id de un usuario que existe y un Token válido (JWT)'

        servicio.getUsuarioById(userId) >> Optional(nuevo)

        when: 'consultamos la información del usuario con ID="45bcf2ac-f68b-4a85-9d47-f87ec72ad10a" '

        utility.isTokenValid(token) == true
        Optional(nuevo).isPresent == true


        then: 'se devuelve la información del usuario con ese id'

        Usuario aRetornar = nuevo
        String token = utility.getToken(nuevo)
        HttpStatus == 200 OK
    }

    void 'Consultar un usuario por ID - Caso token inválido'() {

        given: 'un id de usuario y un Token inválido (JWT)'

        def Boolean isTokenValid = utility.isTokenValid(token)

        when: 'consultamos la información del usuario enviando un token inválido (endpoint: login/{userId})'

        isTokenValid == false

        then: 'se devuelve BAD REQUEST con ERROR código 111, msj = INVALID TOKEN'

        def e = thrown(InvalidTokenException)

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
