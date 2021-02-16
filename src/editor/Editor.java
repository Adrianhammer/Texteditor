/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor;

import editor.action.*;
import editor.display.CharacterDisplay;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

/**
 * Editor is the main class of the editor application. It is mainly
 * responsible for creating the document and display models, and to
 * connect them up.
 *
 * @author evenal
 */
public class Editor extends JFrame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Editor editor = new Editor();
        editor.setVisible(true);

        editor.doc = new Document(editor.display);
    }

    private InputMap inputMap;
    private ActionMap actionMap;
    private CharacterDisplay display;
    private Document doc;

    public Editor() throws HeadlessException {
        super("Adel & Adrian's text editor");
        setLocationRelativeTo(null);


        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exit();
            }
        });

        display = new CharacterDisplay();
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(display, BorderLayout.CENTER);

        /**
         * The inputMap and actionMap determine what happens when the
         * user presses a key on the keyboard. The keys are not
         * hard-coded to the actions. The keyboard is
         */
        inputMap = display.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        actionMap = display.getActionMap();
        addKeyMappings();
        pack();
    }

    protected void exit() {
        for (java.awt.Window win : java.awt.Dialog.getWindows()) {
            win.dispose();
        }
        for (java.awt.Frame frame : java.awt.Frame.getFrames()) {
            frame.dispose();
        }
    }

    /**
     * Add a key mapping, which binds an action to a particular key
     * (represented by the keyStroke). Whenever the key is pressed,
     * the actionPerformed() method in the action will be called
     *
     * @param keyStroke key to bind
     * @param action action to bind the key to
     */
    public void addKeyMapping(KeyStroke keyStroke,
                              EditorAction action) {
        inputMap.put(keyStroke, action.getName());
        actionMap.put(action.getName(), action);
    }

    public void addKeyMapRange(char min,
                               char max,
                               EditorAction action) {
        for (char c = min; c <= max; c++) {
            KeyStroke keyStroke = KeyStroke.getKeyStroke(c);
            addKeyMapping(keyStroke, action);
        }
    }

    public void addKeyMappings() {
        inputMap.clear();
        actionMap.clear();
        String name = "insert";
        EditorAction action = new InsertAction(name, this);
        addKeyMapRange('a', 'z', action);
        addKeyMapRange('A', 'Z', action);
        addKeyMapRange('0', '9', action);

        for (var c : ".,;:!@'-<>? ()æøåÆØÅ".toCharArray()) {
            KeyStroke keyStroke = KeyStroke.getKeyStroke(c);
            addKeyMapping(keyStroke, action);
        }
        addKeyMapping(KeyStroke.getKeyStroke(KeyEvent.VK_UP, InputEvent.ALT_DOWN_MASK),
                new ArrowKeyAction("UP", "UP", this));

        addKeyMapping(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, InputEvent.ALT_DOWN_MASK),
                new ArrowKeyAction("DOWN", "DOWN", this));

        addKeyMapping(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, InputEvent.ALT_DOWN_MASK),
                new ArrowKeyAction("LEFT", "LEFT", this));

        addKeyMapping(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, InputEvent.ALT_DOWN_MASK),
                new ArrowKeyAction("RIGHT", "RIGHT", this));

        addKeyMapping(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0),
                new DeletePrevAction("DeletePrev", this));

        addKeyMapping(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0),
                new DeleteNextAction("DeleteNext", this));

        addKeyMapping(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.ALT_DOWN_MASK),
                new InsertLineAction("InsertLine", this));
    }


    public CharacterDisplay getDisplay() {
        return display;
    }

    public Document getDocument() {
        return doc;
    }

    public JButton darkMode() {
        JButton button = new JButton("Turn on darkmode");
        button.setBounds(100,100,100,100);
        return button;
    }
}
