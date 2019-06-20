/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.romanrum45.parsers;

import com.romanrum45.model.AdvertOfCarsModelInterface;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Rum
 */
public class ParserCities implements  Runnable {
    
    
    AdvertOfCarsModelInterface model;  
    Thread t;    
    String url;
    String selector;
    ArrayList<String> citiesList;
    ArrayList<String> urlCitiesList;
    
    public ParserCities(AdvertOfCarsModelInterface model,String url,String selector){        
        this.model = model;
        this.url = url;
        this.selector = selector;
        t = new Thread(this,"ParserCities");
        t.start();
    }
    
    public ParserCities(AdvertOfCarsModelInterface model){        
        this.model = model;
        this.url = Selector.CITIES.getUrl();
        this.selector = Selector.CITIES.toString();
        t = new Thread(this,"ParserCities");
        t.start();
    }
    
    private void parse(){        
        Document doc;    
        citiesList = new ArrayList<>();
        urlCitiesList = new ArrayList<>();
        int selectedIndex = model.getSelectedIndexRegion();        
        try{                 
            doc = Jsoup.connect(url).userAgent("Mozilla").get();
            Elements regions = doc.select(selector);            
            Element cities = regions.get(selectedIndex);
            regions = cities.children();
            for(Element city : regions){
                urlCitiesList.add(city.attr("href"));                
                citiesList.add(city.text());                
            }
                        
            setData();
            
        }catch(IOException e){
            System.out.println(e);
        }
    }
    
    
    
    private void setData() {        
        String [] arrCities = new String[citiesList.size()];
        arrCities = citiesList.toArray(arrCities);        
        model.setArrayCities(arrCities);
        model.setUrlCityList(urlCitiesList);
    }

    @Override
    public void run() {
        parse();
    }
}
    

