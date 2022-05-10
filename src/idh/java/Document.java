package idh.java;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Document implements Iterable<String> {
	String documentText;

	public static Document readFromFile(File f) throws IOException {
		FileReader fileReader = new FileReader(f);
		int ch;
		StringBuilder b = new StringBuilder();
		while( (ch = fileReader.read()) != -1 ) {
			b.append((char) ch);
		}
		fileReader.close();
		Document doc = new Document();
		doc.documentText = b.toString();
		
		return doc;
	}
	
	public String getDocumentText() {
		return documentText;
	}

	public void setDocumentText(String documentText) {
		this.documentText = documentText;
	}
	
	public static final void main(String[] args) throws IOException {
		Document d = Document.readFromFile(new File("data/dracula.txt"));
		Iterator<String> docString = new TokenIterator(d);
		while(docString.hasNext()) {
			String s = docString.next();
			System.out.println(s);
		}
	}

	@Override
	public Iterator<String> iterator() {
		return new TokenIterator(this);
	}
	
	public static class TokenIterator implements Iterator<String> {
		String readDocument;
		StringTokenizer st;
		int counter;
		
		public TokenIterator(Document document) {
			readDocument = document.documentText;
			st = new StringTokenizer(readDocument);
			counter = 0;
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
	
}
