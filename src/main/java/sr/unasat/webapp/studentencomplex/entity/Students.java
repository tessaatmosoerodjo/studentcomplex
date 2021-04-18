package sr.unasat.webapp.studentencomplex.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
public class Students implements Serializable {

    private Long studentId;
    private String voornaam;
    private String achternaam;
    private String adres;
    private int telefoon;
    private String idNummer;
    private IdentificatieTypes identificatieType;
    private Status status;

    private List<Reserveringen> reserveringen = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id", unique = true)
    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    @Column(name = "voornaam", nullable = false)
    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    @Column(name = "achternaam", nullable = false)
    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    @Column(name = "adres", nullable = false)
    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    @Column(name = "telefoon", nullable = false)
    public int getTelefoon() {
        return telefoon;
    }

    public void setTelefoon(int telefoon) {
        this.telefoon = telefoon;
    }

    @Column(name = "id_nummer", nullable = false)
    public String getIdNummer() {
        return idNummer;
    }

    public void setIdNummer(String idNummer) {
        this.idNummer = idNummer;
    }

    @ManyToOne
    @JoinColumn(name = "identificatie_type_id")
    public IdentificatieTypes getIdentificatieType() {
        return identificatieType;
    }

    public void setIdentificatieType(IdentificatieTypes identificatieType) {
        this.identificatieType = identificatieType;
    }

    @ManyToOne
    @JoinColumn(name = "status_id")
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    public List<Reserveringen> getReserveringen() {
        return reserveringen;
    }

    public void setReserveringen(List<Reserveringen> reserveringen) {
        this.reserveringen = reserveringen;
    }
}
