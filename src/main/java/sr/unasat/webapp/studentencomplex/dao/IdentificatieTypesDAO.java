package sr.unasat.webapp.studentencomplex.dao;

import sr.unasat.webapp.studentencomplex.entity.IdentificatieTypes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class IdentificatieTypesDAO {

    private static IdentificatieTypesDAO instance = new IdentificatieTypesDAO();

    private IdentificatieTypesDAO(){}

    public static IdentificatieTypesDAO getInstance(){
        return instance;
    }

    public static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("studentencomplex");


    public List<IdentificatieTypes> loadAllIdentificatieTypes() {

        List<IdentificatieTypes> identificatieTypes = new ArrayList<>();

        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();

            identificatieTypes = manager.createQuery("SELECT i FROM IdentificatieTypes i", IdentificatieTypes.class).getResultList();

            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            manager.close();
        }
        return identificatieTypes;
    }

    public IdentificatieTypes getIdentificatieType(int identificatieTypeId) {
        IdentificatieTypes identificatieTypes = new IdentificatieTypes();
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();
            identificatieTypes = manager.find(IdentificatieTypes.class, identificatieTypeId);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            manager.close();
        }
        return identificatieTypes;
    }

}
