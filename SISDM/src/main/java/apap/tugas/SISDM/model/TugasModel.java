package apap.tugas.SISDM.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.math.BigInteger;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tugas")
public class TugasModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long idTugas;

    @NotNull
    @Size(max = 255)
    @Column(name = "nama")
    private String namaTugas;

    @NotNull
    @Size(max = 255)
    @Column(name = "deskripsi")
    private String deskripsiTugas;

    @NotNull
    @Column(name = "story_point")
    private int storyPoint;

    @NotNull
    @Size(max = 10)
    @Column(name = "status")
    private String status;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_presensi", referencedColumnName = "idPresensi")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PresensiModel presensi;
}
