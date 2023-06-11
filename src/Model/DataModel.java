package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Dal.FileIO;

public class DataModel {

    private String category;
    private String question;
    private HashMap<String, String> images;
    private HashMap<String, Integer> imagesValues;

    public void createQuestion(final String category) {
        Catalog catalog = FileIO.readCatalog(category);
        this.category = category;
        this.question = catalog.getQuestion();
        HashMap<String, Integer> imagesMap = this.selectRandom(catalog.getOptions());
        HashMap<String, String> images = new HashMap<>();
        this.imagesValues = new HashMap<>();

        int cardCount = 0;
        char startLetter = 'A';
        
        for (Map.Entry<String, Integer> entry : imagesMap.entrySet()) {
            char currentLetter = (char) (startLetter + cardCount);
            images.put(String.valueOf(currentLetter), entry.getKey());
            imagesValues.put(String.valueOf(currentLetter), entry.getValue());
            cardCount++;
        }
        
        this.images = images;
    }

    public HashMap<String, Integer> selectRandom(final HashMap<String, Integer> options) {
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(options.entrySet());
        Collections.shuffle(entryList);
        List<Map.Entry<String, Integer>> randomEntries = entryList.subList(0, 4);
        HashMap<String, Integer> randomHashMap = new HashMap<>();

        for (Map.Entry<String, Integer> entry : randomEntries) {
            randomHashMap.put(entry.getKey(), entry.getValue());
        }

        return randomHashMap;
    }

    public String getCategory() {
        return category;
    }

    public String getQuestion() {
        return question;
    }

    public HashMap<String, String> getImages() {
        return images;
    }

    public String getAnwser() {
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(imagesValues.entrySet());
        Collections.sort(entryList, Comparator.comparing(Map.Entry::getValue));

        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, Integer> entry : entryList) {
            sb.append(entry.getKey() + " " +  entry.getValue() + "\n");
        }

        return sb.toString();
    }
    

}
