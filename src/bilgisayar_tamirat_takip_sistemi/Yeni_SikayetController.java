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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author enes_08
 */
public class Yeni_SikayetController extends Baglanti implements Initializable {
public TextField sikayet_aciklamasi;
public ListView listview;
                  ObservableList<String> items =FXCollections.observableArrayList ();

Statement st = null;
         ResultSet rs =null;
    /**
     * Initializes the controller class.
     */
         
       public  void  sikayetsilbutton(ActionEvent event) {
             try{
            baglan();
            PreparedStatement ogrenciSil=con.prepareStatement("delete from sikayet_turleri"
                    + " where sikayet_turu_aciklama=?");
            ogrenciSil.setString(1, listview.getSelectionModel().getSelectedItem().toString());
            ogrenciSil.executeUpdate();
            ogrenciSil.close();
            listview.getItems().clear();
            comboata();
         JOptionPane.showMessageDialog(null, "Seçilen şikaytiniz sistemden   silindi .", "Bilgi Mesajı", JOptionPane.INFORMATION_MESSAGE);

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            baglantiKes();
        }
       }
    public  void sikayeteklebutton(ActionEvent event) throws SQLException{
        boolean yokmu=false;
                    st =con.createStatement();

         rs = st.executeQuery( "select  sikayet_turu_aciklama  from sikayet_turleri where  sikayet_turu_aciklama='"+sikayet_aciklamasi.getText()+"'");
         
            while ( rs.next() ) {
              
                yokmu=true;    
                     
                 } 
            if(!yokmu){
                 
           String sql="insert into sikayet_turleri (sikayet_turu_aciklama)values('"+sikayet_aciklamasi.getText()+"')";
            st.execute(sql);
                                    listview.getItems().clear();
         comboata();

                                 JOptionPane.showMessageDialog(null, "Şilayetiniz başarıyla sisteme eklendi.", "Bilgi Mesajı", JOptionPane.INFORMATION_MESSAGE);

            }
            else{
                                                 JOptionPane.showMessageDialog(null, "Şilayetiniz zaten  sisteme kayıtlı.", "Bilgi Mesajı", JOptionPane.INFORMATION_MESSAGE);

            }
        
        
        
        
        

            
    }
    public void comboata(){
        
         baglan();
          
 
    try {
        st =con.createStatement();
    } catch (SQLException ex) {
        Logger.getLogger(Yeni_SikayetController.class.getName()).log(Level.SEVERE, null, ex);
    }
            
    try {
        rs = st.executeQuery( "select sikayet_turu_aciklama from sikayet_turleri ");
    } catch (SQLException ex) {
        Logger.getLogger(Yeni_SikayetController.class.getName()).log(Level.SEVERE, null, ex);
    }
            
             

             try {
                 // rs = st.executeQuery("SELECT VERSION()");
      

                 while ( rs.next() ) {
                     System.out.println(rs.getString("sikayet_turu_aciklama"));
                             items.add(rs.getString("sikayet_turu_aciklama"));
                        listview.setItems(items); 
                     
                 }   } catch (SQLException ex) {
                 Logger.getLogger(Calisan_SilController.class.getName()).log(Level.SEVERE, null, ex);
             }
       
        
    }    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         comboata();

          }    
    
}
