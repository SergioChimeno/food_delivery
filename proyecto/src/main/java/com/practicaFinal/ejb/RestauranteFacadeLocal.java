package com.practicaFinal.ejb;

import com.practicaFinal.modelo.EspecificacionProducto;
import com.practicaFinal.modelo.Pedido;
import com.practicaFinal.modelo.Restaurante;
import java.util.List;
import javax.ejb.Local;


@Local
public interface RestauranteFacadeLocal {

    void create(Restaurante restaurante);

    void edit(Restaurante restaurante);

    void remove(Restaurante restaurante);

    Restaurante find(Object id);

    List<Restaurante> findAll();

    List<Restaurante> findRange(int[] range);

    int count();
    
    Restaurante findByEspecificacionProducto(EspecificacionProducto prod);
    
    List<Restaurante> findByTitle(String titulo);
    
    Restaurante findByPedido(Pedido pedido);
}
