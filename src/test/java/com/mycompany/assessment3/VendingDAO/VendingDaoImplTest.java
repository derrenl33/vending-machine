/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.assessment3.VendingDAO;

import com.mycompany.assessment3.VendingDTO.Item;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Darren
 */
public class VendingDaoImplTest {
    //uses testInventory to test
    
    VendingDao testDao;
    
    public VendingDaoImplTest() {
    }
    
    @BeforeEach
    public void setUp() throws Exception{
        String testFile = "testInventory.txt";
        Scanner myScanner;
        myScanner = new Scanner(
                    new BufferedReader(
                            new FileReader(testFile)));
        testDao = new VendingDaoImpl(testFile);
        myScanner.close();
    }

    @Test
    public void testGetAllItems() throws VendingDaoException {
        // Retrieve the list of items from DAO
        List<Item> allItems = testDao.getAllItems();
        
        // First check the general contents of the list
        assertEquals(2, allItems.size(),"List of items should have 2 items.");

        // Then the specifics
        assertTrue(allItems.contains(testDao.getItemByName("Soda")),
                    "The list of items should include Soda.");
        assertTrue(allItems.contains(testDao.getItemByName("Chips")),
                "The list of items should include Chips.");
        }
    
    @Test
    public void testGetItemByName() throws VendingDaoException {
        // Retrieve the item by name
        Item retrievedItem = testDao.getItemByName("Soda");
        
        // Check the general contents of the item
        assertEquals("Soda",
                retrievedItem.getName(),
                "Checking item name.");
        assertEquals("1.50",
                retrievedItem.getCost(),
                "Checking item cost.");
        assertEquals("5", 
                retrievedItem.getNum(),
                "Checking item amount.");
    }
    
    @Test
    public void testUpdateInventory() throws VendingDaoException {
        // Record current amount of chips.
        int prevNum = Integer.parseInt(testDao.getItemByName("Chips").getNum());
        
        // Update the item by name
        testDao.updateInventory(testDao.getItemByName("Chips"), "Chips");
        
        //Record new amount of chips.
        int newNum = Integer.parseInt(testDao.getItemByName("Chips").getNum());
        
        // Check if item amount decremented
        assertEquals(prevNum, newNum+1,
                "Checking item amount.");
    }
    
}
