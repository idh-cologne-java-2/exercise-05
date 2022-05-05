package idh.java;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class MyLinkedList<T> implements List<T> {

	

	/**
	 * We only need to store the very first element of our list, 
	 * because it will now whether there is a next element.
	 */
	ListElement first;
	
	//the string array used internally
	private T[] data;
	
	//the next free, descriptive place in the array
	private int nextInsertPosition;
			
	/**
	 * indicates how many elements this list currently contains
	 * @return numbers of elements in this list
	 */
	@Override
	public int size() {
		return nextInsertPosition;
	}

	@Override
	public boolean isEmpty() {
		return first == null;
	}

	@Override
	public boolean contains(Object o) {
		for (int i = 0; i < ListElement.size(); i++) {
			if (o.toString().equals(ListElement.get().toString())) return true;
		}
		return false;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			ListElement next = first;
			
			@Override
			public boolean hasNext() {
				return next != null;
			}

			@Override
			public T next() {
				T ret = next.value;
				next = next.next;
				return ret;
			}
			
		};
	}

	@Override
	public Object[] toArray() {
	    this.data = data;
	    this.nextInsertPosition = data.length;
		return null;
	}

	@Override
	public <E> E[] toArray(E[] a) {
		if (a.length < size()) {
			a = (E[]) new Object[size()];
		}
		int i = 0;
		for (T t : this) {
			a[i++] = (E) t;
		}
		return a;
	}

	@Override
	public boolean add(T e) {
		ListElement newListElement = new ListElement(e);
		if (first == null)
			first = newListElement;
		else
			last().next = newListElement;
		return true;
	}

	/**
	 * this method removes a string from the list
	 */
	@Override
	public boolean remove(Object o) {
		for (int i = 0; i < nextInsertPosition; i++) 
		{
			if (data[i].equals(o)) {
				//here the other remove() method is used
				remove(i);
				//loop is aborted
				break;
			}
			
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object o : c)
			if (! contains(o))
				return false;
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		for (T t : c) 
			this.add(t);
		return true;
	}
	
    
	public boolean addAll1(int index, Collection<? extends T> c) {
		Object[] a = c.toArray();
		int numNew = a.length;
		int size = 0;
		ensureCapacityInternal(size + numNew);
		Object elementData = null;
		System.arraycopy(a, 0, elementData, size, numNew);
		size += numNew;
		return numNew != 0;
	}

	private void ensureCapacityInternal(int i) {
	}

	private void grow() {
	}

	private boolean full() {
		return data.length <= nextInsertPosition;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		boolean r = true;
		for (Object o : c) 
			r = r || this.remove(o);
		return r;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		first = null;
	}

	@Override
	public T get(int index) {
		return getElement(index).value;
	}
	
    /**
     * replace the string at the desired position with another one
     * @param index Index of the element to be replaced
     * @param element String to replace with
     */
	@Override
	public T set(int index, T element) {
		if (isIndexValid(index)) {
			data[index] = element;
		} else {
			System.err.println(index + "is not a valid index for this list:(");
		}
		return null;
	}

	private boolean isIndexValid(int index) {
		return index < nextInsertPosition;
	}

	/**
     * this method adds new string to the String list at the end
     * @param element the string to add
     */
	@Override
	public void add(int index, T element) {
		//check if there is enough space, if not enlarge
		if (full()) grow();
				
		//write the passed string to the next free position
		data[nextInsertPosition] = element;
				
		//increase the value for the next free place by 1
		nextInsertPosition++;
		return;
	}

	/**
	 * this method removes the string at position "index" from the list
	 */
	@Override
	public T remove(int index) {
		//is there an index at all? if not cancel
		if (!isIndexValid(index)) return null;
		
		//copy all elements one position forward from the given index
		for (int i = index; i < nextInsertPosition -1; i++) {
			data[i] = data[i + 1];
		}
		//decrease the value for the next free place by 1
		nextInsertPosition--;
		return null;
	}

	/**
	 * this method returns the index of the first element of the list
	 * that matches the given string
	 * @param s String whose index is to be searched
	 * @return Index of the searched string
	 */
	@Override
	public int indexOf(Object o) {
		for (int i = 0; i < nextInsertPosition; i++) {
			if (data[i].equals(o)) {
				//i is returned, method terminated
				return i;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		List<T> Obj = null;
		int lastIndexOf = Obj.lastIndexOf('o');
		return lastIndexOf;
	}

	@Override
	public ListIterator<T> listIterator() {
		return new ListIterator<T>() {

			ListElement previous = null;
			ListElement next = first;
			int index;
			
			@Override
			public boolean hasNext() {
				return next != null;
			}

			@Override
			public T next() {
				previous = next;
				T ret = next.value;
				next = next.next;
				index++;
				return ret;
			}

			@Override
			public boolean hasPrevious() {
				return false;
			}

			@Override
			public T previous() {
				throw new UnsupportedOperationException();
			}

			@Override
			public int nextIndex() {
				return index+1;
			}

			@Override
			public int previousIndex() {
				return index-1;
			}

			@Override
			public void remove() {
				previous.next = next.next;
			}

			@Override
			public void set(T e) {
				next.value = e;
			}

			@Override
			public void add(T e) {
				throw new UnsupportedOperationException();				
			}
			
		};
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException();
	}
	
	private class ListElement {
		
		T value;
		ListElement next;
		
		ListElement(T value) {
			this.value = value;
		}

		public static Object get() {
			// TODO Auto-generated method stub
			return null;
		}

		public static int size() {
			// TODO Auto-generated method stub
			return 0;
		}

		
	}
	
	/**
	 * Internal method that iterates over the list, returning the last element (i.e., the one whose next field is null)
	 * @return
	 */
	private ListElement last() {
		if (first == null)
			return null;
		ListElement current = first;
		
		while(current.next != null) {
			current = current.next;
		}
		return current;
	}
	
	/** 
	 * Internal method to get the list element (not the value) of the list at the specified index position.
	 * @param index
	 * @return
	 */
	private ListElement getElement(int index) {
		if (isEmpty()) 
			return null;
		ListElement current = first;
		while(current != null) {
			if (index == 0) 
				return current;
			index--;
			current = current.next;
		}
		return null;
	}

	public static void main(String[] args) {
		MyLinkedList<String> ll = new MyLinkedList<String>();
		ll.add("Hallo");
		ll.add("Welt");
		ll.get(0);
		for (String s : ll) {
			System.out.println(s);
		}
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
