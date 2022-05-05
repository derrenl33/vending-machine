/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.assessment3.VendingService;
import com.mycompany.assessment3.VendingDAO.VendingAuditDao;
import com.mycompany.assessment3.VendingDAO.VendingDao;
import com.mycompany.assessment3.VendingDAO.VendingDaoException;
import com.mycompany.assessment3.VendingDTO.Item;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Darren
 */
public class VendingServiceLayerImpl implements VendingServiceLayer{
    private VendingDao dao;
    private VendingAuditDao auditDao;

    public VendingServiceLayerImpl(VendingDao dao, VendingAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }
    
    public enum Coin {
        P(1), N(5), D(10), Q(25);
        private int value;

        Coin(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
    
    Scanner myScanner = new Scanner(System.in);

    public List<Item> getAllItems() throws VendingDaoException{
        return dao.getAllItems();
    }

    public Item getItemByName(String item) throws VendingDaoException{
        return dao.getItemByName(item);
    }

    public void updateInventory(Item item, String name) throws VendingDaoException{
        dao.updateInventory(item, name);
    }

    public String getFunds() throws VendingDaoException{
        //getting funds
        String stringFunds = myScanner.nextLine();
        if(stringFunds.equals("Exit")){
            System.exit(0);
        }
        auditDao.writeAuditEntry("USER INPUT: $" + stringFunds);
        return stringFunds;
    }
    
    public String getItem() throws VendingDaoException{
        //getting item
        String itemSelected = myScanner.nextLine();
        if(itemSelected.equals("Exit")){
            System.exit(0);
        }
        return itemSelected;
    }
    
    public String purchaseItem(String funds, String itemSelected) throws VendingDaoException,  VendingInsufficientFundsException, VendingNoItemException {
        //getting funds
        BigDecimal bdFunds = new BigDecimal(funds);
        BigDecimal roundedFunds = bdFunds.setScale(2, RoundingMode.HALF_UP);

        //getting item cost
        BigDecimal itemCost = new BigDecimal(dao.getItemByName(itemSelected).getCost());
        BigDecimal roundedCost = itemCost.setScale(2, RoundingMode.HALF_UP);

        int bdCompare;
        bdCompare = roundedFunds.compareTo(roundedCost); 
        if(!dao.getItemByName(itemSelected).getNum().equals("0")){

                switch (bdCompare) {
                    case 0:
                        //exactly enough to purchase
                        dao.updateInventory(dao.getItemByName(itemSelected), itemSelected);
                        auditDao.writeAuditEntry("ITEM " + itemSelected + " PURCHASED. " + 
                                                dao.getItemByName(itemSelected).getNum() + " REMAINING");
                        roundedFunds = roundedFunds.subtract(roundedCost);
                        return getChange(roundedFunds.floatValue());
                    case 1:
                        //more than enough funds to purchase
                        dao.updateInventory(dao.getItemByName(itemSelected), itemSelected);
                        auditDao.writeAuditEntry("ITEM " + itemSelected + " PURCHASED. " + 
                                                dao.getItemByName(itemSelected).getNum() + " REMAINING");
                        roundedFunds = roundedFunds.subtract(roundedCost);
                        return getChange(roundedFunds.floatValue());
                    case -1:
                        //insufficient funds exception
                        auditDao.writeAuditEntry("NOT ENOUGH FUNDS - GIVE FUNDS BACK TO USER");
                        throw new VendingInsufficientFundsException("Not enough funds to purchase item. Need $" + roundedCost);
                    default:
                        break;
                }

        }else{
            //(no item exception)
            auditDao.writeAuditEntry("OUT OF STOCK - GIVE FUNDS BACK TO USER");
            throw new VendingNoItemException("Item is out of stock.");
        }
        return "ERROR";
    }
    public String getChange(float change) throws VendingDaoException {
        int qCount = 0;
        int dCount = 0;
        int nCount = 0;
        int pCount = 0;
        auditDao.writeAuditEntry("USER GETS BACK: $" + change);
        change = change * 100;
        if(Math.floor(change/Coin.Q.getValue()) >= 1){
            qCount = (int) Math.floor(change/Coin.Q.getValue());
            change -= qCount * Coin.Q.getValue();
        }
        if(Math.floor(change/Coin.D.getValue()) >= 1){
            dCount = (int) Math.floor(change/Coin.D.getValue());
            change -= dCount * Coin.D.getValue();
        }
        if(Math.floor(change/Coin.N.getValue()) >= 1){
            nCount = (int) Math.floor(change/Coin.N.getValue());
            change -= nCount * Coin.N.getValue();
        }
        if(Math.floor(change/Coin.P.getValue()) >= 1){
            pCount = (int) Math.floor(change/Coin.P.getValue());
            change -= pCount * Coin.P.getValue();
        }
        
        String changeMsg = "Enjoy! Your change is: " 
                            + String.valueOf(qCount) + Coin.Q + ", "
                            + String.valueOf(dCount) + Coin.D + ", "
                            + String.valueOf(nCount) + Coin.N + ", "
                            + String.valueOf(pCount) + Coin.P;
        return changeMsg;
    }
}

