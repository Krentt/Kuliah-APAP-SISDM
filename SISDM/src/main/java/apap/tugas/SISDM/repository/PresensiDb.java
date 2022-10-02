package apap.tugas.SISDM.repository;

import apap.tugas.SISDM.model.PresensiModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PresensiDb extends JpaRepository<PresensiModel, Long> {
}
