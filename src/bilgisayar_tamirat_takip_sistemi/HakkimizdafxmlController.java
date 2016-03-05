/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bilgisayar_tamirat_takip_sistemi;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author enes_08
 */
public class HakkimizdafxmlController implements Initializable {
   public ImageView logoImageview,alt_ResimImageview,hakkimizdaPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          Image image = new Image("bilgisayar_tamirat_takip_sistemi/logomuz.jpg");  
                    Image image2 = new Image("bilgisayar_tamirat_takip_sistemi/hakkımızda.jpg");  
                    Image image3 = new Image("bilgisayar_tamirat_takip_sistemi/teknik_servis1.jpg");  

            logoImageview.setImage(image);
                        alt_ResimImageview.setImage(image3);
                        hakkimizdaPane.setImage(image2);

        // TODO
    }    
    
}
