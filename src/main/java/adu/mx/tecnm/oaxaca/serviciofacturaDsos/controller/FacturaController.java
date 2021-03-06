/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adu.mx.tecnm.oaxaca.serviciofacturaDsos.controller;

import adu.mx.tecnm.oaxaca.serviciofacturaDsos.authentication.Authentication;
import adu.mx.tecnm.oaxaca.serviciofacturaDsos.exceptions.ExternalMicroserviceException;
import adu.mx.tecnm.oaxaca.serviciofacturaDsos.exceptions.UnauthorizedException;
import adu.mx.tecnm.oaxaca.serviciofacturaDsos.model.FacturaModel;
import adu.mx.tecnm.oaxaca.serviciofacturaDsos.service.FacturaService;
import adu.mx.tecnm.oaxaca.serviciofacturaDsos.utils.CustomResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private Authentication authentication;
    /**
    * Registro de facturas
    */
    @PostMapping("/")
    public ResponseEntity resgistrarFactura(@RequestBody FacturaModel factura, HttpServletRequest request) {
        ResponseEntity<CustomResponse> valueResponse = null;
        CustomResponse responseData = new CustomResponse();
        try {
            authentication.auth(request);
            boolean flag = true;
            if (factura.getFolio() == null) {
                flag = false;
                responseData.setMensaje("Falta el folio de la factura");
                valueResponse = ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(responseData);
                responseData.setHttpCode(400);
                return valueResponse;
            }
            if (factura.getFolio()==0) {
                flag = false;
                responseData.setMensaje("El folio de la factura es inv??lido");
                responseData.setHttpCode(422);
                valueResponse = ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(responseData);
            }
            if (factura.getFolioFiscal().isEmpty() || factura.getFolioFiscal()==null) {
                flag = false;
                responseData.setMensaje("Falta folio fiscal");
                responseData.setHttpCode(400);
                valueResponse = ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(responseData);
            }
            if (factura.getIdPago() == null) {
                flag = false;
                responseData.setMensaje("Falta el id de pago");
                valueResponse = ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(responseData);
                responseData.setHttpCode(400);
                return valueResponse;
            }
            if (factura.getIdPago() == 0) {
                flag = false;
                responseData.setHttpCode(422);
                responseData.setMensaje("El id del pago no es v??lido");
                valueResponse = ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(responseData);
            }
            if (factura.getRfcCliente().isEmpty() || factura.getRfcCliente()==null) {
                flag = false;
                responseData.setHttpCode(400);
                responseData.setMensaje("Falta rfc del cliente");
                valueResponse = ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(responseData);
            } else if (flag) {
                    if (facturaService.getFactura(factura.getFolio()) != null) {
                        responseData.setHttpCode(400);
                        responseData.setMensaje("El folio ya se encuentra registrado");
                        valueResponse = ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(responseData);
                        return valueResponse;
                    }
                    if (facturaService.getFacturaByFolioFiscal(factura.getFolioFiscal()) != null) {
                        responseData.setHttpCode(400);
                        responseData.setMensaje("El folio fiscal ya se encuentra registrado");
                        valueResponse = ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(responseData);
                        return valueResponse;
                    }
                    if (factura.getFolioFiscal().length() != 36) {
                        responseData.setHttpCode(422);
                        responseData.setMensaje("El folio fiscal no cumple con el formato solicitado");
                        valueResponse = ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(responseData);
                    }
                    if (factura.getFolioFiscal().length() == 36) {
                        Pattern pat = Pattern.compile("[a-zA-Z0-9]{8}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{12}");
                        Matcher mat = pat.matcher(factura.getFolioFiscal());
                        if (mat.matches()) {
                            facturaService.registrarFactura(factura);
                            responseData.setMensaje("CREATED");
                            responseData.setHttpCode(201);
                            valueResponse = ResponseEntity.status(HttpStatus.CREATED).body(responseData);
                        } else {
                            responseData.setMensaje("El folio fiscal no cumple con el formato solicitado");
                            responseData.setHttpCode(422);
                            valueResponse = ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(responseData);
                        }
                    }
                }
            return valueResponse;
        }catch (UnauthorizedException ex) {
                responseData.setData(ex.toJSON());
                responseData.setHttpCode(401);
                valueResponse = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseData);
        }catch (ExternalMicroserviceException ex) {
                 responseData.setData(ex.toJSON());
                responseData.setHttpCode(503);
                valueResponse = ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(responseData);
        }catch (Exception ex) {
                responseData.setHttpCode(500);
                valueResponse = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return valueResponse;
    }
        /**
        * Obtener todas las facturas registradas
        */
        @GetMapping("/")
        public ResponseEntity getFacturas(HttpServletRequest request) {
        ResponseEntity valueResponse = null;   
        CustomResponse responseData = new CustomResponse();
            try{
                authentication.auth(request);
                responseData.setData(facturaService.getFacturas());
                responseData.setMensaje("OK");
                responseData.setHttpCode(200);
                valueResponse = ResponseEntity.status(HttpStatus.OK).body(responseData);
            }catch (UnauthorizedException ex) {
                responseData.setData(ex.toJSON());
                responseData.setHttpCode(401);
                valueResponse = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseData);
            }catch (ExternalMicroserviceException ex) {
                responseData.setData(ex.toJSON());
                responseData.setHttpCode(503);
                valueResponse = ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(responseData);
            }catch (Exception ex) {
                responseData.setHttpCode(500);
                valueResponse = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
            return valueResponse;
        }
        /**
        * Obtener todas las facturas registradas de un cliente en espec??fico
        */
        @GetMapping("/facturasCliente/{rfcCliente}")
        public ResponseEntity getFacturasCliente(@PathVariable String rfcCliente,HttpServletRequest request) {
        ResponseEntity valueResponse = null;   
        CustomResponse responseData = new CustomResponse();
            try{
                authentication.auth(request);
                if (facturaService.getFacturasCliente(rfcCliente) == null) {
                    valueResponse = ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
                    responseData.setMensaje("No hay facturas con este rfc: " + rfcCliente);
                    responseData.setHttpCode(404); 
                    return valueResponse;
                }else{
                    responseData.setData(facturaService.getFacturasCliente(rfcCliente));
                    responseData.setMensaje("OK");
                    responseData.setHttpCode(200);
                    valueResponse = ResponseEntity.status(HttpStatus.OK).body(responseData);
                    return valueResponse;
                }
            }catch (UnauthorizedException ex) {
                responseData.setData(ex.toJSON());
                responseData.setHttpCode(401);
                valueResponse = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseData);
            }catch (ExternalMicroserviceException ex) {
                responseData.setData(ex.toJSON());
                responseData.setHttpCode(503);
                valueResponse = ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(responseData);
            }catch (Exception ex) {
                responseData.setHttpCode(500);
                valueResponse = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
            return valueResponse;
            
        }
        /**
        * Obtener una factura de acuerdo al folio
        */
        @GetMapping("/{folio}")
        public ResponseEntity getFactura(@PathVariable Integer folio,HttpServletRequest request) {
        ResponseEntity valueResponse = null;   
        CustomResponse responseData = new CustomResponse();
            try{
                authentication.auth(request);
                if (facturaService.getFactura(folio) == null) {
                    valueResponse = ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
                    responseData.setMensaje("No hay facturas con este folio: " + folio);
                    responseData.setHttpCode(404);
                    return valueResponse;
                }else{
                    responseData.setData(facturaService.getFactura(folio));
                    responseData.setMensaje("OK");
                    responseData.setHttpCode(200);
                    valueResponse = ResponseEntity.status(HttpStatus.OK).body(responseData);
                    return valueResponse;
                }
            }catch (UnauthorizedException ex) {
                responseData.setData(ex.toJSON());
                responseData.setHttpCode(401);
                valueResponse = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseData);
            }catch (ExternalMicroserviceException ex) {
                responseData.setData(ex.toJSON());
                responseData.setHttpCode(503);
                valueResponse = ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(responseData);
            }catch (Exception ex) {
                responseData.setHttpCode(500);
                valueResponse = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
            return valueResponse;
            
        }
         /**
        * Obtener la factura registradas por folio fiscal
        */
        @GetMapping("/foliofiscal/{folio_fiscal}")
        public ResponseEntity getFacturaF(@PathVariable String folio_fiscal,HttpServletRequest request) {
        ResponseEntity valueResponse = null;   
        CustomResponse responseData = new CustomResponse();
            try{
                authentication.auth(request);
                if (facturaService.getFacturaByFolioFiscal(folio_fiscal) == null) {
                    valueResponse = ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
                    responseData.setMensaje("No hay facturas con este folio fiscal: " + folio_fiscal);
                    responseData.setHttpCode(422);
                    return valueResponse;
                }else{
                    responseData.setData(facturaService.getFacturaByFolioFiscal(folio_fiscal));
                    responseData.setMensaje("OK");
                    responseData.setHttpCode(200);
                    valueResponse = ResponseEntity.status(HttpStatus.OK).body(responseData);
                }
            }catch (UnauthorizedException ex) {
                responseData.setData(ex.toJSON());
                responseData.setHttpCode(401);
                valueResponse = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseData);
            }catch (ExternalMicroserviceException ex) {
                responseData.setData(ex.toJSON());
                responseData.setHttpCode(503);
                valueResponse = ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(responseData);
            }catch (Exception ex) {
                responseData.setHttpCode(500);
                valueResponse = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
            return valueResponse;
            
        }
        /**
        * Actualizar una factura por folio
        */
        @PutMapping("/{folio}")
        public ResponseEntity updateFactura(@RequestBody FacturaModel factura, 
        @PathVariable Integer folio, HttpServletRequest request) {
        ResponseEntity valueResponse = null;
        CustomResponse responseData = new CustomResponse();
            try {
            authentication.auth(request);
            boolean flag = true;
            if (factura.getFolio() == null) {
                flag = false;
                responseData.setMensaje("Falta el folio de la factura");
                valueResponse = ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(responseData);
                responseData.setHttpCode(400);
                return valueResponse;
            }
            if (factura.getFolio()==0) {
                flag = false;
                responseData.setMensaje("El folio de la factura es inv??lido");
                responseData.setHttpCode(400);
                valueResponse = ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(responseData);
            }
            if (factura.getFolioFiscal().isEmpty() || factura.getFolioFiscal()==null) {
                flag = false;
                responseData.setMensaje("Falta folio fiscal");
                responseData.setHttpCode(400);
                valueResponse = ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(responseData);
            }
            if (factura.getIdPago() == null) {
                flag = false;
                responseData.setMensaje("Falta el id de pago");
                valueResponse = ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(responseData);
                responseData.setHttpCode(400);
                return valueResponse;
            }
            if (factura.getIdPago() == 0) {
                flag = false;
                responseData.setHttpCode(400);
                responseData.setMensaje("El id del pago no es v??lido");
                valueResponse = ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(responseData);
            }
            if (factura.getRfcCliente().isEmpty() || factura.getRfcCliente()==null) {
                flag = false;
                responseData.setHttpCode(400);
                responseData.setMensaje("Falta rfc del cliente");
                valueResponse = ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(responseData);
            } 
            else if (flag) {
                    if (factura.getFolioFiscal().length() != 36) {
                        responseData.setHttpCode(422);
                        responseData.setMensaje("El folio fiscal no cumple con el formato solicitado");
                        valueResponse = ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(responseData);
                    }
                    if (factura.getFolioFiscal().length() == 36) {
                        Pattern pat = Pattern.compile("[a-zA-Z0-9]{8}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{12}");
                        Matcher mat = pat.matcher(factura.getFolioFiscal());
                        if (mat.matches()) {
                            boolean estado = factura.isEstado();
                            facturaService.registrarFactura(factura);
                            responseData.setMensaje("OK");
                            responseData.setHttpCode(200);
                            valueResponse = ResponseEntity.status(HttpStatus.OK).body(responseData);
                        } else {
                            responseData.setMensaje("El folio fiscal no cumple con el formato solicitado");
                        }
                    }
                }
            return valueResponse;
            }catch (NullPointerException e) { 
                responseData.setMensaje("Faltan campos por rellenar");
                valueResponse = ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseData);
                responseData.setHttpCode(401);
                }catch (UnauthorizedException ex) {
                responseData.setData(ex.toJSON());
                responseData.setHttpCode(401);
                valueResponse = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseData);
            }catch (ExternalMicroserviceException ex) {
                 responseData.setData(ex.toJSON());
                responseData.setHttpCode(503);
                valueResponse = ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(responseData);
            }catch (Exception ex) {
                responseData.setHttpCode(500);
                valueResponse = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        return valueResponse;
        }
        /**
        * Actualizar el estado de una factura por folio
        */
        @PutMapping("/estado/{folio}")
        public CustomResponse updateEstadoFactura(@RequestBody FacturaModel factura,@PathVariable Integer folio){
            CustomResponse customResponse = new CustomResponse();
            boolean estado= factura.isEstado();
            FacturaModel cambio= facturaService.getFactura(folio);
            cambio.setEstado(estado);
            facturaService.updateFactura(cambio, folio);
            return customResponse;
        }
        /**
        * Eliminar una factura
        */
        @DeleteMapping("/{folio}")
        public ResponseEntity deleteFactura(@PathVariable Integer folio,HttpServletRequest request) {
        ResponseEntity<CustomResponse> valueResponse = null;
            CustomResponse responseData = new CustomResponse();
            try {
                authentication.auth(request);
                boolean flag = true;
                if (facturaService.getFactura(folio) == null) {
                            valueResponse = ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
                            responseData.setMensaje("No hay facturas con este folio: " + folio);
                            responseData.setHttpCode(404);
                            return valueResponse;
                }else{
                    facturaService.deleteFactura(folio);
                    responseData.setMensaje("OK");
                    responseData.setHttpCode(200);
                    valueResponse = ResponseEntity.status(HttpStatus.OK).body(responseData);
                    return valueResponse;
                }
            }catch (UnauthorizedException ex) {
                responseData.setData(ex.toJSON());
                responseData.setHttpCode(401);
                valueResponse = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseData);
            }catch (ExternalMicroserviceException ex) {
                     responseData.setData(ex.toJSON());
                    responseData.setHttpCode(503);
                    valueResponse = ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(responseData);
            }catch (Exception ex) {
                    responseData.setHttpCode(500);
                    valueResponse = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
            return valueResponse;
            }
        }