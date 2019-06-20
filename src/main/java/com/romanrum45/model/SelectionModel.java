/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.romanrum45.model;

import com.romanrum45.Parser;
import com.romanrum45.Pause;
import com.romanrum45.observers.AdvertsObserver;
import com.romanrum45.observers.LocationObserver;
import com.romanrum45.parsers.ParserBrands;
import com.romanrum45.parsers.ParserCities;
import com.romanrum45.parsers.ParserModels;
import com.romanrum45.parsers.ParserRegions;
import com.romanrum45.parsers.Selector;
import java.awt.Toolkit;
import java.util.ArrayList;

/**
 *
 * @author Rum
 */
public class SelectionModel implements AdvertOfCarsModelInterface {
    
    private ArrayList<String> carList;
    private ArrayList<String> newCars = new ArrayList<>();
    private ArrayList<String> urlCityList = new ArrayList<>();
    private ArrayList<String> urlBrandList = new ArrayList<>();
    private ArrayList<String> urlModelList = new ArrayList<>();
    private ArrayList<AdvertsObserver> carsListObservers = new ArrayList<>();
    private ArrayList<LocationObserver> locationListObservers = new ArrayList<>();    
    private String[] arrayRegions, arrayCities;
    private String[] arrayBrands,arrayModels;   
    private int indexRegionSelected;
    private int indexCitySelected;
    private int indexBrandSelected;
    private int indexModelSelected;
    private int updateTime;
    private String citySelected;
    private String urlRequestLink;
    private String urlSubmit;
    private boolean statusSearch;
    
    
    public SelectionModel(){
        carList = null;      
        updateTime = 60000;     // Один раз в минуту парсит новые данные
        indexCitySelected = -1;
        arrayBrands = new String[] {"Любая фирма"};
        arrayModels = new String[] {"Любая модель"}; 
        setUrlRequestLink("http://auto.drom.ru/");
        statusSearch = false;                
    }
    
    @Override
    public void startSearch(String urlOtherParam){
        setStatusSearch(true);
        urlSubmit = urlRequestLink + urlOtherParam;    
        System.out.println(urlSubmit);
        new Parser(this);
    }
    
    @Override
    public void stopSearch(){
        setStatusSearch(false);
    }    

    @Override
    public void reviewAutoList(ArrayList<String> list) {
        
        if(carList == null){
            carList = list;
        }else{
            if(!carList.equals(list)){                
                changeAutoList(list);
                Toolkit.getDefaultToolkit ().beep();
            }
        }        
        new Pause(this,updateTime);
    }
   
    
    public void changeAutoList(ArrayList<String> list) {
        
        for(int i = 0; i < carList.size(); i++){
            if(!carList.contains(list.get(i)))
                newCars.add(list.get(i));
        }        
        carList = list;
        notifyAutoListObservers();
    }        
    
    public void setUrlRequestLink(String urlRequestLink){
        this.urlRequestLink = urlRequestLink;
    }  
        
    public void setStatusSearch(boolean status){
        statusSearch = status;
        if(!status)
            carList = null;
    }
    
    @Override
    public void selectCity(){        
        new ParserRegions(this);
    }
    
    @Override
    public void setIndexRegionSelected(int index){
        indexRegionSelected = index;        
        new ParserCities(this);
    }
        
    @Override
    public void setIndexCitySelected(int index){
        indexCitySelected = index;   
        String urlCitySelected = urlCityList.get(indexCitySelected);        
        citySelected = arrayCities[index];        
        notifyCitySelectedObservers();
        Selector.BRANDS.setUrl(urlCitySelected);
        Selector.MODELS.setUrl(urlCitySelected);
        notifyBrandListObservers();
        notifyModelListObservers();
        setUrlRequestLink(urlCitySelected);
        new ParserBrands(this);
    }   
    
    @Override
    public int getIndexCitySelected(){
        return indexCitySelected;
    }
    
