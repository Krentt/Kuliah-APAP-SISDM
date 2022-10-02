package apap.tugas.SISDM.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tanggal;

    @Nullable
    @Column(name = "waktu_masuk")
    @Temporal(TemporalType.TIMESTAMP)
    private Date a_waktuMasuk;

    @Nullable
    @Column(name = "waktu_keluar")
    @Temporal(TemporalType.TIMESTAMP)
    private Date b_waktuKeluar;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_karyawan", referencedColumnName = "idKaryawan", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private KaryawanModel karyawan;

    @OneToMany(mappedBy = "presensi", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TugasModel> listTugas;
}
