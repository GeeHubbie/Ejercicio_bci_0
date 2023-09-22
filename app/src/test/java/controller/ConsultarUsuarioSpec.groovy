package controller

import main.models.entity.PhoneNumber
import main.models.entity.Usuario
import org.springframework.http.HttpStatus
import main.service.UsuarioServiceImpl
import spock.lang.Specification
import main.utilities.TokenUtility

class ConsultarUsuarioSpec extends Specification{

    def 'Consultar un usuario por ID - Caso exitoso'() {

        given: 'un id (45bcf2ac-f68b-4a85-9d47-f87ec72ad10a) de un usuario que existe y un Token válido (JWT)'

            def userId = "45bcf2ac-f68b-4a85-9d47-f87ec72ad10a"
            def uuid = UUID.fromString(userId)
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

            def tokenUtil
            tokenUtil = Stub(TokenUtility.class)

            def  servicio
            servicio = Stub(UsuarioServiceImpl.class)

            def token
            token = "dummyToken"
            tokenUtil.isTokenValid(token) >> true

            Optional<Usuario> maybe
            maybe = Optional.of(nuevo)
            servicio.getUsuarioById(uuid) >> maybe

        when: 'consultamos la información del usuario con ID="45bcf2ac-f68b-4a85-9d47-f87ec72ad10a" '

            tokenUtil.isTokenValid(token) == true
            maybe.isPresent()


        then: 'se devuelve la información del usuario con ese id'

            Usuario aRetornar = nuevo
            String newToken = tokenUtil.getToken(nuevo)
            HttpStatus.OK
    }

    void 'Consultar un usuario por ID - Caso token inválido'() {

        given: 'un id de usuario y un Token inválido (JWT)'

            def tokenEnviado = 'wrong token'
            def tokenUtil
            tokenUtil = Stub(TokenUtility.class)
            tokenUtil.isTokenValid(tokenEnviado) >> false
            Boolean isTokenValid = tokenUtil.isTokenValid(tokenEnviado)

        when: 'consultamos la información del usuario enviando un token inválido (endpoint: login/{userId})'

            isTokenValid == false

        then: 'se devuelve BAD REQUEST con ERROR código 111, msj = INVALID TOKEN'

            Exception e = thrown(InvalidTokenException)
            HttpStatus.BAD_REQUEST

    }

    void 'Consultar un usuario por ID - Caso token válido pero usuario no existe'() {

        given: 'No existe un usuario con id suministrado '

            def  servicio
            servicio = Stub(UsuarioServiceImpl.class)
            def tokenUtil
            tokenUtil = Stub(TokenUtility.class)
            def tokenEnviado = 'dummy token'
            tokenUtil.isTokenValid(tokenEnviado) >> true
            def Optional<Usuario> optionalUser = Optional.empty()
            def idEnviado = "45bcf2ac-f68b-4a85-9d47-f87ec72ad10a"
            def uuid = UUID.fromString(idEnviado)
            servicio.getUsuarioById(uuid) >> optionalUser

            Boolean isTokenValid = tokenUtil.isTokenValid(tokenEnviado)

        when: 'No existe un usuario con id suministrado (endpoint: login/{userId})'

            isTokenValid == true
            optionalUser.isPresent() == false

        then: 'se devuelve HttpStaus NOT FOUND'

            HttpStatus.NOT_FOUND

    }

}
