package main.models.dao;

import main.models.entity.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IUsuarioDao {

    Optional<Usuario> getUsuarioById(UUID id);
    void saveUsuario(Usuario u);
}
