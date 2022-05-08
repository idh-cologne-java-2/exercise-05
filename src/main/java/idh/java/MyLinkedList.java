package idh.java;

import java.util.*;

public class MyLinkedList<T> implements List<T> {

    /**
     * We only need to store the very first element of our list,
     * because it will now whether there is a next element.
     */
    ListElement first;
    int size = 0;

    //gibt die Größe der Liste zurück
    @Override
    public int size() {
        return size;

    }

    @Override
    //falls das erste ListElement nicht existiert, dann ist die Liste leer
    public boolean isEmpty() {
        return first == null;
    }

    //fragt, ob ein bestimmtes Object in der Liste enthalten ist
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

            //gibt nächstes Element, wenn Nächstes nicht gleich null ist
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
        //neues ListElement der Liste anhängen
        ListElement newListElement = new ListElement(e);

        //an den Anfang der Liste hängen
        if (first == null)
            first = newListElement;
        else
            //hinten anhängen
            last().next = newListElement;
        //Größe der Liste erhöhen
        size++;
        return true;
    }

    @Override
    public boolean remove(Object toRemove) {
        ListElement previous;

        //Fall, wenn Object am Anfang der Liste entfernt werden soll
        if (first.value == toRemove) {
            first = first.next;
            size--;
            return true;
        }
        //Fall 1 abgehandelt, dann ist das 1. Element dann der Previous
        previous = first;

        for (int i = 1; i < size; i++) {

            ListElement current = previous.next;
            if (current.value == toRemove) {
                previous.next = current.next;
                size--;
                return true;
            }
        }
        return false;

    }

    @Override
    //enhält die Collection c alle Elemente (Objekte) von meiner Liste?
    public boolean containsAll(Collection<?> c) {
        for (Object o : c)
            if (!contains(o))
                return false;
        return true;
    }

    @Override
    //Alle Elemente aus der Collection an meine Liste anhängen?
    public boolean addAll(Collection<? extends T> c) {
        for (T t : c)
            this.add(t);
        return true;
    }

    @Override
    //Alle Elemente aus meiner Collection c an bestimmten Index an meine Liste anhängen?
    public boolean addAll(int index, Collection<? extends T> c) {

        for (T t : c) {
            this.add(index++, t);
        }
        return true;
    }

    @Override
    //Alle Elemente aus der Liste entfernen, die auch in Collection c sind
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

    //Liste leeren, indem Zeiger des ListElement (first) auf null gesetzt wird
    @Override
    public void clear() {

        first = null;
    }

    //Element (T) an bestimmten Index zurückgeben
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
        //Element (T) an bestimmter Stelle Index setzen
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

        if (index == size) {
            add(element);
            return;
        }

        if (index > size) {
            throw new IndexOutOfBoundsException();
        }

        ListElement current = first;

        ListElement toAdd = new ListElement(element);

        if (index == 0) {
            first = toAdd;
            first.next = current;
            size++;
            return;
        }

        for (int i = 0; i < size; i++) {
            if (i == index) {
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
        if (index == 0) {
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
        for (int i = 1; i < size; i++) {

            if (index == i) {
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
        //Index des erten Vorkommens von o in Liste prüfen
        ListElement listElement = first;

        for (int i = 0; i < size; i++) {
            if (listElement.value.equals(o)) {
                return i;
            }
            listElement = listElement.next;
        }
        //Return -1 für den Falls dass Object nicht in Liste enthalten ist
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {

        //Index des letzten Vorkommens von o in Liste prüfen
        ListElement listElement = first;

        //Für den Fall das Object nicht in Liste enthalten ist
        int highestOccurrence = -1;

        for (int i = 0; i < size; i++) {
            if (listElement.value.equals(o)) {
                highestOccurrence = i;
            }
            listElement = listElement.next;
        }

        //Rückgabe des Index des letzten Vorkommens in der Liste
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

        ll.remove("Welt");

        for (String s : ll) {
            System.out.println(s);
        }
    }
}
