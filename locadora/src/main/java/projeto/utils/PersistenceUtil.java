package projeto.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class PersistenceUtil {

    private static Session s = null;

    private static ThreadLocal<Session> threadLocalSession = new ThreadLocal<Session>() {
        @Override
        protected Session initialValue() {
            StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
            Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
            SessionFactory factory = meta.getSessionFactoryBuilder().build();
            return factory.openSession();
        }
    };

    public static Session getSession() {
        return threadLocalSession.get();
    }
}