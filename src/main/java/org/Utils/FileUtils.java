package org.Utils;

import java.io.FileInputStream;
import java.io.IOException;

public class FileUtils {
    public static String readFileAsString(String filePath){
        StringBuilder content = new StringBuilder();
        FileInputStream fs = null;

        try{
            fs = new FileInputStream(filePath);
            int ch;
            while((ch = fs.read()) != -1){
                content.append((char) ch);
            }
        } catch (IOException e){
            throw new RuntimeException("Failed to read file: " +filePath,e);
        } finally {
            if(fs != null){
                try{
                    fs.close();
                } catch(IOException e){

                }
            }
        }
        return content.toString();
    }
}
