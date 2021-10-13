package com.practicaFinal.ejb;

import com.practicaFinal.modelo.EspecificacionProducto;
import com.practicaFinal.modelo.Valoracion;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;


@Stateless
public class ValoracionFacade extends AbstractFacade<Valoracion> implements ValoracionFacadeLocal {

    private static final String FIND_BY_ESPECIFICACION_PRODUCTO="FROM Valoracion v WHERE v.producto = :prod ORDER BY v.fecha DESC";
    private static final String FIND_ALL_ORDER_BY_FECHA="FROM Valoracion v ORDER BY v.fecha";
    
    @PersistenceContext(unitName = "foodDeliveryPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ValoracionFacade() {
        super(Valoracion.class);
    }

    @Override
    public List<Valoracion> findByEspecificacionProducto(EspecificacionProducto prod) {
        
        List<Valoracion> valoraciones=null;
    
        try{
            
            Query q = em.createQuery(FIND_BY_ESPECIFICACION_PRODUCTO);
            q.setParameter("prod",prod);
            
            valoraciones=q.getResultList();
        }catch(Exception e){
            throw e;
        }
        
        return valoraciones;
        
    }
    
    @Override
    public List<Valoracion> findAllOrderFecha() {
        List<Valoracion> valoraciones=null;
        
        try{
            Query q = em.createQuery(FIND_ALL_ORDER_BY_FECHA);
            
            valoraciones=q.getResultList();
        }catch(Exception e){
            throw e;
        }
        
        return valoraciones;
    }
    
}
