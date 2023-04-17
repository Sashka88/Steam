package framework.services;

import java.util.ResourceBundle;

public class PropertyLocalizationReader {
    private String language;
    private String filename;

    public PropertyLocalizationReader(String language) {
        this.language=language;
        chooseLanguage();
    }

    private void chooseLanguage() {
        filename = "localization." + language.substring(0,2).toLowerCase();
    }

    public String getProperty(String key) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(filename);
        return resourceBundle.getString(key);
    }
}
