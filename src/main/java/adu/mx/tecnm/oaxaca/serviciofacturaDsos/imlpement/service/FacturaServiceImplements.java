/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adu.mx.tecnm.oaxaca.serviciofacturaDsos.imlpement.service;

import adu.mx.tecnm.oaxaca.serviciofacturaDsos.model.FacturaModel;
import adu.mx.tecnm.oaxaca.serviciofacturaDsos.repository.FacturaRepository;
import adu.mx.tecnm.oaxaca.serviciofacturaDsos.service.FacturaService;
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
    public void updateFactura(FacturaModel factura, Integer folio) {
        factura.setFolio(folio);
        facturaRepository.save(factura);
    }

    @Override
    public void deleteFactura(Integer folio) {
        facturaRepository.deleteById(folio);
    }
    
}
