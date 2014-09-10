/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file opens the Lexicon text file, reads all the letters in it and copy
 * them into an ArrayList.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class HangmanLexicon {
    
    private ArrayList<String> lexicon = new ArrayList<String>();

    public HangmanLexicon() {
        String filename = "HangmanLexicon.txt";
        try {
            BufferedReader bf = new BufferedReader(new FileReader(filename));
            try {
                while (bf.readLine() != null) {
                    lexicon.add(bf.readLine());
                }
                bf.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (FileNotFoundException e)  {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /** Returns the number of words in the lexicon. */
    public int getWordCount() {
        return lexicon.size();
    }

    public String getWord(int index) {
        return lexicon.get(index);
    }
}
