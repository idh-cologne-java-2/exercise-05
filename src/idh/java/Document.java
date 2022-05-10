package idh.java;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

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
	
	public int size() {
		StringTokenizer t = new StringTokenizer(this.getDocumentText());
		
		return t.countTokens();
	}
	
	public String[] st() {
		StringTokenizer t = new StringTokenizer(this.getDocumentText());
		
		String[] out = new String[this.size()];
		
		int v = 0;
		
		while (t.hasMoreTokens()) {
			String current = t.nextToken();
			out[v] = current;
			v++;
		}
		return out;
	}
	
	public static final void main(String[] args) throws IOException {
		Document d = Document.readFromFile(new File("data/dracula.txt"));
		
		for(Iterator<String> text = d.iterator(); text.hasNext();) {
			String current = text.next();
			System.out.println(current);
		}
	}

	@Override
	public Iterator<String> iterator() {
		
		return new DocumentIterator(this.st());
	}
	
	public class DocumentIterator implements Iterator<String> {
		
		int position = 0;
		String[] st;
		
		public DocumentIterator(String[] st) {
			super();
			this.st = st;
		}

		
		public boolean hasNext() {
			return position < st.length;
		}
		
		public String next() {
			return st[position++];
		}
		
	}

}
