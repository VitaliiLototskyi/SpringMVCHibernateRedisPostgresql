package utils;

import model.Developer;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public  static  SessionFactory getSessionFactory() {
        if(sessionFactory == null) {
            try {
                Configuration configuration =  new Configuration().configure();
                configuration.addAnnotatedClass(Developer.class);
                //to connect to DB using class, otherwise hibernate.cfg.xml needed
                configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
                configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/postgres");
                configuration.setProperty("hibernate.connection.username", "postgres");
                configuration.setProperty("hibernate.connection.password", "postgres");
                configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
                configuration.setProperty("hibernate.show_sql", "true");

                StandardServiceRegistryBuilder builder =  new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("exception" + e);
            }
        }
        return sessionFactory;
    }

}
