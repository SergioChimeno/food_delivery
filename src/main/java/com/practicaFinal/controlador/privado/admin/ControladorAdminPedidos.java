package com.practicaFinal.controlador.privado.admin;
import com.practicaFinal.controlador.Utilidades.Utilidades;
import com.practicaFinal.ejb.PedidoFacadeLocal;
import com.practicaFinal.modelo.EstadoPedido;
import com.practicaFinal.modelo.Pedido;
import com.practicaFinal.modelo.TipoRecogida;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class ControladorAdminPedidos implements Serializable{
    
    private List<Pedido> pedidos;
    
    @EJB
    private PedidoFacadeLocal pedidoFacade;
    
    private Pedido pedidoSeleccionado;

    
    @PostConstruct
    public void init(){
        pedidos=pedidoFacade.findAll();
        pedidoSeleccionado=new Pedido();
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Pedido getPedidoSeleccionado() {
        return pedidoSeleccionado;
    }

    public void setPedidoSeleccionado(Pedido pedidoSeleccionado) {
        this.pedidoSeleccionado = pedidoSeleccionado;
    }

    public void eliminarPedido(Pedido pedido){
        try{
            pedidoFacade.remove(pedido);
            Utilidades.lanzarMensajeInfo("Pedido eliminado", "completado");
        }catch(Exception e){
            Utilidades.lanzarMensajeInfo("ERROR", "no se ha podido completar la acción");
        }
        pedidos=pedidoFacade.findAll();
    }
    
    public EstadoPedido[] getEstadosPedido(){
        return EstadoPedido.values();
    }
    
    public TipoRecogida[] getTiposRecogida(){
        return TipoRecogida.values();
    }
    
    public void editarPedido(){
        try{
            pedidoFacade.edit(pedidoSeleccionado);
            Utilidades.lanzarMensajeInfo("Actualizado", "Actualizado correctamente");
        }catch(Exception e){
            Utilidades.lanzarMensajeError("ERROR", "no se ha podido actualizar");
            pedidos=pedidoFacade.findAll();
        }
    }
    
    public void masDetallesPedido(Pedido pedido) throws IOException{
        Utilidades.ponerEnFlash("pedido", pedido);
        Utilidades.redireccion("/privado/admin/adminDetallesPedido.xhtml");
    }
}
