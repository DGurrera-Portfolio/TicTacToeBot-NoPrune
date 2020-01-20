/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.noprune;

import java.util.Scanner;

/**
 * 
 * @author CL4P-TP
 */
public class TicTacToeNoPrune {

    public static Board board;
    public static char currentPlayer;
    public static int[][] victoryStates;
    
    public static void main(String[] args) {
        initialize();
        initializeVictoryStates();
        if (gameType() == 1)
            System.out.println("not implemented");
        else {
            System.out.println();
            humanGame();
        }
    }
    
    public static int gameType() {
        Scanner in = new Scanner(System.in);
        System.out.print("1 for AI, 2 for humans: ");
        int gameType = in.nextInt();
        if (!validGameType(gameType))
            gameType = gameType();
        else
            return gameType;
        
        return gameType;
    }
    
    public static boolean validGameType(int i) {
        return (i == 1 || i == 2);
    }
    
    public static void initialize() {
        char[] b = new char[9];
        for(int i = 0; i < 9; ++i) {
            b[i] = '-';
        }
        board = new Board(b, 9, 0, '-');
    }
    
    public static void humanGame() {
        printBoard();
        while (true) {
            currentPlayer = 'X';
            System.out.println("Player 1 move:");
            makeMove();
            printBoard();
            if (checkVictoryStates()) {
                System.out.println("Player 1 wins!");
                System.exit(0);
            }
            currentPlayer = 'O';
            System.out.println("Player 2 move:");
            makeMove();
            printBoard();
            if (checkVictoryStates()) {
                System.out.println("Player 2 wins!");
                System.exit(0);
            }
        }
    }
    
    public static void makeMove() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter move (0-8): ");
        int input = in.nextInt();
        if (board.isValidMove(input))
            board.addMove(input, currentPlayer);
        else {
            System.out.println("This is invalid, make a valid move.");
            makeMove();
        }
        System.out.println();
    }
    
    public static void printBoard() {
        char[] b = board.getBoard();
        for (int i = 0; i < 9; i = i + 3)
            System.out.println(b[i] + " " + b[i+1] + " " + b[i + 2]);
        System.out.println();
    }
    
    public static boolean checkVictoryStates() {
        char[] b = board.getBoard();
        for( int i = 0; i < 8; ++i)
            if(b[victoryStates[i][0]] == currentPlayer && b[victoryStates[i][1]] == currentPlayer && b[victoryStates[i][2]] == currentPlayer)
                return true;
        return false;
    }

    private static void initializeVictoryStates() {
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