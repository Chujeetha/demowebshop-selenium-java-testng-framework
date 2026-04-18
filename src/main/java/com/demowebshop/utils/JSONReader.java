package com.demowebshop.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Parses generic JSON artifact configurations dynamically into matrix-ready Java Map arrays via Jackson API.
 */
public class JSONReader {
    private static final Logger logger = LoggerFactory.getLogger(JSONReader.class);

    public static List<Map<String, String>> getTestData(String jsonFileName) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = JSONReader.class.getClassLoader().getResourceAsStream(jsonFileName);
            if (inputStream == null) {
                throw new RuntimeException("Structural defect: Resource " + jsonFileName + " could not be localized within runtime constraints.");
            }
            logger.info("Decompressing parameterized runtime logic from core configuration payload: {}", jsonFileName);
            return mapper.readValue(inputStream, new TypeReference<List<Map<String, String>>>(){});
        } catch (Exception e) {
            throw new RuntimeException("JSON Structural Marshalling fault natively detected: ", e);
        }
    }
}
