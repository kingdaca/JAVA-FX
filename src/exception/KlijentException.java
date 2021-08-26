/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author David
 */
public class KlijentException extends Exception {
    
    public String message;
    
      public KlijentException() {
    }
      
       public KlijentException(String message) {
        super(message);
         Alert alert;
        alert = new Alert(Alert.AlertType.ERROR,
                 message, ButtonType.OK);
        alert.show();
    
       }
       
    
}



