package sr.unasat.webapp.studentencomplex.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "identificatie_types")
public class IdentificatieTypes implements Serializable {

    private int identificatieTypeId;
    private String identificatieType;

    private List<Students> students = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "identificatie_type_id", unique = true)
    public int getIdentificatieTypeId() {
        return identificatieTypeId;
    }

    public void setIdentificatieTypeId(int identificatieTypeId) {
        this.identificatieTypeId = identificatieTypeId;
    }

    @Column(name = "identificatie_type", nullable = false)
    public String getIdentificatieType() {
        return identificatieType;
    }

    public void setIdentificatieType(String identificatieType) {
        this.identificatieType = identificatieType;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "identificatieType", fetch = FetchType.LAZY)
    public List<Students> getStudents() {
        return students;
    }

    public void setStudents(List<Students> students) {
        this.students = students;
    }
}
