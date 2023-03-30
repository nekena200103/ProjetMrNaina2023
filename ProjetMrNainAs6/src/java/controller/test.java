/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Service.ObjectinsertService;
import entity.Objectinsert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author caeyla
 */
@Component
public class test {
    @Autowired
    ObjectinsertService oi;
    public static void main(String[] args) throws Exception {
        ObjectinsertService ois=new ObjectinsertService();
        
        System.out.println(ois.find(new Objectinsert()));
    }
}
