/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.romanrum45.parsers;

/**
 *
 * @author Rum
 */
public enum Selector  {
    
    
    REGIONS("http://auto.drom.ru/cities/"),CITIES("http://auto.drom.ru/cities/"),BRANDS("http://auto.drom.ru/"),MODELS("");
    
    private String url;
    
    Selector(String u){
        url = u;
    } 
    
    public void setUrl(String url){
        this.url = url;
    }
    
    
    public String getUrl(){
        return url;
    }
    
    @Override
    public String toString(){
        switch(this){
            case REGIONS:
                return "div.b-selectCars__item a";
            case CITIES:
                return "noscript";
            case BRANDS:
                return "div.b-selectCars__item a";
            case MODELS:
                return "div.b-selectCars__link a"; 
            default:
                return null;
        }
    }
    
}
