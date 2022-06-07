/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adu.mx.tecnm.oaxaca.serviciofacturaDsos.service;

import adu.mx.tecnm.oaxaca.serviciofacturaDsos.model.FacturaModel;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author JOSELYNE
 */
@Service
public interface FacturaService {
   public void registrarFactura(FacturaModel factura); 
   public List getFacturas();
   public List getFacturasCliente(int idCliente);
   public FacturaModel getFactura(int folio);
   public void updateFactura(FacturaModel factura, Integer folio);
   public void deleteFactura(Integer folio);
}