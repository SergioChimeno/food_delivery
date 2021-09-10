package com.practicaFinal.ejb;

import com.practicaFinal.modelo.Articulo;
import com.practicaFinal.modelo.Pedido;
import java.util.List;
import javax.ejb.Local;


@Local
public interface ArticuloFacadeLocal {

    void create(Articulo articulo);

    void edit(Articulo articulo);

    void remove(Articulo articulo);

    Articulo find(Object id);

    List<Articulo> findAll();

    List<Articulo> findRange(int[] range);

    int count();
    
    List<Articulo> removeLista(List<Articulo> articulos);
    
    List<Articulo> findByPedido(Pedido pedido);
}
