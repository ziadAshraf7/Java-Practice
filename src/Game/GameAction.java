package Game;

import ConquerGame.Enums.GameActionConstants;

import java.util.function.Function;

public final class GameAction {

    private String key;
    private String prompt;
    private Function<Integer , GameActionConstants> action;

    public GameAction(String key, String prompt, Function<Integer , GameActionConstants> action) {
        this.key = key;
        this.prompt = prompt;
        this.action = action;
    }

    public String getKey() {
        return key;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public Function<Integer , GameActionConstants> getAction() {
        return action;
    }

    public void setAction(Function<Integer , GameActionConstants> action) {
        this.action = action;
    }
}
