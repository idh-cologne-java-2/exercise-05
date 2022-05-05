package idh.java;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Document implements Iterable<String>{
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
	
	public int size() {
		StringTokenizer sT = new StringTokenizer(this.getDocumentText());
		return sT.countTokens();
	}
	
	public String[] strings() {
		StringTokenizer sT = new StringTokenizer(this.getDocumentText());
		
		String[] output = new String[this.size()];
		
		int h = 0;
		
		while(sT.hasMoreTokens()) {
			String current = sT.nextToken();
			output[h] = current;
			h++;
		}
		
		return output;
	}
	
	public static final void main(String[] args) throws IOException {
		Document d = Document.readFromFile(new File("data/dracula.txt"));
		
		//System.out.println(d.getDocumentText());
		
		//String[] stringText = d.strings();
		
		//StringTokenizer sT = new StringTokenizer(d.getDocumentText());
		
		//System.out.println(sT.countTokens());
		//System.out.println();
		
//		while(sT.hasMoreTokens()) {
//			String current = sT.nextToken();
//			System.out.println(current);
//		}
		
		
		//Ausgabe hätte man auch nur mit StringTokenizer machen können
//		for(int i = 0; i < d.size(); i++) {
//			System.out.println(d.strings()[i]);
//		}
		
		//Iterator<String> tIter = d.iterator();
		
//		//warum nicht mit nur StringTokenizer? //gibt nicht alles aus?
		for(Iterator<String> tIter = d.iterator(); tIter.hasNext();) {
			String current = tIter.next();
			System.out.println(current);
		}
	}

	@Override
	public Iterator<String> iterator() {
		
		return new DocIterator(this.strings());
			
	}
	
}
