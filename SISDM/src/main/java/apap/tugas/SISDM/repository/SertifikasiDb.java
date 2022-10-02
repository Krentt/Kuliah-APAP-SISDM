package apap.tugas.SISDM.repository;

import apap.tugas.SISDM.model.SertifikasiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SertifikasiDb extends JpaRepository<SertifikasiModel, Long> {
    Optional<SertifikasiModel>findByIdSertifikat(Long Id);
}
