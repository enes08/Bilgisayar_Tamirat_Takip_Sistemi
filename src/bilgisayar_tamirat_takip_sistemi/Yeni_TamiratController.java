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
public class Yeni_TamiratController extends  Baglanti implements Initializable {
    public ListView listview;
    public TextField  tamirataciklamasi,standartucret; 
      ObservableList<String> items =FXCollections.observableArrayList ();

Statement st = null;
         ResultSet rs =null;

    /**
     * Initializes the controller class.
     */
    public void Yeni_Tamirat_Tipi_Ekle(ActionEvent event){
     boolean yokmu=false;
        
        try {
                    baglan();

            st =con.createStatement();
                     rs = st.executeQuery( "select  tamir_turu_aciklama,standart_fiyat  from tamir_turleri where  sikayet_turu_aciklama='"+tamirataciklamasi.getText()+"' and standart_fiyat='"+standartucret.getText()+"'" );

       
            while ( rs.next() ) {
                
                yokmu=true;
                
            } 
       
            if(!yokmu){
                 
           String sql="insert into tamir_turleri (tamir_turu_aciklama,standart_fiyat)values('"+tamirataciklamasi.getText()+"','"+standartucret.getText()+"')";
       
             st.execute(sql);
        
                                    listview.getItems().clear();
         comboata();

                                 JOptionPane.showMessageDialog(null, "Şilayetiniz başarıyla sisteme eklendi.", "Bilgi Mesajı", JOptionPane.INFORMATION_MESSAGE);

            }
            else{
                                                 JOptionPane.showMessageDialog(null, "Şilayetiniz zaten  sisteme kayıtlı.", "Bilgi Mesajı", JOptionPane.INFORMATION_MESSAGE);

            }
        
         } catch (SQLException ex) {
            Logger.getLogger(Yeni_TamiratController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        

            
    }
    public void comboata(){
        
         baglan();
          
 
    
        try {
            st =con.createStatement();
       
            rs = st.executeQuery( "select tamir_turu_aciklama,standart_fiyat from tamir_turleri ");
       
            
            
            while ( rs.next() ) {
                items.add(rs.getString("tamir_turu_aciklama"));

                listview.setItems(items);
                   
            }
        } catch (SQLException ex) {
            Logger.getLogger(Yeni_TamiratController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }    
     public void Yeni_Tamirat_Tipi_Sil(ActionEvent event){
           try{
            baglan();
            PreparedStatement ogrenciSil=con.prepareStatement("delete from tamir_turleri"
                    + " where tamir_turu_aciklama=? ");
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
comboata();
    }    
    
}
