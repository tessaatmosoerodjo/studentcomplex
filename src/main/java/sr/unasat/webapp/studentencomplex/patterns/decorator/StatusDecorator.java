package sr.unasat.webapp.studentencomplex.patterns.decorator;

public class StatusDecorator extends StatusInterfaceImplementatie{

    public StatusDecorator(StatusInterface status) {
        super(status);
    }
    //attach new behaviors to objects by
    // placing these objects inside special wrapper objects that contain the behaviors.
}
