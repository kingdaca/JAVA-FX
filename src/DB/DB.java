/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import avioKarte.AvioKarte;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import klijent.Klijent;

/**
 *
 * @author David
 */
public class DB {

    public static DB database = new DB();

    private static String url = "jdbc:mysql://localhost:3306";
    private static String dataBaseName = "projekatcs102";
    private static String username = "root";
    private static String password = "";

    private static Connection conn = null;
    
    //Kreiranje baze

    public static void initDatabase() {
        try {
            conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            stmt.execute("create database if not exists " + dataBaseName + ";");
            stmt.execute("use " + dataBaseName + ";");
            crateTable();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                closeConn();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    //Kreiranje tabele 
    
    public static void crateTable() {
        try {
            openConn();
            Statement stmt = conn.createStatement();
            stmt.execute("drop table if exists avio_karte");
            stmt.execute("create table if not exists avio_karte("
                    + "id int auto_increment primary key,"
                    + "odGrada varchar(200) not null,"
                    + "doGrada varchar(200) not null,"
                    + "klasa varchar(200) not null,"
                    + "cena varchar(200) not null,"
                    + "mesta int default 30"
                    + ");");
            
            

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                closeConn();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void openConn() throws SQLException {
        conn = DriverManager.getConnection(url + "/" + dataBaseName, username, password);
    }

    public static void closeConn() throws SQLException {
        conn.close();
    }
    
    //Insert u tabelu avio karte
    

    public static void insertAvioKarte(AvioKarte a) {
        try {
            openConn();
            PreparedStatement stmt = conn.prepareStatement("insert into avio_karte("
                    + "odGrada,doGrada,klasa,cena) values(?,?,?,?);");
            stmt.setString(1, a.getOdGrada());
            stmt.setString(2, a.getDoGrada());
            stmt.setString(3, a.getKlasa());
            stmt.setString(4, a.getCena());

            stmt.execute(); 
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                closeConn();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    //Citanje podataka iz baze
    
    
    public ObservableList prikazBaze() {
        ObservableList<AvioKarte> avioKarte = FXCollections.observableArrayList();
      
      
        try {
            openConn();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM avio_karte");
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                Integer id = result.getInt("id");
                String odGrada = result.getString("odGrada");
                String doGrada = result.getString("doGrada");
                String klasa = result.getString("klasa");
                String cena = result.getString("cena");
                Integer mesta = result.getInt("mesta");
                

                avioKarte.add(new AvioKarte(id,odGrada, doGrada,klasa, cena, mesta));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                closeConn();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
   
         
        return avioKarte;
    }

    //Kreiranje tabele klijenata
    
    public static void tabelKlijent() {
        try {
            openConn();
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS klijenti("
                    + "id int auto_increment primary key,"
                    + "ime varchar(20) not null,"
                    + "prezime varchar(20) not null,"
                    + "jmbg varchar(13) not null,"
                    + "brojPasosa varchar(9) not null,"
                    + "id_Karte int not null"
                    + ");");

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                closeConn();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    //Upis klijenata u tabelu

    public static void insertKlijent(Klijent k) {
        try {
            openConn();
            PreparedStatement stmt = conn.prepareStatement("insert into klijenti("
                    + "ime,prezime,jmbg,brojPasosa,id_Karte) values(?,?,?,?,?);");
            stmt.setString(1, k.getIme());
            stmt.setString(2, k.getPrezime());
            stmt.setString(3, k.getJmbg());
            stmt.setString(4, k.getBrojPasosa());
            stmt.setInt(5, k.getId_karte());

            stmt.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                closeConn();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}
