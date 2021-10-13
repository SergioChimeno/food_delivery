package com.practicaFinal.ejb;

import com.practicaFinal.modelo.EspecificacionProducto;
import com.practicaFinal.modelo.Valoracion;
import java.util.List;
import javax.ejb.Local;


@Local
public interface ValoracionFacadeLocal {

    void create(Valoracion valoracion);

    void edit(Valoracion valoracion);

    void remove(Valoracion valoracion);

    Valoracion find(Object id);

    List<Valoracion> findAll();

    List<Valoracion> findRange(int[] range);

    int count();
    
    List<Valoracion> findByEspecificacionProducto(EspecificacionProducto prod);
    
    List<Valoracion> findAllOrderFecha();
}
