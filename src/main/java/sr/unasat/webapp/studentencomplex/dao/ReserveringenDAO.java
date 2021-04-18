package sr.unasat.webapp.studentencomplex.dao;

import sr.unasat.webapp.studentencomplex.entity.Reserveringen;
import sr.unasat.webapp.studentencomplex.entity.Status;
import sr.unasat.webapp.studentencomplex.entity.Students;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class ReserveringenDAO {

    private static ReserveringenDAO instance = new ReserveringenDAO();

    private ReserveringenDAO(){}

    public static ReserveringenDAO getInstance(){
        return instance;
    }

    public static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("studentencomplex");

    public Reserveringen addReservering(Reserveringen reserveringen) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        Reserveringen newReservering = new Reserveringen();
        try {
            transaction = manager.getTransaction();
            transaction.begin();

            newReservering.setCheckinDate(reserveringen.getCheckinDate());
            newReservering.setCheckoutDate(reserveringen.getCheckoutDate());
            newReservering.setKamer(reserveringen.getKamer());

            Students student = manager.find(Students.class, reserveringen.getStudent().getStudentId());
            Status status = manager.find(Status.class, 1);
            student.setStatus(status);

            newReservering.setStudent(student);

            manager.persist(newReservering);

            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            manager.close();
        }
        return newReservering;
    }

    public List<Reserveringen> loadAllReserveringen() {

        List<Reserveringen> reserveringen = new ArrayList<>();
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();

            reserveringen = manager.createQuery("SELECT r FROM Reserveringen r", Reserveringen.class).getResultList();

            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            manager.close();
        }
        return reserveringen;
    }

    public Reserveringen getReservering(Long reserveringId) {
        Reserveringen reservering = new Reserveringen();
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();
            reservering = manager.find(Reserveringen.class, reserveringId);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            manager.close();
        }
        return reservering;
    }

    public boolean updateReservering(Reserveringen reservering) {
        boolean isUpdated;

        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();
            manager.merge(reservering);
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

    public boolean deleteReservering(Reserveringen reservering) {
        boolean isDeleted;

        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();
            manager.remove(manager.contains(reservering) ? reservering : manager.merge(reservering));
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
