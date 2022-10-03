package apap.tugas.SISDM.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="karyawan")
public class KaryawanModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long idKaryawan;

    @NotNull
    @Size(max=255)
    @Column(name = "nama_depan", nullable = false)
    private String a_namaDepanKaryawan;

    @NotNull
    @Size(max=255)
    @Column(name = "nama_belakang", nullable = false)
    private String b_namaBelakangKaryawan;

    @NotNull
    @Size(max=10)
    @Column(name = "jenis_kelamin", nullable = false)
    private String jenisKelaminKaryawan;

    @NotNull
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tanggalLahirKaryawan;

    @NotNull
    @Size(max=255)
    @Column(name = "email", nullable = false)
    private String z_emailKaryawan;

    @OneToMany(mappedBy = "b_karyawan", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<SertifikasiKaryawanModel> sertifikatKaryawan;

    @OneToMany(mappedBy = "karyawan", cascade = CascadeType.ALL)
    private List<PresensiModel> listPresensi;

}
