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
@Table(name = "factura")
public class FacturaModel {

    @Id
    @Column
    private Integer folio;
    private String razonSocialEmpresa;
    private String direccion;
    private Integer cp;
    private String correo;
    private Integer telefono;
    private String rfc;
    private String regimenFiscal;
    private Date fecha;
    private String folioFiscal;
    private String certificadoDigital;
    private String SerieCertificadoSat;
    private boolean estado;
    private Integer idPago;
    private String rfcCliente;

    public FacturaModel(Integer folio, String razonSocialEmpresa, String direccion, Integer cp, String correo, Integer telefono, String rfc, String regimenFiscal, Date fecha, String folioFiscal, String certificadoDigital, String SerieCertificadoSat, boolean estado, Integer idPago, String rfcCliente) {
        this.folio = folio;
        this.razonSocialEmpresa = razonSocialEmpresa;
        this.direccion = direccion;
        this.cp = cp;
        this.correo = correo;
        this.telefono = telefono;
        this.rfc = rfc;
        this.regimenFiscal = regimenFiscal;
        this.fecha = fecha;
        this.folioFiscal = folioFiscal;
        this.certificadoDigital = certificadoDigital;
        this.SerieCertificadoSat = SerieCertificadoSat;
        this.estado = estado;
        this.idPago = idPago;
        this.rfcCliente = rfcCliente;
    }

    public FacturaModel() {
    }

    public Integer getFolio() {
        return folio;
    }

    public void setFolio(Integer folio) {
        this.folio = folio;
    }

    public String getRazonSocialEmpresa() {
        return razonSocialEmpresa;
    }

    public void setRazonSocialEmpresa(String razonSocialEmpresa) {
        this.razonSocialEmpresa = razonSocialEmpresa;
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

    public String getRegimenFiscal() {
        return regimenFiscal;
    }

    public void setRegimenFiscal(String regimenFiscal) {
        this.regimenFiscal = regimenFiscal;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getFolioFiscal() {
        return folioFiscal;
    }

    public void setFolioFiscal(String folioFiscal) {
        this.folioFiscal = folioFiscal;
    }

    public String getCertificadoDigital() {
        return certificadoDigital;
    }

    public void setCertificadoDigital(String certificadoDigital) {
        this.certificadoDigital = certificadoDigital;
    }

    public String getSerieCertificadoSat() {
        return SerieCertificadoSat;
    }

    public void setSerieCertificadoSat(String SerieCertificadoSat) {
        this.SerieCertificadoSat = SerieCertificadoSat;
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

    public String getRfcCliente() {
        return rfcCliente;
    }

    public void setRfcCliente(String rfcCliente) {
        this.rfcCliente = rfcCliente;
    }

    @Override
    public String toString() {
        return "FacturaModel{" + "folio=" + folio + ", razonSocialEmpresa=" + 
                razonSocialEmpresa + ", direccion=" + direccion + ", cp=" + cp + 
                ", correo=" + correo + ", telefono=" + telefono + ", rfc=" + rfc + 
                ", regimenFiscal=" + regimenFiscal + ", fecha=" + fecha + 
                ", folioFiscal=" + folioFiscal + ", certificadoDigital=" + 
                certificadoDigital + ", SerieCertificadoSat=" + 
                SerieCertificadoSat + ", estado=" + estado + ", idPago=" + 
                idPago + ", rfcCliente=" + rfcCliente + '}';
    }

}
