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
public class personel {
       private String personel_isim;
    private String personel_kullaniciadi;
    private String personel_kullanicisifre;
    private int yonetici_mi;
    
    public personel( String personel_isim, String personel_kullaniciadi,String personel_kullanicisifre, int yonetici_mi){
        this.personel_isim=personel_isim;
        this.personel_kullaniciadi=personel_kullaniciadi;
        this.personel_kullanicisifre=personel_kullanicisifre;
        this.yonetici_mi=yonetici_mi;
    }

    personel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getPersonel_isim() {
        return personel_isim;
    }

    public String getPersonel_kullaniciadi() {
        return personel_kullaniciadi;
    }

    public String getPersonel_kullanicisifre() {
        return personel_kullanicisifre;
    }

    public int getYonetici_mi() {
        return yonetici_mi;
    }

    public void setPersonel_isim(String personel_isim) {
        this.personel_isim = personel_isim;
    }

    public void setPersonel_kullaniciadi(String personel_kullaniciadi) {
        this.personel_kullaniciadi = personel_kullaniciadi;
    }

    public void setPersonel_kullanicisifre(String personel_kullanicisifre) {
        this.personel_kullanicisifre = personel_kullanicisifre;
    }

    public void setYonetici_mi(int yonetici_mi) {
        this.yonetici_mi = yonetici_mi;
    }
  
  
}
