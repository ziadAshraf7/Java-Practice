package Game;

import ConquerGame.ConquerPlayer;
import ConquerGame.Enums.GameActionConstants;
import ConquerGame.Interfaces.ConquerMainCharachters;

import java.util.*;
import java.util.function.Function;

public abstract class  Game<T extends GamePlayer> {
    private String gameName;

    private  List<Map<String,GameAction>> gameActionsList;

    private List<T> players;

    private final Map<String , GameAction> standardGameActions;

    {
        standardGameActions =  Map.of(
             "I"  ,  new GameAction( "I" ,"press to get player info" , (playerIndx) -> getPlayerInfo(playerIndx)) ,
                "Q" ,   new GameAction("Q","Quit the game" , (playerIndx) -> QuitGame(playerIndx))
                );

    }

    public Game(String gameName) {
        this.gameName = gameName;
        this.players = new ArrayList<>();
        this.gameActionsList = new ArrayList<>();
    }

    public GameActionConstants getPlayerInfo(int playerIndx){
        GamePlayer player = getPlayer(playerIndx);

        System.out.println(player.getInformation());

        return GameActionConstants.STAY;
    }

    public T getPlayer(int playerIndx){
        return players.get(playerIndx);
    }

    public Map<String, GameAction> getStandardGameActions() {
        return Map.copyOf(standardGameActions);
    }

    public GameActionConstants QuitGame(int playerIndx){
        T player = getPlayer(playerIndx);
        System.out.println(player.getName() + " has quited the game");
        return GameActionConstants.QUIT;
    }

    public abstract List<Map<String, GameAction>>  initiateGameActions(GamePlayer player );

    public List<Map<String, GameAction>>  startGame(GamePlayer player ){
        List<Map<String, GameAction>> gameActionsList = initiateGameActions(player);
        setGameActionsList(gameActionsList);

        return List.copyOf(gameActionsList);
    };

    public GameActionConstants excuteGameAction(Function<Integer , GameActionConstants> action , int playerIndx){
      return  action.apply(playerIndx);
    }

    public GameAction getGameAction(Map<String , GameAction> gameActions,String  key){
        GameAction gameAction = gameActions.get(key);
        return new GameAction(gameAction.getKey() , gameAction.getPrompt() , gameAction.getAction()) ;
    }

    public int addPlayer(T player){
        players.add(player);
        return players.size() - 1;
    }

    public  List<T> getPlayers(){
        return players;
    }

    public Map<String,GameAction> getGameActions(int GameActionIndex){
            return Map.copyOf(gameActionsList.get(GameActionIndex));
    }

    public Map<String , GameAction> addGameAction(int GameActionsIndex , String key , GameAction gameAction){
        Map<String , GameAction> gameActions = getGameActions(GameActionsIndex);
        if(gameActions != null){
            gameActions.put(key,gameAction);
        }
        return Map.copyOf(gameActions);
    }

    public Map<String , GameAction> deleteGameAction (int GameActionsIndex , String key){
        Map<String , GameAction> gameActions = getGameActions(GameActionsIndex);
        gameActions.remove(key);
        return Map.copyOf(gameActions) ;
    }

    public List<Map<String , GameAction>> addGameActions(Map<String , GameAction> gameActionMap){
        gameActionsList.add(gameActionMap);
        return List.copyOf(gameActionsList);
    }

    public void setGameActionsList(List<Map<String , GameAction>> gameActionsList){
        this.gameActionsList = gameActionsList;
    }

    public GameAction getStandardGameAction(String key){
        GameAction standardGameAction = standardGameActions.get(key);
        return new GameAction(standardGameAction.getKey() , standardGameAction.getPrompt() , standardGameAction.getAction());
    }


    public List<Map<String , GameAction>> getGameActionsList(){
        return gameActionsList;
    }

    public String getGameName() {
        return gameName;
    }
}
