package com.practicaFinal.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table
@PrimaryKeyJoinColumn()
public class Administrador extends Usuario implements Serializable{
    
    @Column(length = 32,unique = true)
    private String DNI;
    
    public Administrador(){
        
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }
    
}
