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
	
	@Override
	public int size() {
		int counter = 0;
		ListIterator<T> li = listIterator();
		while(li.hasNext()) {
			li.next();
			counter++;
		}
		return counter;
	}

	@Override
	public boolean isEmpty() {
		return first == null;
	}

	@Override
	public boolean contains(Object o) {
		ListIterator<T> li = listIterator();
		while(li.hasNext()) {
			if(li.equals(o)) {
				return true;
			}
			li.next();
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
		MyLinkedList<String> ll = new MyLinkedList<String>();
		int size = ll.size();
		Object[] listArray = new Object[size];
		ListIterator<T> li = listIterator();
		for(int i = 0; i < size; i++) {
			listArray[i] = li;
			li.next();
		}
		return listArray;
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

	@Override
	public boolean remove(Object o) {
		ListIterator<T> li = listIterator();
		while(li.hasNext()) {
			if(li.equals(o)) {
				li.remove();
				return true;
			}
			li.next();
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

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		ListIterator<T> li = listIterator();
		while(li.hasNext()) {
			if(li.nextIndex() == index) {
				//Hier müsste noch etwas stehen
			}
			li.next();
		}
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

	@Override
	public T set(int index, T element) {
		ListIterator<T> li = listIterator();
		for(int i = 1; i < index; i++) {
			li.next();
		}
		li.set(element);
		return element;
	}

	@Override
	public void add(int index, T element) {
		/*ListIterator<T> li = listIterator();
		T elValue;
		for(int i = 1; i < index; i++) {
			li.next();
			
		}
		while(li.hasNext()) {
			elValue = ;
			li.set(element);
			li.next();
		}*/
		
		/*ListElement pushElement;
		ListElement ListElementValue = new ListElement(element);
		
		pushElement = getElement(index);
		getElement(index-1).next = ListElementValue;
		int m = 1;
		while(getElement(index+m)) {
			
		}*/
			
		//Leider keine Lösung gefunden
		
	}

	@Override
	public T remove(int index) {
		ListIterator<T> li = listIterator();
		for(int i = 1; i < index; i++) {
				li.next();
		}
		li.remove();
		return getElement(index).value;
	}

	@Override
	public int indexOf(Object o) {
		ListIterator<T> li = listIterator();
		int counter = 1;
		while(li.hasNext()) {
			if(li.equals(o)) {
				return counter;
			}
			counter++;
			li.next();
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		ListIterator<T> li = listIterator();
		int occurence = -1;
		int counter = 1;
		while(li.hasNext()) {
			if(li.equals(o)) {
				occurence = counter;
			}
			counter++;
			li.next();
		}
		return occurence;
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
		ll.add("Hallo1");
		ll.add("Welt1");
		ll.add("Hallo5");
		ll.add("Welt5");
		ll.add("Hallo8");
		ll.add("Welt8");
		
		ll.remove(3);
		System.out.println(ll.size());
		ll.get(0);

		for (String s : ll) {
			System.out.println(s);
		}
	}
}
