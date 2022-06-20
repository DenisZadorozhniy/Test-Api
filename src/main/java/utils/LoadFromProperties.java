package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoadFromProperties {

    public static String getProperties(String property) {

        String path = "src/main/resources/config.properties";
        Properties properties = new Properties();

        try (FileInputStream fileProperties = new FileInputStream(path)) {
            properties = new Properties();
            properties.load(fileProperties);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties.getProperty(property);
    }

    public static String getDataProperties(String property) {
        String path = "src/main/resources/data.properties";
        Properties properties = new Properties();

        try (FileInputStream fileProperties = new FileInputStream(path)) {
            properties = new Properties();
            properties.load(fileProperties);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties.getProperty(property);
    }
}