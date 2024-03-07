package ConquerGame;


import ConquerGame.Enums.*;
import ConquerGame.Interfaces.ConquerMainCharachters;
import Game.GamePlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConquerPlayer extends ConquerCharacter implements GamePlayer {


    private int level;

    private int score;

    private Town currentTown;

    private List<Loots> earnedLoots;

    private List<ConquerWeaponsTypes> weapons;

    private List<Features> features;

    public ConquerPlayer(){
        super();
    }


    {
        this.level = 1;
        this.score = 0;
        this.weapons = new ArrayList<>(20);
        this.earnedLoots = new ArrayList<>(20);
        this.features = new ArrayList<>(20);
    }

    public ConquerPlayer(String name,
                         ConquerPlayerCharachtersTypes chrachter
    ) {
        super(name, chrachter);
    }

    public void addLoot(Loots loot){
        this.earnedLoots.add(loot);
        this.increaseScore(loot.getScoreBonus());

    }

    public void experienceFeature(Features feature){
        features.add(feature);
    }


    public List<ConquerWeaponsTypes> experienceWeapon(){
        ConquerWeaponsTypes characterMainWeapon = this.getCharachter() != null ? this.getCharachter().getWeapon() : null;

        List<ConquerWeaponsTypes> mainWeapons =  ConquerWeaponsTypes.getMainWeapons();

        List<ConquerWeaponsTypes> allCharacterWeapons = new ArrayList<>(mainWeapons.size() + 1);

        if(characterMainWeapon != null ) allCharacterWeapons.add(characterMainWeapon);

        allCharacterWeapons.addAll(mainWeapons);

        List<ConquerWeaponsTypes> unLockedWeapons = new ArrayList<>();

        unLockedWeapons.addAll(allCharacterWeapons);

        weapons.addAll(unLockedWeapons);

        return weapons;

    }

    @Override
    public String getName(){
        return super.getName();
    }

    public int getLevel(){
        return level;
    }

    public int increaseLevel(){
        this.level += 1;
        return level;
    }

    public void increaseScore(int score){
        this.score += score;
    }


    public Town getCurrentTown(){
        return currentTown;
    }

    public void useWeapon(ConquerWeaponsTypes weapon){
        this.setCurrentWeapon(weapon);
    }

    public void setCurrentTown(Town town){
        this.currentTown = town;
    }

    public void usePlayerCharacter(ConquerMainCharachters character){
        super.setGameCharacter(character);
    }

    @Override
    public String getInformation() {
        return "[ player name is " + super.getName() + " and his game character is " + super.getCharachter() + " its level is" + this.getLevel() + " and his score is " + score + "%" + " ]";
    }
}
