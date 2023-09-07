package utilities;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import models.entity.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.auth0.jwt.JWT;

import java.util.Date;
import java.util.UUID;

@Component
public class TokenUtility {

    private static final long EXPIRE_DURATION = 60 * 60 * 1000; // token valid x 1 hour

    @Value(("${app.jwt.secret}"))
    private String SECRET_KEY;

    private final Algorithm algo = Algorithm.HMAC256(SECRET_KEY);

    private final JWTVerifier checa = JWT.require(algo)
            .withIssuer("Ejercicio-BCI")
            .build();

    public  String getToken(Usuario user) {

        return JWT.create()
                .withSubject(String.format("%s,%s", user.getUserId(), user.getEmail()))
                .withIssuer("Ejercicio-BCI")
                .withClaim("ID_USUARIO", user.getUserId().toString())
                .withIssuedAt(new Date())
                .withJWTId(UUID.randomUUID().toString())
                .sign(algo);
    }

    public Boolean isTokenValid(String token) {

        if (token.isEmpty()) {
            return false;
        } else {

// se valida q el token se decodifique OK

            return !(checa.verify(token).getToken().isEmpty()) ;
        }
    }
}
