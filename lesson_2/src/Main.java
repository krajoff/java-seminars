import netscape.javascript.JSObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder = new StringBuilder("TEST");
        String filePath = "test.txt";
        ex2(stringBuilder, filePath);
    }


    static void ex2(StringBuilder stringBuilder, String filePath){

        Logger logger = Logger.getAnonymousLogger();
        logger.log(Level.INFO, "So bad");
        SimpleFormatter formatter = new SimpleFormatter();
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler("log.txt");
            fileHandler.setFormatter(formatter);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.addHandler(fileHandler);
        try (FileWriter fileWriter = new FileWriter(filePath, false)) {
            fileWriter.write(stringBuilder.toString());
        } catch (Exception e){
            logger.log(Level.WARNING, e.getMessage());
            e.printStackTrace();
        }
    }
}
