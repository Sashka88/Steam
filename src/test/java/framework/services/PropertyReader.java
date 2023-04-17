package framework.services;

import java.util.ResourceBundle;

public class PropertyReader {

    public static String getProperty(String fileName, String key) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(fileName);
        return resourceBundle.getString(key);
    }
}
