/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adu.mx.tecnm.oaxaca.serviciofacturaDsos.controller;

import adu.mx.tecnm.oaxaca.serviciofacturaDsos.model.FacturaModel;
import adu.mx.tecnm.oaxaca.serviciofacturaDsos.service.FacturaService;
import adu.mx.tecnm.oaxaca.serviciofacturaDsos.utils.CustomResponse;
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
    public CustomResponse resgistrarFactura(@RequestBody FacturaModel factura){
        CustomResponse customResponse = new CustomResponse();
        if(facturaService.getFactura(factura.getFolio())!=null){
        customResponse.setMensaje("El folio ya se encuentra registrado");
        return customResponse;
        }else{
        facturaService.registrarFactura(factura);
        return customResponse;}
    }
    
    @GetMapping("/")
    public CustomResponse getFacturas(){
       CustomResponse customResponse = new CustomResponse();
       customResponse.setData(facturaService.getFacturas());
       return customResponse;
    }
    
    @GetMapping("/{folio}")
    public CustomResponse getVenta(@PathVariable int folio){
       CustomResponse customResponse = new CustomResponse(); 
       customResponse.setData(facturaService.getFactura(folio));
       return customResponse;
    }
    
    @PutMapping("/{folio}")
    public CustomResponse updateFactura(@RequestBody FacturaModel factura, @PathVariable Integer folio){
        CustomResponse customResponse = new CustomResponse();
        facturaService.updateFactura(factura, folio);
        return customResponse;
    }
    
    @PutMapping("/estado/{folio}")
    public CustomResponse updateEstadoFactura(@RequestBody FacturaModel factura,@PathVariable Integer folio){
        CustomResponse customResponse = new CustomResponse();
        boolean estado= factura.isEstado();
        FacturaModel cambio= facturaService.getFactura(folio);
        cambio.setEstado(estado);
        facturaService.updateFactura(cambio, folio);
        return customResponse;
    }
    
    @DeleteMapping("/{folio}")
    public CustomResponse deleteFactura(@PathVariable Integer folio){
        CustomResponse customResponse = new CustomResponse();
        facturaService.deleteFactura(folio);
         return customResponse;
    }
}
