package projeto;

import org.hibernate.Session;
import org.hibernate.Transaction;

import projeto.model.domain.Ator;
import projeto.utils.PersistenceUtil;

/**
 * Hello world!
 *
 */
public class TesteConexao {
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        // StandardServiceRegistry ssr = new
        // StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        // Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        // SessionFactory factory = meta.getSessionFactoryBuilder().build();
        // Session session = factory.openSession();

        Session session = PersistenceUtil.getSession();

        Transaction t = session.beginTransaction();

        Ator ator = new Ator();

        ator.setNome("teste");

        // session.save(u);

        session.persist(ator);

        t.commit();

        session.close();
        // factory.close();
        // ssr.close();

    }
}
