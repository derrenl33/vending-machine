/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.assessment3.VendingService;

import com.mycompany.assessment3.VendingDAO.VendingDao;
import com.mycompany.assessment3.VendingDAO.VendingDaoException;
import com.mycompany.assessment3.VendingDTO.Item;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Darren
 */
public class VendingDaoStubImpl implements VendingDao{

    public Item firstItem;
    public Item secondItem;
    
    public VendingDaoStubImpl() {
        firstItem = new Item("Cookies");
        firstItem.setCost("2.50");
        firstItem.setNum("5");
        
        secondItem = new Item("Jerky");
        secondItem.setCost("2.00");
        secondItem.setNum("0");
    }

    public VendingDaoStubImpl(Item testItem){
         this.firstItem = testItem;
         this.secondItem = testItem;
     }
    
    @Override
    public List<Item> getAllItems() throws VendingDaoException {
        List<Item> itemList = new ArrayList<>();
        itemList.add(firstItem);
        itemList.add(secondItem);
        return itemList;
    }

    @Override
    public Item getItemByName(String name) throws VendingDaoException {
        if (name.equals(firstItem.getName())) {
            return firstItem;
        } else if (name.equals(secondItem.getName())){
            return secondItem;
        } else {
            return null;
        }  
    }

    @Override
    public void updateInventory(Item item, String name) throws VendingDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
