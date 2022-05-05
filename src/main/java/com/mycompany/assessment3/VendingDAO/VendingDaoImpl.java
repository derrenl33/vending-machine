/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.assessment3.VendingDAO;
import com.mycompany.assessment3.VendingDTO.Item;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author Darren
 */
public class VendingDaoImpl implements VendingDao{
    private Map<String, Item> items = new HashMap<>();
    private final String INVENTORY_FILE; 
    
    public VendingDaoImpl(){
        INVENTORY_FILE = "inventory.txt";
    }

    public VendingDaoImpl(String inventoryTextFile){
        INVENTORY_FILE = inventoryTextFile;
    }
    
    @Override
    public List<Item> getAllItems() throws VendingDaoException {
        loadInventory();
        return new ArrayList<Item>(items.values());
    }
    
    @Override
    public Item getItemByName(String name) throws VendingDaoException {
        loadInventory();
        return items.get(name);
    }

    @Override
    public void updateInventory(Item item, String name) throws VendingDaoException {
        loadInventory();
        int itemCount = Integer.parseInt(items.get(name).getNum());
        itemCount--;
        items.get(name).setNum(String.valueOf(itemCount));
        writeInventory();      
    }
    
    private Item unmarshallItem(String itemAsText){
        String[] itemTokens = itemAsText.split("::");
        String name = itemTokens[0];

        Item itemFromFile = new Item(name);

        itemFromFile.setCost(itemTokens[1]);

        itemFromFile.setNum(itemTokens[2]);

        return itemFromFile;
    }
    
    private void loadInventory() throws VendingDaoException {
        Scanner myScanner;

        try {
            // Create Scanner for reading the file
            myScanner = new Scanner(
                    new BufferedReader(
                            new FileReader(INVENTORY_FILE)));
        } catch (FileNotFoundException ex) {
            throw new VendingDaoException(
                    "Could not load Inventory data into memory.", ex);
        }

        String currentLine;
        Item currentItem;
        while (myScanner.hasNextLine()) {
            currentLine = myScanner.nextLine();
            currentItem = unmarshallItem(currentLine);
            items.put(currentItem.getName(), currentItem);
        }
        // close scanner
        myScanner.close();
    }
    
    private String marshallItem(Item aItem){

        String itemAsText = aItem.getName() + "::";

        itemAsText += aItem.getCost() + "::";

        itemAsText += aItem.getNum();

        return itemAsText;
    }
    
    private void writeInventory() throws VendingDaoException {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(INVENTORY_FILE));
        } catch (IOException ex) {
            throw new VendingDaoException(
                    "Could not save Inventory data.", ex);
        }

        String itemAsText;
        List<Item> list = this.getAllItems();
        List<Item> itemList = list.stream().filter((p) -> p.getName().equals(p.getName()))
                                           .collect(Collectors.toList()); 
        for (Item currentItem : itemList) {
            itemAsText = marshallItem(currentItem);
            out.println(itemAsText);
            out.flush();
        }
        // Clean up
        out.close();
    }
    
}
