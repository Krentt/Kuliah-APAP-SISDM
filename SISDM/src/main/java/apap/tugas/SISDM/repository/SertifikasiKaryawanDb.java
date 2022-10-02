package apap.tugas.SISDM.repository;

import apap.tugas.SISDM.model.KaryawanModel;
import apap.tugas.SISDM.model.SertifikasiKaryawanKey;
import apap.tugas.SISDM.model.SertifikasiKaryawanModel;
import apap.tugas.SISDM.model.SertifikasiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SertifikasiKaryawanDb extends JpaRepository<SertifikasiKaryawanModel, SertifikasiKaryawanKey> {
}
