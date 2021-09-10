package com.practicaFinal.ejb;

import com.practicaFinal.modelo.Articulo;
import com.practicaFinal.modelo.Cliente;
import com.practicaFinal.modelo.EstadoPedido;
import com.practicaFinal.modelo.Pedido;
import com.practicaFinal.modelo.Restaurante;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class PedidoFacade extends AbstractFacade<Pedido> implements PedidoFacadeLocal {

    @PersistenceContext(unitName = "foodDeliveryPU")
    private EntityManager em;
    
    @EJB
    private ArticuloFacadeLocal articuloFacade;
    
    private static final String FIND_BY_RESTAURANTE_Y_ESTADO=
            "SELECT DISTINCT a.pedido FROM Articulo a WHERE a.producto.restaurante=:restaurante"
            +" AND a.pedido.estado=:estado";
    private static final String FIND_BY_CLIETNE="FROM Pedido p WHERE p.cliente=:cliente ORDER BY p.fecha DESC";

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PedidoFacade() {
        super(Pedido.class);
    }
    
    @Override
    public void create(Pedido pedido){
        em.persist(pedido);
        
        if(pedido.getArticulos()!=null && pedido.getArticulos().size()>0){
            for(Articulo a: pedido.getArticulos()){
                a.setPedido(pedido);
                articuloFacade.edit(a);
            }
        }
    }

    @Override
    public List<Pedido> findByRestauranteYEstado(Restaurante restaurante, EstadoPedido estado) {

        List<Pedido> pedidos=null;
        
        try{
            Query q=em.createQuery(FIND_BY_RESTAURANTE_Y_ESTADO);
            q.setParameter("estado",estado);
            q.setParameter("restaurante", restaurante);
            
            pedidos=q.getResultList();
        }catch(Exception e){
            throw e;
        }
        
        return pedidos;
    }

    @Override
    public List<Pedido> findByCliente(Cliente cliente) {
        List<Pedido> pedidos=null;
        
        try{
            Query q=em.createQuery(FIND_BY_CLIETNE);
            q.setParameter("cliente",cliente);
            
            pedidos=q.getResultList();
        }catch(Exception e){
            throw e;
        }
        
        return pedidos;    
    }
    
}
