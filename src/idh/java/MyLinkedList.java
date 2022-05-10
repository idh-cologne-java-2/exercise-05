package idh.java;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyLinkedList<T> implements List<T> {

	/**
	 * We only need to store the very first element of our list, because it will
	 * know whether there is a next element.
	 */
	ListElement first;

	/**
	 * Like method last() this method iterates over the list. Int size serves as a
	 * counter that increases per iteration
	 */
	@Override
	public int size() {
		int size = 0;
		if (first == null) {
			return size = 0;
		}
		ListElement current = first;
		while (current.next != null) {
			current = current.next;
			size++;
		}
		return size;
	}

	@Override
	public boolean isEmpty() {
		return first == null;
	}

	/**
	 * iterates over Listelements and checks, if current element equals the given
	 * element
	 */
	@Override
	public boolean contains(Object o) {
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
	 * Returns an array containing all of the elements in this list in proper
	 * sequence (from first to last element) by iterating over ListElements
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
	 * Removes first occurrence of the specified element from this list, if it is
	 * present (optional). If this list does not contain the element, it is
	 * unchanged.
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
			if (!contains(o))
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
	 * Inserts all of the elements in the specified collection into this list at the
	 * specified position (optional operation).
	 */
	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		// TODO Implement!
		for (T t : c)
			this.add(index++, t);
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

	/**
	 * Replaces the element at the specified position in this list with the
	 * specified element (optional operation).
	 */
	@Override
	public T set(int index, T element) {
		// TODO: Implement
		return getElement(index).value = element;
	}

	@Override
	public void add(int index, T element) { //Kein plan, obs funzt
		// TODO: Implement
		ListElement newListElement = new ListElement(element);

		if (first == null)
			first = newListElement;
		else
			for (int j = 0; j < this.size(); j++) {
				if(j==index) {
					this.remove(j);
					this.add(element);
				}
			}
			
	}

	/**
	 *Removes the element at the specified position in this list (optional operation). 
	 *Shifts any subsequent elements to the left (subtracts one from their indices). 
	 *Returns the element that was removed from the list.
	 */
	@Override
	public T remove(int index) {
		// TODO: Implement
		T current = (T) first;
		for (int i = 0; i < this.size(); i++) {
			while(current != null) {
				if(i==index) {
					this.get(i);
					this.set(i--, null);
				}
			}			
		}
		return current;

	}

	@Override
	public int indexOf(Object o) {//I'm lost
		// TODO: Implement
		int index = 0;
		for (int j = 0; j < this.size(); j++) {
			if (this.contains(o))
				index = j;		
		}
		return index;
	}

	@Override
	public int lastIndexOf(Object o) { //guess this could work? 
		// TODO: Implement
		int index = 0;
		if (first == null)
			return 0;
		ListElement current = first;

		while (current.next != null) {
			current = current.next;
			index++;
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
				return index + 1;
			}

			@Override
			public int previousIndex() {
				return index - 1;
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
	 * Internal method that iterates over the list, returning the last element
	 * (i.e., the one whose next field is null)
	 * 
	 * @return
	 */
	private ListElement last() {
		if (first == null)
			return null;
		ListElement current = first;

		while (current.next != null) {
			current = current.next;
		}
		return current;
	}

	/**
	 * Internal method to get the list element (not the value) of the list at the
	 * specified index position.
	 * 
	 * @param index
	 * @return element
	 */
	private ListElement getElement(int index) {
		if (isEmpty())
			return null;
		ListElement current = first;
		while (current != null) {
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
