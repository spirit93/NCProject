package ru.ncedu.service;

import javax.servlet.http.Part;
import java.io.*;
import java.net.URL;
import java.util.Properties;

/**
 * Created by Павел on 27.04.2016.
 */
public class PropertiesClass {

    public static String getProperties(String kay) {
        Properties properties = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        try (InputStream is = classLoader.getResourceAsStream("configs.properties")) {

            properties.load(is);

            return properties.getProperty(kay);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
