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
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
/**
 * FXML Controller class
 *
 * @author enes_08
 */
public class Yeni_Kayit_EkleController extends Baglanti implements Initializable {
    
    public ListView listview;
    public TextField musteri_ad, musteri_soyad, tutar,verildigi_tarih,alinacagi_tarih;
    public ComboBox turler_combobox, sikayetler_combobox;
    private List<String> combobox_tur_id = new ArrayList<String>();
    private List<String> combobox_personel_id = new ArrayList<String>();
    private List<String> combobox_sikayet_id = new ArrayList<String>();
    private List<String> combobox_sikayetliste_id = new ArrayList<String>();
    ObservableList<String> items = FXCollections.observableArrayList();
 int b=0;
    String temp = "";
    Statement st = null;
    ResultSet rs, rs2 = null;
 int bilgisayar_tur_id=0;
  String bilgisayar_id="";
    String musteri_id = "";
    private int personel_id;
    public Label duzenleyen_label;

    private String personel_name = "";
    private Object stage;

    public void ata(int personel_id, String personel_adi) {
        this.personel_id = personel_id;
        this.personel_name = personel_adi;
        duzenleyen_label.setText(personel_adi);

    }

    public void ekle_button(ActionEvent event) throws SQLException {
                    liste_goster();


  
    }

    public void kaydet_button(ActionEvent event) throws SQLException {

        baglan();
        boolean yokmu = false;
        temp = musteri_ad.getText() + musteri_soyad.getText();

        st = con.createStatement();

        rs = st.executeQuery("select  musteri_detaylari  from musteri where  musteri_detaylari='" + temp + "'");

        while (rs.next()) {

            yokmu = true;

        }
        if (!yokmu) {
            System.out.println("girdi la eklemeye");
            String sql = "insert into musteri(musteri_detaylari) values ('" + temp + "')";
            st.execute(sql);
            st = con.createStatement();
            //eklenen customerın id si çekiliyor
            rs2 = st.executeQuery(" SELECT musteri_id FROM musteri ORDER BY musteri_id DESC LIMIT 1");
            while (rs2.next()) {

                System.out.println("musteri id=" + rs2.getString("musteri_id"));
                musteri_id = rs2.getString("musteri_id").toString();
            }
 bilgisayar_tur_id = Integer.parseInt(combobox_tur_id.get(turler_combobox.getSelectionModel().getSelectedIndex()));
            System.out.println("turukodu"+bilgisayar_tur_id); 
            
            System.out.println("verildigi_tarih="+verildigi_tarih.getText());
               System.out.println("fiyat="+tutar.getText());
                System.out.println( "musteri_id"+musteri_id);
                       System.out.println(  "bilgisayar_tur_id"+bilgisayar_tur_id);
                                System.out.println( "personel_id"+personel_id);
                                       System.out.println( "alinacagi_tarih"+ alinacagi_tarih.getText());
            
                         combobox_sikayetliste_id.add(sikayetler_combobox.getSelectionModel().getSelectedItem().toString());

                    
                    
            st = con.createStatement();
             String sql2 ="insert into bilgisayar (fiyat,getirme_tarihi,beklenen_bitis_tarihi,musteri_idm,bilgisayar_turu_kodum,personel_idm) values ('"+tutar.getText()+ "','" + verildigi_tarih.getText() + "','" + alinacagi_tarih.getText()+ "','"+musteri_id+"','"+bilgisayar_tur_id+"','"+ personel_id+"');";
            st.execute(sql2);

       st = con.createStatement();
            //eklenen customerın id si çekiliyor
            rs2 = st.executeQuery(" SELECT bilgisayar_id FROM bilgisayar ORDER BY bilgisayar_id DESC LIMIT 1");
            while (rs2.next()) {

                System.out.println("bilgisayar id=" + rs2.getString("bilgisayar_id"));
                 bilgisayar_id = rs2.getString("bilgisayar_id").toString();
            }
             for (int i = 0; i < listview.getItems().size(); i++)
			{  
               

                            System.out.println("fordayımm");
   b=Integer.parseInt(bilgisayar_id);
  int c=Integer.parseInt(musteri_id);
        
int a=Integer.parseInt(combobox_sikayetliste_id.get(i));
eklee(a,b,c);

			}
            
            JOptionPane.showMessageDialog(null, "musteri ve ürün başarıyla sisteme eklendi.Urun kodunuz= "+b, "Bilgi Mesajı", JOptionPane.INFORMATION_MESSAGE);

        } else {
            JOptionPane.showMessageDialog(null, "musteri_ve urun zaten  sisteme kayıtlı.", "Bilgi Mesajı", JOptionPane.INFORMATION_MESSAGE);

        }
       
          
    }
     public void  eklee(int a,int b,int c){
         baglan();
         

        try {
              st = con.createStatement();
            System.out.println("fordayımm a="+a+"b="+b+"c="+c  );
            String sql3="insert into sikayetler(musteri_id,sikayet_turu_kodu,bilgisayar_id) values ('" + c + "','"+a+"','"+b+"');";
            st.executeUpdate(sql3);
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Yeni_Kayit_EkleController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    public void liste_goster(){
       
         items.add(sikayetler_combobox.getSelectionModel().getSelectedItem().toString());

                listview.setItems(items);
        combobox_sikayetliste_id.add(combobox_sikayet_id.get(sikayetler_combobox.getSelectionModel().getSelectedIndex()));
       // System.out.println("combobox_sikayetliste_id"+combobox_sikayetliste_id.get(5));
               



    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(com.sun.javafx.runtime.VersionInfo.getRuntimeVersion());
        baglan();
        try {
            st = con.createStatement();
            rs2 = st.executeQuery("select * from  bilgisayar_tipleri");

            while (rs2.next()) {
                turler_combobox.getItems().add(rs2.getString("bilgisayar_tipi_tanim"));
                combobox_tur_id.add(rs2.getString("bilgisayar_tipi_kodu"));
            }
                System.out.println("combo_bilgisayartipi_kodu"+combobox_tur_id.get(0));
                System.out.println("combo_bilgisayartipi_kodu"+combobox_tur_id.get(1));
                                System.out.println("combo_bilgisayartipi_kodu"+combobox_tur_id.get(2));


            st = con.createStatement();

            rs2 = st.executeQuery("select * from  sikayet_turleri");
            while (rs2.next()) {
                sikayetler_combobox.getItems().add(rs2.getString("sikayet_turu_aciklama"));
                combobox_sikayet_id.add(rs2.getString("sikayet_turu_kodu"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(Yeni_Kayit_EkleController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        
        
       }
    

}
