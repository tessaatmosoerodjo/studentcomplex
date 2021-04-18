package sr.unasat.webapp.studentencomplex.dao;

import sr.unasat.webapp.studentencomplex.entity.Types;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class TypesDAO {

    private static TypesDAO instance = new TypesDAO();

    private TypesDAO(){}

    public static TypesDAO getInstance(){
        return instance;
    }

    public static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("studentencomplex");

    public List<Types> loadAllTypes() {

        List<Types> types = new ArrayList<>();
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();

            types = manager.createQuery("SELECT t FROM Types t", Types.class).getResultList();

            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            manager.close();
        }
        return types;
    }

    public Types getType(int typeId) {
        Types type = new Types();
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();
            type = manager.find(Types.class, typeId);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            manager.close();
        }
        return type;
    }

}
