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
@Table(name="sertifikasi_karyawan")
public class SertifikasiKaryawanModel {

    @EmbeddedId
    SertifikasiKaryawanKey id;

    @ManyToOne
    @MapsId("idKaryawan")
    @JoinColumn(name = "id_karyawan")
    private KaryawanModel karyawan;

    @ManyToOne
    @MapsId("idSertifikat")
    @JoinColumn(name = "id_sertifikat")
    private SertifikasiModel sertifikat;

    @NotNull
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime tanggalPengambilan;

    @NotNull
    @Size(max = 14)
    @Column(name = "no_sertifikasi")
    private String noSertifikasi;


}
