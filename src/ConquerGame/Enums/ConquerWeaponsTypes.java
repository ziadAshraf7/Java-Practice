package ConquerGame.Enums;

import java.util.ArrayList;
import java.util.List;

public enum ConquerWeaponsTypes {

    SWORD(2 , 3 , true) ,
    BOWS(2 , 1 , true) ,
    AXE(2 , 3 , true) ,
    SPEARS(2 , 4 , true) ,
    BLADE(2 , 2 , false) ,
    KNIFE(1,1,false) ,
    PIKES(3 , 2 , false);

    private int minimumLevel;
    private int hitPoints;
    private boolean charachterSpecific;

    ConquerWeaponsTypes(int minimumLevel, int hitPoints, boolean charachterSpecific) {
        this.minimumLevel = minimumLevel;
        this.hitPoints = hitPoints;
        this.charachterSpecific = charachterSpecific;
    }


    public static List<ConquerWeaponsTypes> getMainWeapons(){
        ConquerWeaponsTypes[] allWeapons = values();
        List<ConquerWeaponsTypes> defaultWeapons = new ArrayList<>(20);

        for(ConquerWeaponsTypes weapon : allWeapons){
            if(!weapon.charachterSpecific) defaultWeapons.add(weapon);
        }

        return defaultWeapons;
    }

    public int getMinimumLevel() {
        return minimumLevel;
    }

    public void setMinimumLevel(int minimumLevel) {
        this.minimumLevel = minimumLevel;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public boolean isCharachterSpecific() {
        return charachterSpecific;
    }

    public void setCharachterSpecific(boolean charachterSpecific) {
        this.charachterSpecific = charachterSpecific;
    }
}
