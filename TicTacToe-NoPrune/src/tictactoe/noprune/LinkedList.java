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
public class LinkedList {
    
    private Node head;
    private int size;
    
    LinkedList() {
        head = null;
        size = 0;
    }
    
    public void addNode(Node n, int i) {
        if (head == null) {
            head = n;
            ++size;
        } else {
            n.getParent().setNextAt(n, i);
            ++size;
        }
    }
    
    public void setHead(Node n) {head = n;}
    public Node getHead() {return head;}
    public int getSize() {return size;}
}
