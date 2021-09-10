package com.practicaFinal.controlador.privado.admin;
import com.practicaFinal.controlador.Utilidades.Utilidades;
import com.practicaFinal.ejb.CategoriaFacadeLocal;
import com.practicaFinal.modelo.Categoria;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.file.UploadedFile;

@Named
@ViewScoped
public class ControladorAdminCategorias implements Serializable{

    @EJB
    private CategoriaFacadeLocal categoriaFacade;
    
    private List<Categoria> categorias;
    private Categoria categoriaTemporal;
    private UploadedFile file;
    private char accion;
   
    
    @PostConstruct
    public void init(){
        categorias=categoriaFacade.findAll();
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public Categoria getCategoriaTemporal() {
        return categoriaTemporal;
    }

    public void setCategoriaTemporal(Categoria categoriaTemporal) {
        this.categoriaTemporal = categoriaTemporal;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public char getAccion() {
        return accion;
    }

    public void setAccion(char accion) {
        this.accion = accion;
    }

    public void setAccionNuevo(){
        categoriaTemporal=new Categoria();
        accion='N';
    }
    
    public void setAccionEditar(Categoria cat){
        categoriaTemporal=cat;
        accion='E';
    }  
    
    //Operaciones categoria
    
    public void eliminarCategoria(Categoria cat){
        try{
            categoriaFacade.remove(cat);
            Utilidades.lanzarMensajeInfo("Categoria eliminada", "completado");
        }catch(Exception e){
            Utilidades.lanzarMensajeInfo("ERROR", "no se ha podido completar la acción");
        }
        categorias=categoriaFacade.findAll();
    }
    
    public void actualizarCategoria(){
        if(file!=null && file.getContentType().contains("image")){
            categoriaTemporal.setImagen(file.getContent());
        }
        
        try{
            categoriaFacade.edit(categoriaTemporal);
            Utilidades.lanzarMensajeInfo("Actualizado", "Actualizado correctamente");
        }catch(Exception e){
            Utilidades.lanzarMensajeError("ERROR", "no se ha podido actualizar");
            categorias=categoriaFacade.findAll();
        }
        file=null;
    }
    
    public void nuevaCategoria(){
        if(file!=null && file.getContentType().contains("image")){
            categoriaTemporal.setImagen(file.getContent());
        }
        
        try{
            categoriaFacade.create(categoriaTemporal);
            Utilidades.lanzarMensajeInfo("Nuevo", "nueva categoria añadida");
        }catch(Exception e){
            Utilidades.lanzarMensajeError("ERROR", "Datos inválidos, no se añadido la categoria");
        }
        
        categorias=categoriaFacade.findAll();
        file=null;
    }
    
}
