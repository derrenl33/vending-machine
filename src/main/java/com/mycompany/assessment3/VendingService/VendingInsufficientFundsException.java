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
public class VendingInsufficientFundsException extends Exception{
    public VendingInsufficientFundsException(String message) {
        super(message);
    }

    public VendingInsufficientFundsException(String message,
            Throwable cause) {
        super(message, cause);
    }
} 
    

