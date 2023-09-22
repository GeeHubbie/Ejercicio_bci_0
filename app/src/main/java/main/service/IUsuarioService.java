package main.service;

import main.models.entity.Usuario;

import java.util.Optional;
import java.util.UUID;

public interface IUsuarioService {

    Optional<Usuario> getUsuarioById(UUID id);
    void saveUsuario(Usuario u);

}
