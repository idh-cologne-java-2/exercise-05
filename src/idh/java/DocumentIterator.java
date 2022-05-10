package idh.java;

import java.util.Iterator;

public class DocumentIterator implements Iterator<String> {

	String[] words;
	int counter = 0; 
	
	public DocumentIterator(String[] words) {
		super();
		this.words = words; 
	}
	
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return counter < words.length;
	}

	@Override
	public String next() {
		// TODO Auto-generated method stub
		return words[counter++];
	}

}
