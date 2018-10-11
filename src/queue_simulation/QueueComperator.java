package queue_simulation;

import java.util.Comparator;

public class  QueueComperator<T> implements Comparator<MyQueue<T>>{

	
	@Override
	public int compare(MyQueue<T> o1, MyQueue<T> o2) {
		int result = 0;
		int value = 0;
		int size = o1.getSize() - o2.getSize(); // Sorts the Queue-objects in ascending order		
		if(size != 0) {
			result = size;
		} else {
			for(int i = 0; i < o1.getSize(); i++) {
				if(!o1.peep(i).equals(o2.peep(i))) {
					result = o1.peep(i).compareTo(o2.peep(i));
					break;				
				}				
			}
		}		
		return result;
	}

} // end of class
