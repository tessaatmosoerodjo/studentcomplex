package sr.unasat.webapp.studentencomplex.controller;

import sr.unasat.webapp.studentencomplex.dao.KamersDAO;
import sr.unasat.webapp.studentencomplex.entity.Kamers;
import sr.unasat.webapp.studentencomplex.patterns.handlers.DoubleRoomsHandler;
import sr.unasat.webapp.studentencomplex.patterns.handlers.Request;
import sr.unasat.webapp.studentencomplex.patterns.handlers.RequestHandler;
import sr.unasat.webapp.studentencomplex.patterns.handlers.SingleRoomsHandler;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/kamers")
public class KamersController {

    private KamersDAO kamersDAO = KamersDAO.getInstance();

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Kamers> findAll() {
        return kamersDAO.loadAllKamers();
    }

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Kamers add(Kamers kamer) {
        return kamersDAO.addKamer(kamer);
    }

    @Path("/update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Kamers update(Kamers kamer) {
        kamersDAO.updateKamer(kamer);
        return kamer;
    }

    @Path("/remove")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Kamers remove(Kamers kamer) {
        kamersDAO.deleteKamer(kamer);
        return kamer;
    }

    @Path("/getKamer")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Kamers getKamer(Kamers kamer) {
        return kamersDAO.getKamer(kamer.getKamerId());
    }


    @Path("/listKamersByType")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Kamers> listKamersByType(Kamers kamer) {

        Request request = new Request(kamer.getType().getTypeId());

        RequestHandler singleRooms = new SingleRoomsHandler();
        RequestHandler doubleRooms = new DoubleRoomsHandler();

        singleRooms.setSuccessor(doubleRooms);
        doubleRooms.setSuccessor(singleRooms);

        singleRooms.processRequest(request);

        return request.getKamers();
    }
}
