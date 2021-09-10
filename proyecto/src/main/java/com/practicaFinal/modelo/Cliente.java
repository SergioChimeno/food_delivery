package com.practicaFinal.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table
@PrimaryKeyJoinColumn()
public class Cliente extends Usuario implements Serializable{
    
    @NotNull
    @Column(length = 32)
    private String nombre;
    
    @NotNull
    @Column(length = 32)
    private String apellidos;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.PERSIST)
    private List<Direccion> direcciones;
    
    @Column(length = 32)
    private String telefono;
    
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;
    
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="CARRO",
            joinColumns = @JoinColumn(name="idCliente"),
            inverseJoinColumns = @JoinColumn(name = "idArticulo")
    )
    private List<Articulo> carro;
    
    
    public Cliente(){
        
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
        
    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public List<Articulo> getCarro() {
        return carro;
    }

    public void setCarro(List<Articulo> carro) {
        this.carro = carro;
    }

    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }
}
