package queue_simulation;

import java.util.Arrays;

public class MyQueue<T> implements Cloneable, Comparable<MyQueue<T>> {
	
	// +++++++++++++++++++++++++++++ Instance Fields ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	private T[] array; // The container holding the objects

	private int index_pointer; // Indicator pointing at the end of the Queue. At the same time it represents the amount of objects currently in the queue.

	private int current_capacity; // Capacity of the array, not the number of held objects
	
	// +++++++++++++++++++++++++++++ Behaviour ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	@SuppressWarnings("unchecked")
	public MyQueue() {
		this.index_pointer = 0; 						// Always points at the first free slot at the end of the queue
		this.current_capacity = 10; 					// The number of slots in the current array
		this.array = (T[]) new Object[current_capacity]; // Since arrays can hold primitives generics cannot be applied directly and the workaround causes a warning
		} // end of constructor

	public void enqueue(T var) {
		if (index_pointer < current_capacity) {
			this.array[index_pointer] = var; 			// New object is added to the first free slot at the end of the queue
		} else {
			this.expandArray();
			this.array[index_pointer] = var;
		}
		this.index_pointer++; 							// Index_pointer must point at the first free slot in the array. The last item is to be found at (index_pointer - 1)
	} // end of enqueue() 

	public T dequeue() {
		if (!this.isEmpty()) {
			T temp = array[0];
			for (int i = 1; i < index_pointer; i++) {
				array[i - 1] = array[i];
			}
			this.array[index_pointer - 1] = null;
			this.index_pointer--;
			return temp;
		} else {
			return null;
		}
	} // end of dequeue()

	public int getSize() {
		return index_pointer; // index_pointer is always equal to the number of objects
	} // end of getSize()

	public String peep(int index) {
		if (index > index_pointer - 1) {
			throw new IndexOutOfBoundsException();
		}
		return array[index].toString(); // In order not to break encapsulation and because generics don't allow method calling only a string representation of the object is returned
	} // end of peep()

	public String showFirstElement() {
		return array[0].toString(); // In order not to break encapsulation and because generics don't allow method calling only a string representation of the object is returned return (T) array[0].copy();
	} // end of showFirstElement()

	public String showLastElement() {
		return array[index_pointer - 1].toString(); // In order not to break encapsulation and because generics don't allow method calling only a string representation of the object is returned
	} // end of showLastElement()

	public int getCapacity() {
		return this.current_capacity;
	} // end of getCapacity()

	private void expandArray() {
		this.current_capacity = this.current_capacity + 10; // Extends the capacity variable by 10
		array = Arrays.copyOf(array, current_capacity); // Copies the old array into a new array with the new capacity and references it back to the old variable
	} // end of expandArray()
	
	public boolean isEmpty() {
		boolean check = false;
		if (index_pointer == 0)
			return true;
		return check;
	} // end of isEmpty()

	@SuppressWarnings("unchecked")
	public void clear() {
		this.current_capacity = 10;
		this.array = (T[]) new Object[this.current_capacity];
		this.index_pointer = 0;
	} // end of clear()

	// ++++++++++++++++++++++++++++++++ Overridden Methods of Object ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	@Override
	public int hashCode() {
		int result;
		result = this.array.hashCode() + 7;
		result += this.index_pointer * 11;
		result += this.current_capacity * 13;
		return result * 17;
	} // end of hashCode()

	@Override
	public boolean equals(Object obj) {
		boolean check = true;
		if (this == obj) return true; // True if both variables point to the same location in memory (must be true because identical)
		if (obj == null || !this.getClass().getName().equals(obj.getClass().getName())) return false; // If obj is null or not of the same class as this return false (can't be true because non-existent or different class)
		@SuppressWarnings("unchecked")
		MyQueue<T> test = (MyQueue<T>) obj;
		if (this.getSize() != test.getSize()) return false; // Will return false if the number of objects in the array is different
		if (this.getCapacity() != test.getCapacity()) return false; // Will return false even if all values are equal (semantic equality), as the state of the object is different
		for (int i = 0; i < this.index_pointer; i++) {			// Only checks the non-null slots
			if (!this.peep(i).equals(test.peep(i))) {         // As peep() only returns the toString()-result of the object, reliabilty is somewhat limited
				check = false;
				break;
			}
		}
		return check;
	} // end of equals()

	public MyQueue<T> deepClone() throws CloneNotSupportedException {
		@SuppressWarnings("unchecked")
		MyQueue<T> obj = (MyQueue<T>) super.clone();
		obj.array = this.array.clone(); // Reference objects should be copied explicitly the rest it taken care of by the call to super()
		return obj;
	} // end of deepClone()

	@SuppressWarnings("unchecked")
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return (MyQueue<T>) super.clone();
	} // end of clone()	

	@Override
	public String toString() {
		return this.getClass().getName() + Arrays.toString(array);
	} // end of toString()
	
	
	@Override
	public int compareTo(MyQueue<T> obj) {
		if(obj == null || !obj.getClass().getName().equals(this.getClass().getName())) return 1;				
		if(this.getSize() == obj.getSize()) return 0;
		return (this.getSize() > obj.getSize()) ? 1 : -1;		
	} // end of compareTo()
} // end of class
