package Backend;

import Main.Main;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileIO {
    private File file;
    private String fileContents;
    private final JFileChooser fileChooser;

    public FileIO() {
        file = new File("file.txt");
        fileContents = "Content";
        fileChooser = new JFileChooser();
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setFile(String file) {
        this.file = new File(file);
    }

    public void setFileContents(String fileContents) {
        this.fileContents = fileContents;
    }

    public File getFile() {
        return file;
    }

    public String getFileContents() {
        return fileContents;
    }

    public void input() throws FileNotFoundException {
        if(!file.exists())
            return;

        Scanner reader = new Scanner(file);
        fileContents = "";
        while(reader.hasNextLine()) {
            fileContents = fileContents.concat(reader.nextLine() + "\n");
        }
        reader.close();
    }

    public void output() throws IOException {
        FileWriter writer = new FileWriter(file);
        writer.write(fileContents);
        writer.close();
    }

    public void chooseFile() {
        if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            System.out.println(fileChooser.getSelectedFile());
            try {
                Main.load(fileChooser.getSelectedFile().toString());
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
