package sr.unasat.webapp.studentencomplex.controller;

import sr.unasat.webapp.studentencomplex.dao.IdentificatieTypesDAO;
import sr.unasat.webapp.studentencomplex.entity.IdentificatieTypes;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/identificatieTypes")
public class IdentificatieTypesController {

    private IdentificatieTypesDAO identificatieTypesDAO = IdentificatieTypesDAO.getInstance();

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<IdentificatieTypes> findAll() {
        return identificatieTypesDAO.loadAllIdentificatieTypes();
    }

    @Path("/getIdentificatieType")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public IdentificatieTypes getIdentificatieType(IdentificatieTypes identificatieType) {
        return identificatieTypesDAO.getIdentificatieType(identificatieType.getIdentificatieTypeId());
    }
}
