package projeto.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import projeto.model.domain.Diretor;
import projeto.utils.PersistenceUtil;


public class DiretorDAO{

    public List<Diretor> listarDiretor() {
        try (Session session = PersistenceUtil.getSession()) {
            String hql = "FROM Diretor ORDER BY id";
            Query<Diretor> query = session.createQuery(hql, Diretor.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Diretor buscarDiretorPorId(int id) {
        try (Session session = PersistenceUtil.getSession()) {
            return session.get(Diretor.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void inserirDiretor(Diretor diretor) {
        try (Session session = PersistenceUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(diretor);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void atualizarDiretor(Diretor diretor) {
        try (Session session = PersistenceUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(diretor);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void excluirDiretor(int id) {
        try (Session session = PersistenceUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            Diretor diretor = session.get(Diretor.class, id);
            if (diretor != null) {
                session.remove(diretor);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}