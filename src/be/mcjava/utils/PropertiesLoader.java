package be.mcjava.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
    private Properties properties = new Properties();
    
    public PropertiesLoader() {
        setupProperties();
    }
    
    public String getString(String property) {
        return properties.getProperty(property);
    }
    
    private void setupProperties() {
        try (InputStream appConfigPath = new FileInputStream("./application.properties")) {
            properties.load(appConfigPath);
        } catch (IOException e) {
            createProperties();
        }
    }
    
    private void createProperties() {
        addProperty("jdbcUrl","");
        addProperty("user","");
        addProperty("password","");
    }

    //TODO met een map als parameter kan je meerdere properties instellen met maar 1x een connectie naar het bestand.
    public void addProperty(String key, String value) {
        try (FileOutputStream fos = new FileOutputStream("./application.properties")) {
            properties.setProperty(key,value);
            properties.store(fos,null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}