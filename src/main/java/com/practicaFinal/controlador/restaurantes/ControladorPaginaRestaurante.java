package com.practicaFinal.controlador.restaurantes;

import com.practicaFinal.controlador.Utilidades.Utilidades;
import com.practicaFinal.ejb.EspecificacionProductoFacadeLocal;
import com.practicaFinal.ejb.ValoracionFacadeLocal;
import com.practicaFinal.modelo.Cliente;
import com.practicaFinal.modelo.EspecificacionProducto;
import com.practicaFinal.modelo.Restaurante;
import com.practicaFinal.modelo.Valoracion;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.PrimeFaces;

@Named
@ViewScoped
public class ControladorPaginaRestaurante implements Serializable{
    
    private Restaurante restaurante;
    private List<EspecificacionProducto> productos;
    private Cliente usuarioActual;
    
    private String textoBusqueda;
    
    @EJB
    private EspecificacionProductoFacadeLocal especificacionProductoFacade;
    
    @PostConstruct
    public void init(){
        
        try {
            restaurante=(Restaurante) Utilidades.getFromFlash("restaurante");

            if(restaurante==null)
                Utilidades.redireccion("/restaurantes.xhtml");


            usuarioActual=(Cliente)Utilidades.getUsuario();
            productos=especificacionProductoFacade.findByRestaurante(restaurante);
        }catch (IOException ex) {
            Utilidades.lanzarMensajeError("ERROR", "Error en la carga de esta página");
        }
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public List<EspecificacionProducto> getProductos() {
        return productos;
    }

    public void setProductos(List<EspecificacionProducto> productos) {
        this.productos = productos;
    }

    public String getTextoBusqueda() {
        return textoBusqueda;
    }

    public void setTextoBusqueda(String textoBusqueda) {
        this.textoBusqueda = textoBusqueda;
    }

    public Cliente getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Cliente usuarioActual) {
        this.usuarioActual = usuarioActual;
    }
    
    public String disponible(EspecificacionProducto producto){
        return producto.getDisponible()==true?null:"(No disponible)";
    }
    
    public void realizarBusqueda(){
        if(textoBusqueda!=null)
            productos=especificacionProductoFacade.findByRestauranteAndTitulo(restaurante,textoBusqueda);
    }
    
    
    /*********************************
     *          COMENTARIOS
    *********************************/
    
    @EJB
    private ValoracionFacadeLocal valoracionFacade;
    
    private List<Valoracion> valoraciones;

    @Inject
    private Valoracion nuevaValoracion;
    
    private EspecificacionProducto productoActual;
    
    public void cargarComentarios(EspecificacionProducto prod){
        valoraciones=valoracionFacade.findByEspecificacionProducto(prod);
        productoActual=prod;
    }

    public EspecificacionProducto getProductoActual() {
        return productoActual;
    }

    public void setProductoActual(EspecificacionProducto producto) {
        this.productoActual = producto;
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
    
    public boolean usuarioLogeado(){
        return usuarioActual!=null;
    }
    
    public String getNombreUsuario(){
        return usuarioActual==null?"Desconocido":usuarioActual.getNombre();
    }
    
    public void guardarValoracion(){
        nuevaValoracion.setProducto(productoActual);
        nuevaValoracion.setCliente(usuarioActual);
        nuevaValoracion.setFecha(new Date());
        
        try{
            valoracionFacade.create(nuevaValoracion);
            Utilidades.lanzarMensajeInfo("comentario guardado", "");
        }catch(Exception e){
            Utilidades.lanzarMensajeError("ERROR", "Error al guardar el comentario");
        }
        
        valoraciones=valoracionFacade.findByEspecificacionProducto(productoActual);
        nuevaValoracion=new Valoracion();
    }
    
}
