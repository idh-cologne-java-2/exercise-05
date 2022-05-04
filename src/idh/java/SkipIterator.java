package idh.java;

import java.util.Iterator;

public class SkipIterator <T> implements Iterator<T> {
	int counter = 0; 
	int skip; 
	Iterator<T> iter; 
	public SkipIterator(Iterator<T> iter, int skip)  {
		this.iter = iter;
		this.skip = skip; 
		
	}
	
	
	@Override
	public boolean hasNext() {
		return iter.hasNext();
	}


	@Override
	public T next() {
		if(counter < skip) {
			counter++;
			return iter.next();
		}else {
			counter = 0;
			iter.next();
			return (T) "";
		}
		
	}
	

}
