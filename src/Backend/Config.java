package Backend;

import com.moandjiezana.toml.Toml;

import Main.Main;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Optional;

public class Config {
    private final Optional<String> configLocation;
    private Toml configToml;
    private File configFile;
    public Color[] colors;

    public Config() {
        colors = new Color[4];
        colors[0] = Color.BLACK;
        colors[1] = Color.DARK_GRAY;
        colors[2] = Color.WHITE;
        colors[3] = Color.GRAY;

        configLocation = Optional.ofNullable(System.getenv("SEJIT_CONFIG"));

        if(configLocation.isPresent()) {
            File file = new File(configLocation.get());
            if(file.exists()) {
                configToml = new Toml().read(file);
                read();
            }
        }

        else {
            configFile = new File("/home/" + System.getProperty("user.name") + "/.config/sejit/config.toml");
            System.out.println("SEJIT_CONFIG is null, attempting to use " + configFile);
            if(configFile.exists()) {
                configToml = new Toml().read(configFile);
                read();
            }
            else {
                System.out.println("Failed to find config file, using defaults");
            }
        }
    }

    public void read() {
        colors[0] = Color.decode(configToml.getString("colors.background"));
        colors[1] = Color.decode(configToml.getString("colors.secondaryBackground"));
        colors[2] = Color.decode(configToml.getString("colors.foreground"));
        colors[3] = Color.decode(configToml.getString("colors.secondaryForeground"));

        int n = Integer.parseInt(configToml.getString("editor.margin"));
        Main.editorGui.setMargin(BorderFactory.createEmptyBorder(n, n, n, n));
        n = Integer.parseInt(configToml.getString("header.margin"));
        Main.header.setMargin(BorderFactory.createEmptyBorder(n, n, n, n));

        Main.editorGui.setFont((new Font(configToml.getString("editor.font.family"), Font.PLAIN, Integer.parseInt(configToml.getString("editor.font.size")))));
        Main.header.setFont(new Font(configToml.getString("header.font.family"), Font.PLAIN, Integer.parseInt(configToml.getString("header.font.size"))));
    }
}
