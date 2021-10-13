package com.practicaFinal.ejb;

import com.practicaFinal.modelo.Articulo;
import com.practicaFinal.modelo.Cliente;
import com.practicaFinal.modelo.Usuario;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class ClienteFacade extends AbstractFacade<Cliente> implements ClienteFacadeLocal {

    @PersistenceContext(unitName = "foodDeliveryPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteFacade() {
        super(Cliente.class);
    }

    
    public static final String FIND_CARRO="SELECT c.carro FROM Cliente c WHERE c=:cliente";
    
    @Override
    public List<Articulo> findCarro(Cliente cliente) {
        
        List<Articulo> carro=null;
        
        try{
            
            Query q=em.createQuery(FIND_CARRO);
            q.setParameter("cliente", cliente);
            
            carro=q.getResultList();
            
        }catch(Exception e ){
            throw e;
        }
        
        //Actualizar los valores de los articulos
        Iterator<Articulo> itCarro=carro.iterator();
        
        while(itCarro.hasNext()){
            Articulo a=itCarro.next();
            
            if(a==null){
                itCarro.remove();
                continue;
            }
            a.setPrecio(a.getProducto().getPrecio());
            a.setPrecioEnvio(a.getProducto().getPrecioEnvio());
        }
        
        return carro;
        
    }
}
