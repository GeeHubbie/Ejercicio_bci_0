package utilities

import main.utilities.PasswordEncryptor
import spock.lang.Specification

class PasswordEncryptorSpec extends Specification{

    def 'Dado un valor, verificar que al encriptarlo y desencriptarlo, el obtenido es igual al original'() {

        given: 'un valor que se desea encriptar'
            String original = "OriginaL"
            def krypto = new PasswordEncryptor()
            String encriptado = krypto.convertToDatabaseColumn(original)
        when: 'se desencripta el valor encriptado ...'
            String devuelta = krypto.convertToEntityAttribute(encriptado)
        then: 'el valor obtenido es igual al original'
            original == devuelta
    }
}
