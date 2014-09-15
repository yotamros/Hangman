/*
 * File: Hangman.java
 * ------------------
 * The goal of this program is to simulate the game Hangman.
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import acm.program.ConsoleProgram;
import acm.util.RandomGenerator;

@SuppressWarnings("serial")
public class Hangman extends ConsoleProgram {

    private static final int GUESSES_ALLOWED = 8;
    private String chosenWord;
    private String wordCovered = "";
    private int wrongGuesses = 0;
    private int lettersFound = 0;
    private HangmanCanvas canvas;

    /** 
     * Initializes the canvas. 
     */
    public void init() {
        canvas = new HangmanCanvas();
        add(canvas);
    }

    /**
     * Asks for the users input, checks if the letter is part of the word. Calls
     * to displays the status of the game. Checks if the game is a win or lost.
     */
    private void play() {
        while (wrongGuesses < GUESSES_ALLOWED) {
            getInputAndCheck();
            showStats();
            if (isWin()) {
                declareOutcome(true);
                return;
            }
        }
        declareOutcome(false);
    }

    /**
     * Display the status of the game and the word. Calls to print the status on
     * the canvas.
     */
    private void showStats() {
        println("The word looks like this now: " + wordCovered);
        println("You have " + (GUESSES_ALLOWED - wrongGuesses + " guesses left"));
        canvas.displayWord(wordCovered);
    }

    /**
     * Picks a random word from the lexicon.
     */
    private void chooseWord() {
        RandomGenerator rd = new RandomGenerator();
        HangmanLexicon hl = new HangmanLexicon();
        chosenWord = hl.getWord(rd.nextInt(0, hl.getWordCount()));
    }

    /** 
     * Declares the outcome of the game. 
     */
    private void declareOutcome(boolean isWin) {
        if (isWin) {
            println("You Won!");
        } else {
            println("You lost!");
        }
        println("The word was: " + chosenWord);
    }

    /**
     * Finds out if the word is fully discovered.
     * @return boolean, true if win, false if not.
     */
    private boolean isWin() {
        if (lettersFound == chosenWord.length()) {
            return true;
        }
        return false;
    }

    /** 
     * Creates a string of '-' with the amount of letters in the chosen word 
     */
    private void hideWord() {
        if (wrongGuesses == 0) {
            for (int i = 0; i < chosenWord.length(); i++) {
                wordCovered += "-";
            }
        }
    }

    /**
     * Ask the user for a letter of the alphabet. Verifies that the input is
     * valid.
     * @return String, userGuess, the letter guessed by the user.
     */
    private String askForInput() {
        String userGuess = null;
        while (userGuess == null) {
            println("guess a letter");
            String guess = readLine().toUpperCase();
            if (isLetterValid(guess)) {
                userGuess = guess;
            } else {
                println("Invalid input, try again");
            }
        }
        return userGuess;
    }
    
    /**
     * Finds out if the input letter is valid, ie. a single letter of the abc.
     * @param letter, String, the input letter from the user. 
     * @return true if the letter belongs to the alphabet, otherwise false.  
     */
    private boolean isLetterValid(String letter) {
        Pattern p = Pattern.compile("[a-zA-z]");
        Matcher m = p.matcher(letter);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /** 
     * Calls to get input from the user and finds out if the guess is correct. 
     * If the guess was incorrect calls the canvas to add another limb. 
     */
    private void getInputAndCheck() {
        int lettersFoundCopy = lettersFound;
        String letter = askForInput();
        for (int i = 0; i < chosenWord.length(); i++) {
            if (chosenWord.substring(i, i + 1).contains(letter)) {
                println("That guess is correct");
                updateWord(letter, i);
            }
        }
        if (lettersFound == lettersFoundCopy) {
            canvas.noteIncorrectGuess(letter.charAt(0));
            wrongGuesses++;
        }
    }

    /**
     * Builds the covered word while exposing previously found letters.
     * @param letter, String, the letter chosen by the user.
     * @param index, int, the index position of the letter in the word.
     */
    private void updateWord(String letter, int index) {
        String updateWord = "";
        for (int i = 0; i < wordCovered.length(); i++) {
            if (wordCovered.charAt(i) != '-') {
                updateWord += wordCovered.substring(i, i + 1);
            } else if (index == i) {
                updateWord += letter;
                lettersFound++;
            } else {
                updateWord += "-";
            }
        }
        wordCovered = updateWord;
    }

    /** 
     * Asks the user if he/she would like to play another round. 
     */
    private boolean playAgain() {
        int input = readInt("Press 1 if you'd like to play again");
        if (input == 1) {
            reset();
            return true;
        }
        return false;
    }

    /** 
     * Resets the variables to their original values. 
     */
    private void reset() {
        canvas.reset();
        wordCovered = "";
        wrongGuesses = 0;
        lettersFound = 0;
    }
    
    public void run() {
        canvas.drawScaf();
        chooseWord();
        hideWord();
        play();
        while (playAgain()) {
            run();
        }
    }
}
