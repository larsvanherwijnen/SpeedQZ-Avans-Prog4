package Model;

import java.util.HashMap;

public class Catalog {

    private String category;
    private String question;
    private HashMap<String, Integer> options = new HashMap<String, Integer>();

    public Catalog(String category) {
        this.category = category;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setOptions(HashMap<String, Integer> options) {
        this.options = options;
    }

    public String getCategory() {
        return category;
    }

    public String getQuestion() {
        return question;
    }

    public HashMap<String, Integer> getOptions() {
        return options;
    }    
}
