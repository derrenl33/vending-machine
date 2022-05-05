/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.assessment3.VendingDAO;

import com.mycompany.assessment3.VendingDTO.Item;
import java.util.List;

/**
 *
 * @author Darren
 */
public interface VendingDao {
    List<Item> getAllItems() throws VendingDaoException;
    Item getItemByName(String name) throws VendingDaoException;
    void updateInventory(Item item, String name) throws VendingDaoException;
}
