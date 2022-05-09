package idh.java;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;


public class MyLinkedList<T> implements List<T> {

	/**
	 * We only need to store the very first element of our list, 
	 * because it will now whether there is a next element.
	 */
	ListElement first;
	
	
	/**
	 *  It returns the number of elements of the list.
	 */
	
	@Override
	public int size() {
		// TODO Implement!
		int size = 0;
		if (first == null) {
			return size = 0;
		}
		ListElement current = first;
		while (current.next != null) {
			size++;
			current = current.next;
		}
		return size;
	}

	@Override
	public boolean isEmpty() {
		return first == null;
	}

	
	/**
	 * It checks whether the given item is present in the list or not.
	 *  If the item is present then it returns true else false.
	 */
	
	@Override
	public boolean contains(Object o) {
		// TODO Implement!
		ListElement current = first;

		while (current.next != null) {
			if (current.equals(o))
				return true;
			current = current.next;
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

	/**
	 * Returns an array containing all of the elements in this
	 *  list in proper sequence (from first to last element).
	 */
	
	@Override
	public Object[] toArray() {
		// TODO Implement!
		Object[] objArr = new Object[this.size()];
		ListElement current = first;
		for (int i = 0; i < objArr.length; i++) {
			while (current.next != null) {
				current = current.next;
				objArr[i] = this.get(i);
			}
		}

		return objArr;
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
	 * It removes the specified object from the list.
	 * Returns true if this list contained the specified element
	 */
	
	@Override
	public boolean remove(Object o) {
		// TODO: Implement
		if (this.contains(o)) {
			o = null;
			return true;
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

	/**
	 * It adds all the elements of collection c to
	 * the list starting from a give index in the list.
	 * It throws NullPointerException if the collection c
	 * is null and IndexOutOfBoundsException when the
	 * specified index is out of the range.
	 */
	
	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		// TODO Implement!
		return false;
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
	 * It updates the item of specified index with the give value.
	 */
	
	@Override
	public T set(int index, T element) {
		// TODO: Implement
		if (first == null) {
			throw new NoSuchElementException(); 
		}else {
			ListElement current = first ; 
			while(current.next != null && index > 0 ) {
				current = current.next;  
				index--; 
			}
			if(index == 0)
			current.value = element; 
			
		}
		
		return null;
	}

	/**
	 *  It adds an item at the given index of the the list.
	 */
	
	@Override
	public void add(int index, T element) {
		// TODO: Implement
		first = new ListElement(element);
	}

	/**
	 * It removes the item from the list which is present at the specified index.
	 */
	
	@Override
	public T remove(int index) {
		// TODO: Implement
		ListElement current = first;
		int n = 0; 
		while(current.next != first) {
			if(n == index) {
				current.value = null; 
				return (T) current; 
			}
			
			n++; 
			current = current.next; 
		}
		return null;
	}

	/**
	 *  It returns the index of the specified item.
	 */
	
	@Override
	public int indexOf(Object o) {
		// TODO: Implement
		ListElement current = first;
		int number = 0; 
		while(current.next != null) {
			if( current.value == o) {
				return number; 
			}
			number++; 
			current = current.next; 
		}
			return -1;
	}

	/**
	 * It returns the index of last occurrence of the specified element.
	 */
	
	@Override
	public int lastIndexOf(Object o) {
		// TODO: Implement
		ListElement current = first; 
		int i= 0 ; 
		while(current.next != null) {
			if(current.value == o) {
				if(current.next.value == o) {
					return i+1;
				}
				return i; 
			}
			i++; 
			 
		}
		return 0;
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
		//System.out.println(ll.size());
		//System.out.println(ll.contains("World"));
		for (String s : ll) {
			System.out.println(s);
		}
	}
}
