// Tim Schäfer 7380391
package idh.java;

import java.util.Iterator;
import java.util.StringTokenizer;

public class DocumentIterator implements Iterator<String>{
	StringTokenizer st;
	
	public DocumentIterator(String TextDocument) {
		st = new StringTokenizer(TextDocument);
	}
	@Override
	public boolean hasNext() {
		return st.hasMoreTokens();
	}

	@Override
	public String next() {
		return st.nextToken();
	}

}
