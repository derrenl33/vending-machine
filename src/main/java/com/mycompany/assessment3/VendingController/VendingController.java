/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.assessment3.VendingController;
import com.mycompany.assessment3.VendingDAO.VendingDao;
import com.mycompany.assessment3.VendingDAO.VendingDaoException;
import com.mycompany.assessment3.VendingDTO.Item;
import com.mycompany.assessment3.VendingService.VendingInsufficientFundsException;
import com.mycompany.assessment3.VendingService.VendingNoItemException;
import com.mycompany.assessment3.VendingService.VendingServiceLayer;
import com.mycompany.assessment3.VendingService.VendingServiceLayerImpl;
import com.mycompany.assessment3.VendingUI.VendingView;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Darren
 */
public class VendingController {
    private VendingView view;
    private VendingServiceLayer service;
    
    public VendingController(VendingServiceLayer service, VendingView view){
        this.service = service;
        this.view = view;
    }
    
    public void run() {

        try{
            while (true) {
                showItemSelection();
                view.promptFunds();
                String funds = service.getFunds();
                view.promptItemOrExit();
                String itemSelected = service.getItem();
                String change = service.purchaseItem(funds, itemSelected);
                view.displayChange(change);
            }
            //exitMessage();
        }catch (VendingDaoException ex){
            view.displayErrorMessage(ex.getMessage());
        }catch(VendingNoItemException ex){
            view.displayErrorMessage(ex.getMessage());
        }catch(VendingInsufficientFundsException ex){
            view.displayErrorMessage(ex.getMessage());
        }
    }
    
    private void showItemSelection() throws VendingDaoException{
        List<Item> itemList = service.getAllItems();
        view.printInventory(itemList);
    }
    
    private void exitMessage() {
        view.displayExitBanner();
    }
}
