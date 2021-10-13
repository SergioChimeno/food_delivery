package com.practicaFinal.ejb;

import com.practicaFinal.modelo.Direccion;
import com.practicaFinal.modelo.Pedido;
import com.practicaFinal.modelo.Usuario;
import java.util.List;
import javax.ejb.Local;


@Local
public interface DireccionFacadeLocal {

    void create(Direccion direccion);

    void edit(Direccion direccion);

    void remove(Direccion direccion);

    Direccion find(Object id);

    List<Direccion> findAll();

    List<Direccion> findRange(int[] range);

    int count();
    
    List<Direccion> findByUsuario(Usuario usuario);
    
    Direccion findByPedido(Pedido pedido);
}
