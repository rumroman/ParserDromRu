/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.romanrum45;

import com.romanrum45.model.AdvertOfCarsModelInterface;

/**
 *
 * @author Rum
 */
public class Pause implements Runnable {
    
    private Thread t;    
    private AdvertOfCarsModelInterface model;
    private int timePause;
    
    public Pause(AdvertOfCarsModelInterface model,int timePause){
        
        this.model = model;
        this.timePause = timePause;
        t = new Thread(this);
        t.start();
    }
    

    @Override
    public void run() {
        
        try{
            Thread.sleep(timePause);            
            new Parser(model);
        }catch(InterruptedException e){
            System.out.println(e);
        }
       
    }
    
}
