package projeto.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import projeto.model.domain.Classe;
import projeto.utils.PersistenceUtil;

public class ClasseDAO {

    public List<Classe> listarClasse() {
        try (Session session = PersistenceUtil.getSession()) {
            String hql = "FROM Classe ORDER BY id";
            Query<Classe> query = session.createQuery(hql, Classe.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Classe buscarClassePorId(int id) {
        try (Session session = PersistenceUtil.getSession()) {
            return session.get(Classe.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void inserirClasse(Classe classe) {
        try (Session session = PersistenceUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(classe);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void atualizarClasse(Classe classe) {
        try (Session session = PersistenceUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(classe);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void excluirClasse(int id) {
        try (Session session = PersistenceUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            Classe classe = session.get(Classe.class, id);
            if (classe != null) {
                session.remove(classe);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
