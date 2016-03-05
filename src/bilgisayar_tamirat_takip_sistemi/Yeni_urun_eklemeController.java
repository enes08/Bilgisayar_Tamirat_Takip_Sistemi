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
public class Yeni_urun_eklemeController extends Baglanti implements Initializable {
  public  TextField urunaciklamasi,ortalamaislemsuresi;
   public ListView listview_urunler;
   
    ObservableList<String> items =FXCollections.observableArrayList ();

Statement st = null;
         ResultSet rs =null;
    /**
     * Initializes the controller class.
     * @param event
     */
  public void urun_ekle_button(ActionEvent event){boolean yokmu=false;
          baglan();
        try {
                  

            st =con.createStatement();
                     rs = st.executeQuery( "select  bilgisayar_tipi_tanim,ortalama_islem_suresi  from bilgisayar_tipleri where  bilgisayar_tipi_tanim='"+urunaciklamasi.getText()+"' and ortalama_islem_suresi='"+ortalamaislemsuresi.getText()+"'" );

       
            while ( rs.next() ) {
                
                yokmu=true;
                
            } 
       
            if(!yokmu){
                 
           String sql="insert into bilgisayar_tipleri (bilgisayar_tipi_tanim,ortalama_islem_suresi)values('"+urunaciklamasi.getText()+"','"+ortalamaislemsuresi.getText()+"')";
       
             st.execute(sql);
        
                                    listview_urunler.getItems().clear();
         comboata();

                                 JOptionPane.showMessageDialog(null, "Ürününüz başarıyla sisteme eklendi.", "Bilgi Mesajı", JOptionPane.INFORMATION_MESSAGE);

            }
            else{
                                                 JOptionPane.showMessageDialog(null, "Ürününüz zaten  sisteme kayıtlı.", "Bilgi Mesajı", JOptionPane.INFORMATION_MESSAGE);

            }
        
         } catch (SQLException ex) {
            Logger.getLogger(Yeni_TamiratController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        

            
    }
    public void comboata(){
        
         baglan();
          
 
    
        try {
            st =con.createStatement();
       
            rs = st.executeQuery( "select bilgisayar_tipi_tanim,ortalama_islem_suresi from bilgisayar_tipleri ");
       
            
            
            while ( rs.next() ) {
                items.add(rs.getString("bilgisayar_tipi_tanim"));

                listview_urunler.setItems(items);
                   
            }
        } catch (SQLException ex) {
            Logger.getLogger(Yeni_TamiratController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }    
   public void urun_sil_button(ActionEvent event){
         baglan();
   try{
          
            PreparedStatement ogrenciSil=con.prepareStatement("delete from bilgisayar_tipleri"
                    + " where bilgisayar_tipi_tanim=? ");
            ogrenciSil.setString(1, listview_urunler.getSelectionModel().getSelectedItem().toString());
            ogrenciSil.executeUpdate();
            ogrenciSil.close();
            listview_urunler.getItems().clear();
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
