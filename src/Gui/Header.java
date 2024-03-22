package Gui;

import Main.Main;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Header extends JPanel {
    private final JLabel statusLabel;
    private final JButton openFileChooser;
    private Font font;
    private Border margin;

    public Header() {
        font = new Font(Font.MONOSPACED, Font.PLAIN, 12);
        margin = BorderFactory.createEmptyBorder();

        statusLabel = new JLabel("e > file.txt");
        openFileChooser = new JButton("Files");
        openFileChooser.addActionListener(e -> Main.fileIO.chooseFile());

        setLayout(new BorderLayout());
        add(statusLabel, BorderLayout.WEST);
        add(openFileChooser, BorderLayout.EAST);
        setVisible(true);
    }

    public void loadDecoration() {
        statusLabel.setForeground(Main.config.colors[2]);
        statusLabel.setFont(font);

        openFileChooser.setBackground(Main.config.colors[2]);
        openFileChooser.setForeground(Main.config.colors[0]);
        openFileChooser.setFont(font);

        setBackground(Main.config.colors[1]);
        setBorder(margin);
    }

    public void updateText() {
        statusLabel.setText(Main.editorLogic.getMode() + " > " + Main.fileIO.getFile().toString());
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
