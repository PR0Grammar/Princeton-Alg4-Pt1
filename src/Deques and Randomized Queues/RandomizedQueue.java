import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.NullPointerException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	Item queue[];
	int N; //size
	int capacity;

   public RandomizedQueue() {
	   capacity = 1;
	   N = 0;
       queue = (Item[]) new Object[capacity];
   }
   public boolean isEmpty() {
	   return N == 0;
   }             
   
   public int size(){
	   return N;
   } 
   
   public void enqueue(Item item) {
	   if(item == null) {
		   throw new NullPointerException();
       }
       //If array is full
       if(N == capacity){
        resize(capacity * 2);
       }
       queue[N] = item;
       N++;
   } 
   
   public Item dequeue() {
	   if(isEmpty()) {
		   throw new NoSuchElementException();
       }
       int index = StdRandom.uniform(N);
       Item removedItem = queue[index];
       //Set the value of the index to the last element in the queue
       queue[index] = queue[N-1];
       queue[N-1] = null;
       N--;
       if((capacity/4) == N){
           resize(capacity/2);
       }

       return removedItem;
   }

   //Returns a random item from the queue but does not remove
   public Item sample() {
	   if(isEmpty()) {
		   throw new NoSuchElementException();
	   }
	   return queue[StdRandom.uniform(N)];
   }
   
   private void resize(int newSize) {
       Item[] copyQueue = (Item[]) new Object[newSize];
       
       for(int i = 0; i < N ; i++){
           copyQueue[i] = queue[i];
       }

       capacity = newSize;
       queue = copyQueue;
   }

   public Iterator<Item> iterator(){
	   return new RandomizedQueueIterator();
   }
   
   private class RandomizedQueueIterator implements Iterator{
        int current;

        RandomizedQueueIterator(){
            current = 1;
        }

        public boolean hasNext(){
            return queue[current] != null;
        }
        public Item next(){
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            Item item = queue[current];
            current++;
            return item;
        }
        public void remove(){
            throw new UnsupportedOperationException();
        }
   }

   //Test
   public static void main(String[] args){
       RandomizedQueue<String> rq = new RandomizedQueue<String>();

       System.out.println("Queue Size: " + rq.size()); //0
       System.out.println("Queue is Empty: " + rq.isEmpty()); //true

       rq.enqueue("Hello");
       rq.enqueue("Java");
       rq.enqueue("World");

       System.out.println("Queue Size: " + rq.size()); // 3
       System.out.println("Queue is Empty: " + rq.isEmpty()); //false

       System.out.println("Sample Queue:" + rq.sample());
       System.out.println("Queue Size: " + rq.size()); //Should remain 3
       System.out.println("Removed Item: " + rq.dequeue());
       System.out.println("Queue Size: " + rq.size()); //Should be 2

       rq.enqueue("The");
       rq.enqueue("Hex");
       rq.enqueue("Decimal");
       rq.enqueue("Binary");
       rq.enqueue("End");

       System.out.println("Queue Size: " + rq.size()); //Should be 7

       Iterator rqIter = rq.iterator();

       while(rqIter.hasNext()){
           System.out.print(rqIter.next() + " ");
       }
   }
}