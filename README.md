Sejit is a simple cross-platform text editor written in Java.

<img src="demo.png" alt="demo" width="512"/>

# Usage

`java -jar ./sejit.jar [OPTIONAL FILE_NAME]`

## Keybindings

| Key Combo | Action    |
|-----------|-----------|
| Ctrl + S  | Save      |
| Ctrl + R  | Reload    |
| Ctrl + O  | Open File |
| Ctrl + F  | Find      |

# Configuration

Sejit will first look for the configuration defined by the `SEJIT_CONFIG` environment 
variable, if none is found it will look for `~/.config/sejit/config.toml`, if no valid
configs are found, the default values are used and a warning is printed to the console.

A sample config.toml file has been provided in this repo.