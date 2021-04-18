package sr.unasat.webapp.studentencomplex.patterns.handlers;

import sr.unasat.webapp.studentencomplex.dao.KamersDAO;
import sr.unasat.webapp.studentencomplex.entity.Kamers;

import java.util.List;

public class DoubleRoomsHandler extends RequestHandler{
    @Override
    protected int getTypeId() {
        return 2;
    }

    @Override
    protected List<Kamers> getKamers() {
        KamersDAO kamersDAO = KamersDAO.getInstance();
        List<Kamers> kamers = kamersDAO.loadDoubleRooms();
        return kamers;
    }
}
