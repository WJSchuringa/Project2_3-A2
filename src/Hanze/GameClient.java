package Hanze;

import Games.TicTacToe;
import Players.Human;
import Players.Player;
import Players.Robot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Class that contains the game client for the application.
 * This will be the main controller for the application.
 *
 * @author JasperSikkema, WJSchuringa
 */

public class GameClient {
    private List<Socket> sockets;
    private List<ServerCommunication> serverCommunications;
    private Player player;
    private HashMap<String, Player> robots;
    private List<String> onlinePlayers;
    private List<String> games;
    private Human human;
    private Robot robot;
    private TicTacToe ticTacToe;

    public GameClient() throws IOException, InterruptedException {
        sockets = new ArrayList<>();
        serverCommunications = new ArrayList<>();
        robots = new HashMap<>();
        onlinePlayers = new ArrayList<>();
        games = new ArrayList<>();
        robots.put("Reversi",new Robot(this,0,"Reversi"));
        robots.put("Tic-tac-toe",new Robot(this, 0, "Tic-tac-toe"));
        player = new Human(this, "kees" );
        TestThread test = new TestThread(player);
    }

    public HashMap<String, Player> getRobots() {
        return robots;
    }

    public List<String> getOnlinePlayers() {
        return onlinePlayers;
    }

    public List<String> getGames() {
        return games;
    }

    public List<Socket> getSockets() {
        return sockets;
    }

    public List<ServerCommunication> getServerCommunications() {
        return serverCommunications;
    }

    public String getPlayerName(){
        return human.getName();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        new GameClient();
    }

    public void playersTurn(){
        ticTacToe.playersTurn();
    }

    public void aiTurn(){
        ticTacToe.AITurn();
    }

}

    /**
     * Sends a command through the PrintWriter out to the server.
     * @param command the command to send to the server.
     * public void sendCommand(String command) {
     *         serverCommunication.getOut().println(command);
     *     }
     */
