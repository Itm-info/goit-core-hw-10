package itm.three;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class WordsCountInText {
    private String inputPath;
    private String wordsString;
    private String[] wordsArray;
    private WordsList wordsList;

    public WordsCountInText(String inputPath) {
        this.inputPath = inputPath;
        FileReaderStringSplitter();
        WordsCounterInOrder();
    }

    private void FileReaderStringSplitter() {
        try ( FileInputStream fileInputStream = new FileInputStream(inputPath) ) {
            byte[] bytes = new byte[fileInputStream.available()];
            bytes = fileInputStream.readAllBytes();
            wordsString = new String(bytes);
            wordsArray = wordsString.split("[\\s\\n]+");
            //for(String s:wordsArray) System.out.println(s); - for debugging
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void WordsCounterInOrder() {
        wordsList = new WordsList();
        for(String s:wordsArray) {
            wordsList.inc(s);
        }
    }
    public WordsList getWordsList(){
        return this.wordsList;
    }
}
