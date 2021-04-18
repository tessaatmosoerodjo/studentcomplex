package sr.unasat.webapp.studentencomplex.patterns.handlers;

import sr.unasat.webapp.studentencomplex.entity.Kamers;

import java.util.List;

public class Request {

    private  int typeId;
    List<Kamers> kamers;

    public Request(int typeId)
    {
        this.typeId = typeId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public List<Kamers> getKamers() {
        return kamers;
    }

    public void setKamers(List<Kamers> kamers) {
        this.kamers = kamers;
    }
}
