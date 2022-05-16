package idh.java;

import java.util.Iterator;

public class SkipIterator implements Iterator<String> {
	
	private int skipAmount;
	private StringIterator stringIter;

	public SkipIterator(StringIterator stringIter, int skipAmount) {
		this.stringIter = stringIter;
		this.skipAmount = skipAmount;
	}

	@Override
	public boolean hasNext() {
		return this.stringIter.getCurrentElement() % this.skipAmount == this.skipAmount-1;
	}

	@Override
	public String next() {
		int lastPosition = this.stringIter.getLastPosition();
		int currentPosition = this.stringIter.getCurrentPosition();
		char[] docText = this.stringIter.getTextAsCharArray();
		char[] temp = new char[currentPosition-lastPosition];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = docText[lastPosition + i];
		}
		return String.valueOf(temp);
	}

}
