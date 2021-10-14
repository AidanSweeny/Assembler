import java.util.Iterator;    // defines the Iterator interface
import java.util.ArrayList;   
import java.util.Collections; // contains a shuffle function
import java.util.Random;

public class LinkedList<T> implements Iterable<T> {
    private class LLIterator implements Iterator<T>{
        //Private Iterator class 
        private Node n;
        public LLIterator(Node head){
            this.n = head;
        }

        //Returns if there is a next node
        public boolean hasNext(){
            return(this.n != null);
        }

        //Returns what the next node is
        public T next(){
            T val = n.getThing();
            n = n.next;
            return val;
        }

        //Optional
        public void remove(){

        }

    }
    //Makes the iterator run
    public Iterator<T> iterator() {
        return new LLIterator( this.head);
        }
    //Private node class
    private class Node{
        Node next;
        T obj;

        //Node constructor
        public Node(T item){
            this.next = null;
            obj = item;
        }

        //Returns the object assigned to the node
        public T getThing(){
            return obj;
        }

        //Set the next node
        public void setNext(Node n){
            this.next = n;
        }

        //Return the next node
        public Node getNext(){
            return this.next;
        }
        
    }
    private Node head;
    private int size;

    //linked list constructor
    public LinkedList(){
        head = null;
        size = 0;
    }

    //Clears the list
    public void clear(){
        head = null;
        size = 0;
    }

    //Returns the size
    public int size(){
        return size;
    }

    //Add an item to the head of the list
    public void addFirst(T item){
        Node n = new Node(item);
        n.setNext(head);
        head = n;
        size++;
    }

    //Add an item to the end of the list
    public void addLast(T item){
        Node n = new Node(item);
        n.setNext(null);
        Node thisN = head;
        if (size ==0){
            addFirst(item);
        }
        else{
        for (int i=0; i<size; i++){
            if (thisN.getNext() != null){
                thisN = thisN.getNext();
            }
            else{
                thisN.setNext(n);
                size++;
            }
        }
        }
    }

    //Add an item anywhere in the list
    public void add(int index, T item){
        Node n = new Node(item);
        Node thisN = head;

        if (index == 0){
            addFirst(item);
        }
        else if (index == size-1){
            addLast(item);
        }
        else{
            for (int i=1; i<index; i++){
                thisN = thisN.getNext();
             }
             n.setNext(thisN.getNext());
             thisN.setNext(n);
            size++;
        }
    }

    //Remove an item from the list
    public T remove(int index){
        Node thisN = head;
        if (index == 0){
            Node headz = head;
            head = head.getNext();
            return headz.getThing();
        }

        for (int i=1; i<index; i++){
            thisN = thisN.getNext();
        }
        Node nextN = thisN.getNext();
        thisN.setNext(nextN.getNext());
        size--;
        return nextN.getThing();
    }

    //Returns the linked list as an arrayList
    public ArrayList<T> toArrayList(){
        Node thisN = head;
        ArrayList<T> returnList = new ArrayList<T>();  
        if (size == 0){
            return null;
        }
        for (int i=0; i<this.size; i++){
            returnList.add(thisN.getThing());
            thisN = thisN.getNext();
        }
        return returnList;
    }

    //Shuffle the arraylist
    public ArrayList<T> toShuffledList(){
        ArrayList<T> list = toArrayList();
        Collections.shuffle(list);
        return list;
    }
}