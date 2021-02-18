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
    private int totalRow;
    private int totalCol;
    private char[][] data;

    private LinkedList<LinkedList> linkedColumn = new LinkedList<>();

    public Document(CharacterDisplay display) {
        //set up data structure
        this.display = display;
        cursorCol = cursorRow = 0;
        totalRow = display.HEIGHT;
        totalCol = display.WIDTH;

        initializeLinkedList();
    }

    private void initializeLinkedList() {
        //Lager 20 rader til brettet
        for (int i = 0; i < totalRow; i++) {
            linkedColumn.add(new LinkedList<Character>());
        }

        //Begynner fra utsiden av listen, så fyller vi innsiden etterpå med Chars.
        for (int i = 0; i < totalRow; i++) {
            LinkedList row = linkedColumn.get(i);

            for (int j = 0; j < totalCol; j++) {
                row.add(j, ' ');
            }
        }
    }

    public void displayCursor(char c, int row, int col) {
        display.displayCursor(' ', cursorRow, cursorCol);

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
        cursorRow++;
        cursorCol = 0;
    }

    public void insert(char c) {
        // insert the character c into the data structure
        linkedColumn.get(cursorRow).add(cursorCol, c);

        // removes a character from the list
        linkedColumn.get(cursorRow).removeLast();

        // Updates the list with displayable characters.
        for (int i = cursorCol; i < totalCol; i++) {
            display.displayChar((Character) linkedColumn.get(cursorRow).get(i), cursorRow, i);
        }

        display.displayCursor(' ', cursorRow, cursorCol +1);

        //if cursor on the end of column, increment the row.
        if (cursorCol == totalCol - 1 && cursorRow == totalRow - 1) {
        } else {
            cursorCol++;
            if (cursorCol >= totalCol - 1) {
                cursorCol = 0;
                cursorRow++;
            }
        }
    }

    //  Disse stod med "public char deleteNext. Why?
    //  substring???
    //  StringUtils.removeEnd(null, *)
    //  StringUtils.removeEnd("", *)
    //  StringUtils.removeEnd(*, null)
    //  ???
    public void deleteNext() {
        linkedColumn.get(cursorRow).set(cursorCol, ' ');

        for(int i= cursorCol; i < totalCol; i++){
            display.displayChar((Character) linkedColumn.get(cursorRow).get(i), cursorRow, i);
        }

        if (cursorCol == 0 && cursorRow == 0) {

        } else if (cursorCol == 0 && cursorRow >= 0) {
            //Virker som denne bugger, og sletter en bak så foran. Lettere å se når vi kan se cursor
            //cursorCol = totalCol -1;
            cursorRow++;
        } else {
            cursorCol++;
        }
    }


    public void deletePrev() {
        // Adds a char to the list
        linkedColumn.get(cursorRow).set(cursorCol, ' ');

        for(int i= cursorCol; i < totalCol; i++){
            display.displayChar((Character) linkedColumn.get(cursorRow).get(i), cursorRow, i);
        }

        //bug
        //display.displayCursor(' ', cursorRow, cursorCol +1);

        if (cursorCol == 0 && cursorRow == 0) {

        } else  if (cursorCol == 0 && cursorRow >= 0) {
            cursorCol = totalCol -1;
            cursorRow--;
        } else {
            cursorCol--;
        }
        display.displayCursor(' ', cursorRow, cursorCol +1);
    }

    /**Cursor Left**/
    //Linje 169-171, ikke ferdig, snakk med Adel
    //Legger igjen traces av cursoren, men cursor traces forsvinner når man trykker
    //DEL eller BACKSPACE
    //Må kanskje lage en loop i updateDisplay metoden som går gjennom lista og sletter
    //siste cursor hver gang
    public void moveCursorLeft() {
        if (cursorRow == 0 && cursorCol == 0) {
            //Do nothing
        } else if (cursorRow >= 0 && cursorCol == 0) {
            cursorCol = totalCol - 1;
            cursorRow--;
        } else {
            cursorCol--;
        }
        var l = linkedColumn.getFirst();
        char o = (char) l.get(cursorCol);
        display.displayCursor(o, cursorRow, cursorCol);
    }

        /**Cursor Right**/
        public void moveCursorRight() {
            if (cursorRow == totalRow - 1 && cursorCol == totalCol - 1) {
                //Do nothing
            } else {
                cursorCol++;
                if (cursorCol >= totalCol) {
                    cursorRow++;
                    cursorCol = 0;
                    }
                }
            var l = linkedColumn.getFirst();
            char o = (char) l.get(cursorCol);
            display.displayCursor(' ', cursorRow, cursorCol);
            }

            /**Cursor Up**/
            public void moveCursorUp() {
                if (cursorRow == 0) {

                } else if (cursorRow > 0) {
                    cursorRow--;
                }
                display.displayCursor(' ', cursorRow, cursorCol);
            }

            public void moveCursorDown() {
                if (cursorCol == totalCol - 1) {

                } else if (cursorRow < totalCol - 1) {
                    cursorRow++;
                }
                display.displayCursor(' ', cursorRow, cursorCol);
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
