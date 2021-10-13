package com.practicaFinal.ejb;

import com.practicaFinal.modelo.Articulo;
import com.practicaFinal.modelo.Cliente;
import com.practicaFinal.modelo.Usuario;
import java.util.List;
import javax.ejb.Local;


@Local
public interface ClienteFacadeLocal {

    void create(Cliente cliente);

    void edit(Cliente cliente);

    void remove(Cliente cliente);

    Cliente find(Object id);

    List<Cliente> findAll();

    List<Cliente> findRange(int[] range);

    int count();
    
    List<Articulo> findCarro(Cliente cliente);
}
