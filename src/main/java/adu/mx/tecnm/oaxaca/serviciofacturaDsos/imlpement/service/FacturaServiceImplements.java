/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adu.mx.tecnm.oaxaca.serviciofacturaDsos.imlpement.service;

import adu.mx.tecnm.oaxaca.serviciofacturaDsos.model.FacturaModel;
import adu.mx.tecnm.oaxaca.serviciofacturaDsos.repository.FacturaRepository;
import adu.mx.tecnm.oaxaca.serviciofacturaDsos.service.FacturaService;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author JOSELYNE
 */
@Service
public class FacturaServiceImplements implements FacturaService{
@Autowired
private FacturaRepository facturaRepository;
    @Override
    public void registrarFactura(FacturaModel factura) {
        factura.setEstado(true);
        factura.setRazonSocialEmpresa("ZAPATERÍA LOS AMIGUITOS");
        factura.setDireccion("MIMOSAS #117,STA. MARIA INSURGENTES,CUAUHTÉMOC,DISTRITO FEDERAL");
        factura.setCp(06430);
        factura.setCorreo("servicioalcliente@grupobimbo.com");
        factura.setTelefono(1800246860);
        factura.setRfc("BIM-011108-DJ5");
        factura.setRegimenFiscal("General");
        factura.setCertificadoDigital("BIM45528");
        factura.setSerieCertificadoSat("SAT45646");
        factura.setFecha(new Date());
        facturaRepository.save(factura);
    }

    @Override
    public List getFacturas() {
        return facturaRepository.findAll();
    }

    @Override
    public FacturaModel getFactura(int folio) {
        return facturaRepository.findByFolio(folio);
    }
    
    @Override
    public List getFacturasCliente(String rfcCliente) {
        return facturaRepository.findByRfcCliente(rfcCliente);
    }

    @Override
    public void updateFactura(FacturaModel factura, Integer folio) {
        factura.setFolio(folio);
        facturaRepository.save(factura);
    }

    @Override
    public void deleteFactura(Integer folio) {
        facturaRepository.deleteById(folio);
    }

    @Override
    public FacturaModel getFacturaByFolioFiscal(String folio_fiscal) {
        return facturaRepository.findByFolioFiscal(folio_fiscal);
    }

    
    
}
