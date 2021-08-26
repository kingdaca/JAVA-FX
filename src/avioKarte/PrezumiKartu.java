/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avioKarte;

import DB.DB;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author David
 */
public class PrezumiKartu implements Runnable {

    public PrezumiKartu() {

    }

    @Override
    public void run() {
        try {
           
            DB.initDatabase();
            
            // Citanje sa sajta
            
            Document doc
                    = Jsoup.connect("https://www.airserbia.com/sr-RS/specijalne-ponude-ultra-niske-cene-za-letove-iz-nisa").get();
            
            
            Elements elems = doc.select(".promotionInList");

            for (Element e : elems) {
                String odGrada = e.select(".origin.originAndDestination.flexed-centered").text();
                String doGrada = e.select(".destination.originAndDestination.flexed-centered").text();
                String klasa = e.select(".class.flexed-centered").text();
                String cena = e.select(".price-val").text();
                AvioKarte a = new AvioKarte();
                a.setOdGrada(odGrada);
                a.setDoGrada(doGrada);
                a.setKlasa(klasa);
                a.setCena(cena + " " + "RSD");
                DB.insertAvioKarte(a);

            }
            
            Document doc1
                    = Jsoup.connect("https://www.airserbia.com/sr-RS/specijalne-ponude-kazi-da-novom-putovanju").get();
            
            
            Elements elems1 = doc1.select(".promotionInList");

            for (Element e : elems1) {
                String odGrada = e.select(".origin.originAndDestination.flexed-centered").text();
                String doGrada = e.select(".destination.originAndDestination.flexed-centered").text();
                 String klasa = e.select(".class.flexed-centered").text();
                String cena = e.select(".price-val").text();
                AvioKarte a = new AvioKarte();
                a.setOdGrada(odGrada);
                a.setDoGrada(doGrada);
                a.setKlasa(klasa);
                a.setCena(cena + " " + "RSD");
                DB.insertAvioKarte(a);

            }
            

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
