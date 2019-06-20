/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.romanrum45.controller;

import com.romanrum45.model.AdvertOfCarsModelInterface;
import com.romanrum45.view.SelectCityDialogGUI;
import com.romanrum45.view.SelectionGUI;

/**
 *
 * @author Rum
 */
public class SelectionController implements SelectionControllerInterface {
    
    private final AdvertOfCarsModelInterface model;
    private final SelectionGUI view;
    private SelectCityDialogGUI citiesDialogView;
    
    public SelectionController(AdvertOfCarsModelInterface model){
        
        this.model = model;
        view = new SelectionGUI(this,model);
        view.createSelectionGUI();
        view.setLocationRelativeTo(null);         
    }
    
    @Override
    public void setBtnSubmit(String submitUrl){        
        model.startSearch(submitUrl);
        actionBtnSubmit();
    } 
    
    @Override
    public void setBtnStopSubmit(){
        model.stopSearch();
        actionBtnStopSubmit();
    }

    @Override
    public void setSelectDialogCity() {   
        model.selectCity();
        citiesDialogView = new SelectCityDialogGUI(this,model);        
        citiesDialogView.setAlwaysOnTop(true);
        citiesDialogView.createWindowsListener();
        citiesDialogView.setVisible(true);
        citiesDialogView.setLocationRelativeTo(null);       
    }

    @Override
    public void setIndexRegion(int index) {
        model.setIndexRegionSelected(index);
    }

    @Override
    public void setIndexCity(int index) {
        model.setIndexCitySelected(index);
        
    }
    
    @Override
    public int getIndexCitySelected(){
        return model.getIndexCitySelected();
    }
    
    @Override
    public void setIndexBrand(int index){
        model.setIndexBrandSelected(index);
        
    }
    
    @Override
    public void setIndexModel(int index){
        model.setIndexModelSelected(index);
        
    }
    
    private void actionBtnSubmit(){
        view.enableBtnStopSubmit();
        view.disableBtnSubmit();
        view.disabledBtnSelectCity();
        view.disabledComboBoxBrands();
        view.disabledComboBoxModels();
        view.disabledTextFieldMinPrice();
        view.disabledTextFieldMaxPrice();
        view.disabledTextFieldMinYear();
        view.disabledTextFieldMaxYear();
    }
    
    private void actionBtnStopSubmit(){
        view.disableBtnStopSubmit();
        view.enableBtnSubmit();
        view.enableBtnSelectCity();
        view.enabledComboBoxBrands();
        view.enabledComboBoxModels();
        view.enabledTextFieldMinPrice();
        view.enabledTextFieldMaxPrice();
        view.enabledTextFieldMinYear();
        view.enabledTextFieldMaxYear();
    }
    
}
