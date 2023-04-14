package steam.framework.services;

import java.util.ResourceBundle;

public class PropertyLocalizationReader {
    private String language;
    private String filename;

    public PropertyLocalizationReader(String language) {
        this.language=language;
        chooseLanguage();
    }

    private void chooseLanguage() {
        switch (language) {
            case "English":
                filename = "localization/localization.ENG";
                break;
            case "Russian":
                filename = "localization/localization.RU";
                break;
        }
    }

    public String getProperty(String key) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(filename);
        return resourceBundle.getString(key);
    }
}
