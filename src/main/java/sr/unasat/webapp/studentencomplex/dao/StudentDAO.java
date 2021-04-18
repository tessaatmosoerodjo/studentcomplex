package sr.unasat.webapp.studentencomplex.dao;

import sr.unasat.webapp.studentencomplex.entity.Students;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    private static StudentDAO instance = new StudentDAO();

    private StudentDAO(){}

    public static StudentDAO getInstance(){
        return instance;
    }

    public static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("studentencomplex");

    public List<Students> loadAllStudent() {

        List<Students> students = new ArrayList<>();
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();

            students = manager.createQuery("SELECT s FROM Students s", Students.class).getResultList();

            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            manager.close();
        }
        return students;
    }

    public Students[] geStudentByName(String voornaam, String achternaam) {
//to see if student exits
        List<Students> student = new ArrayList<>();
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();
            student = manager.createQuery("SELECT s FROM Students s WHERE voornaam = '"+voornaam+"' AND achternaam = '"+achternaam+"'").getResultList();
            transaction.commit();
//Closing the transaction
            if (student != null) {
                for (Students students : student) {
                    if (students.getVoornaam().equals(voornaam) && students.getAchternaam().equals(achternaam)) {
                        return (student.toArray(new Students[0]));
                    }
                }
            }
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            manager.close();
            //releasing the factory resources
        }
        return null;
    }

    public Students getStudent(Long studentId) {
        Students student = new Students();
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();
            student = manager.find(Students.class, studentId);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            manager.close();
        }
        return student;
    }

    public boolean updateStudent(Students student) {
        boolean isUpdated;

        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();
            manager.merge(student);
            //Merge the state of the given entity into the current persistence context.
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

    public boolean deleteStudent(Students student) {
        boolean isDeleted;

        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();
            manager.remove(manager.contains(student) ? student : manager.merge(student));
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
