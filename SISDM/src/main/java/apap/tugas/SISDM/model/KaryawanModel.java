package apap.tugas.SISDM.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
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
    @NotNull
    private Long idKaryawan;

    @NotNull
    @Size(max=255)
    @Column(name = "nama_depan", nullable = false)
    private String namaDepan;

    @NotNull
    @Size(max=255)
    @Column(name = "nama_belakang", nullable = false)
    private String namaBelakang;

    @NotNull
    @Size(max=10)
    @Column(name = "jenis_kelamin", nullable = false)
    private String jenisKelamin;

    @NotNull
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime tanggalLahir;

    @OneToMany(mappedBy = "karyawan")
    private List<SertifikasiKaryawanModel> sertifikatKaryawan;

    @OneToMany(mappedBy = "karyawan")
    private List<PresensiModel> listPresensi;

}
