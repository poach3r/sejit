package Backend;

import Main.Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class KeyHandler implements KeyListener {
    private String query;

    public KeyHandler() {
        query = "";
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        // check if the user is trying to find a string
        if(Main.editorLogic.getMode() == Modes.FINDING) {
            if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                Main.editorLogic.setMode(Modes.EDITING);
                Main.editorGui.setEditability(true);
                query = "";
                return;
            }

            // ignore shifts
            if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
                return;
            }

            // we need to manually do backspaces since it'll just put in the backspace character otherwise
            else if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                if(!query.isEmpty())
                    query = query.substring(0, query.length() - 1);
            }

            else
                query = query.concat(String.valueOf(e.getKeyChar()));

            Main.editorGui.find(query);
        }

        // check if user is holding the control
        else if(e.getKeyCode() == KeyEvent.VK_CONTROL) {
            Main.editorLogic.setMode(Modes.MOD);
        }


        // if the user is already holding control, parse their input
        else if(Main.editorLogic.getMode() == Modes.MOD) {

            // saving
            if (e .getKeyCode()== KeyEvent.VK_S) {
                Main.editorLogic.setMode(Modes.SAVING);
                Main.fileIO.setFileContents(Main.editorGui.getText());
                try {
                    Main.fileIO.output();
                } catch (IOException ex) {
                    System.err.println("Failed to save file, I'm sorry: " + e);
                }

                Main.editorLogic.setMode(Modes.EDITING);
            }

            // reloading, primarily used for debugging
            if (e.getKeyCode() == KeyEvent.VK_R) {
                Main.editorLogic.setMode(Modes.RELOADING);
                Main.header.loadDecoration();
                Main.editorGui.loadDecoration();
                Main.header.revalidate();
                Main.editorGui.revalidate();

                try {
                    Main.fileIO.input();
                } catch (FileNotFoundException ex) {
                    System.err.println("Failed to load file: " + ex);
                    System.exit(1);
                }

                Main.editorLogic.setMode(Modes.EDITING);
            }

            // find string
            if(e.getKeyCode() == KeyEvent.VK_F) {
                Main.editorLogic.setMode(Modes.FINDING);
                Main.editorGui.setEditability(false);
            }

            // open file chooser
            if(e.getKeyCode() == KeyEvent.VK_B) {
                Main.editorLogic.setMode(Modes.FILES);
                Main.fileIO.chooseFile();
                Main.editorLogic.setMode(Modes.EDITING);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(Main.editorLogic.getMode() != Modes.FINDING)
            if (e.getKeyCode() == KeyEvent.VK_CONTROL)
                Main.editorLogic.setMode(Modes.EDITING);
    }
}
