package org.example;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties properties = new Properties();
                properties.put(Environment.DRIVER,"org.postgresql.Driver");
                properties.put(Environment.URL,"jdbc:postgresql://localhost:5432/postgres");
                properties.put(Environment.USER,"postgres");
                properties.put(Environment.PASS,"Jashtykketty");
                properties.put(Environment.DIALECT,"org.hibernate.dialect.PostgreSQL9Dialect");
                properties.put(Environment.SHOW_SQL,"true");
                properties.put(Environment.HBM2DDL_AUTO,"update");
                configuration.setProperties(properties);

                configuration.addAnnotatedClass(Deliveryman.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            } catch (Exception e) {
                e.getMessage();
            }
        }
        return sessionFactory;
    }
}

