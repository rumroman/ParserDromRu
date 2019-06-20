/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.romanrum45.controller;

/**
 *
 * @author Rum
 */
public interface SelectionControllerInterface {    
    
    void setSelectDialogCity();
    void setIndexRegion(int index);
    void setIndexCity(int index);
    int getIndexCitySelected();
    void setIndexBrand(int index);
    void setIndexModel(int index);
    void setBtnSubmit(String url);
    void setBtnStopSubmit();
}
