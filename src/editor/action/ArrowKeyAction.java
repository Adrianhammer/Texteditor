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

    public ArrowKeyAction(String direction, String name, Editor ed) {
        super(name, ed);
        this.direction = direction;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Document doc = editor.getDocument();
        doc.moveCursor(direction);
    }

}
