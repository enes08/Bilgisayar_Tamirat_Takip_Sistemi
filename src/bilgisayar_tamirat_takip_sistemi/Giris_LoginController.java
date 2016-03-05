/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bilgisayar_tamirat_takip_sistemi;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author enes_08
 */
public class Giris_LoginController extends Baglanti implements Initializable {
    @FXML
    TextField textogrkullaniciadi;
    @FXML
          AnchorPane  panelim;
    @FXML

    PasswordField textogrkullanicisifre;
     public  ImageView loginresim,ust_resim;
       String  name="";
     private int id ,a=0;
    private String   password="";
    private Object event;
    private Object stage;
    private Object stage2;
     public void handleButtonAction1(ActionEvent event) throws IOException, SQLException{
       girisyap(1);
        
    }
    
   
    
    public void  handleButtonAction2(ActionEvent event) throws SQLException, IOException{
        
        girisyap(0);
        
    }

    private void girisyap(int i) throws SQLException, IOException {
        baglan();
         Statement stmt = null;
         ResultSet rs =null;
          stmt = con.createStatement();

       if(i==1){
  rs = stmt.executeQuery( "select personel_idm, personel_detaylari, kullanici_sifre from personel where yonetici_mi='1' and kullanici_adi='"+textogrkullaniciadi.getText()+"'");
      a=i;
       }
       else{
          
  rs = stmt.executeQuery( "select personel_idm, personel_detaylari, kullanici_sifre from personel where yonetici_mi='0' and kullanici_adi='"+textogrkullaniciadi.getText()+"'");
  a=i;
            // rs = st.executeQuery("SELECT VERSION()");
       }
        while ( rs.next() ) {
            id = rs.getInt("personel_idm");
                   name = rs.getString("personel_detaylari").trim();
                 password =  rs.getString("kullanici_sifre");
            
       
            System.out.println( "şifre = " + password );
            System.out.println();
         }
        
   if (textogrkullanicisifre.getText().toString().equals(password.trim())){
       
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AnaSayfa.fxml"));     

Parent root = (Parent)fxmlLoader.load();          
AnaSayfaController controller = fxmlLoader.<AnaSayfaController>getController();
controller.ata(id,name,a);
    ((Stage)panelim.getScene().getWindow()).close();

Scene scene = new Scene(root); 
 Stage stage = new Stage();
 stage.setTitle("Teknik Servis Takip Otomasyon Sistemi");
        stage.setScene(scene);
               stage.show();

       
            }
            else{
                   JOptionPane.showMessageDialog(null, "kullanıcı bilgileriniz hatalı.", "Bilgi Mesajı", JOptionPane.WARNING_MESSAGE);

                 textogrkullanicisifre.requestFocus(); // get focus first
            textogrkullanicisifre.positionCaret(0);
            textogrkullanicisifre.selectNextWord();
            }
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
    
   
    
    /**Calisan_girisi_button
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
      //loginresim = new ImageView(new javafx.scene.image.Image("file:///d:/images.jpg", 100, 100, false, false));
            Image image = new Image("bilgisayar_tamirat_takip_sistemi/Login_1.png");  
                        Image image2 = new Image("bilgisayar_tamirat_takip_sistemi/teknik_servis1.jpg");  

            loginresim.setImage(image);
                        ust_resim.setImage(image2);

        }    
    
}
