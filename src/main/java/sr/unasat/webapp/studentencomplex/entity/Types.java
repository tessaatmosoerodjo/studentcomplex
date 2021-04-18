package sr.unasat.webapp.studentencomplex.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "types")
public class Types implements Serializable {

    private int typeId;
    private String type;

    private List<Kamers> kamers = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id", unique = true)
    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    @Column(name = "type", nullable = false)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "type", fetch = FetchType.LAZY)
    public List<Kamers> getKamers() {
        return kamers;
    }

    public void setKamers(List<Kamers> kamers) {
        this.kamers = kamers;
    }
}
