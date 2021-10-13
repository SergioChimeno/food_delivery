package com.practicaFinal.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Entity
@Table
public class Valoracion implements Serializable{
    
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @NotNull
    @ManyToOne()
    @JoinColumn(name = "idEspecificacionProducto")
    private EspecificacionProducto producto;
    
    @NotNull
    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "idCliente")
    private Cliente cliente;
    
    @NotNull
    @Column()
    @Min(0)
    @Max(5)
    private short estrellas;
    
    @NotNull
    @Column(length = 64)
    private String titulo;
    
    @NotNull
    @Column(length = 256)
    private String opinion;
    
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date fecha;

    
    public Valoracion() {
    }

    public Integer getId() {
        return id;
    }

    public EspecificacionProducto getProducto() {
        return producto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public short getEstrellas() {
        return estrellas;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setProducto(EspecificacionProducto producto) {
        this.producto = producto;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setEstrellas(short estrellas) {
        this.estrellas = estrellas;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
        
}
