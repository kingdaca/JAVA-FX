/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avioKarte;

/**
 *
 * @author David
 */
public class AvioKarte {
    
  private Integer id;
   private String odGrada;
   private String doGrada;
   private String klasa;
   private String cena;
   private int mesta;

    public AvioKarte() {
    }

    public AvioKarte(Integer id, String odGrada, String doGrada, String klasa, String cena, int mesta) {
        this.id = id;
        this.odGrada = odGrada;
        this.doGrada = doGrada;
        this.klasa = klasa;
        this.cena = cena;
        this.mesta = mesta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOdGrada() {
        return odGrada;
    }

    public void setOdGrada(String odGrada) {
        this.odGrada = odGrada;
    }

    public String getDoGrada() {
        return doGrada;
    }

    public void setDoGrada(String doGrada) {
        this.doGrada = doGrada;
    }

    public String getKlasa() {
        return klasa;
    }

    public void setKlasa(String klasa) {
        this.klasa = klasa;
    }

    public String getCena() {
        return cena;
    }

    public void setCena(String cena) {
        this.cena = cena;
    }

    public int getMesta() {
        return mesta;
    }

    public void setMesta(int mesta) {
        this.mesta = mesta;
    }

    @Override
    public String toString() {
        return "AvioKarte{" + "id=" + id + ", odGrada=" + odGrada + ", doGrada=" + doGrada + ", klasa=" + klasa + ", cena=" + cena + ", mesta=" + mesta + '}';
    }

    
   
    
    }

   
   

  
    
    

  

