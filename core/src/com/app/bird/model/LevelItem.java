package com.app.bird.model;

public class LevelItem {
    float bitisKonumX;// = 5;
    float kusunHizi ;//= 200,
    float ziplama;// = 350,
    float  yercekimi ;//= -20f,
    float  ariHizi ;//= 2;
    //int ariXset; = 12;
    int arisutunsayisi;


 /*   double yercekimidizi[] = {-20f, -22.5f, -25f, -27.5f, -30f, -32.5f, -35f, -37.5f, -40f, -42.5f, -45f, -47.5f, -50f, -52.5f, -55f, -57.5f, -60f
            - 62.5f, -65f, -67.5f, -70f, -72.5f, -75f, -77.5f};
    float kusHiziDizi[] = {200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775};
    float ziplamaDizi[] = {350, 369, 388, 407, 426, 445, 464, 483, 502, 521, 540, 559, 578, 597, 616, 635, 654, 673, 692, 711, 730, 749, 768, 787};
    float bitisKonumXDizi[] = {3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 8,};
*/


    public int getArisutunsayisi() { return arisutunsayisi; }

    public void setArisutunsayisi(int arisutunsayisi) { this.arisutunsayisi = arisutunsayisi; }

    public float getAriHizi() { return ariHizi; }

    public void setAriHizi(float ariHizi) {
        this.ariHizi = ariHizi;
    }

   /* public int getAriXset() {
        return ariXset;
    }

    public void setAriXset(int ariXset) {
        this.ariXset = ariXset;
    }
*/
    public float getBitisKonumX() {
        return bitisKonumX;
    }

    public void setBitisKonumX(float bitisKonumX) {
        this.bitisKonumX = bitisKonumX;
    }

    public float getKusunHizi() {
        return kusunHizi;
    }

    public void setKusunHizi(float kusunHizi) {
        this.kusunHizi = kusunHizi;
    }

    public float getZiplama() {
        return ziplama;
    }

    public void setZiplama(float ziplama) {
        this.ziplama = ziplama;
    }

    public float getYercekimi() {
        return yercekimi;
    }

    public void setYercekimi(float yercekimi) {
        this.yercekimi = yercekimi;
    }
}
