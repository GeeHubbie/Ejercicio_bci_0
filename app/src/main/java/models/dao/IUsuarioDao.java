package models.dao;

import models.entity.Usuario;

import java.util.Optional;
import java.util.UUID;

public interface IUsuarioDao {

    Optional<Usuario> getUsuarioById(UUID id);
    void saveUsuario(Usuario u);
}
