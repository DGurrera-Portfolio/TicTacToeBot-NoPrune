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
public class Board {
    
    private char[] board;
    private int emptySpaces;
    private char player;
    
    Board(char[] b, int es, int m, char p) {
        copyBoard(b);
        emptySpaces = es;
        player = p;
        makeMove(m);
    }
    
    private void copyBoard(char[] b) {
        board = new char[9];
        for(int i = 0; i < 9; ++i) {
            board[i] = b[i];
        }
    }

    private void makeMove(int m) {
        board[m] = player;
    }
    
    public boolean isValidMove(int m) {
        return board[m] == '-';
    }
    
    public void addMove(int m, char p) {
        board[m] = p;
    }
    
    public char[] getBoard() { return board; }
    public int getEmptySpaces() { return emptySpaces; }
    public char getPlayer() {return player; }
}
