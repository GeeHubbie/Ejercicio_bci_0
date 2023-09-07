package service;

import models.entity.Usuario;

import java.util.Optional;
import java.util.UUID;

public interface IUsuarioService {

    Usuario saveUsuario(Usuario u);
    Optional<Usuario> getUsuarioById(UUID id);

}
