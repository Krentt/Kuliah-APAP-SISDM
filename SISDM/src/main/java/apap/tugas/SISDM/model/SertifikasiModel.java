package apap.tugas.SISDM.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="sertifikasi")
public class SertifikasiModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long idSertifikat;

    @NotNull
    @Size(max = 255)
    @Column(name = "nama")
    private String namaSertifikat;

    @OneToMany(mappedBy = "a_sertifikat", cascade = CascadeType.ALL)
    private List<SertifikasiKaryawanModel> sertifikatKaryawan;
}
