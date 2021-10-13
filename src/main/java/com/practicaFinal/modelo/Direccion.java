package com.practicaFinal.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table
public class Direccion implements Serializable{
    
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @NotNull
    @Column(length=32)
    private String pais;
    
    @NotNull
    @Column(length=32)
    private String ciudad;
    
    @NotNull
    @Column(length=32)
    private String calle;
    
    @Column(length=8)
    private String puerta;
    
    @NotNull
    @Column
    private int numero;
    
    @NotNull
    @Column(length=32)
    private String codigoPostal;
    
    @Column(length = 32)
    private String infoAdicional;
    
    @ManyToOne()
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;    
    
    public Direccion(){
        
    }

    public String getInfoAdicional() {
        return infoAdicional;
    }

    public void setInfoAdicional(String infoAdicional) {
        this.infoAdicional = infoAdicional;
    }
    
    public Integer getId() {
        return id;
    }

    public String getPais() {
        return pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getCalle() {
        return calle;
    }

    public String getPuerta() {
        return puerta;
    }

    public int getNumero() {
        return numero;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setPuerta(String puerta) {
        this.puerta = puerta;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    @Override
    public String toString(){
        String cadena="Calle "+calle+", nº "+numero+(puerta!= null?", puerta:"+puerta+"":"")
                +" "+ciudad+","+pais+", CP: "+codigoPostal+(infoAdicional!=null?". "+infoAdicional:"");
        
        return cadena;
    }
}
