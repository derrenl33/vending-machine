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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Darren
 */
public class VendingServiceLayerImplTest {
    //uses daoStub items to test
    
    
    private VendingServiceLayer service;
    
    public VendingServiceLayerImplTest() {
        /*VendingDao dao = new VendingDaoStubImpl();
        VendingAuditDao auditDao = new VendingAuditDaoStubImpl();

        service = new VendingServiceLayerImpl(dao, auditDao);*/
        ApplicationContext ctx = 
            new ClassPathXmlApplicationContext("applicationContext.xml");
        service = 
            ctx.getBean("serviceLayer", VendingServiceLayer.class);
    }

    @Test
    public void testInsufficientFundsPurchase() {
        // ARRANGE
        String funds = "2.00";
        // ACT
        try {
            service.purchaseItem(funds, "Cookies");
            fail("Expected Insufficient Funds Exception was not thrown.");
        } catch (VendingDaoException
                | VendingNoItemException ex) {
            fail("Incorrect Exception thrown.");
        // ASSERT
        } catch (VendingInsufficientFundsException ex){
            return;
        }
    }
    
    @Test
    public void testNoItemsPurchase() throws VendingDaoException {
        // ARRANGE
        String funds = "2.00";
        // ACT
        try {
            service.purchaseItem(funds, "Jerky");
            fail("Expected No Item Exception was not thrown.");
        } catch (VendingDaoException
                | VendingInsufficientFundsException ex) {
            fail("Incorrect Exception thrown.");
        // ASSERT
        } catch (VendingNoItemException ex){
            return;
        }
    }
    
    @Test
    public void testGetAllItems() throws VendingDaoException {
        // ACT & ASSERT
        assertEquals( 2, service.getAllItems().size(), 
                                       "Should only have two items.");
        assertTrue( service.getAllItems().contains(service.getItemByName("Cookies")),
                                  "One item should be Cookies.");
        assertTrue( service.getAllItems().contains(service.getItemByName("Jerky")),
                                  "One item should be Jerky.");
    }
}
