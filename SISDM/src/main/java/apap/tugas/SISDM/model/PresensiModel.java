package apap.tugas.SISDM.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="presensi")
public class PresensiModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long idPresensi;

    @NotNull
    @Size(max = 255)
    @Column(name = "status")
    private String status;

    @NotNull
    @Column()
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime tanggal;

    @NotNull
    @Column(name = "waktu_masuk")
    @Temporal(TemporalType.TIMESTAMP)
    private Date waktuMasuk;

    @NotNull
    @Column(name = "waktu_keluar")
    @Temporal(TemporalType.TIMESTAMP)
    private Date waktuKeluar;

    @ManyToOne
    @JoinColumn(name = "id_karyawan", nullable = false)
    private KaryawanModel karyawan;

    @OneToMany(mappedBy = "presensi")
    private List<TugasModel> listTugas;
}
