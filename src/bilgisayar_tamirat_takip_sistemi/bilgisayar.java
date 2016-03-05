/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bilgisayar_tamirat_takip_sistemi;

/**
 *
 * @author enes_08
 */
public class bilgisayar {
   String bilgisayar_id;
   String bilgisayar_tipi_tanim;
         String  fiyat;
         String  getirme_tarihi;
               String    beklenen_bitis_tarihi;
                  String gercek_bitis_tarihi;
                        String   musteri_detaylari;
                     public   bilgisayar(String bilgisayar_id,String bilgisayar_tipi_tanim,String fiyat,String getirme_tarihi,String beklenen_bitis_tarihi,String gercek_bitis_tarihi,String musteri_detaylari){
                         this.bilgisayar_id=bilgisayar_id;
                          this.bilgisayar_tipi_tanim=bilgisayar_tipi_tanim;
                                   this.fiyat=fiyat;
                                   this.getirme_tarihi=getirme_tarihi;
                                   this.beklenen_bitis_tarihi=beklenen_bitis_tarihi;
                                   this.gercek_bitis_tarihi=gercek_bitis_tarihi;
                                   this.musteri_detaylari=musteri_detaylari;
                     }
}
