package com.practicaFinal.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario implements Serializable {
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column()
    protected Integer id;
    
    @NotNull
    @Column(unique=true,length=32)
    protected String email;
    
    @NotNull
    @Column(length=32)
    protected String contrasena;
    
    @NotNull
    @Column(unique = true,length=32)
    protected String username;
    
    @Lob
    @Column()
    protected byte[] imagen;

    
    public Usuario() {
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getUsername() {
        return username;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
    
}
