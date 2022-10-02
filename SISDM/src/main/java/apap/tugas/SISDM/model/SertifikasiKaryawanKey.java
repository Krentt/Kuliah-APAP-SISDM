package apap.tugas.SISDM.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class SertifikasiKaryawanKey implements Serializable {
    @Column(name="id_karyawan")
    private Long idKaryawan;

    @Column(name = "id_sertifikat")
    private Long idSertifikat;
}
