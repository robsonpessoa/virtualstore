package br.org.rpessoa.vstore.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    private static final String FILENAME = "application.properties";
    private Properties prop = null;
    private InputStream inputStream = null;

    public String read(String name) throws IOException {
        try {
            init();
            return prop.getProperty(name);
        } finally {
            finish();
        }
    }

    private void init() throws IOException {
        inputStream = getClass().getClassLoader().getResourceAsStream(FILENAME);
        if (inputStream == null)
            throw new RuntimeException("Could not load the properties file");
        prop = new Properties();
        prop.load(inputStream);
    }

    private void finish() {
        prop = null;
        if (inputStream != null)
            try {
                inputStream.close();
            } catch (IOException e1) {
                inputStream = null;
            }
    }
}
