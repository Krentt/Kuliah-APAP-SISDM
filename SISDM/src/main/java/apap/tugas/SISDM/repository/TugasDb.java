package apap.tugas.SISDM.repository;

import apap.tugas.SISDM.model.PresensiModel;
import apap.tugas.SISDM.model.TugasModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TugasDb extends JpaRepository<TugasModel, Long> {
    List<TugasModel> findAllByPresensi_IdPresensi(Long idPresensi);
}
