package com.practicaFinal.modelo;

import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table
@PrimaryKeyJoinColumn()
public class Restaurante extends Usuario{
    
    @Column(length = 256)
    private String informacion;
    
    @Column(length = 256)
    private String horario;
    
    @NotNull
    @Column(length = 15)
    private String telefono;
    
    @OneToMany(mappedBy = "restaurante")
    private List<EspecificacionProducto> productos;
    
    @OneToOne(mappedBy = "usuario",fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    private Direccion direccion;
    
    public Restaurante() {
    }

    public String getInformacion() {
        return informacion;
    }

    public String getHorario() {
        return horario;
    }

    public List<EspecificacionProducto> getProductos() {
        return productos;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public void setProductos(List<EspecificacionProducto> productos) {
        this.productos = productos;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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
        final Restaurante other = (Restaurante) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }
    
    
}
