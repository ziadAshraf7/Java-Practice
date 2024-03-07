package ConquerGame;

import ConquerGame.Enums.ConquerWeaponsTypes;
import ConquerGame.Interfaces.ConquerMainCharachters;

public class ConquerMonster extends ConquerCharacter {
    public ConquerMonster(String name, ConquerMainCharachters chrachter ) {
        super(name, chrachter);
        getWeapon();
    }

    public void getWeapon() {
        this.setCurrentWeapon(ConquerWeaponsTypes.KNIFE);
    }

}
