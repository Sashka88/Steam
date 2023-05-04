package framework.services;

import java.util.ResourceBundle;

public class PropertyReader {
    private String language;
    private String filename;

    public PropertyReader(String language) {
        this.language=language;
        chooseLanguage();
    }

    private void chooseLanguage() {
        filename = "localization." + language.substring(0,2).toLowerCase();
    }

    public String getLanguageProperty(String key) {
       return PropertyReader.getPropertyByFilename(filename, key);
    }

    public static String getPropertyByFilename(String fileName, String key) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(fileName);
        return resourceBundle.getString(key);
    }
}
