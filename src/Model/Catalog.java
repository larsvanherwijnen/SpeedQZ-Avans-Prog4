package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

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
        return removeOptionsWithDuplicateValues(options);
    }

    private HashMap<String, Integer> removeOptionsWithDuplicateValues(final HashMap<String, Integer> options) {
        HashMap<String, Integer> resultMap = new HashMap<>();

        // Group the entries by value
        Map<Integer, List<Map.Entry<String, Integer>>> groupedEntries = options.entrySet()
                .stream()
                .collect(Collectors.groupingBy(Map.Entry::getValue));

        for (List<Map.Entry<String, Integer>> entries : groupedEntries.values()) {
            Map.Entry<String, Integer> selectedEntry = entries.get(new Random().nextInt(entries.size()));
            resultMap.put(selectedEntry.getKey(), selectedEntry.getValue());
        }

        return resultMap;
    }
}
