package ConquerGame;

import ConquerGame.Enums.ConquerWeaponsTypes;
import ConquerGame.Interfaces.ConquerMainCharachters;

public abstract class ConquerCharacter {
    private String name ;

    private ConquerMainCharachters character;

    private ConquerWeaponsTypes currentWeapon;

    private int health;


    private ConquerMainCharachters type;

    // default values
    {
        this.health = 10;
    }

    public ConquerCharacter(){

    }

    public ConquerCharacter(String name,
                            ConquerMainCharachters character
) {
        this.name = name;
        this.character = character;
    }



    public void useCharachterMainWeapon(){
        this.currentWeapon = character.getWeapon();
    }

    public void setWeapon(ConquerWeaponsTypes weapon){
        this.currentWeapon = weapon;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ConquerMainCharachters getCharachter() {
        return character;
    }


    public ConquerWeaponsTypes getCurrentWeapon() {
        return currentWeapon;
    }

    public void setCurrentWeapon(ConquerWeaponsTypes currentWeapon) {
        this.currentWeapon = currentWeapon;
    }

    public int getHealth() {
        return health;
    }

    public void setGameCharacter(ConquerMainCharachters character){
        this.character = character;
    }

    public int adjustHealth(int health) {
        this.health += health;
        return health;
    }

}
