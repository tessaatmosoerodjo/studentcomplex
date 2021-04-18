package sr.unasat.webapp.studentencomplex.patterns.decorator;


import sr.unasat.webapp.studentencomplex.entity.Status;

public abstract class StatusInterfaceImplementatie implements StatusInterface{

    protected StatusInterface status;

    public StatusInterfaceImplementatie(StatusInterface status){
        this.status = status;
    }

    public Status changeStatus(){
        return status.changeStatus();
    }

}
