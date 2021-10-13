package com.practicaFinal.controlador.privado.cliente;

import com.practicaFinal.controlador.RolUsuario;
import com.practicaFinal.controlador.Utilidades.Utilidades;
import com.practicaFinal.ejb.ArticuloFacadeLocal;
import com.practicaFinal.ejb.ClienteFacadeLocal;
import com.practicaFinal.ejb.RestauranteFacadeLocal;
import com.practicaFinal.modelo.Articulo;
import com.practicaFinal.modelo.Cliente;
import com.practicaFinal.modelo.EspecificacionProducto;
import com.practicaFinal.modelo.Restaurante;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.PrimeFaces;

@Named
@ViewScoped
public class ControladorCarro implements Serializable{
    
    private List<Articulo> carro;
    private Articulo nuevoArticulo;
    
    @EJB
    private ClienteFacadeLocal clienteFacade;
    
    @EJB 
    private RestauranteFacadeLocal restauranteFacade;
    
    @EJB
    private ArticuloFacadeLocal articuloFacade;
    
    
    @EJB    
    @PostConstruct
    public void init(){
        
        if(Utilidades.getRol()!=RolUsuario.CLIENTE)
            try{
                Utilidades.redireccion("/productos.xhtml");
            }catch(Exception e){
                
            }
            
        carro=clienteFacade.findCarro((Cliente) Utilidades.getUsuario());
        System.out.println("CONTROLADOR CARRO");
    }

    public Articulo getNuevoArticulo() {
        return nuevoArticulo;
    }

    public void setNuevoArticulo(Articulo nuevoArticulo) {
        this.nuevoArticulo = nuevoArticulo;
    }
    
    public void resetearArticulo(EspecificacionProducto prod){
        nuevoArticulo=new Articulo();
        nuevoArticulo.setPrecio(prod.getPrecio());
        nuevoArticulo.setPrecioEnvio(prod.getPrecioEnvio());
        nuevoArticulo.setProducto(prod);
    }
    
    
    public void accionARealizar(Restaurante restaurante,EspecificacionProducto prod){     
        resetearArticulo(prod);
        if(carro.isEmpty() || carro.get(0)==null){
            PrimeFaces.current().executeScript("PF('dlgAnadir').show();");
        }else{
            
            Restaurante restauranteCarro=restauranteFacade.findByEspecificacionProducto(carro.get(0).getProducto());
            
            if(restauranteCarro.equals(restaurante)){
                PrimeFaces.current().executeScript("PF('dlgAnadir').show();");
            }else
                PrimeFaces.current().executeScript("PF('dlgNuevoCarro').show();");
        }
    }
    
    public void borraCarro(){
        Cliente cliente=(Cliente) Utilidades.getUsuario();
        
        List<Articulo> articulos=cliente.getCarro();
        cliente.setCarro(new ArrayList<Articulo>());
        clienteFacade.edit(cliente);
        
        articuloFacade.removeLista(articulos);
        carro=clienteFacade.findCarro(cliente);
    }
    

    
    public void anadirNuevoArticulo(){
        if(nuevoArticulo.getCantidad()==0){
            Utilidades.lanzarMensajeError("Cantidad 0", "La cantidad marcada es de 0");
            return;
        }
        
        try{
            carro.add(nuevoArticulo);
            Cliente cliente=(Cliente) Utilidades.getUsuario();
            cliente.setCarro(carro);
            clienteFacade.edit(cliente);
            carro=clienteFacade.findCarro(cliente);
            Utilidades.lanzarMensajeInfo("Añadido", "Se ha añadido con éxito");
        }catch(Exception e){
            Utilidades.lanzarMensajeError("ERROR", "Se produjo un error al añadir el producto al carro");
        }
    }

    public List<Articulo> getCarro() {
        return carro;
    }

    public void setCarro(List<Articulo> carro) {
        this.carro = carro;
    }
    
    public void eliminarArticulo(Articulo articulo){
        Cliente cliente=(Cliente) Utilidades.getUsuario();
        
        carro.remove(articulo);
        cliente.setCarro(carro);
        clienteFacade.edit(cliente);   
        articuloFacade.remove(articulo);
    }
    
    public float getTotal(){
        float total=0;
        
        for(Articulo a:carro)
            total+=(a.getPrecio()+a.getPrecioEnvio())*a.getCantidad();
        
        return total;
    }
    
}
