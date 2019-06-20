/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.romanrum45;

import com.romanrum45.controller.SelectionController;
import com.romanrum45.model.SelectionModel;

/**
 *
 * @author Rum
 */
public class SelectionDemo {
    public static void main(String [] args){
        SelectionModel model = new SelectionModel();
        SelectionController controller = new SelectionController(model);
        
    }
}
