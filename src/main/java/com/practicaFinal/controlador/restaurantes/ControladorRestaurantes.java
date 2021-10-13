package com.practicaFinal.controlador.restaurantes;

import com.practicaFinal.controlador.Utilidades.Utilidades;
import com.practicaFinal.ejb.RestauranteFacadeLocal;
import com.practicaFinal.modelo.Restaurante;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


@Named
@ViewScoped
public class ControladorRestaurantes implements Serializable{
    
    @EJB
    private RestauranteFacadeLocal restaurantesFacade;
    
    private List<Restaurante> restaurantes;
    
    private String textoBusqueda;
    
    @PostConstruct
    public void init(){
        
        try{
            restaurantes=restaurantesFacade.findAll();
        }catch(Exception e){
            Utilidades.lanzarMensajeError("ERROR", "Error al cargar la página");
        }
    }

    public List<Restaurante> getRestaurantes() {
        return restaurantes;
    }

    public void setRestaurantes(List<Restaurante> restaurantes) {
        this.restaurantes = restaurantes;
    }

    public String getTextoBusqueda() {
        return textoBusqueda;
    }

    public void setTextoBusqueda(String textoBusqueda) {
        this.textoBusqueda = textoBusqueda;
    }
    
    public String getResumenInformacion(Restaurante res){
        String info=res.getInformacion();
        
        if(info.length()>=30){
            info=info.substring(0, 30)+"...";
        }
        
        return info;
    }
    
    public void realizarBusqueda(){
        if(textoBusqueda!=null)
            restaurantes=restaurantesFacade.findByTitle(textoBusqueda);
    }
    
    public String navegarAlRestaurante(Restaurante r){
        
        Utilidades.ponerEnFlash("restaurante", r);
        return "/paginaRestaurante";
    }
    
}
