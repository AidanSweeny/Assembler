//Katie Andre, Aidan Sweeny
//Linked List Stack

import java.util.Iterator;   
import java.util.ArrayList;  
import java.util.Collections; 
import java.util.Random;

/*
*The below is a Linked List based Stack, with a head and a size as private fields. We use the help of an 
*iterator class that will allow us to loop through the Stack, but only make changes to the top of the stack,
*also known as the head. 
*/
public class LLStack<T> implements Iterable<T>{
    private Node head;
    private int size;

    //The below is an iterator that allows us to move from node to node. 
    private class LLIterator implements Iterator<T>{
        private Node next;
        //Iterator constructor that allows us to set the next value. Initialize to head. 
        public LLIterator(Node head){
            this.next = head;
        }
        //returns whether or not there is a next item.
        public boolean hasNext(){
            if(this.next == null){
                return false;
            }
            return true;
        }
        
        //goes to the next node and returns the current node. 
        public T next(){
            T nextUp = this.next.getThing();
            this.next = this.next.getNext();
            return nextUp;
        }
    }

    /*The class Node allows us to define a Node in our LLStack. Every Node has a value inside of it of
    *arbitrary type, along with a next Node.*/      
    private class Node{
        private Node next;
        private T item;
        
        //The constructor creates a node with a null next node and a specified item.
        public Node(T item){
            this.next = null;
            this.item = item;
        }
        //returns the value inside the node.
        public T getThing(){
            return this.item;
        }
        //sets the value inside the node. 
        public void setThing(T item2){
            this.item = item2;
        }
        //sets the next node. Requires a node as a parameter. 
        public void setNext(Node n){
            this.next = n;
        }
        //returns the next Node. 
        public Node getNext(){
            return this.next;
        }
        
    }
    //constructor for our stack, sets the head to null and the size to 0. 
    public LLStack(){
        this.head = null;
        this.size = 0;
    }

    //returns the size of the LLStack.
    public int size(){
        return this.size;
    }
    
    //clears the stack by setting the head to null and the size to 0. 
    public void clear(){
        this.head = null;
        this.size = 0;
    }
    
    //pushes a value into the stack by creating a node, putting a value in the node, and setting the node as the head (former head as the next node).
    public void push(T item){
        Node temp = new Node(item);
        temp.setNext(this.head);
        this.head = temp;
        this.size++;
    }

    //pops the current head node and returns the value. 
    public T pop(){
        Node temp = this.head;
        this.head = this.head.getNext();
        this.size--;
        return temp.getThing();
    }

    //allows us to access the current value in the head node, but does nothing with it. 
    public T peek(){
        return this.head.getThing();
    } 
        
    //creates a new iterator and returns it. 
    public Iterator<T> iterator(){
        LLIterator iterator = new LLIterator(this.head);
        return iterator;
    }
    
    //allows us to access a specific index in our stack, returns null if the index does not exist. 
    public T get(int i){
        Node current = this.head;

        if (i >= this.size){
            return null;
        }
        for (int v = 0; v < i; v++){
            current = current.getNext();
        }
        return current.getThing();
    }

    public String inToPost(String infix){
        String[] parts = infix.split(" ");
        for (String part : parts) {
            this.push((T)part);
        }

        Iterator it = this.iterator();
        String answ = "";
        while(it.hasNext()){
            answ += it.next();
        }

        return answ;
    }

    //returns the stack as a string with all values. 
    public String toString() {
        if( this.head == null ) {
            return "<>";
        }
        String result = "";
        Node current = this.head;
        while( current != null ) {
            result += current.getThing().toString() + ", ";
                current = current.next;

        }

        result = "<" + result.substring(0,result.length()-2) + ">";
        return result;  
    }
    //Main function. We test with integers, strings, and floats. 
    public static void main(String args[]) {
        LLStack stk = new LLStack();
        for (int i = 0; i < 10; i++){
            stk.push(i);
        }
        System.out.println("\nTEST 1: INTEGERS\n");
        System.out.println("Stack with 10 numbers in reverse order:");
        System.out.println(stk);

        System.out.println("Popping the first 5 numbers:");
        for (int i = 0; i <5; i++){
            stk.pop();
        }
        System.out.println(stk);

        System.out.println("Peeking, number should be 4:");
        System.out.println(stk.peek());
        System.out.println("Now, peeking at index 3, should be 1:");
        System.out.println(stk.get(3));

        System.out.println("\nTEST2: MIXED TYPES\n");

        stk.clear();

        ArrayList<String> word  = new ArrayList<String>();
        word.add("K");
        word.add("C");
        word.add("A");
        word.add("T");
        word.add("S");

        System.out.println("Our new stack should say stack 2.0:");
        stk.push(2.0);

        for (String str: word){
            stk.push(str);
        }

        System.out.println(stk);

        System.out.println("Popping two letters, now it says ack 2.0:");
        stk.pop();
        stk.pop();
        System.out.println(stk);

        System.out.println("Peeking should be: A");
        System.out.println(stk.peek());

        System.out.println("And size should be 4:");
        System.out.println(stk.size());

        System.out.println("Clearing, now it should be empty:");
        stk.clear();
        System.out.println(stk);

        System.out.println("Now, size should be 0:");
        System.out.println(stk.size());

        System.out.println("Infix to postfix: ");
        System.out.println(stk.inToPost("(AB) * (CD)"));
    }

}   