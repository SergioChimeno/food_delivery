package com.practicaFinal.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Entity
@Table
public class Pedido implements Serializable {
    
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idCliente")
    private Cliente cliente;
    
    @NotNull
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.REMOVE)
    private List<Articulo> articulos;
    
    @OneToOne()
    @JoinColumn( name = "idDireccion")
    private Direccion direccion;
    
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date fecha;
    
    @NotNull
    @Column
    @Min(0)
    private float precioTotal;
    
    @NotNull
    @Column
    @Enumerated(EnumType.STRING)
    private TipoRecogida recogida;
    
    @NotNull
    @Column
    @Enumerated(EnumType.STRING)
    private EstadoPedido estado;
    
    
    public Pedido(){
        
    }

    public Integer getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<Articulo> getArticulos() {
        return articulos;
    }

    public Date getFecha() {
        return fecha;
    }

    public float getPrecioTotal() {
        return precioTotal;
    }

    public TipoRecogida getRecogida() {
        return recogida;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setArticulos(List<Articulo> articulos) {
        this.articulos = articulos;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setPrecioTotal(float precioTotal) {
        this.precioTotal = precioTotal;
    }

    public void setRecogida(TipoRecogida recogida) {
        this.recogida = recogida;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
    
    public void calcularPrecioTotal(){
        precioTotal=0;
        
        if(articulos==null)
            return;
  
        for(Articulo a: articulos){
            if(a.getPrecio()<0 || a.getCantidad()<0 || a.getPrecioEnvio()< 0){
                precioTotal=0;
                throw new ArithmeticException();
            }
            precioTotal+=a.getCantidad()*a.getPrecio();
            if(recogida==TipoRecogida.ENVIO)
                precioTotal+=a.getCantidad()*a.getPrecioEnvio();
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.id);
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
        final Pedido other = (Pedido) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
