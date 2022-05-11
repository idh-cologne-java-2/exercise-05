package idh.java;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Arrays;


public class MyLinkedList<T> implements List<T> {

	/**
	 * We only need to store the very first element of our list, 
	 * because it will now whether there is a next element.
	 */
	ListElement prefirst = new ListElement(null);
	
	
	@Override
	public int size() {
		int i = 0;
		for (T x : this)
			i++;
		return i;
	}

	@Override
	public boolean isEmpty() {
		return prefirst.next == null;
	}

	@Override
	public boolean contains(Object o) {
		for (T x : this)
			if (o.equals(x))
				return true;
		return false;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			ListElement next = prefirst.next;
			
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
		return toArray(new Object[size()]);
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
		last().next = newListElement;
		return true;
	}

	@Override
	public boolean remove(Object o) {
		ListIterator<T> li = this.listIterator();
		boolean r = false;
		while(li.hasNext()) {
			T element = li.next();
			if (!r && element.equals(o)) {
				li.remove();
				r = true;
			}
		}
		return r;
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

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		
		ListElement first = null, previous = null, current= null;
		for (T x : c) {
			current = new ListElement(x);
			if (first == null) {
				first = current;
			} else {
				previous.next = current;
			}
			previous = current;
		}
		
		ListElement atPosition = getElement(index-1);
		if (atPosition == null) {
			return false;
		}
		current.next = atPosition.next;
		atPosition.next = first;
		return true;
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
		prefirst.next = null;
	}

	@Override
	public T get(int index) {
		try {
			return getElement(index).value;
		} catch (NullPointerException e) {
			throw new IndexOutOfBoundsException();
		}
	}

	@Override
	public T set(int index, T element) {
		ListElement le = getElement(index);
		T ret = le.value;
		le.value = element;
		return ret;
	}

	@Override
	public void add(int index, T element) {
		ListElement atPosition = getElement(index-1);
		ListElement newElement = new ListElement(element);
		newElement.next = atPosition.next;
		atPosition.next = newElement;
	}

	@Override
	public T remove(int index) {
		ListElement atPreviousPosition = getElement(index-1);
		T ret = atPreviousPosition.next.value;
		atPreviousPosition.next = atPreviousPosition.next.next;
		return ret;
	}

	@Override
	public int indexOf(Object o) {
		int i = 0;
		for (T x : this) {
			if (o.equals(x))
				return i;
			i++;
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		int lastIndex = -1;
		int index = 0;
		for (T x : this) {
			if (o.equals(x))
				lastIndex = index;
			index++;
		}
		return lastIndex;
	}

	@Override
	public ListIterator<T> listIterator() {
		return new ListIterator<T>() {

			ListElement previous = null;
			ListElement next = prefirst;
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
		ListElement current = prefirst;
		
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
		if (index == -1)
			return prefirst;
		ListElement current = prefirst.next;
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
}
