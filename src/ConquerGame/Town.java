package ConquerGame;

import ConquerGame.Enums.Features;
import ConquerGame.Enums.Loots;

import java.util.ArrayList;
import java.util.List;

public final class Town {

    private String name;

    private int level;

    private List<Features> features;

    private List<Loots> loots;

    private ConquerMonster conquerMonster;

    public Town(String name, int level, List<Features> features, List<Loots> loots, ConquerMonster conquerMonster) {
        this.name = name;
        this.level = level;
        this.features = new ArrayList<>(features);
        this.loots = new ArrayList<>(loots);
        this.conquerMonster = conquerMonster;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<Features> getFeatures() {
        return new ArrayList<>(features);
    }


    public ConquerMonster getMonster(){
        return conquerMonster;
    }

    public List<Loots> getLoots() {
        return new ArrayList<>(loots);
    }


}
