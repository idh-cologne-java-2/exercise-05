package idh.java;

import java.util.Iterator;

public class DocumentIterator implements Iterator<String> {
	
	String[] woerter;
	int zaehlervariable = 0;
	
	

	public DocumentIterator(String[] woerter) {
		super();
		this.woerter = woerter;
	}

	@Override
	public boolean hasNext() {
		return zaehlervariable < woerter.length;
	}

	@Override
	public String next() {
		return woerter[zaehlervariable++];
	}

}
