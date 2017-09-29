package br.org.rpessoa.vstore.dao;

import br.org.rpessoa.vstore.util.PropertiesReader;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnitUtil;
import java.util.HashMap;
import java.util.Map;

public class ConnectionFactory {
    private static final String PERSISTENCE_UNIT = "VirtualStoreUnit";

    private static EntityManagerFactory factory;

    static {
        Map<String, String> properties = new HashMap<>();

        try {
            PropertiesReader reader = new PropertiesReader();
            String uri = new StringBuilder("jdbc:postgresql://")
                    .append(reader.read("database.connection.url"))
                    .append(":").append(reader.read("database.connection.port"))
                    .append("/")
                    .append(reader.read("database.name")).toString();

            //if (Boolean.getBoolean(reader.read("debug")))
                properties.put("eclipselink.logging.level", "fine");
            properties.put("javax.persistence.jdbc.driver", reader.read("database.connection.driver"));
            properties.put("javax.persistence.jdbc.url", uri);
            properties.put("javax.persistence.jdbc.user", reader.read("database.connection.username"));
            properties.put("javax.persistence.jdbc.password", reader.read("database.connection.password"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT, properties);
    }

    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }

    public static PersistenceUnitUtil getPersistenceUnitUtil() {
        return factory.getPersistenceUnitUtil();
    }
}
