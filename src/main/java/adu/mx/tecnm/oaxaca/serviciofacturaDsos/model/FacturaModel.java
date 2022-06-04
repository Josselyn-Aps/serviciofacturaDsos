/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adu.mx.tecnm.oaxaca.serviciofacturaDsos.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author JOSELYNE
 */
@Entity
@Table(name= "factura")
public class FacturaModel {
    @Id
@Column
    private Integer folio ;
    private String razon_social_empresa ;
    private String direccion;
    private Integer cp;
    private String correo;
    private Integer telefono;
    private String rfc;
    private String regimen_fiscal;
    private Date fecha;
    private String folio_fiscal;
    private String certificado_digital;
    private String serie_cerificado_SAT;
    private boolean estado;
    private Integer idPago;
    private Integer idCliente;
    
    
    

    public Integer getFolio() {
        return folio;
    }

    public void setFolio(Integer folio) {
        this.folio = folio;
    }

    public String getRazon_social_empresa() {
        return razon_social_empresa;
    }

    public void setRazon_social_empresa(String razon_social_empresa) {
        this.razon_social_empresa = razon_social_empresa;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getCp() {
        return cp;
    }

    public void setCp(Integer cp) {
        this.cp = cp;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getRegimen_fiscal() {
        return regimen_fiscal;
    }

    public void setRegimen_fiscal(String regimen_fiscal) {
        this.regimen_fiscal = regimen_fiscal;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getFolio_fiscal() {
        return folio_fiscal;
    }

    public void setFolio_fiscal(String folio_fiscal) {
        this.folio_fiscal = folio_fiscal;
    }

    public String getCertificado_digital() {
        return certificado_digital;
    }

    public void setCertificado_digital(String certificado_digital) {
        this.certificado_digital = certificado_digital;
    }

    public String getSerie_cerificado_SAT() {
        return serie_cerificado_SAT;
    }

    public void setSerie_cerificado_SAT(String serie_cerificado_SAT) {
        this.serie_cerificado_SAT = serie_cerificado_SAT;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Integer getIdPago() {
        return idPago;
    }

    public void setIdPago(Integer idPago) {
        this.idPago = idPago;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }
}
