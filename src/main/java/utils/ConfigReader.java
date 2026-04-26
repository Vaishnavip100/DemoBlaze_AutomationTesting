package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
    Properties prop;
    public ConfigReader() {
        try {
            FileInputStream fis=new FileInputStream("src/test/resources/config.properties");
            prop=new Properties();
            prop.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getBrowser() {
        return prop.getProperty("browser");
    }

    public String getBaseUrl() {
        return prop.getProperty("baseUrl");
    }

    public int getTimeout() {
        return Integer.parseInt(prop.getProperty("timeout"));
    }
    
    public String getUsername() {
        return prop.getProperty("username");
    }

    public String getPassword() {
        return prop.getProperty("password");
    }
    
    public boolean isHeadless() {
        return prop.getProperty("headless").equalsIgnoreCase("true");
    }
}