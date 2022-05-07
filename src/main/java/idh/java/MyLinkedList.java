package idh.java;

import java.util.*;

public class MyLinkedList<T> implements List<T> {

    /**
     * We only need to store the very first element of our list,
     * because it will now whether there is a next element.
     */
    ListElement first;
    int size = 0;

    @Override
    public int size() {
        return size;

    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public boolean contains(Object o) {
        for (T t : this) {
            if (t == o) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
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
		/*
		Object[] objArray = new Object[size];

		Iterator<T> it = iterator();

		for (int i = 0; i < size; i++){
			objArray[i] = it.next();

			//objArray[i] = getElement(i);
		}
		return objArray;
		 */
        return toArray(new Object[size]);
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
        size++;
        return true;
    }

    @Override
    public boolean remove(Object toRemove) {
        ListElement previous;

        if (first == toRemove) {
            first = first.next;
            size--;
            return true;
        }
        previous = first;
        for (int i = 1; i < size; i++) {
            ListElement current = previous.next;
            if (current == toRemove) {
                previous.next = current.next;
                size--;
                return true;
            }
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

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {

        for (T t : c) {
            this.add(index++, t);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {

        boolean r = false;
        for (Object o : c) {
            if (this.remove(o)) {
                r = true;
            }
        }
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
        ListElement current = first;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                return current.value;
            }
            current = current.next;

        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public T set(int index, T element) {
        ListElement current = first;

        for (int i = 0; i < size; i++) {
            if (i == index) {

                T returnElement = current.value;
                current.value = element;
                return returnElement;

            }
            current = current.next;

        }
        throw new IndexOutOfBoundsException();

    }

    @Override
    public void add(int index, T element) {

        if (index== size){
            add(element);
            return;
        }

        if(index > size)
        {
            throw new IndexOutOfBoundsException();
        }

        ListElement current = first;

        ListElement toAdd = new ListElement(element);

        if (index == 0){
            first = toAdd;
            first.next = current;
            size++;
            return;
        }

        for(int i = 0; i < size; i++)
        {
            if(i==index){
                toAdd.next = current.next;
                current.next = toAdd;
                size++;
                return;


            }
            current = current.next;
        }



    }

    @Override
    public T remove(int index) {

        T ret;
        if (index == 0){
            ret = first.value;
            first = first.next;
            size--;
            return ret;
        }
        ListElement previous;
        ListElement current;

        previous = first;
        current = previous.next;

        //fange bei 1 an, da Sonderfall i=0 abgehandelt
        for(int i = 1; i< size; i++){

           if(index == i){
               ret = current.value;
               previous.next = current.next;
               size--;
               return ret;

           }
           previous = current;
           current = current.next;

        }
        throw new IndexOutOfBoundsException();


    }

    @Override
    public int indexOf(Object o) {
        ListElement listElement = first;

        for (int i = 0; i < size; i++){
            if(listElement.value.equals(o)){
                return i;
            }
            listElement = listElement.next;
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {

        ListElement listElement = first;

        int highestOccurrence = -1;

        for (int i = 0; i < size; i++){
            if(listElement.value.equals(o)){
                highestOccurrence = i;
            }
            listElement = listElement.next;
        }

        return highestOccurrence;
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
     * Internal method that iterates over the list, returning the last element (i.e., the one whose next field is null)
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
     * Internal method to get the list element (not the value) of the list at the specified index position.
     *
     * @param index
     * @return
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

        MyLinkedList<String> ll = new MyLinkedList<>();

        ll.add("Hallo");
        ll.add("Welt");

      String st = ll.getElement(0).value;
        System.out.println(st);



        for (String s : ll) {
            System.out.println(s);
        }
    }
}
