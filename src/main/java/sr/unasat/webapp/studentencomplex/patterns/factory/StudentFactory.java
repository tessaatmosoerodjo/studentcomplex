package sr.unasat.webapp.studentencomplex.patterns.factory;

import sr.unasat.webapp.studentencomplex.dao.StudentDAO;
import sr.unasat.webapp.studentencomplex.entity.Students;

public class StudentFactory {

    public static Students createRestStudent(Students student) {
        StudentDAO studentDAO = StudentDAO.getInstance();
        Students[] students = studentDAO.geStudentByName(student.getVoornaam(), student.getAchternaam());

        if (students != null) {
            System.out.println(student.getVoornaam() + " " + student.getAchternaam() + " ALREADY EXISTS.");
        } else {

            System.out.println("CREATING NEW STUDENT: " + student.getVoornaam() + " " + student.getAchternaam());
            return new StudentCreator().createStudent(student);
        }
        return null;
    }

    //Factory provides interface for creating objects, but allow subclasses to alter type of obj.
    //create object without exposing creation logic
}
