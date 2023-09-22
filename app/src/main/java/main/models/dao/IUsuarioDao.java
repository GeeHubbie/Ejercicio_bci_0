package main.models.dao;

import main.models.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface IUsuarioDao {

    Optional<Usuario> getUsuarioById(UUID id);
    void saveUsuario(Usuario u);
}
