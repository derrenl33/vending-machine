/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.assessment3.VendingDAO;

/**
 *
 * @author Darren
 */
public interface VendingAuditDao {
    public void writeAuditEntry(String entry) throws VendingDaoException;
}