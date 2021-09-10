package com.practicaFinal.ejb;

import com.practicaFinal.modelo.EspecificacionProducto;
import com.practicaFinal.modelo.Pedido;
import com.practicaFinal.modelo.Restaurante;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class RestauranteFacade extends AbstractFacade<Restaurante> implements RestauranteFacadeLocal {
    
    private final static String GET_BY_ESPECIFICACION_PRODUCTO="SELECT e.restaurante FROM EspecificacionProducto e WHERE e=:especificacion";
    private final static String FIND_BY_TITULO="FROM Restaurante r WHERE LOWER(r.username) LIKE CONCAT('%',LOWER(:titulo),'%')";
    private final static String FIND_BY_PEDIDO="SELECT DISTINCT a.producto.restaurante FROM Pedido a WHERE a.pedido=:pedido";
    
    @PersistenceContext(unitName = "foodDeliveryPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RestauranteFacade() {
        super(Restaurante.class);
    }

    @Override
    public Restaurante findByEspecificacionProducto(EspecificacionProducto prod) {
        Restaurante r =null;
        
        try{
            Query q=em.createQuery(GET_BY_ESPECIFICACION_PRODUCTO);
            q.setParameter("especificacion",prod );
            
            r=(Restaurante) q.getSingleResult();
        }catch(Exception e){
            throw e;
        }
        
        return r;
    }

    @Override
    public List<Restaurante> findByTitle(String titulo) {
        List<Restaurante> restaurantes=null;
        
        try{
            Query q=em.createQuery(FIND_BY_TITULO);
            q.setParameter("titulo", titulo);

            restaurantes=q.getResultList();
        }catch(Exception e){
            throw e;
        } 

        return restaurantes;
    }

    @Override
    public Restaurante findByPedido(Pedido pedido) {
        Restaurante r =null;
        
        try{
            Query q=em.createQuery(FIND_BY_PEDIDO);
            q.setParameter("pedido",pedido );
            
            r=(Restaurante) q.getSingleResult();
        }catch(Exception e){
            throw e;
        }
        
        return r;    
    }
}
