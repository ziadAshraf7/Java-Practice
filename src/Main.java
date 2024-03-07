import ConquerGame.ConquerPlayer;
import ConquerGame.ConquerGame;
import Game.GameConsole;

import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        ConquerGame conquerGame = new ConquerGame("Conquer");

        GameConsole gameConsole = new GameConsole(conquerGame);

        ConquerPlayer player = new ConquerPlayer();

        gameConsole.startGame(player);

        }

}