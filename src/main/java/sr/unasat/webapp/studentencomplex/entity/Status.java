package sr.unasat.webapp.studentencomplex.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "status")
public class Status implements Serializable {

    private int statusId;
    private String status;

    private List<Students> students = new ArrayList<>();
    private List<Kamers> kamers = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id", unique = true)
    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    @Column(name = "status", nullable = false)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "status", fetch = FetchType.LAZY)
    public List<Students> getStudents() {
        return students;
    }

    public void setStudents(List<Students> students) {
        this.students = students;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "status", fetch = FetchType.LAZY)
    public List<Kamers> getKamers() {
        return kamers;
    }

    public void setKamers(List<Kamers> kamers) {
        this.kamers = kamers;
    }


}
