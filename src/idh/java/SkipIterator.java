// Tim Schäfer 7380391
package idh.java;

import java.util.Iterator;

public class SkipIterator<T> implements Iterator<T> {

	DocumentIterator di;
	int breakInt;
	
	public SkipIterator(DocumentIterator di, int breakInt){
		this.di = di;
		this.breakInt = breakInt;
	}
	
	@Override
	public boolean hasNext() {
		
		return false;
	}

	@Override
	public T next() {
		// TODO Auto-generated method stub
		return null;
	}

}
