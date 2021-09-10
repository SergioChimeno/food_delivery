package com.practicaFinal.controlador.productos;

import com.practicaFinal.controlador.Utilidades.Utilidades;
import com.practicaFinal.ejb.RestauranteFacadeLocal;
import com.practicaFinal.ejb.ValoracionFacadeLocal;
import com.practicaFinal.modelo.Cliente;
import com.practicaFinal.modelo.EspecificacionProducto;
import com.practicaFinal.modelo.Restaurante;
import com.practicaFinal.modelo.Valoracion;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class ControladorPaginaProducto implements Serializable{
    
    @Inject
    private ControladorProductos controladorProducto;
    
    @EJB
    private RestauranteFacadeLocal restauranteFacade;
    
    @EJB
    private ValoracionFacadeLocal valoracionFacade;
    
    private EspecificacionProducto producto;
    private Restaurante restaurante;
    private List<Valoracion> valoraciones;
    
    private Cliente usuarioActual;
    
    @Inject
    private Valoracion nuevaValoracion;
    
    @PostConstruct
    public void init(){
        producto=controladorProducto.getProductoSeleccionado();
        
        try{
            if(producto == null){
                Utilidades.redireccion("/productos.xhtml");
                return;
            }
                
            restaurante=restauranteFacade.findByEspecificacionProducto(producto);
            valoraciones=valoracionFacade.findByEspecificacionProducto(producto);
            
            usuarioActual=(Cliente)Utilidades.getUsuario();
        }catch(Exception e){
            Utilidades.lanzarMensajeError("ERROR", "Error en la carga de esta página");
        }
    }

    public EspecificacionProducto getProducto() {
        return producto;
    }

    public void setProducto(EspecificacionProducto producto) {
        this.producto = producto;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public List<Valoracion> getValoraciones() {
        return valoraciones;
    }

    public void setValoraciones(List<Valoracion> valoraciones) {
        this.valoraciones = valoraciones;
    }

    public Valoracion getNuevaValoracion() {
        return nuevaValoracion;
    }

    public void setNuevaValoracion(Valoracion nuevaValoracion) {
        this.nuevaValoracion = nuevaValoracion;
    }

    public Cliente getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Cliente usuarioActual) {
        this.usuarioActual = usuarioActual;
    }
    
    public boolean usuarioLogeado(){
        return usuarioActual==null?false:true;
    }
    
    public String getNombreUsuario(){
        return usuarioActual==null?"Desconocido":usuarioActual.getNombre();
    }
    
    public String disponible(){
        return producto.getDisponible()==true?null:"(No disponible)";
    }
    
    public void guardarValoracion(){
        nuevaValoracion.setProducto(producto);
        nuevaValoracion.setCliente(usuarioActual);
        nuevaValoracion.setFecha(new Date());
        
        try{
            valoracionFacade.create(nuevaValoracion);
            Utilidades.lanzarMensajeInfo("comentario guardado", "");
        }catch(Exception e){
            Utilidades.lanzarMensajeError("ERROR", "Error al guardar el comentario");
        }
        
        valoraciones=valoracionFacade.findByEspecificacionProducto(producto);
        
        nuevaValoracion=new Valoracion();
    }
    
    public String navegarAlRestaurante(){
        
        Utilidades.ponerEnFlash("restaurante", restaurante);
        return "/paginaRestaurante";
    }
    
}
