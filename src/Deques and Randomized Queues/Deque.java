import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.NullPointerException;

public class Deque<Item> implements Iterable<Item> {
   private Node first, last;
   private int n;
   
   public Deque() {
	   first = null;
	   last = null;
	   n = 0;
   }
   
   private class Node {
	   Item item;
	   Node next;
	   Node prev;
   }
   
   public boolean isEmpty() {
	   return n == 0;
   }
   
   public int size() {
	   return n;
   }  
   
   public void addFirst(Item item) {
	   if (item == null) {
		   throw new NullPointerException();
	   }
	   Node oldFirst = first;
	   first = new Node();
	   first.item = item;
	   first.next = oldFirst;
	   
	   if(isEmpty()) {
		   last = first;
	   }else {
		   oldFirst.prev = first;
	   }
	   
	   n++;
   }
   
   public void addLast(Item item) {
	   if (item == null) {
		   throw new NullPointerException();
	   }
	   Node oldLast = last;
	   last = new Node();
	   last.item = item;
	   last.next = null;
	   
	   if(isEmpty()) {
		   first = last;
	   }else {
		   oldLast.next = last;
	   }
	   
	   n++;
   }
   
   public Item removeFirst() {
	   if(isEmpty()) {
		   throw new NoSuchElementException();
	   }
	   Node oldFirst = first;
	   if(n == 1) {
		   first = null;
		   last = null;
	   }else {
		   first = first.next;
		   first.prev = null;
	   }
	   n--;
	   return oldFirst.item;
   }              
   
   public Item removeLast() {
	   if(isEmpty()) {
		   throw new NoSuchElementException();
	   }
	   Node oldLast = last;
	   if(n == 1) {
		   first = null;
		   last = null;
	   }else {
		   last = last.prev;
		   last.next = null;
	   }
	   n--;
	   return oldLast.item;
   } 
   

   
   public Iterator<Item> iterator(){
	   return new ListIterator();
   }     
   
   private class ListIterator implements Iterator <Item>{ 
	   private Node current;
	   
	   public ListIterator(){
		current = first;
	   }
	   public boolean hasNext() {return current !=null;}
	   public Item next() {
		   if(!hasNext()){
		   	throw new NoSuchElementException();
	           }
		   Item item = current.item;
		   current = current.next;
		   return item;
	   }
   }
   
}
