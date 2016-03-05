/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bilgisayar_tamirat_takip_sistemi;

import com.sun.glass.ui.Application;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;


import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author enes_08
 */
public class AnaSayfaController  implements Initializable {
    public AnchorPane anasayfaarka;
    private String personel_ad = "";
        private int yonetici_mi;
        private int personel_id;
    
    public  void ata (int id, String personelad, int yonetici_mi){
        this.personel_ad=personelad;
        this.yonetici_mi=yonetici_mi;
       this.personel_id=id;

 

    }
   
   
@FXML
Text kayanyazitext;
     String a="  Ritzy  Teknik  Servis  ";
    private Object root;

 
       
     public void basla(){
        new java.util.Timer().schedule( 
        new java.util.TimerTask() {
            @Override
            public void run() {
              basla();
               a=a.substring(1)+a.charAt(0);
       kayanyazitext.setText(a);
            }
        }, 
        250                         
);
        
         
      
     }
   public void hakkimizda_button(ActionEvent event) throws IOException{
       System.out.println("girdi hakkımızda");
        
         Parent root = FXMLLoader.load(getClass().getResource("hakkimizdafxml.fxml"));
        
        Scene scene = new Scene(root);
         Stage stage = new Stage();
             stage.setTitle("HAKKIMIZADA");
        stage.setScene(scene);
               stage.show();
    }
  
     public void eski_calısan_sil (ActionEvent event) throws IOException{
         if(yonetici_mi==1){
              System.out.println("girdi laaa");
           Parent root = FXMLLoader.load(getClass().getResource("Calisan_Sil.fxml"));
        
        Scene scene = new Scene(root);
         Stage stage = new Stage();
             stage.setTitle("Calisan Sil");
        stage.setScene(scene);
               stage.show();
         }
         else{
            JOptionPane.showMessageDialog(null, "Yonetici yetkiniz  bulunmamaktadır.", "Bilgi Mesajı", JOptionPane.INFORMATION_MESSAGE);

         }
        

     
    }
     public void yen_calisan_ekle(ActionEvent event) throws IOException{
         if(yonetici_mi==1){
              Parent root = FXMLLoader.load(getClass().getResource("Yeni_Calisan_Ekle.fxml"));
        
        Scene scene = new Scene(root);
         Stage stage = new Stage();
         stage.setTitle("Yeni Calisan Ekle");
        stage.setScene(scene);

        stage.show();
         }
          else{
            JOptionPane.showMessageDialog(null, "Yonetici yetkiniz  bulunmamaktadır.", "Bilgi Mesajı", JOptionPane.INFORMATION_MESSAGE);

         }
        
    }
     public void yeni_sikayet(ActionEvent event) throws IOException{
           Parent root = FXMLLoader.load(getClass().getResource("Yeni_Sikayet.fxml"));
        
        Scene scene = new Scene(root);
         Stage stage = new Stage();
                  stage.setTitle("Yeni Sikayet Ekle");

        stage.setScene(scene);

        stage.show();
        
    }
   public void Yeni_tamirat(ActionEvent event) throws IOException{
           Parent root = FXMLLoader.load(getClass().getResource("Yeni_Tamirat.fxml"));
        
        Scene scene = new Scene(root);
         Stage stage = new Stage();
                           stage.setTitle("Yeni Tamirat Ekle");

        stage.setScene(scene);

        stage.show();
        
    }
     public void Yeni_urun_ekle(ActionEvent event) throws IOException{
           Parent root = FXMLLoader.load(getClass().getResource("Yeni_urun_ekleme.fxml"));
        
        Scene scene = new Scene(root);
         Stage stage = new Stage();
                                    stage.setTitle("Yeni Urun Ekle");

        stage.setScene(scene);

        stage.show();
        
    }
     public void  tamirat_ekle(ActionEvent event) throws IOException{
           Parent root = FXMLLoader.load(getClass().getResource("Tamirat_Ekle.fxml"));
        
        Scene scene = new Scene(root);
         Stage stage = new Stage();
                                             stage.setTitle("Tamirat Ekle");

        stage.setScene(scene);

        stage.show();
        
    }
  
    
public void  teslimat_action(ActionEvent event) throws IOException{
           Parent root = FXMLLoader.load(getClass().getResource("Teslimat.fxml"));
        
        Scene scene = new Scene(root);
         Stage stage = new Stage();
                                                      stage.setTitle("Teslimat");

        stage.setScene(scene);

        stage.show();
        
    }
    public void menu_yenikayit(ActionEvent event) throws IOException{
          FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Yeni_Kayit _Ekle.fxml"));     

Parent root = (Parent)fxmlLoader.load();          
Yeni_Kayit_EkleController controller = fxmlLoader.<Yeni_Kayit_EkleController>getController();
controller.ata(personel_id,personel_ad);
Scene scene = new Scene(root); 
 Stage stage = new Stage();
                                                       stage.setTitle("Yeni Kayit Ekle");

        stage.setScene(scene);
               stage.show();
        
        
        
           
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

      //ImageView background = new ImageView(new javafx.scene.image.Image("file:///d:/images.jpg", 100, 100, false, false));
 // anasayfaarka.getChildren().add(background);
      anasayfaarka.getStylesheets().add("bilgisayar_tamirat_takip_sistemi/tasarim.css");
  anasayfaarka.getStyleClass().add("pane");
        basla();
    }    

    
}
