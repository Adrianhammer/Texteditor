/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor.action;

import editor.Document;
import editor.Editor;
import java.awt.event.ActionEvent;

/**
 *
 * @author evenal
 */
public class ArrowKeyAction extends EditorAction {

    private String direction;
    Editor editor;

    public ArrowKeyAction(String direction, String name, Editor ed) {
        super(name);
        this.direction = direction;
        this.editor = ed;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Document doc = editor.getDocument();
        switch (direction) {

            case "LEFT": doc.moveCursorLeft();
            break;

            case "RIGHT": doc.moveCursorRight();
            break;

            case "UP": doc.moveCursorUp();
            break;

            case "DOWN": doc.moveCursorDown();
            break;

            default:
                System.out.println("Input not registered correctly");
                break;
        }
    }

}
