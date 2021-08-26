/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import exception.KlijentException;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import klijent.Klijent;

/**
 *
 * @author David
 */
public class KlijentTest {

    public KlijentTest() {
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
    
    //Test ocekivani unos

    @Test
    public void test() throws KlijentException {
        String jmbg = "1234567893216"; //mora imati 13 karaktera
        String ime = "David";
        String prezime = "Mitic";
        String brojPasosa = "321546489"; // mora imati 9 karaktera

        Klijent k = new Klijent(ime,prezime, jmbg, brojPasosa, Integer.SIZE);
        
        Assert.assertTrue(k.getJmbg().equals(jmbg));
        Assert.assertEquals(k.getBrojPasosa(), brojPasosa);
        
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
