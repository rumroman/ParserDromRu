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
public class ParserRegions implements Runnable {

    ArrayList<String> regionsList;
    AdvertOfCarsModelInterface model;
    Thread t;
    String url;
    String selector;

    public ParserRegions(AdvertOfCarsModelInterface model, String url, String selector) {
        this.model = model;
        this.url = url;
        this.selector = selector;
        t = new Thread(this, "ParserRegions");
        t.start();
    }

    public ParserRegions(AdvertOfCarsModelInterface model) {
        this.model = model;
        this.url = Selector.REGIONS.getUrl();
        this.selector = Selector.REGIONS.toString();
        t = new Thread(this, "ParserRegions");
        t.start();
    }

    private void parse() {
        Document doc;
        regionsList = new ArrayList<>();        
        try {
            doc = Jsoup.connect(url).userAgent("Mozilla").get();
            Elements regions = doc.select(selector);
            for (Element region : regions) {
                regionsList.add(region.text());
            }
            setData();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void setData() {
        String[] regions = new String[regionsList.size()];
        regions = regionsList.toArray(regions);
        model.setArrayRegions(regions);
    }

    @Override
    public void run() {
        parse();
    }
}
