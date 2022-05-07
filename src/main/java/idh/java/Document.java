package idh.java;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Document implements Iterable<String> {
    private String documentText;
    StringTokenizer st;

    public static Document readFromFile(File f) throws IOException {

        FileReader fileReader = new FileReader(f);
        int ch;

        StringBuilder b = new StringBuilder();

        while ((ch = fileReader.read()) != -1) {
            b.append((char) ch);
        }
        fileReader.close();

        Document doc = new Document();
        doc.setDocumentText(b.toString());

        return doc;
    }

    public String getDocumentText() {
        return documentText;
    }

    public void setDocumentText(String documentText) {
        this.documentText = documentText;
        st = new StringTokenizer(documentText);
    }

    public static void main(String[] args) throws IOException {
        Document d = Document.readFromFile(new File("data/dracula.txt"));


        int i = 0;
        int j = 0;

        //BasisIterator- Ausgabe ohne Skip
        for (String s : d) {
            i++;
            //System.out.println(s);
        }

        SkipIterator<String> skipIterator = new SkipIterator<>(d.iterator(), 2, d.st.countTokens());

        while (skipIterator.hasNext()) {
            j++;
            skipIterator.next();
            //System.out.println(skipIterator.next());
        }
        System.out.println("Basis Iterator hatte " + i + " Objekte, Skip Iterator hatte " + j + " Objekte.");

    }

    @Override
    public Iterator<String> iterator() {

        st = new StringTokenizer(documentText);

        return new Iterator<>() {

            @Override
            public boolean hasNext() {
                return st.hasMoreTokens();
            }

            @Override
            public String next() {
                return st.nextToken();
            }
        };
    }
}

