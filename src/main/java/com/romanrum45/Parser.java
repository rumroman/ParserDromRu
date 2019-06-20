/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.romanrum45;

import java.io.IOException;
import java.util.ArrayList;

import com.romanrum45.model.AdvertOfCarsModelInterface;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author Rum
 */
public class Parser implements Runnable {    
    
    private final String url;
    private final Thread thread;
    private AdvertOfCarsModelInterface model;
    private Document doc;
    
    
    public Parser(AdvertOfCarsModelInterface model){        
        
        this.model = model;
        this.url = model.getUrlRequest();
        thread = new Thread(this,"ParserMain");
        thread.start(); 
    } 
    

    @Override
    public void run() {
        ArrayList<String> linkList = new ArrayList<>();  
        String str;        
        try{            
            doc = Jsoup.connect(url).userAgent("Mozilla").get();
            Elements links = doc.select("a.b-advItem");
            Elements price = doc.select("div.b-advItem__price_mobile");
            Elements title = doc.select("div.b-advItem__header div.b-advItem__title");
            String strConc;            
            for(int i = 0; i < links.size();i++){               
                str = links.get(i).attr("href");
                strConc = title.get(i).text() + " " + price.get(i).text() + " " + str;
                linkList.add(strConc);
            }           
            
            
            if(model.getStatusSearch()){                
                model.reviewAutoList(linkList);
            }
            
        }catch(IOException e){
            System.out.println(e);
        }
    }        
}
