/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor.action;

import editor.Editor;
import javax.swing.AbstractAction;

/**
 *
 * @author evenal
 */
public abstract class EditorAction extends AbstractAction {

    protected char ch;
    Editor editor;

    public EditorAction(String name, Editor editor) {
        super(name);
        this.editor = editor;
    }

    public String getName() {
        return (String) getValue(AbstractAction.NAME);
    }
}
