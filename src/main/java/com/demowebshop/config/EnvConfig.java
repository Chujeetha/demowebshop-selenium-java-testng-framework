package com.demowebshop.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

/**
 * Singleton configuration reader resolving key-value matrices from application.properties.
 * Ensures resilient environment bootstrapping before execution orchestration.
 */
public class EnvConfig {
    private static final Logger logger = LoggerFactory.getLogger(EnvConfig.class);
    private static EnvConfig instance;
    private Properties properties;

    private EnvConfig() {
        properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                logger.error("Deterioration in Configuration Pipeline: application.properties could not be located.");
                return;
            }
            properties.load(input);
            logger.info("Environment configuration loaded successfully.");
        } catch (Exception e) {
            logger.error("Critical Failure in Config Resolution", e);
        }
    }

    public static synchronized EnvConfig getInstance() {
        if (instance == null) {
            instance = new EnvConfig();
        }
        return instance;
    }

    public String getProperty(String key) {
        String value = System.getProperty(key); // Allow override via JVM arguments
        if (value == null) {
            value = properties.getProperty(key);
        }
        return value;
    }

    public String getBaseUrl() { return getProperty("base.url"); }
    public String getBrowser() { return getProperty("browser"); }
    public String getUsername() { return getProperty("login.username"); }
    public String getPassword() { return getProperty("login.password"); }
    public int getDefaultTimeout() { return Integer.parseInt(getProperty("default.timeout.seconds")); }
}
