package com.practicaFinal.ejb;

import com.practicaFinal.modelo.Categoria;
import com.practicaFinal.modelo.EspecificacionProducto;
import com.practicaFinal.modelo.Restaurante;
import java.util.List;
import javax.ejb.Local;


@Local
public interface EspecificacionProductoFacadeLocal {

    void create(EspecificacionProducto especificacionProducto);

    void edit(EspecificacionProducto especificacionProducto);

    void remove(EspecificacionProducto especificacionProducto);

    EspecificacionProducto find(Object id);

    List<EspecificacionProducto> findAll();

    List<EspecificacionProducto> findRange(int[] range);

    int count();
    
    List<EspecificacionProducto> findByCategory(Categoria cat);
    
    List<EspecificacionProducto> findByTitulo(String titulo);
    
    List<EspecificacionProducto> findByRestaurante(Restaurante restaurante);
    
    List<EspecificacionProducto> findByRestauranteAndTitulo(Restaurante restaurante,String titulo);
}
