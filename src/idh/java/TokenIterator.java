package idh.java;

import java.util.Iterator;

import java.util.StringTokenizer;

public class TokenIterator implements Iterator<String>{

	StringTokenizer sT;
	
	public TokenIterator(String data){
		sT = new StringTokenizer(data);
	}
	
	@Override
	public boolean hasNext() {
		return sT.hasMoreTokens();
	}

	@Override
	public String next() {
		return sT.nextToken();
	}

}