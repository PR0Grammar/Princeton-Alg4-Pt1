import edu.princeton.cs.algs4.StdIn;
import java.util.Iterator;

public class Permutation{
    public static void main(String[] args){
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        int k = Integer.parseInt(args[0]); // k is the number of strings printed, where 0 <= k <= N

        //Linear to the size of the input
        while(!StdIn.isEmpty()){
            queue.enqueue(StdIn.readString());
        }
        
        System.out.println("Size: " + queue.size()); //Size should be at most N, where N is the number of strings on StdIn
        
        Iterator queueIter = queue.iterator();

        for(int i = 0; i < k ; i++){
            System.out.println(queueIter.next());
        }
    }
}
