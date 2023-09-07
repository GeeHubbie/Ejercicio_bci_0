package service;

import models.entity.Usuario;

import java.util.Optional;
import java.util.UUID;

public class UsuarioServiceImpl implements IUsuarioService{

//    @Autowired
//    private UsuarioRepository repo;

    @Override
    public Usuario saveUsuario(Usuario u) {
        return null;
//        repo.save(u);
//        return u;
    }

    @Override
    public Optional<Usuario> getUsuarioById(UUID id) {
        return Optional.empty();
//        Optional<Usuario> maybe = repo.findById(id);
//        return maybe;
    }
}
