package com.practicaFinal.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class Articulo implements Serializable {
    
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @NotNull
    @Column
    @Min(0)
    private int cantidad;
    
    @NotNull
    @Column
    @Min(0)
    private float precio;
    
    @NotNull
    @Column
    @Min(0)
    private float precioEnvio;
    
    @ManyToOne
    @JoinColumn(name = "idPedido")
    private Pedido pedido;
    
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idEspecificacionProducto")
    private EspecificacionProducto producto;
    
    
    public Articulo(){
        
    }

    public Integer getId() {
        return id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public EspecificacionProducto getProducto() {
        return producto;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public void setProducto(EspecificacionProducto producto) {
        this.producto = producto;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getPrecioEnvio() {
        return precioEnvio;
    }

    public void setPrecioEnvio(float precioEnvio) {
        this.precioEnvio = precioEnvio;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Articulo other = (Articulo) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
