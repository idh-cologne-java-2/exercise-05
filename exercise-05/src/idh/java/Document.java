package idh.java;
import java.io.File;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;



//Hier wurde das Interface Iterable<String> implementiert
public class Document implements Iterable<String>{
	static String documentText;

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
		Document.documentText = documentText;
	}
	
	public static final void main(String[] args) throws IOException {
		Document d = Document.readFromFile(new File("data/dracula.txt"));
		/*
		Aufgabe 1: Zur Erkennung einzelner Tokens, haben wir auf die Klasse StringTokenizer 
		zugegriffen und java.util StringTokenizer importiert (s.o)
		*/
	    StringTokenizer token = new StringTokenizer(documentText);
	 int  countWordsUp = 0;
	     while (token.hasMoreTokens()) { 
	         System.out.println(countWordsUp++ +  " " +token.nextToken());
	         if (countWordsUp > 100)
	        	 break;
	         
	     }
	 
	}
	@Override
	public Iterator<String> iterator() {
		return this.iterator();
			}
	
	}
	
	