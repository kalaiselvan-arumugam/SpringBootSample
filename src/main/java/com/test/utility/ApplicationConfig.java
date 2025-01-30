
package com.test.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    private static final Logger logger = LogManager.getLogger(ApplicationConfig.class);
    private static final Properties properties = new Properties();

    static {
        new ApplicationConfig().loadApplicationProperties();
    }

    public boolean loadApplicationProperties() {
        try (InputStream configFile = new FileInputStream(Constants.CONFIGPATH)) {
            logger.debug("Loading config file: {}", Constants.CONFIGPATH);
            properties.load(configFile);
            logger.debug("Config loaded at: {}", DateUtil.getCurrentDateTimeAsString(Constants.DATE_12TIME_WITH_ENGLISH_MONTH));
            return true;
        } catch (IOException ex) {
            logger.error("Exception in loading config file: {}", GetStackTrace.getMessage(ex));
            return false;
        }
    }

    public static String getTag(String key) {
        String value = properties.getProperty(key);
        logger.debug("Getting property key: {}, value: {}", key, value);
        return (value != null) ? value.trim() : null;
    }
    
    public static List<String> getEmailRecipients() {
        // This method should return a list of email addresses
        return Arrays.asList("email1@example.com", "email2@example.com");
    } 
}

