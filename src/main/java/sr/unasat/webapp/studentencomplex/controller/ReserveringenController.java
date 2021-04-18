package sr.unasat.webapp.studentencomplex.controller;

import sr.unasat.webapp.studentencomplex.dao.ReserveringenDAO;
import sr.unasat.webapp.studentencomplex.entity.Reserveringen;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("/reserveringen")
public class ReserveringenController {

    private ReserveringenDAO reserveringenDAO = ReserveringenDAO.getInstance();

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Reserveringen> findAll() {
        return reserveringenDAO.loadAllReserveringen();
    }

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Reserveringen add(Reserveringen reservering) {
        return reserveringenDAO.addReservering(reservering);
    }

    @Path("/update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Reserveringen update(Reserveringen reservering) {
        reserveringenDAO.updateReservering(reservering);
        return reservering;
    }

    @Path("/remove")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Reserveringen remove(Reserveringen reservering) {
        reserveringenDAO.deleteReservering(reservering);
        return reservering;
    }

    @Path("/getReservering")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Reserveringen getReservering(Reserveringen reservering) {
        return reserveringenDAO.getReservering(reservering.getReserveringId());
    }
}
