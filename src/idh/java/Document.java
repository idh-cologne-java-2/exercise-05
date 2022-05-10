package idh.java;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class Document implements Iterable<String>{
	String documentText;
	String[] text;

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
		
		for (String text : d){
		    System.out.println("Aktuelles Element: " + text);
		}
	}

	@Override
	public Iterator<String> iterator() {
		return new Iterator<String>(){

			private int pos = 0;
			
			@Override
			public boolean hasNext() {
				return pos < text.length;
			}

			@Override
			public String next() {
				return text[pos++];
			}
		};
	}
	
}
