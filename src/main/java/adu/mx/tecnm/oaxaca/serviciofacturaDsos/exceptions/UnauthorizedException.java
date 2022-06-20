/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adu.mx.tecnm.oaxaca.serviciofacturaDsos.exceptions;

import adu.mx.tecnm.oaxaca.serviciofacturaDsos.constans.AuthenticationConstans;
/**
 *
 * @author JOSELYNE
 */
public class UnauthorizedException extends CustomException{
    
    public UnauthorizedException() {
        super(AuthenticationConstans.INVALID_TOKEN_MENSAJE_EXCEPTION);
    }
    
}
