package com.practicaFinal.controlador.privado.cliente;
import com.practicaFinal.controlador.Utilidades.JavaMailService;
import com.practicaFinal.controlador.Utilidades.Utilidades;
import com.practicaFinal.ejb.ArticuloFacadeLocal;
import com.practicaFinal.ejb.DireccionFacadeLocal;
import com.practicaFinal.ejb.PedidoFacadeLocal;
import com.practicaFinal.modelo.Articulo;
import com.practicaFinal.modelo.Cliente;
import com.practicaFinal.modelo.Direccion;
import com.practicaFinal.modelo.EstadoPedido;
import com.practicaFinal.modelo.Pedido;
import com.practicaFinal.modelo.Restaurante;
import com.practicaFinal.modelo.TipoRecogida;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class ControladorMisPedidos implements Serializable{
    
    private Cliente cliente;
    private List<Pedido> pedidos;
    
    @EJB
    private PedidoFacadeLocal pedidoFacade;
    
    @EJB
    private DireccionFacadeLocal direccionFacade;
    
    @EJB
    private ArticuloFacadeLocal articuloFacade;
    
    private Pedido pedidoActual;
    private List<Articulo> articulos;
    
    @PostConstruct
    public void init(){
        cliente=(Cliente) Utilidades.getUsuario();
        pedidos=pedidoFacade.findByCliente(cliente);
        
        pedidoActual=new Pedido();
        pedidoActual.setCliente(new Cliente());
        pedidoActual.setDireccion(new Direccion());
        articulos=new ArrayList<Articulo>();
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Pedido getPedidoActual() {
        return pedidoActual;
    }

    public void setPedidoActual(Pedido pedidoActual) {
        this.pedidoActual = pedidoActual;
    }

    public List<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<Articulo> articulos) {
        this.articulos = articulos;
    }
    
    public void masDetallesPedido(Pedido pedido){
        pedidoActual=pedido;
        articulos=articuloFacade.findByPedido(pedido);
    }
}
