
public class ResizingArrayQueueOfStrings {
	String[] q;
	int head; //Index of first element
	int tail; //Index of next open
	int n; //Number of elements
	
	public ResizingArrayQueueOfStrings() {
		 q = new String[1];
		 tail = 0;
		 head = 0;
		 n = 0;
	}
	
	public boolean isEmpty() {
		return (n == 0);
	}
	
	public int size() {
		return n;
	}
	
	public void enqueue(String item) {
		if(tail == q.length)
			resize(2*q.length);
		q[tail++] = item;
		n++;
		
	}
	
	public String dequeue() {
		if(isEmpty())
			return "ERR: Queue is Empty";
		String item = q[head++];
		q[head-1] = null;
		n--;
		//To avoid thrashing, only change at 1/4 full
		if(n>0 && n == q.length/4)
			resize(q.length/2);
		return item;
	}
	
	private void resize(int capacity) {
		String[] copy = new String[capacity];
		
		for(int i = 0; i < n; i++) {
			copy[i] = q[(head + i)];
		}
		
		head = 0;
		tail = n;
		q = copy;
	}
	
}
