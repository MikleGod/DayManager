package by.epam.java_training.mihail_poliansky.final_project.util;

import by.epam.java_training.mihail_poliansky.final_project.entity.LanguageEnum;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceManager {

    private ResourceBundle resourceBundle;
    private final String resourceName = "by.epam.java_training.mihail_poliansky.final_project.property.text";

    public ResourceManager() {
        resourceBundle = ResourceBundle.getBundle(resourceName, LanguageEnum.RUS.getLocale());
    }

    public void changeResource(Locale locale) {
        resourceBundle = ResourceBundle.getBundle(resourceName, locale);
    }

    public String getString(String key){
        return new String(resourceBundle.getString(key).getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
    }

    public Enumeration<String> getKeys(){
        return resourceBundle.getKeys();
    }

    public void addLocalizationAttributes(HttpServletRequest req) {
        Enumeration<String> keys = getKeys();

        while (keys.hasMoreElements()){
            String key = keys.nextElement();

            req.setAttribute(key, getString(key));
        }
    }

}