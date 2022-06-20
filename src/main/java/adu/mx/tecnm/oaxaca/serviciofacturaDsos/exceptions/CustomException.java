/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adu.mx.tecnm.oaxaca.serviciofacturaDsos.exceptions;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author JOSELYNE
 */
public abstract class CustomException extends Exception {
    public CustomException(String string) {
        super(string);
    }

    public Map<String, Object> toJSON() {
        Map<String, Object> exception = new HashMap();
        exception.put("errorName", getClass().getSimpleName());
        exception.put("cause", getMessage());
        return exception;
    }
}
