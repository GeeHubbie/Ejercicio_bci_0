package models.dao;

import models.entity.PhoneNumber;
import models.entity.Usuario;
import models.entity.Usuario_in_BD;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

import java.util.*;

@Repository
public class UsuarioDaoImpl implements IUsuarioDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Usuario> getUsuarioById(UUID id) {

        String sql;
        Usuario user = new Usuario();
        try {
            // primero traemos info de tabla usuarios
             sql = "SELECT * FROM usuarios WHERE usuarios.userid = :id";

            Query query = em.createQuery(sql);
            query.setParameter("id", id);

            Usuario_in_BD userBD = (Usuario_in_BD) query.getSingleResult();
            user.setEmail(userBD.getEmail());
            user.setPassword(userBD.getPassword());
            user.setLastLogin(userBD.getLastLogin());
            user.setIsActive(userBD.getIsActive());
            user.setCreated(userBD.getCreated());

        } catch (NoResultException nre) {
            return Optional.empty();

        } catch (Exception e){
            throw new RuntimeException(e);
        }

        // Dado que el usuario existe, ahora buscamos sus números telefónicos en tabla fonos
        try {
            sql = "SELECT countrycode, citycode, number FROM fonos WHERE fonos.userid = :param ";
            TypedQuery<PhoneNumber> tquery = (TypedQuery<PhoneNumber>) em.createQuery(sql, PhoneNumber.class);
            tquery.setParameter("param", id);
            List<PhoneNumber> tlfs = tquery.getResultList();
            if (!tlfs.isEmpty()) {
                user.setPhones(tlfs);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return Optional.of(user);

    }

    @Override
    public void saveUsuario(Usuario u) {

//        Dividir data entre tabla "Usuarios" y "fonos" y enviar sendos em.persist

        Usuario_in_BD userBD = new Usuario_in_BD(u.getEmail(), u.getPassword());
        userBD.setCreated(u.getCreated());
        userBD.setIsActive(u.getIsActive());
        userBD.setLastLogin(u.getLastLogin());

        try {
            em.persist(userBD);
        } catch (EntityExistsException ex) {
            // asumimos que se quiere actualizar algún valor del usuario
            String sql = "UPDATE usuarios SET email = :email, password = :pwd, "
                        + "created = :created, isactive = :isactive, lastlogin = :lastlogin";
            Query q = em.createQuery(sql);
            q.setParameter("email", u.getEmail());
            q.setParameter("pwd", u.getPassword());
            q.setParameter("created", u.getCreated());
            q.setParameter("isactive", u.getIsActive());
            q.setParameter("lastlogin", u.getLastLogin());

            try {
                q.executeUpdate();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (!u.getPhones().isEmpty()){
            // salvar números telefónicos en tabla "fonos"
            String sql = "INSERT into fonos (userid, countrycode, citycode, number) VALUES (:userid, :pais, :ciudad, :num)";
            Query q = em.createQuery(sql);
            u.getPhones().stream()
                    .map(
                            nro -> {
                                q.setParameter("userid", u.getUserId());
                                q.setParameter("pais", nro.getCountryCode());
                                q.setParameter("ciudad", nro.getCityCode());
                                q.setParameter("num", nro.getNumber());

                                try {
                                    q.executeUpdate();
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }

                                return null;
                            }
                    ) ;
        }

    }
}
