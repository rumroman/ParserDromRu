/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.romanrum45.observers;

/**
 *
 * @author Rum
 */
public interface AdvertsObserver {
    
    void updateCars();
    void updateBrands();
    void updateModels();
    void updateCitySelected();    
}
