package idh.java;

import java.util.Iterator;

import java.util.StringTokenizer;

public class TokenIterator implements Iterator<String>{

	StringTokenizer STOK;
	
	public TokenIterator(String Dracula){
		STOK = new StringTokenizer(Dracula);
	}
	
	@Override
	public boolean hasNext() {
	return STOK.hasMoreTokens();
	}

	@Override
	public String next() {
	return STOK.nextToken();
	}

}
