/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bilgisayar_tamirat_takip_sistemi;

import java.awt.Desktop.Action;
import java.awt.Dialog;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;



/**
 * FXML Controller class
 *
 * @author enes_08
 */
public class Yeni_Calisan_EkleController extends Baglanti implements Initializable {
   public TextField yeni_calisanisim,yeni_calisan_kullaniciadi;
    public PasswordField yeni_calisan_sifre;
    public CheckBox yonetici_mi;
             ResultSet rs =null;

    
   public void yeni_calisan_ekle_button(ActionEvent event){
      int a;
           if(yonetici_mi.isSelected()){
               a=1;
               System.out.println("girdila yonetici");
           }
              
           else{
                a=0;
            System.out.println("girdila çalisan");
           }
              
        personel personel=new personel(yeni_calisanisim.getText(), yeni_calisan_kullaniciadi.getText(),yeni_calisan_sifre.getText(),a);
          
        
         if (yeni_calisanisim.getText() == "" || yeni_calisan_kullaniciadi.getText()=="" || yeni_calisan_sifre.getText()=="")
System.out.print("selam");
         
         
         else
            {
                
                 try{
                     
            baglan();
           Statement st =con.createStatement();
                            rs = st.executeQuery( "select  personel_detaylari,kullanici_adi  from personel where  personel_detaylari='"+yeni_calisanisim.getText()+"' and kullanici_adi='"+yeni_calisan_kullaniciadi.getText()+"'");
boolean yokmu=false;
                            
            while ( rs.next() ) {
              
                yokmu=true;    
                System.out.println("buldu la="+rs.getString("personel_detaylari"));
                     
                 } 
            if(!yokmu){
                 
           String sql="insert into personel values('"+personel.getPersonel_isim()+"','"+personel.getPersonel_kullaniciadi()+"','"+personel.getPersonel_kullanicisifre().trim()+"','"+a+"')";
            st.execute(sql);
                                 JOptionPane.showMessageDialog(null, "Çalışan başarıyla sisteme eklendi.", "Bilgi Mesajı", JOptionPane.INFORMATION_MESSAGE);

            }
            else{
                                                 JOptionPane.showMessageDialog(null, "Çalışan zaten  sisteme kayıtlı.", "Bilgi Mesajı", JOptionPane.INFORMATION_MESSAGE);

            }
           
          
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            baglantiKes();
        }
                

            }
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
