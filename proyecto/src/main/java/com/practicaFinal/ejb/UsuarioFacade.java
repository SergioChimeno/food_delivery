package com.practicaFinal.ejb;

import com.practicaFinal.modelo.Articulo;
import com.practicaFinal.modelo.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    
    @PersistenceContext(unitName = "foodDeliveryPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    private static final String GET_USUARIO_LOGIN="FROM Usuario u WHERE u.email=:email AND u.contrasena=:contrasena ";
    
    @Override
    public Usuario login(String email, String contrasena){
        
        Usuario usuario=null;
        
        try{
            Query q=em.createQuery(GET_USUARIO_LOGIN);
            q.setParameter("email", email);
            q.setParameter("contrasena", contrasena);
            
            usuario=(Usuario) q.getSingleResult();
        }catch(Exception e){
            throw e;
        }
        
        return usuario;
    }


    
}
