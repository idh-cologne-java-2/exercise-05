package idh.java;

import java.util.Iterator;

public class SkipIterator<T> implements Iterator<T> {

	Iterator<T> baseIterator;
	int skip;
	boolean first = true;

	public SkipIterator(Iterator<T> baseIterator, int skip) {
		super();
		this.baseIterator = baseIterator;
		this.skip = skip;
	}

	@Override
	public boolean hasNext() {
		int i = 0; 
		if (first && baseIterator.hasNext())
			return true;
		while(baseIterator.hasNext() && i < skip) {
			baseIterator.next();
			i++;
		}
		return baseIterator.hasNext();
	}

	@Override
	public T next() {
		if (first) {
			first = false;
		}
		return baseIterator.next();
	}

}
