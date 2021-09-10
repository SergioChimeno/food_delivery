package com.practicaFinal.ejb;

import com.practicaFinal.modelo.Categoria;
import com.practicaFinal.modelo.EspecificacionProducto;
import com.practicaFinal.modelo.Restaurante;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;



@Stateless
public class EspecificacionProductoFacade extends AbstractFacade<EspecificacionProducto> implements EspecificacionProductoFacadeLocal {
    
    private static final String FIND_BY_CATEGORIA="FROM EspecificacionProducto e WHERE e.categoria = :categoria";
    private static final String FIND_BY_TITULO="FROM EspecificacionProducto e WHERE LOWER(e.titulo) LIKE CONCAT('%',LOWER(:titulo),'%')";
    private static final String FIND_BY_RESTAURANTE="FROM EspecificacionProducto e WHERE e.restaurante = :restaurante";
    private static final String FIND_BY_RESTAURANTE_AND_TITULO="FROM EspecificacionProducto e WHERE e.restaurante = :restaurante AND LOWER(e.titulo) LIKE CONCAT('%',LOWER(:titulo),'%')";
    
    @PersistenceContext(unitName = "foodDeliveryPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EspecificacionProductoFacade() {
        super(EspecificacionProducto.class);
    }

    @Override
    public List<EspecificacionProducto> findByCategory(Categoria cat) {
        
        List<EspecificacionProducto> especificacionesProductos=null;
        
        try{
            Query q=em.createQuery(FIND_BY_CATEGORIA);
            q.setParameter("categoria", cat);
            
            especificacionesProductos=q.getResultList();
        }catch(Exception e){
            throw e;
        }
        
        return especificacionesProductos;
    }

    @Override
    public List<EspecificacionProducto> findByTitulo(String titulo) {
        List<EspecificacionProducto> especificacionesProductos=null;
        
        try{
            Query q=em.createQuery(FIND_BY_TITULO);
            q.setParameter("titulo", titulo);

            especificacionesProductos=q.getResultList();
        }catch(Exception e){
            throw e;
        } 

        return especificacionesProductos;
    }

    @Override
    public List<EspecificacionProducto> findByRestaurante(Restaurante restaurante) {
        List<EspecificacionProducto> especificacionesProductos=null;
        
        try{
            Query q=em.createQuery(FIND_BY_RESTAURANTE);
            q.setParameter("restaurante", restaurante);

            especificacionesProductos=q.getResultList();
        }catch(Exception e){
            throw e;
        } 

        return especificacionesProductos;
    }

    @Override
    public List<EspecificacionProducto> findByRestauranteAndTitulo(Restaurante restaurante, String titulo) {
        List<EspecificacionProducto> especificacionesProductos=null;
        
        try{
            Query q=em.createQuery(FIND_BY_RESTAURANTE_AND_TITULO);
            q.setParameter("restaurante", restaurante);
            q.setParameter("titulo", titulo);

            especificacionesProductos=q.getResultList();
        }catch(Exception e){
            throw e;
        } 

        return especificacionesProductos;    }
    
}
