package idh.java;

import java.util.Iterator;

public class StringIterator implements Iterator<String> {

	private int lastPosition;
	private int currentPosition;
	private int currentElement;
	private char[] docText;
	
	public StringIterator(Document d) {
		this.lastPosition = 0;
		this.currentPosition = 0;
		this.currentElement = 0;
		docText = d.getDocumentText().toCharArray();
	}

	@Override
	public boolean hasNext() {
		for (int i = lastPosition; i < docText.length; i++) {
			currentPosition++;
			// Man kann den Datentyp "char" ohne equals-Methode vergleichen
			if (docText[i] == '.') {
				return true;
			}
		}
		return false;
	}

	@Override
	public String next() {
		char[] temp = new char[currentPosition-lastPosition];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = docText[lastPosition + i];
		}
		lastPosition = currentPosition;
		currentElement++;
		return String.valueOf(temp);
	}
	
	public char[] getTextAsCharArray() {
		return this.docText;
	}
	
	public int getCurrentElement() {
		return this.currentElement;
	}
	
	public int getCurrentPosition() {
		return this.currentPosition;
	}
	
	public int getLastPosition() {
		return this.lastPosition;
	}

}
