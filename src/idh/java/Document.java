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
	
	public static final void main(String[] args) throws IOException {
		Document d = Document.readFromFile(new File("data/dracula.txt"));
	}

	@Override
	public Iterator<String> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public class StringTokenizerHasMoreTokens {
		public void main(String[] args) {
			
			//Creating a StringTokenizer
			StringTokenizer st = new StringTokenizer("Dracula by Bram Stoker");
			
			//Checking and displaying the Tokens
			while (st.hasMoreTokens()) {
				System.out.println("The Next token: " + st.nextToken());
		}


			}	

			
		
		
		
	}
}

	

