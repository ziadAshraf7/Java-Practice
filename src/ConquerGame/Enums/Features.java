package ConquerGame.Enums;

public enum Features {

    SPRING(5) ,
    POISON(-3) ,
    TREE(4) ,
    FIRE(-5);


    private int healthPoints;

    Features(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getHealthPoints(){
        return healthPoints;
    }
}
