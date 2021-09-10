package com.practicaFinal.ejb;

import com.practicaFinal.modelo.Administrador;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class AdministradorFacade extends AbstractFacade<Administrador> implements AdministradorFacadeLocal {

    @PersistenceContext(unitName = "foodDeliveryPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdministradorFacade() {
        super(Administrador.class);
    }
    
}
