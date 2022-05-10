// Tim Schäfer 7380391
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
		int length = 1;
		
		if(first == null) {
			
			return 0;
			
		}
		
		ListElement current = first;
		while(current.next != null) {
			length++;
			current = current.next;
		}
		
		return length;
	}

	@Override
	public boolean isEmpty() {
		return first == null;
	}

	@Override
	public boolean contains(Object o) {
		ListElement current = first;
		while(current.next != null) {
			if(current.value == o) {
				return true;
			}
			current= current.next;
		}
		
		if(current.value == o) {
			return true;
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
		Object[] result = new Object[size()];
		
		ListElement current = first;
		int i = 0;
		while(current.next != null) {
			result[i] = current;
			i++;
			current = current.next;
		}
		return result;
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
		
		// wenn  nur ein object in der liste
		if(first != null && first.next == null) {
			if(first.value == o) {
				first = null;
				return true;
			}
		}
		//minestens zwei in der liste?
		if(first != null && first.next != null) {
			
		
			ListElement current = first.next;
			ListElement bevor = first;
		
		// ist dass erste Element das gesuchte?
			if(bevor.value == o) {
				if(current != null) {
					first = current;
					return true;
				}else {
					return false;
				}
			
			}
		// restliche liste wird durchlaufen 
			while(current.next != null) {
				if(current.value == o) {
					bevor.next = current.next; 
					return true;
				}
				
				bevor = current;
				current= current.next;
			
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

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		MyLinkedList<T> helpList = new MyLinkedList<T>();
		
		for (T t : c) { 
			helpList.add(t);
		}
		
		ListElement bevor = first;
		ListElement current = first.next;
		int i = 1;
		ListElement lastFromHelpList = helpList.last();

		if(index == 0) {
			this.first = helpList.first;
		}
		
		while(current.next != null && i < index) {
			current = current.next;
			bevor = bevor.next;
			i++;
		}
		bevor.next = helpList.first;
		lastFromHelpList.next = current;
		
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
		first = null;
	}

	@Override
	public T get(int index) {
		return getElement(index).value;
	}

	@Override
	public T set(int index, T element) {
		
		ListElement current = first;
		T changedObject;
		int i = 0; 
		
		while(current.next != null && i < index) {
			current = current.next;
			i++;
		}
		
		changedObject = current.value;
		current.value = element;
		
		return changedObject;
	}

	@Override
	public void add(int index, T element) {
		ListElement newElement = new ListElement(element);
		
		ListElement bevor = first;
		ListElement current = first.next;
		int i = 1;
		
		if(index == 0) {
			newElement.next = first;
			first = newElement;
			return;
		}
		
		while(current.next != null && i < index) {
			current = current.next;
			bevor = bevor.next;
			i++;
		}
		
		newElement.next = current;
		bevor.next = newElement;
		
		
	}

	@Override
	public T remove(int index) {
		
		ListElement current = first.next;
		ListElement bevor = first;
		
		if(index == 0) {
			first = current;
			return bevor.value;
		}
		
		int i = 1;
		
		while(current.next != null && i < index) {
			current = current.next;
			bevor = bevor.next;
			i++;
		}
		
		bevor.next = current.next;
		return current.value;
	}

	@Override
	public int indexOf(Object o) {
		
		ListElement current = first;
		int i = 0;
		
		while(current.next != null) {
			if(current.value != o) {
				i++;	
			}else {
				return i;
			}
			current = current.next;
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		int index = -1;
		int zaehler = 0;
		ListElement current = first;
		
		while(current.next != null) {
			if(current.value == o) {
				index = zaehler;
			}
			current = current.next;
			zaehler++;
		}
		
		if(current.value == o) {
			index = zaehler;
		}
		
		return index;
		
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
		ll.add("kannst");
		ll.add("du");
		ll.add("mich");
		ll.add("Welt");
		ll.add("hoeren");
		ll.add("Welt");
		ll.add("hoeren");
		ll.add(2,"All");
		ll.get(0);
		ll.set(3, "koennen");
		ll.remove(4);
		ll.remove("du");
		
		for (String s : ll) {
			System.out.println(s);
		}
		
		System.out.println(ll.size());
		System.out.println(ll.indexOf("Welt"));
		System.out.println(ll.contains("hoeren"));
		System.out.println(ll.lastIndexOf("Welt"));
		
	}
}
