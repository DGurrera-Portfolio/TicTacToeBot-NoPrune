/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.noprune;

import java.util.Scanner;
import java.util.Random;

/**
 * 
 * @author CL4P-TP
 */
public class TicTacToeNoPrune {

    public static Game game;
    public static Board board;
    public static char currentPlayer;
    public static LinkedList gameTree;
    public static int[][] victoryStates;
    
    public static void main(String[] args) {
        initialize();
        if (gameType() == 1) {
            System.out.println("Human will go first.");
            System.out.println();
            computerGame();
        }
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
        game = new Game();
        char[] b = new char[9];
        for(int i = 0; i < 9; ++i) {
            b[i] = '-';
        }
        board = new Board(b, 9, 0, '-');
        gameTree = new LinkedList();
    }
    
    public static void computerGame() {
        initializeGameTree();
        printBoard();
        while (true) {
            currentPlayer = 'X';
            System.out.println("Human move:");
            humanMove();
            printBoard();
            if (board.checkVictoryStates(currentPlayer, board)) {
                System.out.println("Human wins!");
                System.exit(0);
            }
            currentPlayer = 'O';
            System.out.println("Computer move:");
            computerMove();
            printBoard();
            if (board.checkVictoryStates(currentPlayer, board)) {
                System.out.println("Computer wins!");
                System.exit(0);
            }
        }
    }
    
    public static void humanGame() {
        printBoard();
        while (true) {
            currentPlayer = 'X';
            System.out.println("Player 1 move:");
            humanMove();
            printBoard();
            if (board.checkVictoryStates(currentPlayer, board)) {
                System.out.println("Player 1 wins!");
                System.exit(0);
            }
            currentPlayer = 'O';
            System.out.println("Player 2 move:");
            humanMove();
            printBoard();
            if (board.checkVictoryStates(currentPlayer, board)) {
                System.out.println("Player 2 wins!");
                System.exit(0);
            }
        }
    }
    
    public static void computerMove() {
        Random rng = new Random();
        int move = rng.nextInt(9);
        if (board.isValidMove(move))
            board.addMove(move, currentPlayer);
        else
            computerMove();
    }
    
    public static void humanMove() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter move (0-8): ");
        int input = in.nextInt();
        if (board.isValidMove(input))
            board.addMove(input, currentPlayer);
        else {
            System.out.println("This is invalid, make a valid move.");
            humanMove();
        }
        System.out.println();
    }
    
    public static void printBoard() {
        char[] b = board.getBoard();
        for (int i = 0; i < 9; i = i + 3)
            System.out.println(b[i] + " " + b[i+1] + " " + b[i + 2]);
        System.out.println();
    }
    
    public static void initializeGameTree() {
        char[] b = {'-','-','-','-','-','-','-','-','-'};
        gameTree.addNode(new Node(new Board(b, 9, 0, '-'), null), '-');
        buildGameTree(gameTree.getHead(), 0, 'X');
    }
    
    public static void buildGameTree(Node n, int x, char cp) {
        for (int i = 0; i < 9; ++i) {
            if (n.isValidMove(i)) {
                Node next = new Node(new Board(n.copyBoard(), n.getEmptySpaces() - 1, i, cp), n);
                gameTree.addNode(next, x);
            }
        }
    }
}