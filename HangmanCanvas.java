/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import java.awt.Color;
import acm.graphics.GCanvas;
import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GOval;

@SuppressWarnings("serial")
public class HangmanCanvas extends GCanvas {

    /** Resets the display so that only the scaffold appears */
    public void reset() {
        removeAll();
        drawScaf();
        count = 0;
        label = null;
    }

    /** Draws the scaffold. */
    private void drawScaf() {
        GLine line1 = new GLine(getWidth() / 2, getHeight() / 2
                - SCAFFOLD_OFFSET, getWidth() / 2, getHeight() / 2
                - SCAFFOLD_OFFSET - ROPE_LENGTH);
        GLine line2 = new GLine(getWidth() / 2, getHeight() / 2
                - SCAFFOLD_OFFSET - ROPE_LENGTH, getWidth() / 2 - BEAM_LENGTH,
                getHeight() / 2 - SCAFFOLD_OFFSET - ROPE_LENGTH);
        GLine line3 = new GLine(getWidth() / 2 - BEAM_LENGTH, getHeight() / 2
                - SCAFFOLD_OFFSET - ROPE_LENGTH, getWidth() / 2 - BEAM_LENGTH,
                getHeight() / 2 - SCAFFOLD_OFFSET - ROPE_LENGTH
                        + SCAFFOLD_HEIGHT);
        add(line1);
        add(line2);
        add(line3);
    }

    /**
     * Updates the word on the screen to correspond to the current state of the
     * game. The argument string shows what letters have been guessed so far;
     * unguessed letters are indicated by hyphens.
     */
    public void displayWord(String word) {
        clearLabel();
        label = new GLabel(word);
        label.setFont("Ariel-20");
        add(label, getWidth() / 4, (getHeight() / 4) * 3);
    }

    /** Removes the label from the canvas. */
    private void clearLabel() {
        if (label != null) {
            remove(label);
        }
    }

    /**
     * Updates the display to correspond to an incorrect guess by the user.
     * Calling this method causes the next body part to appear on the scaffold
     * and adds the letter to the list of incorrect guesses that appears at the
     * bottom of the window.
     */
    public void noteIncorrectGuess(char letter) {
        postLetter(letter);
        if (count == 0) {
            createHead();
        }
        if (count == 1) {
            createBody();
        }
        if (count == 2) {
            createLeftArm();
        }
        if (count == 3) {
            createRightArm();
        }
        if (count == 4) {
            createLeftLeg();
        }
        if (count == 5) {
            createRightLeg();
        }
        if (count == 6) {
            createLeftFoot();
        }
        if (count == 7) {
            createRightFoot();
        }
        count++;
    }
    
    /** Display a misguessed letter on the window. */
    private void postLetter(char letter) {
        GLabel wrongLetters = new GLabel(String.valueOf(letter));
        wrongLetters.setFont("Ariel-15");
        wrongLetters.setColor(Color.RED);
        add(wrongLetters, getWidth() / 4 + 15*count, (getHeight() / 4) * 3 + 40);
    }
    
    private void createLeftFoot() {
        GLine foot = new GLine(getWidth()
                / 2 - HIP_WIDTH, getHeight() / 2 - SCAFFOLD_OFFSET
                + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH, getWidth()
                / 2 - HIP_WIDTH - FOOT_LENGTH, getHeight() / 2 - SCAFFOLD_OFFSET
                + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH);
        add(foot);
    }
    
    private void createRightFoot() {
        GLine foot = new GLine(getWidth()
                / 2 + HIP_WIDTH, getHeight() / 2 - SCAFFOLD_OFFSET
                + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH, getWidth()
                / 2 + HIP_WIDTH + FOOT_LENGTH, getHeight() / 2 - SCAFFOLD_OFFSET
                + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH);
        add(foot);
    }
    
