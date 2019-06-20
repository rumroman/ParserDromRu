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
public class ParserBrands implements Runnable {
    
    ArrayList<String> brandsNameList;
    ArrayList<String> urlBrandsList;
    AdvertOfCarsModelInterface model;  
    Thread t;    
    String url;
    String selector;
    
    public ParserBrands(AdvertOfCarsModelInterface model,String url,String selector){        
        this.model = model;
        this.url = url;
        this.selector = selector;
        t = new Thread(this,"ParserBrands");
        t.start();
    }
    
    public ParserBrands(AdvertOfCarsModelInterface model){        
        this.model = model;
        this.url = Selector.BRANDS.getUrl();
        this.selector = Selector.BRANDS.toString();
        t = new Thread(this,"ParserBrands");
        t.start();
    }    
    
    private void parse(){        
        Document doc;     
        brandsNameList = new ArrayList<>();
        urlBrandsList = new ArrayList<>();        
        try{                 
            doc = Jsoup.connect(url).userAgent("Mozilla").get();
            Elements brands = doc.select(selector);
            brandsNameList.add("Любая фирма");
            urlBrandsList.add(Selector.CITIES.getUrl());
            for(Element brand : brands){  
                urlBrandsList.add(brand.attr("href"));
                brandsNameList.add(brand.text());                
            }            
            setData();
            
        }catch(IOException e){
            System.out.println(e);
        }
    }   
    
    private void setData() {
        String [] arrayBrands = new String[brandsNameList.size()];
        arrayBrands = brandsNameList.toArray(arrayBrands);
        model.setArrayBrands(arrayBrands);
        model.setUrlBrandList(urlBrandsList);
    }

    @Override
    public void run() {
        parse();
    }
    
}
