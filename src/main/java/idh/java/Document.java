package idh.java;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Document implements Iterable<String> {

    //Unser Dokument "Dracula"
    private String documentText;

    //String Tokenizer "st" als Klassenvariable
    StringTokenizer st;

    public static Document readFromFile(File f) throws IOException {

        //FileReader liest File f (dracula.txt) - liest Streams von Buchstaben
        FileReader fileReader = new FileReader(f);

        //int und nicht char, da Methode read() als Rückgabe int erwartet
        int ch;

        //StringBuilder der Buchstaben aneinanderreiht zu Strings
        StringBuilder b = new StringBuilder();

        //in Schleife Datei lesen bis -1 (Ende des Streams erreicht)
        while ((ch = fileReader.read()) != -1) {

            //Dem StringBuilder die einzelnen char übergeben + anhängen
            b.append((char) ch);
        }
        //FileReader schließen, ansonsten endlos
        fileReader.close();

        //neues Document erstellen
        Document doc = new Document();

        //Ergebnis des StringBuilder als String darstellen (toString)
        doc.setDocumentText(b.toString());

        //Document zurück geben
        return doc;
    }

    //Getter
    public String getDocumentText() {
        return documentText;
    }

    //Setter
    public void setDocumentText(String documentText) {
        this.documentText = documentText;

        //Initialisierung StringTokenizer + Übergabe documentText
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

        //SkipIterator erstellen, über Document iterieren, n = 2, Länge (size) der Wörter vom StringTokenizer
        SkipIterator<String> skipIterator = new SkipIterator<>(d.iterator(), 2, d.st.countTokens());

        //solange Skipiterator nächstes Element hat, erhöhe j um 1 und gebe nächstes Element aus
        while (skipIterator.hasNext()) {
            j++;
            skipIterator.next();
            //System.out.println(skipIterator.next());
        }
        System.out.println("BasisIterator hatte " + i + " Objekte, SkipIterator hatte " + j + " Objekte.");

    }

    @Override
    public Iterator<String> iterator() {

        setDocumentText(documentText);
        //st = new StringTokenizer(documentText);

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

