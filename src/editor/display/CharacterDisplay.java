/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor.display;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.LayoutManager;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 * This class represent some kind of old style terminal. It is capable
 * of displaying individual characters in a rectangular grid. It is
 * intended to be used as is. Just like a library class.
 *
 * @author evenal
 */
public class CharacterDisplay extends JPanel {

    /**
     * The size of the terminal
     */
    public static final int HEIGHT = 20;
    public static final int WIDTH = 40;

    /**
     * Holds the data for the grid of characters
     */
    DisplayTableModel tableModel;
    JTextField messageArea;
    CharacterRenderer renderer;

    public CharacterDisplay() {
        tableModel = new DisplayTableModel();

        JTable table = createTable();
        messageArea = new JTextField();
        messageArea.setEditable(false);
        LayoutManager layout = new BorderLayout();
        setLayout(layout);
        add(table, BorderLayout.CENTER);
        add(messageArea, BorderLayout.SOUTH);
    }

    private JTable createTable() {
        JTable table = new JTable(tableModel);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setRowHeight(16);
        table.setBackground(CharacterRenderer.CELLBG);
        table.setForeground(CharacterRenderer.CELLFG);
        renderer = new CharacterRenderer();
        table.setDefaultRenderer(Character.class, renderer);

        TableColumnModel colModel = table.getColumnModel();
        for (int col = 0; col < WIDTH; col++) {
            TableColumn column = colModel.getColumn(col);
            column.setMaxWidth(8);
            column.setMinWidth(8);
            column.setCellRenderer(renderer);
        }

        table.setRowSelectionAllowed(false);
        table.setColumnSelectionAllowed(false);
        table.setCellSelectionEnabled(false);

        return table;
    }

    /**
     * Display the character c, in position row,col in the grid. Use
     * this method to display characters. To erase a character, call
     * this method with a blank/space as c.
     */
    public void displayChar(char c, int row, int col) {
        String s = String.format("%c", c);
        tableModel.setValueAt(s, row, col);
        repaint();
    }

    public void eraseChar(char c, int row, int col) {
        displayChar(' ', row, col);
    }

    public void displayCursor(char c, int row, int col) {
        String s = String.format("%c|", c);
        tableModel.setValueAt(s, row, col);
        tableModel.setCursorAt(s, row, col);
        repaint();
    }

    private static class CharacterRenderer
            extends JLabel
            implements TableCellRenderer {

        public static final Color CELLFG = Color.BLUE;
        public static final Color CELLBG = Color.WHITE;

        public CharacterRenderer() {
            super();
            setOpaque(true);

        }

        public Component getTableCellRendererComponent(
                JTable table, Object value,
                boolean selected, boolean focus,
                int row, int col) {
            DisplayTableModel model
                    = (DisplayTableModel) table.getModel();
            if (row == model.cursorRow && col == model.cursorCol) {
                setBackground(CELLBG);
                setForeground(CELLFG);
            }
            else {
                setBackground(CELLBG);
                setForeground(CELLFG);
            }
            if (value == null)
                setText("");
            else
                setText((String) value);
            return this;
        }
    }

    /**
     * This class is the "glue" between your code and the actual UI
     * code.
     *
     */
    private class DisplayTableModel extends AbstractTableModel {

        private String[][] data;
        int cursorRow, cursorCol;

        public DisplayTableModel() {
            cursorCol = cursorRow = 0;
            this.data = new String[HEIGHT][WIDTH];
        }

        @Override
        public int getRowCount() {
            return HEIGHT;
        }

        @Override
        public int getColumnCount() {
            return WIDTH;
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return Character.class;
        }

        /**
         * Change the position of the displayed cursor to row, col.
         *
         * @param c
         * @param row
         * @param col
         */
        public void setCursorAt(String c, int row, int col) {
            cursorRow = row;
            cursorCol = col;
            fireTableCellUpdated(row, col);
            System.out.format("screen(%d,%d) <= cursor\n", row, col);
        }

        /**
         * Get the character currently displayed at the position
         * specified by row and col.
         *
         * @param row
         * @param col
         * @return
         * @throws IndexOutOfBoundsException
         */
        @Override
        public Object getValueAt(int row, int col) throws IndexOutOfBoundsException {
            if (row >= HEIGHT)
                throw new IndexOutOfBoundsException("Line index too large!");
            if (col >= WIDTH)
                throw new IndexOutOfBoundsException("Column index too large!");
            return data[row][col];
        }

        @Override
        public void setValueAt(Object o, int row, int col)
                throws IndexOutOfBoundsException {
            System.out.format("screen(%d,%d) <= %s\n", row, col, data);
            data[row][col] = (String) o;
            fireTableCellUpdated(row, col);
//            repaint();
        }

        @Override
        public boolean isCellEditable(int r, int c) throws IndexOutOfBoundsException {
            return false;
        }
    }
}
