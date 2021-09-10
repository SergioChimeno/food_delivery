package com.practicaFinal.ejb;

import com.practicaFinal.modelo.Cliente;
import com.practicaFinal.modelo.EstadoPedido;
import com.practicaFinal.modelo.Pedido;
import com.practicaFinal.modelo.Restaurante;
import java.util.List;
import javax.ejb.Local;


@Local
public interface PedidoFacadeLocal {

    void create(Pedido pedido);

    void edit(Pedido pedido);

    void remove(Pedido pedido);

    Pedido find(Object id);

    List<Pedido> findAll();

    List<Pedido> findRange(int[] range);

    int count();
    
    List<Pedido> findByRestauranteYEstado(Restaurante restaurante,EstadoPedido estado);
    
    List<Pedido> findByCliente(Cliente cliente);
}
