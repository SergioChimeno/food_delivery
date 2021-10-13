package com.practicaFinal.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Entity
@Table
public class EspecificacionProducto implements Serializable{
    
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @NotNull
    @Column(length=64)
    private String titulo;
    
    @NotNull
    @Column
    @Min(0)
    private float precio;
    
    @NotNull
    @Column
    private boolean disponible;
    
    @Column(length=512)
    private String descripcion;
    
    @NotNull
    @Column
    @Min(0)
    private float precioEnvio;
    
    @Column
    private String tiempoEstimado;
    
    @NotNull
    @Lob
    @Column
    private byte[] foto;
    
    @NotNull
    @ManyToOne()
    @JoinColumn(name="idRestaurante")
    private Restaurante restaurante;
    
    @ManyToOne
    @JoinColumn(name="idCategoria")
    private Categoria categoria;
    
    @OneToMany(mappedBy = "producto")
    private List<Valoracion> valoraciones;

    
    public EspecificacionProducto() {
    }

    public Integer getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public float getPrecio() {
        return precio;
    }

    public boolean getDisponible() {
        return disponible;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public float getPrecioEnvio() {
        return precioEnvio;
    }

    public byte[] getFoto() {
        return foto;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public List<Valoracion> getValoraciones() {
        return valoraciones;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecioEnvio(float precioEnvio) {
        this.precioEnvio = precioEnvio;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setValoraciones(List<Valoracion> valoraciones) {
        this.valoraciones = valoraciones;
    }

    public String getTiempoEstimado() {
        return tiempoEstimado;
    }

    public void setTiempoEstimado(String tiempoEstimado) {
        this.tiempoEstimado = tiempoEstimado;
    }
    
}
