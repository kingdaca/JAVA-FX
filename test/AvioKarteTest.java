/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import avioKarte.AvioKarte;
import junit.framework.Assert;

/**
 *
 * @author David
 */
public class AvioKarteTest {
    
    public AvioKarteTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    //Test ocekivani usno
    
    @Test
    public void test(){
        String odGrada = "Nis";
        String doGrada = "Beograd";
        String klasa = "Ekonomska";
        String cena = "1513";
        AvioKarte a = new AvioKarte(Integer.SIZE, odGrada, doGrada, klasa, cena,Integer.SIZE);
        
        Assert.assertTrue(a.getOdGrada().equals(odGrada));
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
