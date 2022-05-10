package idh.java;

import java.io.File;
import java.util.Iterator;
import java.io.FileReader;
import java.io.IOException;

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
	}
	
	public String[] AusgabeTokens() {
		ArrayList<String> eineListe = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(documentText);
		
		try {
	     while (st.hasMoreTokens()) {
	    	 eineListe.add(st.nextToken());
	     }}  catch(ArrayIndexOutOfBoundsException e) {
	    	 System.out.println("ne");
	     }
		String[] words = new String[eineListe.size()];
		for(int i = 0; i < eineListe.size(); i++) {
			words[i] = eineListe.get(i);
		}
	     
	     return words;
	}
	
}
