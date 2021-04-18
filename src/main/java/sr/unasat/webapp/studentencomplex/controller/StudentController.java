package sr.unasat.webapp.studentencomplex.controller;

import sr.unasat.webapp.studentencomplex.dao.StudentDAO;
import sr.unasat.webapp.studentencomplex.entity.Students;
import sr.unasat.webapp.studentencomplex.patterns.factory.StudentFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/students")
public class StudentController {

    private StudentDAO studentsDao = StudentDAO.getInstance();

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Students> findAll() {
        return studentsDao.loadAllStudent();
    }

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Students add(Students student) {
        return StudentFactory.createRestStudent(student);
    }

    @Path("/update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Students update(Students student) {
        studentsDao.updateStudent(student);
        return student;
    }

    @Path("/remove")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Students remove(Students student) {
        studentsDao.deleteStudent(student);
        return student;
    }

    @Path("/get")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Students getStudent(Students student) {
        return studentsDao.getStudent(student.getStudentId());
    }

}
