package idh.java;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;


public class Document implements Iterable<String>{
	static List<String> tokens = new ArrayList<>();
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
		
		StringTokenizer stokenizer = new StringTokenizer(d.documentText);
		while(stokenizer.hasMoreTokens()) {
		tokens.add(stokenizer.nextToken());
		}
		
		Iterator<String> iter = tokens.iterator();
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
	}

	@Override
	public Iterator<String> iterator() {
		return this.iterator();
	}
	
}
