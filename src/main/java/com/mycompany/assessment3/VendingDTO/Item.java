/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.assessment3.VendingDTO;

import java.util.Objects;

/**
 *
 * @author Darren
 */
public class Item {
    private String name;
    private String cost;
    private String inventoryNum;

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
    
    public String getNum() {
        return inventoryNum;
    }

    public void setNum(String inventoryNum) {
        this.inventoryNum = inventoryNum;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + Objects.hashCode(this.cost);
        hash = 37 * hash + Objects.hashCode(this.inventoryNum);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.cost, other.cost)) {
            return false;
        }
        if (!Objects.equals(this.inventoryNum, other.inventoryNum)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Item{" + "name=" + name + ", cost=" + cost + ", inventoryNum=" + inventoryNum + '}';
    }
}