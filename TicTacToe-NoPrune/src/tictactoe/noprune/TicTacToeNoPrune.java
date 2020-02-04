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
        System.out.println("Game Tree size: " + gameTree.getSize());
        System.out.println();
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
        System.out.println("Amount of children for current board: " + gameTree.getHead().getNumOfChildren());
        Node currentBoard = gameTree.getHead();
        
        Node bestBoard = currentBoard.getNextAt(0);
        int score = currentBoard.getNextAt(0).getScore();
        
        for(int i = 0; i < currentBoard.getNumOfChildren(); ++i){
            if (currentBoard.getNextAt(i).isLeaf()) {
                board = currentBoard.getNextAt(i).getBoardRef();
                gameTree.setHead(currentBoard.getNextAt(i));
                return;
            }
            
            if (currentBoard.getNextAt(i).getScore() > score) {
                score = currentBoard.getNextAt(i).getScore();
                bestBoard = currentBoard.getNextAt(i);
            }
        }
        
        System.out.println("Move score: " + score);
        
        board = bestBoard.getBoardRef();
        gameTree.setHead(bestBoard);
        
//        Random rng = new Random();
//        int move = rng.nextInt(9);
//        if (board.isValidMove(move))
//            board.addMove(move, currentPlayer);
//        else
//            computerMove();
    }
    
    public static void humanMove() {
        Scanner in = new Scanner(System.in);
        System.out.println("Amount of children for current board: " + gameTree.getHead().getNumOfChildren());
        System.out.print("Enter move (0-8): ");
        int input = in.nextInt();
        if (board.isValidMove(input)) {
            for (int i = 0; i < gameTree.getHead().getNumOfChildren(); ++i){
                if (gameTree.getHead().getNextAt(i).getMove() == input)
                    gameTree.setHead(gameTree.getHead().getNextAt(i));
            }
        }
        else {
            System.out.println("This is invalid, make a valid move.");
            humanMove();
        }
        board = gameTree.getHead().getBoardRef();
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
        gameTree.addNode(new Node(new Board(b, 9, 0, '-'), null), 0);
        long startTime = System.currentTimeMillis();
        System.out.println("Initializing Game Tree...");
        buildGameTree(gameTree.getHead(), 0, 'X');
        long endTime = System.currentTimeMillis();
        System.out.println("Initialization completed: " + ((endTime - startTime)) + " milliseconds");
    }
    
    public static void buildGameTree(Node parent, int childNum, char currPlayer) {
        int child = childNum;
        for (int i = 0; i < 9; ++i) {
            if (parent.isValidMove(i)) {
                Node next = new Node(new Board(parent.copyBoard(), parent.getEmptySpaces() - 1, i, currPlayer), parent);
                gameTree.addNode(next, child);
                ++child;
                if(!next.isLeaf()) {
                    if (currPlayer == 'X')
                        buildGameTree(next, 0, 'O');
                    else
                        buildGameTree(next, 0, 'X');
                }
            }
        }
        
        int score = 0;
        for (int i = 0; i < parent.getNumOfChildren(); ++i)
            score += parent.getNextAt(i).getScore();
        parent.setScore(score);
    }
}