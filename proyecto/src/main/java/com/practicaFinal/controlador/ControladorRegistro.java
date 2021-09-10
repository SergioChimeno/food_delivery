package com.practicaFinal.controlador;

import com.practicaFinal.ejb.ClienteFacadeLocal;
import com.practicaFinal.modelo.Cliente;
import com.practicaFinal.modelo.Direccion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.file.UploadedFile;

@Named
@RequestScoped
public class ControladorRegistro implements Serializable {
    
    @Inject
    private Cliente cliente;
    @Inject
    private Direccion direccion;
    
    private UploadedFile imagenPerfil;
    
    @EJB
    private ClienteFacadeLocal clienteFacade;
    
    @PostConstruct
    public void init(){
    }
        
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public UploadedFile getImagenPerfil() {
        return imagenPerfil;
    }

    public void setImagenPerfil(UploadedFile imagenPerfil) {
        this.imagenPerfil = imagenPerfil;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
    
    public String registrar(){
        if(imagenPerfil!=null && imagenPerfil.getContentType().contains("image"))
            cliente.setImagen(imagenPerfil.getContent());
        
        
        List<Direccion> direcciones=new ArrayList<Direccion>();
        direccion.setUsuario(cliente);
        direcciones.add(direccion);
        cliente.setDirecciones(direcciones);

        FacesMessage msg;
        try{
            clienteFacade.create(cliente);
            msg=new FacesMessage(FacesMessage.SEVERITY_INFO,"Registro completado","Bienvenido "+cliente.getNombre());
            FacesContext.getCurrentInstance().addMessage("growl", msg);
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            
            return "/productos.xhtml?faces-redirect=true";
        }catch(Exception e ){
            msg=new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR","El registro no ha podido ser completado.");
            e.printStackTrace();
        }
        
        FacesContext.getCurrentInstance().addMessage("msj", msg);
        
        return "";
    }
    
}
