package com.practicaFinal.modelo;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table
public class Categoria implements Serializable{
    
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @NotNull
    @Column(unique = true,length = 64)
    private String nombre;
    
    @NotNull
    @Lob
    @Column
    private byte[] imagen;
    
    @OneToMany(mappedBy = "categoria")
    private List<EspecificacionProducto> productos;
    
    
    public Categoria(){
        
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public List<EspecificacionProducto> getProductos() {
        return productos;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setProductos(List<EspecificacionProducto> productos) {
        this.productos = productos;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
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
        final Categoria other = (Categoria) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }


    
    
}
