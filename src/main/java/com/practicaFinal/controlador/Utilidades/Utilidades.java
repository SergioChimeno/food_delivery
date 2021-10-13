package com.practicaFinal.controlador.Utilidades;

import com.practicaFinal.controlador.RolUsuario;
import com.practicaFinal.modelo.Usuario;
import java.io.IOException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


public class Utilidades {
    
    public static final String PAGINA_ERROR="/PaginasError/Error404.html";
    
    public static Usuario getUsuario(){
        return (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
    }
    
    public static void borrarSesion(){
        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).invalidate();
    }
    
    public static RolUsuario getRol(){
        return (RolUsuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("rol");
    }
    
    public static void redireccion(String destino) throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().redirect("/PracticaFinal"+destino);
    }
    
    public static void lanzarMensajeError(String msg,String detalles){
        FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, detalles));
    }
    
    public static void lanzarMensajeError(String id,String msg,String detalles){
        FacesContext.getCurrentInstance().addMessage(id, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, detalles));
    }
    
    public static void guardarMsjEnFlash(){
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
    }
    
    public static void lanzarMensajeInfo(String msg,String detalles){
        FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_INFO, msg, detalles));
    }
    
    public static void lanzarMensajeInfo(String id,String msg,String detalles){
        FacesContext.getCurrentInstance().addMessage(id, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, detalles));
    }
    
    public static void ponerEnFlash(String clave, Object valor){
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put(clave, valor);
    }
    
    public static Object getFromFlash(String clave){
        return FacesContext.getCurrentInstance().getExternalContext().getFlash().get(clave);
    }
    
}
