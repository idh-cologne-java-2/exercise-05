package idh.java;

import java.util.Iterator;

public class DocIterator implements Iterator<String>{

	int currentPosition = 0;
	String[] strings;
	
	
	
	
	public DocIterator(String[] strings) {
		super();
		this.strings = strings;
	}

	public boolean hasNext() {
		return currentPosition < strings.length;
	}

	public String next() {
		return strings[currentPosition++];
	}
	
	

}
