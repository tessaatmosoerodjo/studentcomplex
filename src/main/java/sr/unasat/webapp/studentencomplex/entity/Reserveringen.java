package sr.unasat.webapp.studentencomplex.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "reserveringen")
public class Reserveringen implements Serializable {

    private Long reserveringId;
    private Date checkinDate;
    private Date checkoutDate;
    private Kamers kamer;
    private Students student;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservering_id", unique = true)
    public Long getReserveringId() {
        return reserveringId;
    }

    public void setReserveringId(Long reserveringId) {
        this.reserveringId = reserveringId;
    }

    @Column(name = "checkin_date", nullable = false)
    public Date getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(Date checkinDate) {
        this.checkinDate = checkinDate;
    }

    @Column(name = "checkout_date", nullable = false)
    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    @ManyToOne
    @JoinColumn(name = "kamer_id")
    public Kamers getKamer() {
        return kamer;
    }

    public void setKamer(Kamers kamer) {
        this.kamer = kamer;
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "student_id")
    public Students getStudent() {
        return student;
    }

    public void setStudent(Students student) {
        this.student = student;
    }
}
