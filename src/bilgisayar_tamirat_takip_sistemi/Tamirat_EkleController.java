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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import javafx.scene.control.ListView;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author enes_08
 */
public class Tamirat_EkleController extends  Baglanti implements Initializable {
   public  ComboBox urun_combobox,islem_combobox;
   public  Button tamiratbitir;
    public ListView yapilacaklar_listview,yapilanlar_listview;
     Statement st,st2 = null;
    ResultSet rs = null;
     private List<String> UrunValues = new ArrayList<String>();
    private List<String> IslemValues = new ArrayList<String>();
    private List<String> IslemListValues = new ArrayList<String>();
                          ObservableList<String> items =FXCollections.observableArrayList ();
                                                    ObservableList<String> items2 =FXCollections.observableArrayList ();


 public void eklee(ActionEvent event) throws SQLException{
     
     
        if(urun_combobox.getSelectionModel().getSelectedIndex()!=-1){
    System.out.println("girdi");
    listeata();
}
else{
        System.out.println("girmedi");

}
 }
 
   
 public void islemekle(ActionEvent event) throws SQLException{
     baglan();
            st = con.createStatement();
            int a=Integer.parseInt(UrunValues.get(urun_combobox.getSelectionModel().getSelectedIndex()));
            int b=Integer.parseInt(IslemValues.get(islem_combobox.getSelectionModel().getSelectedIndex()));
                 System.out.println("b="+IslemValues.get(islem_combobox.getSelectionModel().getSelectedIndex()));

           yapilanlar_listview.getItems().clear();
                                     IslemListValues.clear();
                 
                  Statement st =con.createStatement();
                            rs = st.executeQuery( "select  bilgisayar_id,tamir_turleri_kodu  from tamirat where  bilgisayar_id='"+a+"' and tamir_turleri_kodu='"+b+"'");
boolean yokmu=false;
                            
            while ( rs.next() ) {
              
                yokmu=true;    
                System.out.println("buldu la="+rs.getString("tamir_turleri_kodu"));
                     
                 } 
            if(!yokmu){
                   
                 System.out.println("a="+UrunValues.get(urun_combobox.getSelectionModel().getSelectedIndex()));
                                   String sql = "INSERT INTO tamirat(bilgisayar_id, tamir_turleri_kodu,tamir_fiyati) VALUES ('"+a+"','"+b+"', 10)";
                                         st.execute(sql);
                                         
                                     st2 = con.createStatement();
   
            
                                     st = con.createStatement();
                                     yapilanlar_listview.getItems().clear();
                                     IslemListValues.clear();
          
                                 JOptionPane.showMessageDialog(null, "tamirat başarıyla sisteme eklendi.", "Bilgi Mesajı", JOptionPane.INFORMATION_MESSAGE);

            }
            else{
                                                 JOptionPane.showMessageDialog(null, "tamirat zaten  sisteme kayıtlı.", "Bilgi Mesajı", JOptionPane.INFORMATION_MESSAGE);

            }
                 
                 
                 
                 
                 
                 
                 
                 
               
            
        rs = st.executeQuery("SELECT * FROM tamir_turleri,tamirat where tamirat.tamir_turleri_kodu=tamir_turleri.tamir_turu_kodu and tamirat.bilgisayar_id='"+UrunValues.get(urun_combobox.getSelectionModel().getSelectedIndex())+"'");
                while (rs.next())
                {
                     items2.add(rs.getString("tamir_turu_aciklama"));

                yapilanlar_listview.setItems(items2);
                    
                    IslemListValues.add(rs.getString("tamirat_id"));
                }    
                                         
                                         
                                         
                                         
                          }
            
 
   
