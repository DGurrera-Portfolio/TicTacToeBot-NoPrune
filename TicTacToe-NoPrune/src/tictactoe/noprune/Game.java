/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.noprune;

/**
 *
 * @author CL4P-TP
 */
public class Game {
    
    private int[][] victoryStates;
    
    Game() {
        initializeVictoryStates();
    }
    
    public boolean checkVictoryStates(char currentPlayer, Board board) {
        char[] b = board.getBoard();
        for( int i = 0; i < 8; ++i)
            if(b[victoryStates[i][0]] == currentPlayer && b[victoryStates[i][1]] == currentPlayer && b[victoryStates[i][2]] == currentPlayer)
                return true;
        return false;
    }

    private void initializeVictoryStates() {
        victoryStates = new int[][]{ 
            {0, 1, 2},
            {0, 4, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 4, 6},
            {2, 5, 8},
            {3, 4, 5},
            {6, 7, 8}
        };
    }
}
