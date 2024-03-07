package ConquerGame;

import ConquerGame.Enums.*;
import Game.Game;
import Game.GameAction;
import Game.GamePlayer;

import java.util.*;

public class ConquerGame extends Game<ConquerPlayer> {
    public ConquerGame(String gameName  ) {
        super(gameName);
    }

    private final List<Town> towns ;


    {
        towns = List.of(
                new Town("Raccoon " , 1 ,
                        List.of(Features.TREE , Features.SPRING) ,
                        List.of(Loots.Flakker , Loots.Unforgiven) ,
                        new ConquerMonster(MonstersTypes.GOBLIN.name() , MonstersTypes.GOBLIN)
                )
               ,
                new Town("Oakvale " , 2 ,
                        List.of(Features.TREE , Features.POISON , Features.SPRING) ,
                        List.of(Loots.Unforgiven , Loots.Infinity , Loots.Conference) ,
                        new ConquerMonster(MonstersTypes.DRAGON.name() , MonstersTypes.DRAGON)
                ) ,

                new Town("Whiterun " , 3 ,
                        List.of(Features.TREE , Features.SPRING , Features.FIRE) ,
                        List.of(Loots.Flakker , Loots.Infinity , Loots.Conference , Loots.Unforgiven) ,
                        new ConquerMonster(MonstersTypes.TROLL.name() , MonstersTypes.TROLL)
                )
        );
    }


    public GameActionConstants visitNextTown(ConquerPlayer player){

        int newLevel = player.getLevel();

        int townsLevels = towns.size();

        if(newLevel > townsLevels){
            System.out.println(player.getName() + " WON!");
            return GameActionConstants.QUIT;
        }

        Town nextTown = towns.get(newLevel - 1);

        player.setCurrentTown(nextTown);
        System.out.println(player.getName() + " has moved to the next town " + nextTown.getName());
        return GameActionConstants.STAY;
    }


    public GameActionConstants doCurrentTownChallenge(int playerIndx){
           ConquerPlayer player = getPlayer(playerIndx);

            if(player.getCurrentWeapon() == null) {
                System.out.println("you should use weapon first to do town challenge");
                return GameActionConstants.STAY;
            }

            Town currentTown = towns.get(player.getLevel() - 1);

            System.out.println(player.getName() + "visited " + currentTown.getName());

            ConquerMonster currentTownConquerMonster = currentTown.getMonster();

            boolean isMonsterKilled =  attackMonster(player , currentTownConquerMonster);

            if(isMonsterKilled && experienceFeatures(player,currentTown)){
                      System.out.println(player.getName() + " has killed " + currentTownConquerMonster.getName());
                      experienceLoots(player);
                      increasePlayerLevel(player);
                      return visitNextTown(player);
            }else {
                  System.out.println(player.getName() + "has been killed by " + currentTownConquerMonster.getName());
                  System.out.println("GAME OVER!");
                  return GameActionConstants.QUIT;
              }
    }

    public boolean attackMonster(ConquerPlayer player, ConquerMonster monster){

        int playerWeaponHitPoints = player.getCurrentWeapon().getHitPoints();

        int monsterWeaponHitPoints = monster.getCurrentWeapon().getHitPoints();

        int playerHealth = player.getHealth();
        int monsterHealth = monster.getHealth();

        while (playerHealth > 0 || monsterHealth > 0){
            if(new Random().nextBoolean()){
                monsterHealth =  monster.adjustHealth(-playerWeaponHitPoints);
                System.out.println(player.getName() + " " + player.getCharachter() + " Man hits the monster " + monster.getName());
            }else {
                System.out.println(monster.getName() + " hits player " + player.getName());
                playerHealth = player.adjustHealth(- monsterWeaponHitPoints);
            }
        }

        if(playerHealth <= 0 && monsterHealth > 0){
            return false;
        }else {
            return true;
        }
    }

    public void increasePlayerLevel(ConquerPlayer player){
        player.increaseLevel();
        System.out.println("player level increased");
    }

