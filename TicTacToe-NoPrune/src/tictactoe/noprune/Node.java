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
    
    Node (Board b, Node n) {
        parent = n;
        board = b;
        if (b.getEmptySpaces() == 0 || b.checkVictoryStates(b.getPlayer(), board)) {
            leaf = true;
        } else {
            leaf = false;
            next = new Node[board.getEmptySpaces()];
        }
    }
    
    public char[] copyBoard() {return board.copyBoard();}
    public Node getParent() {return parent;}
    public Node getNextAt(int i) {return next[i];}
    public void setNextAt(Node n, int i) {next[i] = n;}
    public int getNumOfChildren() {return next.length;}
    public char[] getBoard() {return board.getBoard();}
    public char getPlayer() {return board.getPlayer();}
    public int getEmptySpaces() {return board.getEmptySpaces();}
    public boolean isValidMove(int i) {return board.isValidMove(i);}
    public boolean isLeaf() {return leaf;}
}