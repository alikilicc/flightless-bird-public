package com.app.bird;

import com.app.bird.model.LevelItem;

import java.util.ArrayList;

public class LevelDimens {

    private static LevelDimens INSTANCE;
    public ArrayList<LevelItem> levelItems = new ArrayList<>();

    // bu classın özelliği 0dan 25 levele kadar bütün levellerin özelliğni verir. bir arrayliste tutar.

    /**
     * Class constructorında levelleri yüzdelik hesaplar ile arraylist içine atıyorum.
     */
    private LevelDimens() {
        configureLevelDimens();
    }

    public static LevelDimens getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LevelDimens();
        }
        return INSTANCE;
    }

    public ArrayList<LevelItem> getLevelItems() {
        return levelItems;
    }

    public void setLevelItems(ArrayList<LevelItem> levelItems) {
        this.levelItems = levelItems;
    }

    private void configureLevelDimens() {
        // he getLevel( ) diyoruz ya 1 inci leveli alıyor arraylistin 1. itemi geliyor yani 2.cisi
        for (int level = 0; level < 25; level++) {
            LevelItem levelItem = new LevelItem();
            if (level == 0) { // ilk leveli default atadığımız değerler olarak giriyorum.
                // Burası ba
                //levelItem.setAriHizi(2f);
                levelItem.setArisutunsayisi(5);
                //levelItem.setZiplama(270);
                //levelItem.setYercekimi(-10);
                //levelItem.setKusunHizi(100);
                //levelItem.setBitisKonumX(5);
                levelItem.setAriHizi(1f);
                levelItem.setZiplama(300);
                levelItem.setYercekimi(-10);
                levelItem.setKusunHizi(80);
                levelItem.setBitisKonumX(5);
                //levelItem.setAriXset(15);
                //levelItem.setAriXset(4);
            } else if (level < 8) {
                levelItem.setAriHizi((float) ((levelItems.get(level - 1).getAriHizi() / 100) * 5) + levelItems.get(level - 1).getAriHizi());
                levelItem.setYercekimi((float) ((levelItems.get(level - 1).getYercekimi() / 100) * 10.5) + levelItems.get(level - 1).getYercekimi());
                levelItem.setZiplama((float) ((levelItems.get(level - 1).getZiplama() / 100) * 6.5) + levelItems.get(level - 1).getZiplama());
                levelItem.setKusunHizi((float) ((levelItems.get(level - 1).getKusunHizi() / 100) * 12.5) + levelItems.get(level - 1).getKusunHizi());
                levelItem.setBitisKonumX(levelItems.get(level - 1).getBitisKonumX() + 1);
                levelItem.setArisutunsayisi(levelItems.get(level - 1).getArisutunsayisi() + 2);


            } else {
                levelItem.setAriHizi((float) ((levelItems.get(level - 1).getAriHizi() / 100) * 2.5) + levelItems.get(level - 1).getAriHizi());
                levelItem.setYercekimi((float) ((levelItems.get(level - 1).getYercekimi() / 100) * 5.0) + levelItems.get(level - 1).getYercekimi());
                levelItem.setZiplama((float) ((levelItems.get(level - 1).getZiplama() / 100) * 3.0) + levelItems.get(level - 1).getZiplama());
                levelItem.setKusunHizi((float) ((levelItems.get(level - 1).getKusunHizi() / 100) * 6.5) + levelItems.get(level - 1).getKusunHizi());
                levelItem.setBitisKonumX(levelItems.get(level - 1).getBitisKonumX() + 1);
                levelItem.setArisutunsayisi(levelItems.get(level - 1).getArisutunsayisi() + 2);

            }
            levelItems.add(levelItem);
        }

    }


}
