/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adu.mx.tecnm.oaxaca.serviciofacturaDsos.repository;

import adu.mx.tecnm.oaxaca.serviciofacturaDsos.model.FacturaModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 *
 * @author JOSELYNE
 */
@Repository
public interface FacturaRepository extends JpaRepository<FacturaModel, Integer>{
   public FacturaModel findByFolio(int folio); 
   public FacturaModel findByFolioFiscal(String folio_fiscal);
   public List findByRfcCliente(String rfcCliente); 
}

