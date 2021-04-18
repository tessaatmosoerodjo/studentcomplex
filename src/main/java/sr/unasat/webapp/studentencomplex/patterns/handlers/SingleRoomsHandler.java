package sr.unasat.webapp.studentencomplex.patterns.handlers;

import sr.unasat.webapp.studentencomplex.dao.KamersDAO;
import sr.unasat.webapp.studentencomplex.entity.Kamers;

import java.util.List;

public class SingleRoomsHandler extends RequestHandler {
    @Override
    protected int getTypeId() {
        return 1;
    }

    @Override
    protected List<Kamers> getKamers() {
        KamersDAO kamersDAO = KamersDAO.getInstance();
        List<Kamers> kamers = kamersDAO.loadSingleRooms();
        return kamers;
    }
}
