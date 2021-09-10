package com.practicaFinal.ejb;

import com.practicaFinal.modelo.Articulo;
import com.practicaFinal.modelo.Pedido;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class ArticuloFacade extends AbstractFacade<Articulo> implements ArticuloFacadeLocal {
    
    private static final String FIND_BY_PEDIDO="FROM Articulo a WHERE a.pedido=:pedido";

    @PersistenceContext(unitName = "foodDeliveryPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArticuloFacade() {
        super(Articulo.class);
    }

    @Override
    public List<Articulo> removeLista(List<Articulo> articulos) {
        for(Articulo a : articulos){
            this.remove(a);
        }
        return new ArrayList<Articulo>();
    }

    @Override
    public List<Articulo> findByPedido(Pedido pedido) {
        List<Articulo> articulos=null;
        
        try{
            Query q=em.createQuery(FIND_BY_PEDIDO);
            q.setParameter("pedido", pedido);
            
            articulos=q.getResultList();
        }catch(Exception e ){
            throw e;
        }
        
        return articulos;
    }
    
}
