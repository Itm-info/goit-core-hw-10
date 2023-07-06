package itm.oneandtwo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneBookReader {
    public static void readFile() {
        try(FileInputStream fileInputStream = new FileInputStream("file.txt")) {
            Pattern pattern = Pattern.compile("(^\\(\\d{3}\\)\\s\\d{3}-\\d{4}$)|" + //(xxx) xxx-xxxx
                                                    "(^\\d{3}-\\d{3}-\\d{4}$)");          // xxx-xxx-xxxx
            Scanner scanner = new Scanner(fileInputStream);
            String line="";
            while(scanner.hasNextLine()) {
                line = scanner.nextLine();
                Matcher matcher = pattern.matcher(line);
                if(matcher.find()) System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
