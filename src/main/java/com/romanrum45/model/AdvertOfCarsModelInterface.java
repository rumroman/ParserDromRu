/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.romanrum45.model;

import com.romanrum45.observers.AdvertsObserver;
import com.romanrum45.observers.LocationObserver;
import java.util.ArrayList;

/**
 *
 * @author Rum
 */
public interface AdvertOfCarsModelInterface {
    
    
    //  Методы используемые контроллером на основании действия пользователя
    void startSearch(String urlOtherParam);
    void stopSearch();
    void selectCity();
    void setIndexRegionSelected(int index);
    void setIndexCitySelected(int index);
    int getIndexCitySelected();
    void setIndexBrandSelected(int index);
    void setIndexModelSelected(int index);
    
    //  Методы используемые парсерами
    void reviewAutoList(ArrayList<String> list);     
    void setArrayRegions(String[] regions);
    void setArrayCities(String[] cities);
    void setArrayBrands(String[] brands);
    void setArrayModels(String[] models);
    void setUrlCityList(ArrayList<String> urlCityList); 
    void setUrlBrandList(ArrayList<String> urlBrandList);
    void setUrlModelList(ArrayList<String> urlModelList);        
    boolean getStatusSearch();    
    String getUrlRequest();       
    int getSelectedIndexRegion();
    
    
    //  Методы, при помощи которых представление получает информацию и изменяют свой статус наблюдателя
    String[] getArrayRegions();
    String[] getArrayCities();
    String getCitySelected();
    String[] getArrayBrands();
    String[] getArrayModels();    
    String[] getArrayNewAuto();
    
    
    
    // Регистрация и удаление слушателей
    void registerObserver(LocationObserver o);
    void removeObserver(LocationObserver o);   
    void registerObserver(AdvertsObserver o);
    void removeObserver(AdvertsObserver o);
    
}
