package Gui;

import Main.Main;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Editor extends JPanel {
    private final JTextArea editableArea;
    private Font font;
    private Border margin;

    public Editor() {
        font = new Font(Font.MONOSPACED, Font.PLAIN, 12);
        margin = BorderFactory.createEmptyBorder();

        editableArea = new JTextArea();
        editableArea.addKeyListener(new Backend.KeyHandler());

        setLayout(new GridLayout(1, 1));
        add(editableArea);
        setVisible(true);
    }

    public void loadDecoration() {
        editableArea.setBackground(Main.config.colors[0]);
        editableArea.setForeground(Main.config.colors[2]);
        editableArea.setFont(font);
        setBackground(Main.config.colors[0]);
        setBorder(margin);
    }

    public void append(String content) {
        editableArea.append(content);
    }

    public void setText(String content) {
        editableArea.setText(content);
    }

    public String getText() {
        return editableArea.getText();
    }

    public void find(String query) {
        if (editableArea.getText().contains(query)) {
            int queryIndex = editableArea.getText().indexOf(query);
            editableArea.select(queryIndex, query.length() + queryIndex);
        } else
            editableArea.select(0, 0);
    }

    public void setEditability(boolean editability) {
        editableArea.setEditable(editability);
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Border getMargin() {
        return margin;
    }

    public void setMargin(Border margin) {
        this.margin = margin;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }
}
