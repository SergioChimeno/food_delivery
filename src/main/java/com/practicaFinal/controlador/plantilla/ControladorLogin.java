package com.practicaFinal.controlador.plantilla;
import com.practicaFinal.controlador.RolUsuario;
import com.practicaFinal.ejb.UsuarioFacadeLocal;
import com.practicaFinal.modelo.Administrador;
import com.practicaFinal.modelo.Restaurante;
import com.practicaFinal.modelo.Usuario;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.PrimeFaces;



@Named
@RequestScoped
public class ControladorLogin implements Serializable{
    
    private String email;
    private String contrasena;
    
    @EJB
    private UsuarioFacadeLocal usuarioFacade;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    public String login(){
        FacesMessage mensaje=null;
        boolean loggedIn=false;
        
        try{
            Usuario us=usuarioFacade.login(email, contrasena);
            
            if(us!=null){
                RolUsuario rol=null;
                Class claseUsuario=us.getClass();
                
                if(claseUsuario==Restaurante.class)
                    rol=RolUsuario.RESTAURANTE;
                else if(claseUsuario==Administrador.class)
                    rol=RolUsuario.ADMIN;
                else
                    rol=RolUsuario.CLIENTE;
                
                
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", us);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("rol", rol);
                
                mensaje=new FacesMessage(FacesMessage.SEVERITY_INFO,"Bienvenido",us.getUsername());
                FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
                loggedIn=true;
            }else
                mensaje=new FacesMessage(FacesMessage.SEVERITY_ERROR,"Login Error","Credenciales inválidas");
            
        }catch(Exception e ){
            mensaje=new FacesMessage(FacesMessage.SEVERITY_ERROR,"Login Error","Error al entrar");
        }
        
        FacesContext.getCurrentInstance().addMessage("growl", mensaje);
        PrimeFaces.current().ajax().addCallbackParam("loggedIn", loggedIn);
        
        if(loggedIn)
            return "/productos.xhtml?faces-redirect=true";
        else
            return "";
    }
}
