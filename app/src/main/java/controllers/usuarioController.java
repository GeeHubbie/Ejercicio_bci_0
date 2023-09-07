package controllers;

import exceptions.InvalidTokenException;
import exceptions.ValidationException;
import models.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import request.UsuarioRequest;
import response.UsuarioResponse;
import service.UsuarioServiceImpl;
import utilities.TokenUtility;
import utilities.Validador;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.UUID;
import org.springframework.http.HttpStatus;
@RestController
public class usuarioController {

    @Autowired
    private UsuarioServiceImpl gestor;

    @Autowired
    private Validador checker;

    @Autowired
    private TokenUtility jwt;

    @Autowired
    private HttpServletRequest request;

    @GetMapping ("/login/{uuid}")
    public ResponseEntity<UsuarioResponse> getUser(@PathVariable String uuid) {

        String token = request.getHeader("token");
        UUID userId = UUID.fromString(uuid);

        if (token == null) {
            throw new InvalidTokenException();
        } else if (token.isEmpty()) {
            throw new InvalidTokenException();
        } else if (!jwt.isTokenValid(token)) {
            throw new InvalidTokenException();
        }

        Optional<Usuario> u = gestor.getUsuarioById(userId);
        if (u.isPresent()) {

            Usuario usu = u.get();
            UsuarioResponse resp = new UsuarioResponse(usu.getEmail(), usu.getPassword());

            String newToken = jwt.getToken(usu);
            resp.setCreated(usu.getCreated());
            resp.setIsActive(usu.getIsActive());
            resp.setLastLogin(usu.getLastLogin());
            resp.setName(usu.getName());
            resp.setToken(newToken);
            resp.setPhoneNumbers(usu.getPhones());

            return new ResponseEntity<>(resp, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping ("/sign-up")
    public ResponseEntity<UsuarioResponse> createUser(@RequestBody UsuarioRequest req){

        if ( req.getEmail() == null ) {
            throw new ValidationException("MISSING EMAIL ADDRESS", 333);
        } else if ( req.getEmail().isEmpty() ) {
            throw new ValidationException("MISSING EMAIL ADDRESS", 333);
        }

        if ( req.getPassword() == null ) {
            throw new ValidationException("MISSING PASSWORD", 444);
        } else if ( req.getPassword().isEmpty() ) {
            throw new ValidationException("MISSING PASSWORD", 444);
        }

// validate email
        if (!checker.emailValidator(req.getEmail())) {
            throw new ValidationException("INVALID EMAIL ADDRESS", 333);
        }
// validate password
        if (!checker.passwordValidator(req.getPassword())) {
            throw new ValidationException("INVALID PASSWORD, PASSWORD DOES NOT MATCH CRITERIA", 444);
        }

        Usuario nuevo = new Usuario(req.getEmail(), req.getPassword());
        nuevo.setIsActive(true); // <-------------------------------------- CHECK ***
        nuevo.setLastLogin(null);
        nuevo.setName(req.getName());
        nuevo.setPhones (req.getUserPhones());
        gestor.saveUsuario(nuevo);

        String newToken = jwt.getToken(nuevo);
        UsuarioResponse resp = new UsuarioResponse(nuevo.getEmail(), nuevo.getPassword());
        resp.setCreated(nuevo.getCreated());
        resp.setIsActive(nuevo.getIsActive());
        resp.setLastLogin(null); // <--------------------------------------- CHECK ***
        resp.setName(nuevo.getName());
        resp.setToken(newToken);
        resp.setPhoneNumbers(nuevo.getPhones());

        return new ResponseEntity<UsuarioResponse> (resp, HttpStatus.CREATED);
    }

}
