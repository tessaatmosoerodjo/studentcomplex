package sr.unasat.webapp.studentencomplex.controller;

import sr.unasat.webapp.studentencomplex.dao.TypesDAO;
import sr.unasat.webapp.studentencomplex.entity.Types;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/types")
public class TypesController {

    private TypesDAO typesDAO = TypesDAO.getInstance();

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Types> findAll() {
        return typesDAO.loadAllTypes();
    }

    @Path("/getType")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Types getType(Types type) {
        return typesDAO.getType(type.getTypeId());
    }

}
