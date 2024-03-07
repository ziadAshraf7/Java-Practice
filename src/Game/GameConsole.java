package Game;


import ConquerGame.Enums.GameActionConstants;

import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class GameConsole<T extends Game> {

    private T game;

    private Scanner scanner = new Scanner(System.in);

    public GameConsole(T game) {
        this.game = game;
    }

    public GameAction checkInvalidInput(String key , Map<String , GameAction> gameActions ){
      String prompt = "Please Enter valid Key";

       String upperCaseKey = key.toUpperCase();

       GameAction selectedGameAction = gameActions.get(upperCaseKey);

       if(selectedGameAction != null){
           selectedGameAction = gameActions.get(upperCaseKey);
           return selectedGameAction;
       }else {
           System.out.println(prompt);
           String newKey = scanner.nextLine().toUpperCase();
           return checkInvalidInput(newKey , gameActions);
       }
    };


    public int defaultGameAction(GamePlayer player){
        game.addPlayer(player);

        int playerIndx = game.getPlayers().indexOf(player);

        System.out.println("Enter your player name");

        String name = scanner.nextLine();

        player.setName(name);

        return playerIndx;
    }

    public void startGame(GamePlayer player){

        List<Map<String , GameAction>> gameActionsList = game.startGame(player);

        int playerIndx = defaultGameAction(player);

        int i;
        for( i = 0  ; i <= gameActionsList.size() - 1 ; ){
            Map<String , GameAction> gameActions = gameActionsList.get(i);

            for(Map.Entry<String , GameAction> entry : gameActions.entrySet()){
                String key = entry.getKey().toUpperCase();
                GameAction action = entry.getValue();
                System.out.println("( " + key + " ) " + action.getPrompt());
            }

            String selectedKey = scanner.nextLine();

            GameAction selectedGameAction =  checkInvalidInput(selectedKey , gameActions);

            GameActionConstants excutedGameAction = game.excuteGameAction(selectedGameAction.getAction() , playerIndx );

             if(excutedGameAction == GameActionConstants.NEXT){
                 i++;
             }else if(excutedGameAction == GameActionConstants.QUIT){
                 break;
             }

        }

    }

}
