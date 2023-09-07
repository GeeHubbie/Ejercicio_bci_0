package service;

import models.dao.IUsuarioDao;
import models.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioServiceImpl implements IUsuarioService{

    @Autowired
    private IUsuarioDao usuarioDao;

    @Override
    @Transactional
    public void saveUsuario(Usuario u) {

        usuarioDao.saveUsuario(u);
    }

    @Override
    @Transactional(readOnly=true)
    public Optional<Usuario> getUsuarioById(UUID id) {

        return usuarioDao.getUsuarioById(id);
    }
}
