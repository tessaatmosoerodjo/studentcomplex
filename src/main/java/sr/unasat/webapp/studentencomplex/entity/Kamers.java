package sr.unasat.webapp.studentencomplex.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "kamers")
public class Kamers implements Serializable {

    private Long kamerId;
    private String kamer;
    private Types type;
    private Status status;

    private List<Reserveringen> reserveringen = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kamer_id", unique = true)
    public Long getKamerId() {
        return kamerId;
    }

    public void setKamerId(Long kamerId) {
        this.kamerId = kamerId;
    }

    @Column(name = "kamer", nullable = false)
    public String getKamer() {
        return kamer;
    }

    public void setKamer(String kamer) {
        this.kamer = kamer;
    }

    @ManyToOne
    @JoinColumn(name = "status_id")
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @ManyToOne
    @JoinColumn(name = "type_id")
    public Types getType() {
        return type;
    }

    public void setType(Types type) {
        this.type = type;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "kamer", fetch = FetchType.LAZY)
    public List<Reserveringen> getReserveringen() {
        return reserveringen;
    }

    public void setReserveringen(List<Reserveringen> reserveringen) {
        this.reserveringen = reserveringen;
    }
}
