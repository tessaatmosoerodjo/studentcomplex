package sr.unasat.webapp.studentencomplex.dao;

import sr.unasat.webapp.studentencomplex.entity.Kamers;
import sr.unasat.webapp.studentencomplex.patterns.decorator.StatusCheckedOut;
import sr.unasat.webapp.studentencomplex.patterns.decorator.StatusDecorator;
import sr.unasat.webapp.studentencomplex.patterns.decorator.StatusInterface;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class KamersDAO {

    private static KamersDAO instance = new KamersDAO();

    private KamersDAO(){}

    public static KamersDAO getInstance(){
        return instance;
    }

    public static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("studentencomplex");

    public Kamers addKamer(Kamers kamer) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        Kamers newKamer = new Kamers();
        try {
            transaction = manager.getTransaction();
            transaction.begin();

            StatusInterface checkedOut = new StatusDecorator(new StatusCheckedOut());
            newKamer.setKamer(kamer.getKamer());
            newKamer.setType(kamer.getType());
            newKamer.setStatus(checkedOut.changeStatus());
            manager.persist(newKamer);

            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            manager.close();
        }
        return newKamer;
    }

    public List<Kamers> loadAllKamers() {

        List<Kamers> kamers = new ArrayList<>();
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();
            kamers = manager.createQuery("SELECT k FROM Kamers k", Kamers.class).getResultList();

            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            manager.close();
        }
        return kamers;
    }

    public List<Kamers> loadSingleRooms() {

        List<Kamers> kamers = new ArrayList<>();
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();

            kamers = manager.createQuery("SELECT k FROM Kamers k JOIN k.status as s JOIN k.type as t WHERE s.status LIKE 'Checked-out' AND t.type LIKE 'Single bed'", Kamers.class).getResultList();

            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            manager.close();
        }
        return kamers;
    }

    public List<Kamers> loadDoubleRooms() {

        List<Kamers> kamers = new ArrayList<>();
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();

            kamers = manager.createQuery("SELECT k FROM Kamers k JOIN k.status as s JOIN k.type as t WHERE s.status LIKE 'Checked-out' AND t.type LIKE 'Double bed'", Kamers.class).getResultList();

            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            manager.close();
        }
        return kamers;
    }

    public Kamers getKamer(Long kamerId) {
        Kamers kamer = new Kamers();
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();
            kamer = manager.find(Kamers.class, kamerId);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            manager.close();
        }
        return kamer;
    }

    public boolean updateKamer(Kamers kamer) {
        boolean isUpdated;

        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();
            manager.merge(kamer);
            transaction.commit();
            isUpdated = true;
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
            isUpdated = false;
        } finally {
            manager.close();
        }

        return isUpdated;
    }

    public boolean deleteKamer(Kamers kamer) {
        boolean isDeleted;

        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();
            manager.remove(manager.contains(kamer) ? kamer : manager.merge(kamer));
            transaction.commit();
            isDeleted = true;
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
            isDeleted = false;
        } finally {
            manager.close();
        }
        return isDeleted;
    }

}