    @Override
    public void setIndexBrandSelected(int index){
        indexBrandSelected = index;
        String urlBrandSelected = urlBrandList.get(indexBrandSelected);        
        Selector.MODELS.setUrl(urlBrandSelected);
        setUrlRequestLink(urlBrandSelected);
        new ParserModels(this);
    }
    
    @Override
    public void setIndexModelSelected(int index){
        indexModelSelected = index;
        String urlModelSelected = urlModelList.get(indexModelSelected);
        setUrlRequestLink(urlModelSelected);        
    }    
    
    @Override
    public void setUrlCityList(ArrayList<String> urlCityList){        
        this.urlCityList = urlCityList;
    }     
    @Override
    public void setUrlBrandList(ArrayList<String> urlBrandList){
        this.urlBrandList = urlBrandList;        
    }
    @Override
    public void setUrlModelList(ArrayList<String> urlModelList){
        this.urlModelList = urlModelList;        
    }
    
    @Override
    public void setArrayRegions(String[] regions) {
        arrayRegions = regions;
        notifyRegionListObservers();
    }
   
    @Override
    public void setArrayCities(String[] cities) {
        arrayCities = cities;
        notifyCityListObservers();
    }     

    @Override
    public void setArrayBrands(String[] brands) {
        arrayBrands = brands;
        notifyBrandListObservers();
    }   

    @Override
    public void setArrayModels(String[] models) {
        arrayModels = models;
        notifyModelListObservers();
    }
    
    @Override
    public String getUrlRequest(){
        return urlSubmit;
    }
    
    @Override
    public boolean getStatusSearch(){
        return statusSearch;
    }

    @Override
    public String[] getArrayRegions() {
        return arrayRegions;
    }
    
    @Override
    public int getSelectedIndexRegion(){
        return indexRegionSelected;
    }

    @Override
    public String[] getArrayCities() {
        return arrayCities;
    }
    
    @Override
    public String getCitySelected(){
        return citySelected;
    }

    @Override
    public String[] getArrayBrands() {
        return arrayBrands;
    }

    @Override
    public String[] getArrayModels() {
        return arrayModels;
    }
    
    @Override
    public String[] getArrayNewAuto(){
        String [] arrayAuto = new String[newCars.size()];
        arrayAuto = newCars.toArray(arrayAuto);        
        return arrayAuto;
    } 
    
    private void notifyAutoListObservers(){
        for(AdvertsObserver autoObserver : carsListObservers){
            autoObserver.updateCars();
        }
    }
    
    private void notifyRegionListObservers(){
        for(LocationObserver locationObserver : locationListObservers){
            locationObserver.updateRegions();
        }
    }
    private void notifyCityListObservers(){
        for(LocationObserver locationObserver : locationListObservers){
            locationObserver.updateCities();
        }
    }
    
    private void notifyBrandListObservers(){        
        for(AdvertsObserver autoObserver : carsListObservers){
            autoObserver.updateBrands();
        }
    }
    
    private void notifyModelListObservers(){
        for(AdvertsObserver autoObserver : carsListObservers){
            autoObserver.updateModels();
        }
    }
    
    private void notifyCitySelectedObservers(){
        for(AdvertsObserver autoObserver : carsListObservers){
            autoObserver.updateCitySelected();
        }
    }    
    
    @Override
    public void registerObserver(LocationObserver o){
        locationListObservers.add(o);
    } 

    @Override
    public void registerObserver(AdvertsObserver o) {
        carsListObservers.add(o);
    }
    
    @Override
    public void removeObserver(LocationObserver o){
        if(!locationListObservers.isEmpty())
            locationListObservers.remove(o);
    }

    @Override
    public void removeObserver(AdvertsObserver o) {
        if(!carsListObservers.isEmpty())
            carsListObservers.remove(o);
    }
    
    public void setUpdateTime(int time){
        updateTime = time;
    }
    public int getUpdateTime(){
        return updateTime;
    }
}
