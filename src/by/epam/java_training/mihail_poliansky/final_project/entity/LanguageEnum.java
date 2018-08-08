package by.epam.java_training.mihail_poliansky.final_project.entity;

import java.util.Locale;

public enum  LanguageEnum {
    RUS(new Locale("ru", "RU")), ENG(new Locale("en", "RU"));
    private final Locale locale;

    LanguageEnum(Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }
}
