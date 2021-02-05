package editor;

import editor.display.CharacterDisplay;

import javax.sound.sampled.Line;
import java.util.LinkedList;

/**
 * This class represents the document being edited. Using a 2d array
 * to hold the document content is probably not a very good choice.
 * Fixing this class is the main focus of the exercise. In addition to
 * designing a better data model, you must add methods to do at least
 * basic editing: write and delete text, and moving the cursor
 *
 * Implement the following commands
 *
 * @author evenal
 */


public class Document {

    /** Document data structure(s) */
    // pointer to the
    CharacterDisplay display;
    private int cursorRow;
    private int cursorCol;
    private char[][] data;

    private StringBuilder sb;
    private LinkedList<LinkedList> linkedColumn = new LinkedList<>();

    public Document(CharacterDisplay display) {
        //set up data structure
        this.display = display;
        cursorCol = cursorRow = 0;

        initializeLinkedList();
    }

    private void initializeLinkedList() {
        //Lager 20 rader til brettet
        for (int i = 0; i < 20; i++) {
            linkedColumn.add(new LinkedList<Character>());
        }

        //Begynner fra utsiden av listen, så fyller vi innsiden etterpå med Chars.
        for (int i = 0; i < 20; i++) {
            LinkedList row = linkedColumn.get(i);

            for (int j = 0; j < 40; j++) {
                row.add(j, ' ');
            }
        }
    }

    //Duplikat updateDisplay, hvilken er riktig?
    private void updateDisplay() {
        // should be called at the end of the functionality
        // and should update the display

    }

    /*
     * The following methods are called from the actions. Decide on
     * the data structure(s) for Document first. Then finish these
     * methods
     */
    public void insertLine() {
        // create a new line in the data structure
        updateDisplay();
    }

    public void insert(Character c) {
        // insert the character c into the data structure
        linkedColumn.get(cursorRow).add(cursorCol, c);

        // removes a character from the list
        linkedColumn.get(cursorRow).removeLast();

        // Updates the list with displayable characters.
        for (int i = cursorCol; i < 40; i++) {
            display.displayChar((Character) linkedColumn.get(cursorRow).get(i), cursorRow, i);
        }

        display.displayCursor(' ', cursorRow, cursorCol);

        //if cursor on the end of column, increment the row.
        if (cursorCol == 39 && cursorRow == 19) {
        } else {
            cursorCol++;
            if (cursorCol >= CharacterDisplay.WIDTH) {
                cursorCol = 0;
                cursorRow++;
            }
        }
        updateDisplay();
    }

    //Disse stod med "public char deleteNext. Why?
    public void deleteNext() {

        updateDisplay();
    }

    public void deletePrev() {

        updateDisplay();
    }

    public void moveCursor(String direction) {

        updateDisplay();
    }
    //display metoden krever 3 parameter, updateDisplay ber om 1 paramter. Why? Skal vi bruke Sentinel strukturen?
    /*
    private void updateDisplay(int line) {
        // for all visible characterso
        // show them in the rightplace
        display.displayChar(c, line, column);

        // and make the cursor stand out a little
        display.displayCursor(c, line, column);
    }

     */

}