    private void createLeftLeg() {
        GLine hip = new GLine(getWidth() / 2, getHeight() / 2 - SCAFFOLD_OFFSET
                + HEAD_RADIUS * 2 + BODY_LENGTH, getWidth() / 2 - HIP_WIDTH,
                getHeight() / 2 - SCAFFOLD_OFFSET + HEAD_RADIUS * 2
                        + BODY_LENGTH);
        GLine leg = new GLine(getWidth() / 2 - HIP_WIDTH, getHeight() / 2
                - SCAFFOLD_OFFSET + HEAD_RADIUS * 2 + BODY_LENGTH, getWidth()
                / 2 - HIP_WIDTH, getHeight() / 2 - SCAFFOLD_OFFSET
                + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH);
        add(hip);
        add(leg);
    }
    
    private void createRightLeg() {
        GLine hip = new GLine(getWidth() / 2, getHeight() / 2 - SCAFFOLD_OFFSET
                + HEAD_RADIUS * 2 + BODY_LENGTH, getWidth() / 2 + HIP_WIDTH,
                getHeight() / 2 - SCAFFOLD_OFFSET + HEAD_RADIUS * 2
                        + BODY_LENGTH);
        GLine leg = new GLine(getWidth() / 2 + HIP_WIDTH, getHeight() / 2
                - SCAFFOLD_OFFSET + HEAD_RADIUS * 2 + BODY_LENGTH, getWidth()
                / 2 + HIP_WIDTH, getHeight() / 2 - SCAFFOLD_OFFSET
                + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH);
        add(hip);
        add(leg);
    }

    private void createLeftArm() {
        GLine arm = new GLine(getWidth() / 2, getHeight() / 2 - SCAFFOLD_OFFSET
                + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD, getWidth() / 2
                - UPPER_ARM_LENGTH, getHeight() / 2 - SCAFFOLD_OFFSET
                + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD);
        GLine hand = new GLine(getWidth() / 2 - UPPER_ARM_LENGTH, getHeight()
                / 2 - SCAFFOLD_OFFSET + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD,
                getWidth() / 2 - UPPER_ARM_LENGTH, getHeight() / 2
                        - SCAFFOLD_OFFSET + HEAD_RADIUS * 2
                        + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
        add(arm);
        add(hand);
    }

    private void createRightArm() {
        GLine arm = new GLine(getWidth() / 2, getHeight() / 2 - SCAFFOLD_OFFSET
                + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD, getWidth() / 2
                + UPPER_ARM_LENGTH, getHeight() / 2 - SCAFFOLD_OFFSET
                + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD);
        GLine hand = new GLine(getWidth() / 2 + UPPER_ARM_LENGTH, getHeight()
                / 2 - SCAFFOLD_OFFSET + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD,
                getWidth() / 2 + UPPER_ARM_LENGTH, getHeight() / 2
                        - SCAFFOLD_OFFSET + HEAD_RADIUS * 2
                        + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
        add(arm);
        add(hand);
    }

    private void createBody() {
        GLine body = new GLine(getWidth() / 2, getHeight() / 2
                - SCAFFOLD_OFFSET + HEAD_RADIUS * 2, getWidth() / 2,
                getHeight() / 2 - SCAFFOLD_OFFSET + HEAD_RADIUS * 2
                        + BODY_LENGTH);
        add(body);
    }

    private void createHead() {
        GOval head = new GOval(HEAD_RADIUS * 2, HEAD_RADIUS * 2);
        add(head, getWidth() / 2 - HEAD_RADIUS, getHeight() / 2
                - SCAFFOLD_OFFSET);
    }

    /* Constants for the simple version of the picture (in pixels) */
    private GLabel label;
    private int count = 0;
    private static final int SCAFFOLD_OFFSET = 200;
    private static final int SCAFFOLD_HEIGHT = 360;
    private static final int BEAM_LENGTH = 144;
    private static final int ROPE_LENGTH = 18;
    private static final int HEAD_RADIUS = 36;
    private static final int BODY_LENGTH = 144;
    private static final int ARM_OFFSET_FROM_HEAD = 28;
    private static final int UPPER_ARM_LENGTH = 72;
    private static final int LOWER_ARM_LENGTH = 44;
    private static final int HIP_WIDTH = 36;
    private static final int LEG_LENGTH = 108;
    private static final int FOOT_LENGTH = 28;

}
