package sr.unasat.webapp.studentencomplex.dao;

import sr.unasat.webapp.studentencomplex.entity.Status;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class StatusDAO {

    private static StatusDAO instance = new StatusDAO();

    private StatusDAO(){}

    public static StatusDAO getInstance(){
        return instance;
    }

    public static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("studentencomplex");

    public List<Status> loadAllStatus() {

        List<Status> status = new ArrayList<Status>();
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();

            status = manager.createQuery("SELECT s FROM Status s", Status.class).getResultList();

            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            manager.close();
        }
        return status;
    }

    public Status getStatus(int statusId) {

        Status status = new Status();
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();
            status = manager.find(Status.class, statusId);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            manager.close();
        }
        return status;
    }

}
