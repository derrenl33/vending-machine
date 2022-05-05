/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.assessment3.VendingService;

import com.mycompany.assessment3.VendingDAO.VendingDaoException;
import com.mycompany.assessment3.VendingDTO.Item;
import java.util.List;

/**
 *
 * @author Darren
 */
public interface VendingServiceLayer {
    List<Item> getAllItems() throws VendingDaoException;
    void updateInventory(Item item, String name) throws VendingDaoException;
    Item getItemByName(String name)throws VendingDaoException;
    String getFunds() throws VendingDaoException;
    String getItem() throws VendingDaoException;
    String purchaseItem(String funds, String item)throws VendingDaoException, VendingInsufficientFundsException, VendingNoItemException;
    String getChange(float change)throws VendingDaoException;
}
