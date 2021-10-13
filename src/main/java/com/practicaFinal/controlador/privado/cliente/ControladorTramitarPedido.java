package com.practicaFinal.controlador.privado.cliente;

import com.practicaFinal.controlador.Utilidades.JavaMailService;
import com.practicaFinal.controlador.Utilidades.Utilidades;
import com.practicaFinal.ejb.ClienteFacadeLocal;
import com.practicaFinal.ejb.DireccionFacadeLocal;
import com.practicaFinal.ejb.PedidoFacadeLocal;
import com.practicaFinal.ejb.RestauranteFacadeLocal;
import com.practicaFinal.modelo.Articulo;
import com.practicaFinal.modelo.Cliente;
import com.practicaFinal.modelo.Direccion;
import com.practicaFinal.modelo.EstadoPedido;
import com.practicaFinal.modelo.Pedido;
import com.practicaFinal.modelo.TipoRecogida;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class ControladorTramitarPedido implements Serializable{
    
    @Inject
    private Direccion direccionTemporal;
    private List<Direccion> direcciones;
    private Pedido estePedido;
    
    @EJB
    private DireccionFacadeLocal direccionFacade;
    
    @EJB
    private PedidoFacadeLocal pedidoFacade;
    
    @EJB
    private ClienteFacadeLocal clienteFacade;
    
    @EJB
    private RestauranteFacadeLocal restauranteFacade;
    
    @EJB
    private JavaMailService mailService;
    
    @Inject
    private ControladorCarro controladorCarro;
    
    @PostConstruct
    public void init(){
        if(controladorCarro.getCarro().isEmpty()){
            Utilidades.lanzarMensajeError(null, "Para tramitar el pedido debe tener algún producto en la cesta.");
            Utilidades.guardarMsjEnFlash();
            try {
                Utilidades.redireccion("/productos.xhtml");
            } catch (IOException ex) {
                
            }
        }
        
        direcciones=direccionFacade.findByUsuario(Utilidades.getUsuario());
        estePedido=new Pedido();
        estePedido.setRecogida(TipoRecogida.RECOGIDA);
        estePedido.setArticulos(controladorCarro.getCarro());
        estePedido.calcularPrecioTotal();
    }

    public Direccion getDireccionTemporal() {
        return direccionTemporal;
    }

    public void setDireccionTemporal(Direccion direccionTemporal) {
        this.direccionTemporal = direccionTemporal;
    }

    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    public Pedido getEstePedido() {
        return estePedido;
    }

    public void setEstePedido(Pedido estePedido) {
        this.estePedido = estePedido;
    }
    
    public TipoRecogida[] getTiposRecogida(){
        return TipoRecogida.values();
    }
    
    public void efectuarPedido(){
        Cliente cliente=(Cliente) Utilidades.getUsuario();
        
        try{
            estePedido.setCliente(cliente);
            estePedido.setEstado(EstadoPedido.NUEVO);
            estePedido.setFecha(new Date());
            if(estePedido.getRecogida()==TipoRecogida.ENVIO)
                estePedido.setDireccion(direccionTemporal);

            pedidoFacade.create(estePedido);
            cliente.setCarro(new ArrayList<Articulo>());
            clienteFacade.edit(cliente);
            
            Utilidades.lanzarMensajeInfo("Pedido completado", "Ha realizado su pedido, le seguiremos informando de su estado via email.");
            Utilidades.guardarMsjEnFlash();
            
            //Mandar un mail al cliente
            mailService.enviarMailInformativo(estePedido);
            mailService.restauranteNuevoPedido(estePedido, estePedido.getArticulos().get(0).getProducto().getRestaurante());
            
            Utilidades.redireccion("/productos.xhtml");
        }catch(Exception e){
            Utilidades.lanzarMensajeError("Error", "No se ha podido realizar el pedido");
        }
    }
}
