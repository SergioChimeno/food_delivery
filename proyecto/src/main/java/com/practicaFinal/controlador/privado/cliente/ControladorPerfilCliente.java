package com.practicaFinal.controlador.privado.cliente;

import com.practicaFinal.controlador.Utilidades.Utilidades;
import com.practicaFinal.ejb.ClienteFacadeLocal;
import com.practicaFinal.ejb.DireccionFacadeLocal;
import com.practicaFinal.modelo.Cliente;
import com.practicaFinal.modelo.Direccion;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.file.UploadedFile;

@Named
@ViewScoped
public class ControladorPerfilCliente implements Serializable{
    
    private Cliente cliente;
    private List<Direccion> direcciones;
    private UploadedFile file;

    @EJB
    private DireccionFacadeLocal direccionFacade;
    
    @EJB
    private ClienteFacadeLocal clienteFacade;
    
    @PostConstruct
    public void init(){       
        cliente=(Cliente) Utilidades.getUsuario();
        
        direcciones=direccionFacade.findByUsuario(cliente);
        System.out.println(direcciones.size());
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }
    
    public void actualizarCliente(){
        if(file!=null && file.getContentType().contains("image")){
            cliente.setImagen(file.getContent());
        }
        
        try{
            clienteFacade.edit(cliente);
            Utilidades.lanzarMensajeInfo("Cliente actualizado", "accion completada");
            Utilidades.guardarMsjEnFlash();
        }catch(Exception e){
            Utilidades.lanzarMensajeError("ERROR", "accion incorrecta");
            Utilidades.guardarMsjEnFlash();
        }
        file=null;
    }
    

    
    /******************************************************************
     *          EDITAR DIRECCION Y NUEVA DIRECCION
    ******************************************************************/
    
    
    private char accion='N'; // 'E' para editar y 'N' para nuevo
    private Direccion direccionTemporal;
    
    public char getAccion() {
        return accion;
    }

    public void setAccion(char accion) {
        this.accion = accion;
    }
    
    public Direccion getDireccionTemporal() {
        return direccionTemporal;
    }

    public void setDireccionTemporal(Direccion direccionTemporal) {
        this.direccionTemporal = direccionTemporal;
    }

    public void setAccionNuevo(){
        direccionTemporal=new Direccion();
        accion='N';
    }
    
    public void setAccionEditar(Direccion dir){
        direccionTemporal=dir;
        accion='E';
    }  
        
    public void actualizarDireccion(){
        try{
            direccionFacade.edit(direccionTemporal);
            Utilidades.lanzarMensajeInfo("Actualizado", "Actualizado correctamente");
        }catch(Exception e){
            Utilidades.lanzarMensajeError("ERROR", "no se ha podido actualizar");
            direcciones=direccionFacade.findByUsuario(cliente);
        }
    }
    
    public void nuevaDireccion(){
        try{
            direccionTemporal.setUsuario(cliente);
            direccionFacade.create(direccionTemporal);
            Utilidades.lanzarMensajeInfo("Nuevo", "nuevo direccion añadida");
        }catch(Exception e){
            Utilidades.lanzarMensajeError("ERROR", "Datos inválidos, no se añadido la direccion");
        }
        
        direcciones=direccionFacade.findByUsuario(cliente);
    }
    
    public void eliminarDireccion(Direccion dir){
        if(direcciones.size()==1){
            Utilidades.lanzarMensajeError("Error", "No se pueden eliminar todas las direcciones. Debe permanecer al menos 1.");
            return;
        }
        
        try{
            direccionFacade.remove(dir);
            Utilidades.lanzarMensajeInfo("Direccion eliminada", "completado");
        }catch(Exception e){
            Utilidades.lanzarMensajeInfo("ERROR", "no se ha podido completar la acción");
        }
        direcciones=direccionFacade.findByUsuario(cliente);
    }
    
}
