package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dal.FileIO;

public class DataModel {

    private static final int OPTIONCOUNT = 4;

    private String category;
    private String question;
    private HashMap<String, String> images;
    private HashMap<String, Integer> imagesValues;
    private HashMap<String, Boolean> sortingDirectionAsc;

    public DataModel() {
        this.sortingDirectionAsc = new HashMap<>();
        this.sortingDirectionAsc.put("speed", true);
        this.sortingDirectionAsc.put("buildings", true);
        this.sortingDirectionAsc.put("cars", false);
    }

    public void createQuestion() {
        this.category = getRandomCategory();
        Catalog catalog = FileIO.readCatalog(category);
        this.question = catalog.getQuestion();
        HashMap<String, Integer> imagesMap = this.selectRandom(catalog.getOptions());
        HashMap<String, String> images = new HashMap<>();
        this.imagesValues = new HashMap<>();

        int imageCount = 0;
        char startLetter = 'A';

        for (Map.Entry<String, Integer> entry : imagesMap.entrySet()) {
            char currentLetter = (char) (startLetter + imageCount);
            images.put(String.valueOf(currentLetter), entry.getKey());
            imagesValues.put(String.valueOf(currentLetter), entry.getValue());
            imageCount++;
        }

        this.images = images;
    }

    public HashMap<String, Integer> selectRandom(final HashMap<String, Integer> options) {
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(options.entrySet());
        Collections.shuffle(entryList);
        List<Map.Entry<String, Integer>> randomEntries = entryList.subList(0, OPTIONCOUNT);
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
        Boolean sortingDirectionAsc = this.sortingDirectionAsc.get(this.category);

        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(imagesValues.entrySet());
        if (sortingDirectionAsc) {
            Collections.sort(entryList, Comparator.comparing(Map.Entry::getValue));
        } else {
            Collections.sort(entryList, Collections.reverseOrder(Comparator.comparing(Map.Entry::getValue)));
        }

        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, Integer> entry : entryList) {
            sb.append(entry.getKey());
        }

        return sb.toString();
    }

    private String getRandomCategory() {
        ArrayList<String> categories = new ArrayList<>(this.sortingDirectionAsc.keySet());
        int random = (int) (Math.random() * categories.size());
        return categories.get(random);
    }
}
