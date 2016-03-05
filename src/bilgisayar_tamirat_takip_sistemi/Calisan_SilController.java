/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bilgisayar_tamirat_takip_sistemi;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author enes_08
 */
public class Calisan_SilController extends Baglanti implements Initializable {
         Statement stmt = null;
         ResultSet rs =null;
   
    public ComboBox calisanisimlercomboBox;
    /**
     * Initializes the controller class.
     * 
     */
    
    public void silbuttonaction(ActionEvent event){

      
  
            try{
            baglan();
            PreparedStatement ogrenciSil=con.prepareStatement("delete from personel"
                    + " where personel_detaylari=?");
            ogrenciSil.setString(1, calisanisimlercomboBox.getSelectionModel().getSelectedItem().toString());
            ogrenciSil.executeUpdate();
            ogrenciSil.close();
            calisanisimlercomboBox.getItems().clear();
            comboata();
                                             JOptionPane.showMessageDialog(null, "personel başarıyla sistemden silinmiştir.", "Bilgi Mesajı", JOptionPane.INFORMATION_MESSAGE);

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            baglantiKes();
        }
    }
        
    public void comboata(){
        
         baglan();
             try {
                 stmt = con.createStatement();
             } catch (SQLException ex) {
                 Logger.getLogger(Calisan_SilController.class.getName()).log(Level.SEVERE, null, ex);
             }
 
          
             try {
                 rs = stmt.executeQuery( "select personel_detaylari from personel ");
             } catch (SQLException ex) {
                 Logger.getLogger(Calisan_SilController.class.getName()).log(Level.SEVERE, null, ex);
             }

             try {
                 // rs = st.executeQuery("SELECT VERSION()");
                 while ( rs.next() ) {
                     System.out.println(rs.getString("personel_detaylari"));
                     calisanisimlercomboBox.getItems().add(rs.getString("personel_detaylari"));
                     
                 }   } catch (SQLException ex) {
                 Logger.getLogger(Calisan_SilController.class.getName()).log(Level.SEVERE, null, ex);
             }
       
        
    }    

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            comboata();
  
    
}
}
