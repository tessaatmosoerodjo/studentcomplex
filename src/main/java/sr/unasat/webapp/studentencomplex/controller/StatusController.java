package sr.unasat.webapp.studentencomplex.controller;

import sr.unasat.webapp.studentencomplex.dao.StatusDAO;
import sr.unasat.webapp.studentencomplex.entity.Status;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/status")
public class StatusController {

    private StatusDAO statusDAO = StatusDAO.getInstance();

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Status> findAll() {
        return statusDAO.loadAllStatus();
    }

    @Path("/getStatus")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Status getStatus(Status status) {
        return statusDAO.getStatus(status.getStatusId());
    }
}
