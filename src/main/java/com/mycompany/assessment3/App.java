/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.assessment3;

import com.mycompany.assessment3.VendingController.VendingController;
import com.mycompany.assessment3.VendingDAO.VendingAuditDao;
import com.mycompany.assessment3.VendingDAO.VendingAuditDaoImpl;
import com.mycompany.assessment3.VendingDAO.VendingDao;
import com.mycompany.assessment3.VendingDAO.VendingDaoImpl;
import com.mycompany.assessment3.VendingService.VendingServiceLayer;
import com.mycompany.assessment3.VendingService.VendingServiceLayerImpl;
import com.mycompany.assessment3.VendingUI.VendingUserIO;
import com.mycompany.assessment3.VendingUI.VendingUserIOConsoleImpl;
import com.mycompany.assessment3.VendingUI.VendingView;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Darren
 */
public class App {
    public static void main(String[] args) {
        /*VendingUserIO myIo = new VendingUserIOConsoleImpl();
        VendingView myView = new VendingView(myIo);
        
        VendingDao myDao = new VendingDaoImpl();
        VendingAuditDao myAuditDao = new VendingAuditDaoImpl();
        VendingServiceLayer myService = new VendingServiceLayerImpl(myDao, myAuditDao);
        
        VendingController controller = new VendingController(myService, myView);
        controller.run();*/
        
        ApplicationContext ctx = 
           new ClassPathXmlApplicationContext("applicationContext.xml");
        VendingController controller = 
           ctx.getBean("controller", VendingController.class);
        controller.run();
    }  
}
