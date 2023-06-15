package model;

import java.util.HashMap;

public class Catalog {

    private String question;
    private HashMap<String, Integer> options = new HashMap<String, Integer>();

    public void setQuestion(final String question) {
        this.question = question;
    }

    public void setOptions(final HashMap<String, Integer> options) {
        this.options = options;
    }

    public String getQuestion() {
        return question;
    }

    public HashMap<String, Integer> getOptions() {
        //remove random duplicate options
        return options;
    }
}
