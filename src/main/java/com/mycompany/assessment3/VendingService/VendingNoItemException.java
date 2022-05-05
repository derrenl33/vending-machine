/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.assessment3.VendingService;

/**
 *
 * @author Darren
 */
public class VendingNoItemException extends Exception{
    public VendingNoItemException(String message) {
        super(message);
    }

    public VendingNoItemException(String message,
            Throwable cause) {
        super(message, cause);
    }
}
