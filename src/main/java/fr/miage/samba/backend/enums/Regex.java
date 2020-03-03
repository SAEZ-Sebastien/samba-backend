package fr.miage.samba.backend.enums;

import java.util.regex.Pattern;

public enum Regex {

    REGEX_MAIL("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");

    private String regex;

    Regex(String value) {
        this.regex = value;
    }

    public String getRegex() {
        return regex;
    }

    public Pattern getPattern(){
        return Pattern.compile(this.regex);
    }
}
