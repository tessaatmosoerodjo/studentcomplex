package sr.unasat.webapp.studentencomplex.patterns.handlers;

import sr.unasat.webapp.studentencomplex.entity.Kamers;

import java.util.List;

public abstract class RequestHandler {

    abstract protected int getTypeId();
    abstract protected List<Kamers> getKamers();

    protected RequestHandler successor;

    public void setSuccessor(RequestHandler successor) {
        this.successor = successor;
    }

    public void processRequest(Request request) {
        if (request.getTypeId() == (this.getTypeId())){
            request.setKamers(getKamers());
        } else if (successor != null) {
            successor.processRequest(request);
        }
    }
}
