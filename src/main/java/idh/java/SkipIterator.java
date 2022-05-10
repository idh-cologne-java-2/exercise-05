package idh.java;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SkipIterator<T> implements Iterator<T> {

    //neuen Iterator erstellen
    Iterator<T> iterator;

    //Variable für die Anzahl der "Sprünge"
    int n;

    //Größe der Liste
    int size;

    //Indizes
    int index;

    //Konstruktor
    public SkipIterator(Iterator<T> iterator, int n, int size) {
        this.iterator = iterator;
        this.n = n;
        this.size = size;
        index = 0;
    }

    //Gibt es ein nächstes Element ?
    //Vom Index ausgehen, n Schritte gehen + 1 addieren bis Ende der Liste erreicht ist
    @Override
    public boolean hasNext() {
        return index + n + 1 < size;
    }

    //Nächstes Element geben lassen
    @Override
    public T next() {
        //Versuche nächstes Element zu geben
        try {
            index += n + 1;

            //Sprünge wiederholen in Schleife bis n erreicht ist
            for (int i = 0; i == n; i++) {
                iterator.next();
            }

            return iterator.next();
        }
        catch (NoSuchElementException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
