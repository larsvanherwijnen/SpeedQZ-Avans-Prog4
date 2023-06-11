package Dal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import Model.Catalog;

public class FileIO {
    public static Catalog readCatalog(final String category) {
        try {
            java.io.File file = new File("Resources/files/cat_" + category + ".txt");
            if (!file.exists()) {
                throw new IncorrectCatFileException("De file cat_" + category + ".txt bestaat niet.");
            }

            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                HashMap<String, Integer> options = new HashMap<>();
                String line;
                int lineCount = 0;
                String question = "";

                while ((line = br.readLine()) != null) {
                    lineCount++;
                    String[] parts = line.split(" ");
                    if (lineCount == 1 && parts.length == 2) {
                        throw new IncorrectCatFileException(
                                "Fout op line " + lineCount + ": Geen instructies gevonden.");
                    } else if (lineCount == 1) {
                        question = line;
                    }

                    if (lineCount != 1) {
                        if (parts.length != 2) {
                            throw new IncorrectCatFileException(
                                    "Fout op line " + lineCount + ": Ongeldig aantal argumenten.");
                        }

                        String name = parts[0];
                        String value = parts[1];

                        if (value.contains(",")) {
                            throw new IncorrectCatFileException(
                                    "Fout op line " + lineCount + ": kon waarden niet parsen.");
                        } else if (value.contains(".")) {
                            throw new IncorrectCatFileException("Fout op line " + lineCount + ": value is een double.");
                        }

                        options.put(name, Integer.parseInt(value));
                    }
                }

                Catalog catalog = new Catalog();
                catalog.setQuestion(question);
                catalog.setOptions(options);

                lineCount++;
                return catalog;
            } catch (IncorrectCatFileException e) {
                System.out.println(e.getMessage());
            }
        } catch (IncorrectCatFileException | IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}