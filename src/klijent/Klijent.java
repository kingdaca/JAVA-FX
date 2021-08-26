/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klijent;

import exception.KlijentException;

/**
 *
 * @author David
 */
public class Klijent {

    private String ime;
    private String prezime;
    private String jmbg;
    private String brojPasosa;
    private int id_karte;

    public Klijent() {
    }

    public Klijent(String ime, String prezime, String jmbg, String brojPasosa, int id_karte) throws KlijentException, NumberFormatException {
        setIme(ime);
        setPrezime(prezime);
        setJmbg(jmbg);
        setBrojPasosa(brojPasosa);
        this.id_karte = id_karte;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) throws KlijentException {
        if (ime.length() <= 20) {
            this.ime = ime;
        } else {
            throw new KlijentException("Ime mora imate manje od 20 karaktera");
        }

    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) throws KlijentException {
        if (prezime.length() <= 20) {
            this.prezime = prezime;
        } else {
            throw new KlijentException("Prezime mora imati manje od 20 karaktera");
        }

    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) throws KlijentException {
        if (jmbg.length() == 13) {
            this.jmbg = jmbg;
        } else {
            throw new KlijentException("Jmbg mora imati 13 karaktera");
        }

    }

    public String getBrojPasosa() {
        return brojPasosa;
    }

    public void setBrojPasosa(String brojPasosa) throws KlijentException {
        if (brojPasosa.length() == 9) {
            this.brojPasosa = brojPasosa;
        } else {
            throw new KlijentException("Broj pasosa mora imati tacno 9 broja");
        }

    }

    public int getId_karte() {
        return id_karte;
    }

    public void setId_karte(int id_karte) {
        this.id_karte = id_karte;
    }

    @Override
    public String toString() {
        return "Klijent{" + "ime=" + ime + ", prezime=" + prezime + ", jmbg=" + jmbg + ", brojPasosa=" + brojPasosa + ", id_karte=" + id_karte + '}';
    }

}
