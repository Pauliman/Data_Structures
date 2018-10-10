package queue_simulation;

public class Controller {
	
	
	
	public static void main(String[] args) throws CloneNotSupportedException {
		MyQueue<String> q = new MyQueue<String>();
		MyQueue<String> p = new MyQueue<String>();
		
		for (int i = 0; i < 31; i++) {
			q.enqueue("String" + i);		
		}
		p = (MyQueue<String>) q.deepClone(); // If simple clone() is used the array inside both objects will be identical. Only deepClone() provides a copy at a new location in memory 
		//p.dequeue();			// Uncomment to see the changes
		//p.enqueue("String1");	// Uncomment to see the changes
		System.out.println(q);
		System.out.println(p);	
		
		System.out.println("Q is equal to P: " +  q.equals(p));
		System.out.println("Size of Q and P:  " + q.getSize() + " : " + p.getSize() );
		System.out.println("Q == P: " +  (q == p));  								//Comparing different types is blocked by the compiler.
		System.out.println("HashCode of Q & P is equal: " + (q.hashCode() == p.hashCode()));
		System.out.println("Q is larger than P: " + (q.compareTo(p) == 0 ? "equal" : ((q.compareTo(p) < 0) ? "false" : "true")));
		
	}

}
