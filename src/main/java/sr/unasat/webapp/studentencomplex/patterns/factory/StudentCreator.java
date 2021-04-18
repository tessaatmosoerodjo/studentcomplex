package sr.unasat.webapp.studentencomplex.patterns.factory;

import sr.unasat.webapp.studentencomplex.entity.Students;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class StudentCreator {

    public static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("studentencomplex");

    public Students createStudent(Students student) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        Students newStudent = new Students();
        try {
            transaction = manager.getTransaction();
            transaction.begin();

            newStudent.setVoornaam(student.getVoornaam());
            newStudent.setAchternaam(student.getAchternaam());
            newStudent.setAdres(student.getAdres());
            newStudent.setTelefoon(student.getTelefoon());
            newStudent.setIdNummer(student.getIdNummer());
            newStudent.setIdentificatieType(student.getIdentificatieType());
            newStudent.setStatus(student.getStatus());
            manager.persist(newStudent);

            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            manager.close();
        }
        return newStudent;
    }
}
