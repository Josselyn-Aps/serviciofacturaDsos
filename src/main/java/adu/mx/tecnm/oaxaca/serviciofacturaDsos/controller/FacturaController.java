/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adu.mx.tecnm.oaxaca.serviciofacturaDsos.controller;

import adu.mx.tecnm.oaxaca.serviciofacturaDsos.model.FacturaModel;
import adu.mx.tecnm.oaxaca.serviciofacturaDsos.service.FacturaService;
import adu.mx.tecnm.oaxaca.serviciofacturaDsos.utils.CustomResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author JOSELYNE
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/factura")
public class FacturaController {
    
    @Autowired
    private FacturaService facturaService;
    
    @PostMapping("/")
    public CustomResponse resgistrarFactura(@RequestBody FacturaModel factura) {
        CustomResponse customResponse = new CustomResponse();
        boolean flag = true;
        
        if (factura.getFolio()==null) {
            flag = false;
            customResponse.setMensaje("Falta folio");
        }        
        if (factura.getFolioFiscal()==null) {
            flag = false;
            customResponse.setMensaje("Falta folio fiscal");
        }
        if (factura.getIdPago()==null) {
            flag = false;
            customResponse.setMensaje("Falta id del pago");
        }
        if(factura.getIdPago()==0){
            flag = false;
            customResponse.setMensaje("El id del pago no es válido");
        }
        if (factura.getRfcCliente()==null) {
            flag = false;
            customResponse.setMensaje("Falta rfc del cliente");
        }
        else if (flag) {
            if (facturaService.getFactura(factura.getFolio()) != null) {
                customResponse.setMensaje("El folio ya se encuentra registrado");
            } 
            if (facturaService.getFacturaByFolioFiscal(factura.getFolioFiscal()) != null) {
                customResponse.setMensaje("El folio fiscal ya se encuentra registrado");
            }if(factura.getFolioFiscal().length()!=36){
                customResponse.setMensaje("El folio fiscal no cumple con el formato solicitado");
            }if(factura.getFolioFiscal().length()==36){
                 Pattern pat = Pattern.compile("[a-zA-Z0-9]{8}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{12}");
                 Matcher mat = pat.matcher(factura.getFolioFiscal());
                 if (mat.matches()) {
                    facturaService.registrarFactura(factura);
                } else {
                    customResponse.setMensaje("El folio fiscal no cumple con el formato solicitado");
                }
            }
        }
        return customResponse;
        
    }
    
    @GetMapping("/")
    public CustomResponse getFacturas() {
        CustomResponse customResponse = new CustomResponse();
        customResponse.setData(facturaService.getFacturas());
        return customResponse;
    }
    
      
    @GetMapping("/facturas/{rfcCliente}")
    public CustomResponse getFacturasCliente(@PathVariable String rfcCliente) {
        CustomResponse customResponse = new CustomResponse();
        customResponse.setData(facturaService.getFacturasCliente(rfcCliente));
        return customResponse;
    }
    
    @GetMapping("/{folio}")
    public CustomResponse getFactura(@PathVariable Integer folio) {
        CustomResponse customResponse = new CustomResponse();
        customResponse.setData(facturaService.getFactura(folio));
        return customResponse;
    }
    
    @GetMapping("/factura/foliofiscal/{folio_fiscal}")
    public CustomResponse getFacturaF(@PathVariable String folio_fiscal) {
        CustomResponse customResponse = new CustomResponse();
        customResponse.setData(facturaService.getFacturaByFolioFiscal(folio_fiscal));
        return customResponse;
    }
    
    @PutMapping("/{folio}")
    public CustomResponse updateFactura(@RequestBody FacturaModel factura, @PathVariable Integer folio) {
        CustomResponse customResponse = new CustomResponse();
        facturaService.updateFactura(factura, folio);
        return customResponse;
    }
    
    @PutMapping("/estado/{folio}")
    public CustomResponse updateEstadoFactura(@RequestBody FacturaModel factura, @PathVariable Integer folio) {
        CustomResponse customResponse = new CustomResponse();
        boolean estado = factura.isEstado();
        FacturaModel cambio = facturaService.getFactura(folio);
        cambio.setEstado(estado);
        facturaService.updateFactura(cambio, folio);
        return customResponse;
    }
    
    @DeleteMapping("/{folio}")
    public CustomResponse deleteFactura(@PathVariable Integer folio) {
        CustomResponse customResponse = new CustomResponse();
        facturaService.deleteFactura(folio);
        return customResponse;
    }
}
