package ConquerGame.Enums;

import ConquerGame.Interfaces.ConquerMainCharachters;

public enum MonstersTypes implements ConquerMainCharachters {

    DRAGON(ConquerWeaponsTypes.KNIFE),
    GOBLIN(ConquerWeaponsTypes.KNIFE),
    ORC(ConquerWeaponsTypes.KNIFE),
    TROLL(ConquerWeaponsTypes.KNIFE);

    private ConquerWeaponsTypes weapon;
    MonstersTypes(ConquerWeaponsTypes weapon) {
        this.weapon = weapon;
    }


    @Override
    public ConquerWeaponsTypes getWeapon() {
        return weapon;
    }

    @Override
    public String getName() {
        return name();
    }
}
