
package com.practicaFinal.modelo;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class PedidoTest {
    
    public PedidoTest() {
    }

    /*
    ** Test de caja blanca usando el metodo del camino básico
    */
    @Test
    public void testCalcularPrecioTotal1() {
        Pedido pedido=new Pedido();
        
        //Camino 1
        pedido.calcularPrecioTotal();
        assertEquals(0, pedido.getPrecioTotal());
        
        //Camino 2
        pedido.setArticulos(new ArrayList<Articulo>());
        pedido.calcularPrecioTotal();
        assertEquals(0, pedido.getPrecioTotal());
        
        //Camino 3
        ArrayList<Articulo> articulos=new ArrayList<Articulo>();
        
        Articulo a1=new Articulo();
        a1.setCantidad(2);
        a1.setPrecio(12);
        a1.setPrecioEnvio(2);
        Articulo a2=new Articulo();
        a2.setCantidad(1);
        a2.setPrecio(12);
        a2.setPrecioEnvio(2);
        Articulo a3=new Articulo();
        a3.setCantidad(1);
        a3.setPrecio(12);
        a3.setPrecioEnvio(2);
        
        articulos.add(a1);
        articulos.add(a2);
        articulos.add(a3);
        
        pedido.setArticulos(articulos);
        pedido.setRecogida(TipoRecogida.RECOGIDA);
        pedido.calcularPrecioTotal();
        
        assertEquals(12 + 12 + 12*2, pedido.getPrecioTotal());
        
        //Camino 4
        pedido.setRecogida(TipoRecogida.ENVIO);
        pedido.calcularPrecioTotal();
        
        assertEquals(12 + 2*2 + 12 + 2 + 12*2 + 2, pedido.getPrecioTotal());
    }
}
