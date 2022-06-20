/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adu.mx.tecnm.oaxaca.serviciofacturaDsos.constans;

/**
 *
 * @author JOSELYNE
 */
public class AuthenticationConstans {
    public static final String URL_AUTH = "https://autenticacion-t.herokuapp.com/login/auth/token/";
    public static final String INVALID_TOKEN_MENSAJE_EXCEPTION = "La sesion es invalida";
    
    public static final String ERROR_EXTERNAL_MENSAJE_EXCEPTION = "Problemas con la comunicacion al microservicio de autenticacion";
    private AuthenticationConstans(){}
}