    public void tamiti_bitir_button(ActionEvent event) throws SQLException
    {
     
        Calendar cal = Calendar.getInstance();
Date today = cal.getTime();
         baglan();
                     int a=Integer.parseInt(UrunValues.get(urun_combobox.getSelectionModel().getSelectedIndex()));
        System.out.println("a="+a);
          Statement st =con.createStatement();
                            rs = st.executeQuery( "select  bilgisayar_id,gercek_bitis_tarihi  from bilgisayar where  bilgisayar_id='"+a+"' ");

     boolean yokmu=false;
                            
            while ( rs.next() ) {
              
                if(rs.getString("gercek_bitis_tarihi")==null)
                  yokmu=true;  
                     
                 } 
            
            if(yokmu==true){
                   
               
         st = con.createStatement();
String sql="UPDATE bilgisayar set gercek_bitis_tarihi='" +today.toString()+ "' where bilgisayar_id='" + UrunValues.get(urun_combobox.getSelectionModel().getSelectedIndex())+"'";
                
st.execute(sql);
          
                                 JOptionPane.showMessageDialog(null, "tamirat başarıyla tamamlandı.", "Bilgi Mesajı", JOptionPane.INFORMATION_MESSAGE);

            }
            else{
                                                 JOptionPane.showMessageDialog(null, "tamirat zaten  tamamlanmış.", "Bilgi Mesajı", JOptionPane.INFORMATION_MESSAGE);

            }
         
         
     
    }
    
    public void geturunler() throws SQLException{
         baglan();
     IslemValues.clear();
               UrunValues.clear();
                islem_combobox.getItems().clear();
                   urun_combobox.getItems().clear();
    st = con.createStatement();
   

        rs = st.executeQuery("SELECT bilgisayar_id,musteri_detaylari,bilgisayar_tipi_tanim FROM bilgisayar,bilgisayar_tipleri,musteri where musteri.musteri_id=bilgisayar.musteri_idm and bilgisayar_tipleri.bilgisayar_tipi_kodu=bilgisayar.bilgisayar_turu_kodum");

                    while (rs.next())
                    {
           urun_combobox.getItems().add(rs.getString("musteri_detaylari")+"-"+rs.getString("bilgisayar_tipi_tanim"));

                        UrunValues.add(rs.getString("bilgisayar_id"));
                    }
                
             
  
      }
    public void listeata() throws SQLException{
         IslemValues.clear();
           yapilacaklar_listview.getItems().clear();
            islem_combobox.getItems().clear();
            yapilanlar_listview.getItems().clear();
                int indexim1=0,indexim2=0;
        baglan();
       st = con.createStatement();
rs =st.executeQuery("SELECT * FROM sikayetler,sikayet_turleri where sikayetler.sikayet_turu_kodu=sikayet_turleri.sikayet_turu_kodu and bilgisayar_id='"+UrunValues.get(urun_combobox.getSelectionModel().getSelectedIndex())+"'");
        //rs =st.executeQuery("SELECT sikayet_turu_aciklama,bilgisayar_id FROM sikayetler,sikayet_turleri where sikayetler.sikayet_turu_kodu=sikayet_turleri.sikayet_turu_kodu and bilgisayar_id='"+UrunValues.get(urun_combobox.getSelectionModel().getSelectedIndex())+"' ");
    while (rs.next())
                    {
                        IslemValues.add(rs.getString("sikayet_turu_kodu"));
                        islem_combobox.getItems().add(rs.getString("sikayet_turu_aciklama"));
                         items.add(rs.getString("sikayet_turu_aciklama"));
                       yapilacaklar_listview.setItems(items); 
                          indexim1++;


                    }
      rs = st.executeQuery("SELECT * FROM tamir_turleri,tamirat where tamirat.tamir_turleri_kodu=tamir_turleri.tamir_turu_kodu and tamirat.bilgisayar_id='"+UrunValues.get(urun_combobox.getSelectionModel().getSelectedIndex())+"'");
                while (rs.next())
                {
                     items2.add(rs.getString("tamir_turu_aciklama"));

                yapilanlar_listview.setItems(items2);
                    
                    IslemListValues.add(rs.getString("tamirat_id"));
                    indexim2++;
                }    
                                         
                                         
                          if(indexim1==indexim2){
                                                   tamiratbitir.setDisable(false);

                              
                          }     
                          else{
                              tamiratbitir.setDisable(true);

                          }
                       

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
           // TODO
           geturunler();
       } catch (SQLException ex) {
           Logger.getLogger(Tamirat_EkleController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }    
    
}
