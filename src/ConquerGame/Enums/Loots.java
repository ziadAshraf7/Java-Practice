package ConquerGame.Enums;

public enum Loots {

    Flakker(25) ,
    Unforgiven(30) ,
    Infinity(40) ,
    Conference(80);

    private int scoreBonus;

    Loots(int scoreBonus) {
        this.scoreBonus = scoreBonus;
    }

    public int getScoreBonus(){
        return scoreBonus;
    }

}
