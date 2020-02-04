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
public class Board extends Game{
    
    private char[] board;
    private int emptySpaces;
    private char player;
    private int move;
    
    Board(char[] b, int es, int m, char p) {
        copyBoard(b);
        emptySpaces = es;
        player = p;
        move = m;
        makeMove(move);
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
    
    public char[] copyBoard() {
        char[] b = new char[9];
        for (int i = 0; i < 9; ++i)
            b[i] = board[i];
        return b;
    }
    
    public void addMove(int m, char p) {
        board[m] = p;
    }
    
    public int getMove() {return move;}
    public char[] getBoard() { return copyBoard(); }
    public int getEmptySpaces() { return emptySpaces; }
    public char getPlayer() {return player; }
}