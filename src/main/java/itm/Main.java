package itm;

import itm.oneandtwo.PhoneBookReader;
import itm.three.WordsList;
import itm.two.SpaceSeparatedToJson;
import itm.three.WordsCountInText;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        // 1
        PhoneBookReader.readFile();

        // 2
        SpaceSeparatedToJson spp = new SpaceSeparatedToJson("file2.txt");
        spp.JSONArrayToFile("file2.json");

        // 3
        WordsCountInText wordsCount = new WordsCountInText("words.txt");
        WordsList wordsList = wordsCount.getWordsList();
        System.out.println();
        wordsList.printWordsList();
    }
}