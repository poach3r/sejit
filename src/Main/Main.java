package Main;

import java.awt.*;
import java.io.FileNotFoundException;

public class Main {
    public static Backend.Editor editorLogic = new Backend.Editor();
    public static Gui.Frame frame = new Gui.Frame();
    public static Gui.Editor editorGui = new Gui.Editor();
    public static Gui.Header header = new Gui.Header();
    public static Backend.FileIO fileIO = new Backend.FileIO();
    public static Backend.Config config = new Backend.Config();

    public static void main(String[] args) {
        frame.add(editorGui, BorderLayout.CENTER);
        frame.add(header, BorderLayout.NORTH);
        frame.pack();

        if(args.length >= 1) {
            try {
                load(args[0]);
            } catch (FileNotFoundException e) {
                System.err.println("Failed to load file: " + e);
                System.exit(1);
            }
        }

        else
            System.out.println("No file provided in arguments, continuing with instantiated variables");

        editorGui.loadDecoration();
        header.loadDecoration();
    }

    public static void load(String file) throws FileNotFoundException {
        fileIO.setFile(file);
        fileIO.input();

        editorGui.setText(fileIO.getFileContents());
        Main.header.updateText();
    }
}
