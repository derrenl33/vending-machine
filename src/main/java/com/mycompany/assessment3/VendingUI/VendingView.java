/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.assessment3.VendingUI;
import com.mycompany.assessment3.VendingDTO.Item;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Darren
 */
public class VendingView {

    private VendingUserIO io;
    
    public VendingView(VendingUserIO io) {
        this.io = io;
    }    
    
    public void printInventory(List<Item> itemList) {
        //print only if item is in stock
        List<Item> filteredList = itemList.stream()
                            .filter((i) -> Integer.parseInt(i.getNum()) > 0)
                            .collect(Collectors.toList());
        for (Item currentItem : filteredList) {    
            String itemInfo = String.format("Item: %s   Price: %s   Amount: %s",
                  currentItem.getName(),
                  currentItem.getCost(),
                  currentItem.getNum());   
                io.print(itemInfo);
        }
        io.print("");
    }
    
    public void promptFunds() {
        io.print("Please enter available funds or Exit.");
    }
    
    public void promptItemOrExit() {
        io.print("Please enter item name or Exit.");
    }
    
    public void displayChange(String change) {
        io.print("");
        io.print(change);
        io.readString("Press enter to continue.");
    }
    
    
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}
