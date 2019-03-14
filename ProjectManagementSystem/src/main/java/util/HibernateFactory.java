package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateFactory {
    private static final HibernateFactory INSTANCE = new HibernateFactory();

    private SessionFactory sessionFactory;


    private HibernateFactory() {
        //System.setProperty("org.jboss.logging.provider", "slf4j");
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public static HibernateFactory getInstance() {
        return INSTANCE;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
