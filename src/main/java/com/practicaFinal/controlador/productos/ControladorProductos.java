package com.practicaFinal.controlador.productos;


import com.practicaFinal.ejb.CategoriaFacadeLocal;
import com.practicaFinal.ejb.EspecificacionProductoFacadeLocal;
import com.practicaFinal.modelo.Categoria;
import com.practicaFinal.modelo.EspecificacionProducto;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;




@Named
@RequestScoped
public class ControladorProductos implements Serializable{
    
    @EJB
    private CategoriaFacadeLocal categoriaFacade;
    
    @EJB
    private EspecificacionProductoFacadeLocal especificacionProductoFacade;
    
    private EspecificacionProducto productoSeleccionado;
    
    private List<Categoria> categorias;
    private List<EspecificacionProducto> especificacionesProductos;

    
    @PostConstruct
    public void init(){
        
        try{
            categorias=categoriaFacade.findAll();
            especificacionesProductos=especificacionProductoFacade.findAll();
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "No se pueden cargar los productos"));
        }
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public List<EspecificacionProducto> getEspecificacionesProductos() {
        return especificacionesProductos;
    }

    public void setEspecificacionesProductos(List<EspecificacionProducto> especificacionesProductos) {
        this.especificacionesProductos = especificacionesProductos;
    }

    public EspecificacionProducto getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public void setProductoSeleccionado(EspecificacionProducto productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
    }
    
    public void filtrarPorCategoria(Categoria cat){
        especificacionesProductos=especificacionProductoFacade.findByCategory(cat);
    }
    
    public String descripcionDeProductoCorta(EspecificacionProducto p){
        
        return ((p.getDisponible()==false)?"No disponible":"envío: "+p.getPrecioEnvio()+"$ • preparación: "+p.getTiempoEstimado());
    }
    
    
    
    /**************************************
     *         BARRA DE BÚSQUEDA
     **************************************/
    private String textoBusqueda;

    public String getTextoBusqueda() {
        return textoBusqueda;
    }

    public void setTextoBusqueda(String textoBusqueda) {
        this.textoBusqueda = textoBusqueda;
    }
    
    public void realizarBusqueda(){
    
        if(textoBusqueda==null || textoBusqueda.length()==0)
            return;
        
        especificacionesProductos=especificacionProductoFacade.findByTitulo(textoBusqueda);
    }
}
