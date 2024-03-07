package ConquerGame.Enums;

import ConquerGame.Interfaces.ConquerMainCharachters;

import java.util.Arrays;
import java.util.List;

public enum ConquerPlayerCharachtersTypes implements ConquerMainCharachters {

    NINJA(ConquerWeaponsTypes.SWORD) ,
    ARCHER(ConquerWeaponsTypes.BOWS) ,
    PIRATE(ConquerWeaponsTypes.AXE) ,
    TROJAN(ConquerWeaponsTypes.SPEARS);

    private ConquerWeaponsTypes Weapon;

    ConquerPlayerCharachtersTypes(ConquerWeaponsTypes Weapon) {
        this.Weapon = Weapon;
    }
    public static List<ConquerPlayerCharachtersTypes> getAllCharacters(){
        return Arrays.asList(values());
    }

    @Override
    public ConquerWeaponsTypes getWeapon() {
        return Weapon;
    }

    @Override
    public String getName() {
        return name();
    }
}
