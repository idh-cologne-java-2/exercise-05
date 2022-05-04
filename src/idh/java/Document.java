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
	
	
	public 	List<String> getTokens(String str){
		List<String> tokens = new ArrayList<>();
		StringTokenizer tokenizer = new StringTokenizer(str);
		while(tokenizer.hasMoreElements()) {
			tokens.add(tokenizer.nextToken());
		}
		return tokens;  
	}
	public static final void main(String[] args) throws IOException {
		Document d = Document.readFromFile(new File("data/dracula.txt"));
		//System.out.print(d.documentText);
		List<String> s = d.getTokens(d.documentText);
		SkipIterator<String> ter = new SkipIterator<String>(s.iterator(),1); 
		
		while(ter.hasNext()) {
			System.out.println(ter.next());
		}
	}

	@Override
	public Iterator<String> iterator() {
		// TODO Auto-generated method stub
		return iterator(); 
	}

	
	
}
