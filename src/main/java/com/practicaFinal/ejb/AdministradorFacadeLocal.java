package com.practicaFinal.ejb;

import com.practicaFinal.modelo.Administrador;
import java.util.List;
import javax.ejb.Local;


@Local
public interface AdministradorFacadeLocal {

    void create(Administrador administrador);

    void edit(Administrador administrador);

    void remove(Administrador administrador);

    Administrador find(Object id);

    List<Administrador> findAll();

    List<Administrador> findRange(int[] range);

    int count();
    
}
