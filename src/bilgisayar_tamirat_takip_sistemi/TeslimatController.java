/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bilgisayar_tamirat_takip_sistemi;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.sound.midi.Sequence;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author enes_08
 */
public class TeslimatController extends Baglanti implements Initializable {
public TextField urun_kodu;
public ListView list_view;
    private List<String> listem = new ArrayList<String>();
 Statement st = null;
    ResultSet rs = null;
    private Object items;
   public void teslimati_tamamla_button(ActionEvent event) throws SQLException{
        
            if (listem.get(5) ==null )
            {
                if (listem.get(4) !=null)
                {
                    Calendar cal = Calendar.getInstance();
Date today = cal.getTime();
System.out.println(today);
                    
                     baglan();
           Statement st =con.createStatement();
           String sql="update  bilgisayar set teslim_tarihi='"+today.toString()+"' where bilgisayar_id='"+listem.get(0)+"'";
            st.execute(sql);
                   
                   
                 JOptionPane.showMessageDialog(null, "Ürün Teslimi Tamamlandı", "Bilgi Mesajı", JOptionPane.INFORMATION_MESSAGE);
list_view.getItems().clear();

                }
                else
                                        JOptionPane.showMessageDialog(null, "Ürün Henüz Hazır Değil.", "Bilgi Mesajı", JOptionPane.INFORMATION_MESSAGE);

            }
            else{
                                           JOptionPane.showMessageDialog(null, "Ürün Daha Önce Teslim Edilmiş Görünüyor.", "Bilgi Mesajı", JOptionPane.INFORMATION_MESSAGE);
 
                    }
listem.clear();
       
    
}

public void urun_getir_button(ActionEvent event) throws SQLException{
    baglan();
    
    st = con.createStatement();

        rs = st.executeQuery("select bilgisayar_id,bilgisayar_tipi_tanim,fiyat,getirme_tarihi,beklenen_bitis_tarihi,gercek_bitis_tarihi,teslim_tarihi,musteri_detaylari from bilgisayar, musteri,bilgisayar_tipleri where bilgisayar_id='"+urun_kodu.getText()+"' and bilgisayar.musteri_idm=musteri.musteri_id and bilgisayar.bilgisayar_turu_kodum=bilgisayar_tipleri.bilgisayar_tipi_kodu");
    while (rs.next()) {

        System.out.println("==="+rs.getString("bilgisayar_id")); 
                System.out.println("==="+rs.getString("fiyat"));      
                        System.out.println("==="+rs.getString("beklenen_bitis_tarihi"));  
                            ObservableList<String> items = FXCollections.observableArrayList();

 items.add("Urun no: "+rs.getString("bilgisayar_id")+"        Musteri Bilgileri: "+rs.getString("musteri_detaylari")+"        Urun Turu: "+rs.getString("bilgisayar_tipi_tanim")+"        Gerirme Tarihi: "+rs.getString("getirme_tarihi").trim()+"        Beklenen Bitis Tarihi: "+rs.getString("beklenen_bitis_tarihi").trim()+"        Bitis Tarihi: "+rs.getString("gercek_bitis_tarihi")+"        Fiyat: "+rs.getString("fiyat").trim());
listem.add(rs.getString("bilgisayar_id"));

 listem.add(rs.getString("bilgisayar_tipi_tanim"));
listem.add(rs.getString("fiyat"));
listem.add(rs.getString("getirme_tarihi"));
listem.add(rs.getString("gercek_bitis_tarihi"));
listem.add(rs.getString("teslim_tarihi"));
listem.add(rs.getString("musteri_detaylari"));

        System.out.println("gercek_bitis_tarihi"+listem.get(5));
                System.out.println("gercek_bitis_tarihi"+listem.get(4));
                                System.out.println("gercek_bitis_tarihi"+listem.get(3));



                list_view.setItems(items);

 




        }
 
}
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
}
}