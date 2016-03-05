/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bilgisayar_tamirat_takip_sistemi;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author enes_08
 */
public class Baglanti {
 protected static final String SERVER="jdbc:postgresql://localhost:5432/ogrenci";
    protected static final String KULLANICIADI="postgres";
    protected static final String SIFRE="enes_123";
    public Connection con=null;
    
    protected void baglan(){
        try{
Class.forName("org.postgresql.Driver");
           con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bilgisayar_Teknik_Servis","postgres","enes_123");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    protected void baglantiKes(){
        try{
            con.close();//açık olan bağlantıyı kapatır
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }   
}
