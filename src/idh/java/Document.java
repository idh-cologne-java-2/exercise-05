package idh.java;

import java.io.File;
import java.util.*;
import java.io.FileReader;
import java.io.IOException;

public class Document implements Iterable<String>{
	String documentText;
	static String st;

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
	
	@Override
	public Iterator<String> iterator() {
		return new TokenIterator(); 
	}
	
	static class TokenIterator implements Iterator<String>{
		StringTokenizer str;
		
		public TokenIterator(){
			
			str = new StringTokenizer(st);
		}
		

		@Override
		public boolean hasNext() {
			return str.hasMoreTokens();
		}

		@Override
		public String next() {
			return str.nextToken();
		}
		
	}
			
			
		
	public static final void main(String[] args) throws IOException {
		Document d = Document.readFromFile(new File("data/dracula.txt"));
		st = String.valueOf(d);

		Iterator<String> iter = new TokenIterator();
		
		while(iter.hasNext()) {
			String x = iter.next();
			System.out.println(x);
		}
		
		
	}

	
	
}
