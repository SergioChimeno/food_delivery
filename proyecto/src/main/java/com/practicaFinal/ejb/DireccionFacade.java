package com.practicaFinal.ejb;

import com.practicaFinal.modelo.Direccion;
import com.practicaFinal.modelo.Pedido;
import com.practicaFinal.modelo.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class DireccionFacade extends AbstractFacade<Direccion> implements DireccionFacadeLocal {
    
    private static final String FIND_BY_USUARIO="FROM Direccion d WHERE d.usuario = :usuario";
    private static final String FIND_BY_PEDIDO="p.direccion FROM Pedido p WHERE p= :pedido";
    
    @PersistenceContext(unitName = "foodDeliveryPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DireccionFacade() {
        super(Direccion.class);
    }

    @Override
    public List<Direccion> findByUsuario(Usuario usuario) {
        List<Direccion> direcciones=null;
        
        try{
            Query q=em.createQuery(FIND_BY_USUARIO);
            q.setParameter("usuario", usuario);

            direcciones=q.getResultList();
        }catch(Exception e){
            throw e;
        } 
        return direcciones;
    }

    @Override
    public Direccion findByPedido(Pedido pedido) {
        Direccion direccion=null;
        
        try{
            Query q=em.createQuery(FIND_BY_USUARIO);
            q.setParameter("pedido", pedido);

            direccion=(Direccion) q.getSingleResult();
        }catch(Exception e){
            throw e;
        } 
        return direccion;
    }
    
}
