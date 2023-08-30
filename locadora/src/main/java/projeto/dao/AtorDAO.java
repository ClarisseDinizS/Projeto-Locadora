package projeto.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import projeto.model.domain.Ator;
import projeto.utils.PersistenceUtil;

public class AtorDAO {

    public List<Ator> listarAtor() {
        try (Session session = PersistenceUtil.getSession()) {
            String hql = "FROM Ator ORDER BY id";
            Query<Ator> query = session.createQuery(hql, Ator.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Ator buscarAtorPorId(int id) {
        try (Session session = PersistenceUtil.getSession()) {
            return session.get(Ator.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void inserirAtor(Ator ator) {
        try (Session session = PersistenceUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(ator);
            // session.save(ator);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void atualizarAtor(Ator ator) {
        try (Session session = PersistenceUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(ator);
            // session.update(ator);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void excluirAtor(int id) {
        try (Session session = PersistenceUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            Ator ator = session.get(Ator.class, id);
            if (ator != null) {
                session.remove(ator);
                // session.delete(ator);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
