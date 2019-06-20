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
public class ParserModels implements Runnable {
    
    ArrayList<String> modelsNameList;
    ArrayList<String> urlModelsList;
    AdvertOfCarsModelInterface model;  
    Thread t;    
    String url;
    String selector;
    
    public ParserModels(AdvertOfCarsModelInterface model,String url,String selector){        
        this.model = model;
        this.url = url;
        this.selector = selector;
        t = new Thread(this,"ParserModels");
        t.start();
    }
    
    public ParserModels(AdvertOfCarsModelInterface model){        
        this.model = model;
        this.url = Selector.MODELS.getUrl();
        this.selector = Selector.MODELS.toString();
        t = new Thread(this,"ParserModels");
        t.start();
    }
    
    
    private void parse(){        
        Document doc;     
        modelsNameList = new ArrayList<>();
        urlModelsList = new ArrayList<>();        
        try{                 
            doc = Jsoup.connect(url).userAgent("Mozilla").get();
            Elements brands = doc.select(selector);
            modelsNameList.add("Любая модель");
            urlModelsList.add(Selector.BRANDS.getUrl());
            for(Element brand : brands){
                urlModelsList.add(brand.attr("href"));
                modelsNameList.add(brand.text());                
            }
            
            setData();
            
        }catch(IOException e){
            System.out.println(e);
        }
    }
    
    
    
    private void setData() {
        String [] arrayBrands = new String[modelsNameList.size()];
        arrayBrands = modelsNameList.toArray(arrayBrands);
        model.setArrayModels(arrayBrands);
        model.setUrlModelList(urlModelsList);
    }

    @Override
    public void run() {
        parse();
    }
    
    
}
