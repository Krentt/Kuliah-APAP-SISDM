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
import java.time.LocalTime;
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
    private Long idPresensi;

    @NotNull
    @Column(name = "status")
    private int status;

    @NotNull
    @Column()
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tanggal;

    @Nullable
    @Column(name = "waktu_masuk")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime u_waktuMasuk;

    @Nullable
    @Column(name = "waktu_keluar")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime v_waktuKeluar;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_karyawan", referencedColumnName = "idKaryawan", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private KaryawanModel karyawan;

    @OneToMany(mappedBy = "presensi", fetch = FetchType.LAZY)
    private List<TugasModel> listTugas;
}
