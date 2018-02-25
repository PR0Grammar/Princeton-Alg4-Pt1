import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.NullPointerException;

public class Deque<Item> implements Iterable<Item> {
   private Node first, last;
   private int n;

   private class Node {
	Item item;
	Node next;
	Node prev;

	//first.prev and last.next should always be null
	Node(){
		this.next = null;
		this.prev = null;
	}
   
   }

   //Constructs empty deque
   public Deque() {
	   first = null;
	   last = null;
	   n = 0;
   }

   //Checks if there are no nodes
   public boolean isEmpty() {
	   return n == 0;
   }
   
   //Returns number of nodes
   public int size() {
	   return n;
   }  
   
   public void addFirst(Item item) {
	   if (item == null) {
		   throw new NullPointerException();
	   }
	   Node oldFirst = this.first;
	   this.first = new Node();
	   this.first.item = item;
	   this.first.next = oldFirst;
	   
	   //If this is the first node, first = last
	   if(isEmpty()) {
		   this.last = this.first;
	   }
	   else {
		   oldFirst.prev = this.first;
	   }
	   
	   n++;
   }
   
   public void addLast(Item item) {
	   if (item == null) {
		   throw new NullPointerException();
	   }
	   Node oldLast = this.last;
	   this.last = new Node();
	   this.last.item = item;
	   this.last.prev = oldLast;

		//If this is the first node, first = last
	   if(isEmpty()) {
		   this.first = this.last;
	   }
	   else {
		   oldLast.next = this.last;
	   }
	   
	   n++;
   }
   
   public Item removeFirst() {
	   if(isEmpty()) {
		   throw new NoSuchElementException();
	   }
	   Node oldFirst = this.first;
	   if(n == 1) {
		   this.first = null;
		   this.last = null;
	   }
	   else {
		   this.first = this.first.next;
		   this.first.prev = null;
	   }
	   n--;
	   return oldFirst.item;
   }              
   
   public Item removeLast() {
	   if(isEmpty()) {
		   throw new NoSuchElementException();
	   }
	   Node oldLast = this.last;
	   if(n == 1) {
		   this.first = null;
		   this.last = null;
	   }
	   else {
		   this.last = this.last.prev;
		   this.last.next = null;
	   }
	   n--;
	   return oldLast.item;
   } 
   

   
   public Iterator<Item> iterator(){
	   return new DequeIterator();
   }     
   
   private class DequeIterator implements Iterator <Item>{ 
	   private Node current;
	   
	   //Starts with the first node
	   public DequeIterator(){
		this.current = first;
	   }
	   public boolean hasNext() {return current !=null;}
	   public void remove(){throw new UnsupportedOperationException();}  
	   public Item next() {
		   if(!hasNext()){
		   	throw new NoSuchElementException();
	        }
		   Item item = current.item;
		   current = current.next;
		   return item;
	   }
   }
   

   //Test
   public static void main (String[] args){
	   Deque<Integer> deque= new Deque<Integer>();

		//Inital size
	   System.out.println("deque empty?: " + deque.isEmpty());
	   System.out.println("deque size: " + deque.size());
   
	   deque.addFirst(3);
	   deque.addFirst(2);
	   deque.addFirst(1);

	   System.out.println("deque size: " + deque.size());
	   //System.out.println("first node: " + deque.next());

	  System.out.println("Last node removed: " + deque.removeLast());
	  System.out.println("First node removed: " + deque.removeFirst());

	  System.out.println("deque size: " + deque.size());

	  deque.addLast(6);
	  deque.addFirst(5);
	  deque.addLast(10);

	  System.out.println("deque size: " + deque.size());

	  //Expected output: 5 -> 2 -> 6 -> 10
	  Iterator deqIter= deque.iterator();
	  while(deqIter.hasNext()){
		System.out.println(deqIter.next());
	  }
	}
}
