package apap.tugas.SISDM.repository;

import apap.tugas.SISDM.model.KaryawanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KaryawanDb extends JpaRepository<KaryawanModel, Long> {
}
