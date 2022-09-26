package apap.tugas.SISDM.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class SertifikasiKaryawanKey implements Serializable {
    @Column(name="id_karyawan")
    private Long idKaryawan;

    @Column(name = "id_sertifikat")
    private Long idSertifikat;
}
