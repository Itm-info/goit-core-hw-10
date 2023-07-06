package itm.two;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class SpaceSeparatedToJson {
    private int attrCount;
    private String[] header;
    private Deque<User> userDeque = new ArrayDeque<>();
    private JSONArray jsonArray = new JSONArray();

    private class User {
        private LinkedHashMap<String, String> attrs;

        public User(){
            this.attrs = new LinkedHashMap<String, String>();
        }
        public void addAttr(String attr, String value) {
            this.attrs.put(attr, value);
        }
        public String getAttr(String attr) {
            return this.attrs.get(attr);
        }
    }

    public SpaceSeparatedToJson(String path) {
        try (FileInputStream fileInputStream = new FileInputStream(path)) {
            Scanner scanner = new Scanner(fileInputStream);
            String[] line;
            if(scanner.hasNextLine()) {
                header = scanner.nextLine().split(" ");
                attrCount = header.length;
            }
            else return;
            while(scanner.hasNextLine()) {
                line = scanner.nextLine().split(" ");
                if (line.length != attrCount) {
                    System.out.println("Values number per row found to be fluctuating");
                    return;
                }
                userDeque.add(new User());
                for(int i=0; i<attrCount; ++i) {
                    userDeque.getLast().addAttr(header[i], line[i]);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void UserDequeToJSONArray() {
        while(userDeque.peek()!=null){
            User user = userDeque.poll();
            JSONObject jsonObject = new JSONObject();
            for(String key : header) {
                jsonObject.put(key,user.getAttr(key));
            }
            jsonArray.put(jsonObject);
        }
    }

    public void JSONArrayToFile(String path) {
        UserDequeToJSONArray();
        try (FileOutputStream fileOutputStream = new FileOutputStream(path)) {
            fileOutputStream.write(jsonArray.toString().getBytes());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
