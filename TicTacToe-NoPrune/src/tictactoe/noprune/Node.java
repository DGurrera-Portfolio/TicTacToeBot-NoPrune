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
public class Node {
    private Board board;
    private Node parent;
    private Node[] next;
    private boolean leaf;
    private int score;
    
    Node (Board b, Node n) {
        parent = n;
        board = b;
        if (b.getEmptySpaces() == 0 || b.checkVictoryStates(b.getPlayer(), board)) {
            leaf = true;
            if (!b.checkVictoryStates(b.getPlayer(), board)) {
                score = 0;
            } else {
                if (board.getPlayer() == 'X')
                    score = -1;
                else
                    score = 1;
            }
        } else {
            leaf = false;
            next = new Node[board.getEmptySpaces()];
        }
    }
    
    public void setScore(int s) {score = s;}
    public int getScore() {return score;}
    public int getMove() {return board.getMove();}
    public char[] copyBoard() {return board.copyBoard();}
    public Node getParent() {return parent;}
    public Node getNextAt(int i) {return next[i];}
    public void setNextAt(Node n, int i) {next[i] = n;}
    public int getNumOfChildren() {return next.length;}
    public char[] getBoard() {return board.getBoard();}
    public Board getBoardRef() {return board;}
    public char getPlayer() {return board.getPlayer();}
    public int getEmptySpaces() {return board.getEmptySpaces();}
    public boolean isValidMove(int i) {return board.isValidMove(i);}
    public boolean isLeaf() {return leaf;}
}