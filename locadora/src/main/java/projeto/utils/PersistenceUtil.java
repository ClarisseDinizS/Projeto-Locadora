package projeto.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class PersistenceUtil {

    private static SessionFactory sessionFactory;

    // Construtor privado para impedir a criação de instâncias
    private PersistenceUtil() {
    }

    // Método estático para obter a instância única da SessionFactory
    public static synchronized SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml")
                    .build();
            Metadata meta = new MetadataSources(ssr)
                    .getMetadataBuilder()
                    .build();
            sessionFactory = meta.getSessionFactoryBuilder().build();
        }
        return sessionFactory;
    }

    // Método estático para obter a sessão a partir da SessionFactory
    public static Session getSession() {
        return getSessionFactory().openSession();
    }
}