    public boolean experienceFeatures(ConquerPlayer player , Town town){
        List<Features> currentTownFeatures = town.getFeatures();

        int randomFeatureIndex = new Random().nextInt(0 , currentTownFeatures.size());

        Features randomFeature = currentTownFeatures.get(randomFeatureIndex);

        player.experienceFeature(randomFeature);
        System.out.println(player.getName() + " has experienced " + randomFeature.name());

        int featureHealthPoints = randomFeature.getHealthPoints();

        player.adjustHealth(featureHealthPoints);

        if(featureHealthPoints > 0){
            System.out.println(player.getName() + " increases his health by " + randomFeature.getHealthPoints() );
        }else {
            System.out.println(player.getName() + " deduced his health by " + randomFeature.getHealthPoints());
        }

        if(player.getHealth() > 0){
            return true;
        }else {
            return false;
        }
    }

    public void experienceLoots(ConquerPlayer player ){

        Town cuurentTown = towns.get(player.getLevel() - 1 );

        List<Loots> townLoots = cuurentTown.getLoots();

        int randomLootIndex = new Random().nextInt(0 , townLoots.size());

        Loots randomLoot = townLoots.get(randomLootIndex);

        player.addLoot(randomLoot);
        System.out.println(player.getName() + " player has added " + randomLoot.name() + " to his stuff" );
        System.out.println(player.getName() + " his score has increased by " + randomLoot.getScoreBonus());
    }

    @Override
    public List<Map<String, GameAction>> initiateGameActions(GamePlayer player ) {

        List<Map<String, GameAction>> gameActionsList = super.getGameActionsList();

        // standard actions
        Map<String, GameAction> standardGameActions = super.getStandardGameActions();

        GameAction quitGameAction = getStandardGameAction("Q");

        // first displayed actions
        Map<String, GameAction> characterGameActions = getChracterGameActions((ConquerPlayer) player);

        if(quitGameAction != null){
            characterGameActions.put("Q", quitGameAction);
        }

        // second displayed actions
        Map<String, GameAction> conquerGameMainActions = getConquerGameMainActions((ConquerPlayer) player);

        conquerGameMainActions.putAll(standardGameActions);

        gameActionsList.addAll(List.of(characterGameActions, conquerGameMainActions));

        return gameActionsList;
    }

    public Map<String , GameAction> getConquerGameMainActions(ConquerPlayer player){
                List<ConquerWeaponsTypes> availableWeapons = player.experienceWeapon();

                Map<String , GameAction> conquerGameMainActions = new LinkedHashMap<String, GameAction>();

                availableWeapons.forEach(weapon -> {
                    String key = Character.toString(weapon.name().charAt(0)).toUpperCase() ;
                    GameAction gameAction = new GameAction(key , "use " + weapon.name() , (playerIndx) -> useWeapon(playerIndx , weapon));
                    conquerGameMainActions.put(key , gameAction);
                });

               conquerGameMainActions.put("E" , new GameAction("E" , "Experience current town" , this::doCurrentTownChallenge));

               return conquerGameMainActions;
    }


    public GameActionConstants useWeapon(int playerIndx , ConquerWeaponsTypes weapon){
        ConquerPlayer player = getPlayer(playerIndx);
        int playerLevel = player.getLevel();

        if(playerLevel >= weapon.getMinimumLevel()){
            player.useWeapon(weapon);
            System.out.println(player.getName() + " is using " + weapon.name());
        }else {
            System.out.println("you don't have the minimum level to hold this weapon");
        }

        return GameActionConstants.STAY;
    }

    public Map<String, GameAction> getChracterGameActions(ConquerPlayer player){
        Map<String , GameAction> characterGameActions = new LinkedHashMap<String, GameAction>();

        List<ConquerPlayerCharachtersTypes> allAvailableCharacters = new ArrayList<>(ConquerPlayerCharachtersTypes.getAllCharacters());

        allAvailableCharacters.forEach(character ->{
            String key = Character.toString(character.getName().charAt(0)).toUpperCase();
            String prompt = "(" + key + ")" + " use " + character.name();
            GameAction gameAction =  new GameAction(key , prompt , (s) -> usePlayerCharacter(player , character));
            characterGameActions.put(key ,gameAction);
        });

        return characterGameActions;
    }

    public GameActionConstants usePlayerCharacter(ConquerPlayer player , ConquerPlayerCharachtersTypes character){
        player.usePlayerCharacter(character);
        System.out.println(player.getName() + " is using " + character.getName() );
        return GameActionConstants.NEXT;
    }

    public Town getTown(int level){
        return towns.get(level);
    }


    public List<Town> getTowns() {
        return List.copyOf(towns);
    }

}
