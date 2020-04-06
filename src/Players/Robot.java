package Players;

import Hanze.GameClient;
import Hanze.ServerCommunication;

import java.io.IOException;
import java.util.*;

public class Robot extends Player {
    private int difficulty; //0 easy,1 hard,2 impossible
    private String game;
    private Random randomGenerator;


    public Robot(GameClient client, int difficulty, String game) throws IOException, InterruptedException {
        super(client,game +".A.I.");
        setServerConnection(new ServerCommunication(client, this));
        this.difficulty = difficulty;
        this.game = game;
        randomGenerator = new Random();
        getServerConnection().logIN(getName());
        getServerConnection().subscribe(game);
    }

    public String think(Map<String,String> gameState){
        String bestMove = "0";
        int bestScore = -10;

        //get all empty spots and try going there to see what is the best spot
        for (Map.Entry<String, String> entry : gameState.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if(value.equals("E")){
                gameState.replace(key, "C");
                int score = minimax(gameState, 0, true);
                gameState.replace(key,"E");
                if(score>bestScore) {
                    bestScore = score;
                    bestMove = key;
                }
            }
        }
        return bestMove;
    }

    private int minimax(Map<String, String> gameState, int steps, boolean isMaximizing){
        System.out.println(testTicTacToeWinner(gameState));
        return 1;
    }

    private int openSpots(Map<String,String> gameState){
        int openspots = 0;
        for (Map.Entry<String, String> entry : gameState.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if(value.equals("E")){
                openspots++;
            }
        }
        return openspots;
    }

    private String testTicTacToeWinner(Map<String,String> gameState){
        String winner = null;
        String a = "";
        String b = "";
        String c = "";
        int position = 0;

        //horizontal
        for (int i = 0; i<3;i++){
             a = gameState.get(Integer.toString(position));
             b = gameState.get(Integer.toString(position+1));
             c = gameState.get(Integer.toString(position+2));
            if (a.equals(b) && b.equals(c)){ if (!a.equals("E")){winner = a;}}
            position += 3;
        }

        //vertical
        for (int i = 0; i<3;i++){
             a = gameState.get(Integer.toString(i));
             b = gameState.get(Integer.toString(position+3));
             c = gameState.get(Integer.toString(position+6));
            if (a.equals(b) && b.equals(c)){ if (!a.equals("E")){winner = a;}}
        }

        //diagonal
         a = gameState.get(Integer.toString(0));
         b = gameState.get(Integer.toString(4));
         c = gameState.get(Integer.toString(8));
        if (a.equals(b) && b.equals(c)){ if (!a.equals("E")){winner = a;}}

         a = gameState.get(Integer.toString(2));
         b = gameState.get(Integer.toString(4));
         c = gameState.get(Integer.toString(6));
        if (a.equals(b) && b.equals(c)){ if (!a.equals("E")){winner = a;}}


        return winner;
    }
}
