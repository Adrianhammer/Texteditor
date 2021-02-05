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
 * This is an example of an action class. You will have to add more.
 * They should call one of the editing methods in the document.
 *
 * @author evenal
 */
public class InsertAction extends EditorAction {

    Editor editor;

    public InsertAction(String name, Editor ed) {
        super(name, ed);
        this.editor = ed;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Document doc = editor.getDocument();
        char ch = ae.getActionCommand().charAt(0);
        doc.insert(ch);
        //doc.print(); <--- var her. Trenger vi den?
    }
}
