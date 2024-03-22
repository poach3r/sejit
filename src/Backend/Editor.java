package Backend;

import Main.Main;

public class Editor {
    private char mode;

    public Editor() {
        mode = Modes.EDITING;
    }

    public char getMode() {
        return mode;
    }

    public void setMode(char mode) {
        this.mode = mode;
        Main.header.updateText();
    }
}

