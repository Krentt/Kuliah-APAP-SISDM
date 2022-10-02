package apap.tugas.SISDM.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="sertifikasi_karyawan")
public class SertifikasiKaryawanModel {

    @EmbeddedId
    SertifikasiKaryawanKey id;

    @ManyToOne
    @MapsId("idKaryawan")
    @JoinColumn(name = "id_karyawan")
    private KaryawanModel b_karyawan;

    @ManyToOne
    @MapsId("idSertifikat")
    @JoinColumn(name = "id_sertifikat")
    private SertifikasiModel a_sertifikat;

    @NotNull
    @Column(nullable = false, name = "tanggalPengambilan")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate c_tanggalPengambilan;

    @NotNull
    @Size(max = 15)
    @Column(name = "no_sertifikasi")
    private String d_noSertifikasi;


}
