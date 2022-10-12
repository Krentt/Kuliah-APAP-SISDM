package apap.tugas.SISDM.services;

import apap.tugas.SISDM.model.PresensiModel;
import apap.tugas.SISDM.model.SertifikasiModel;
import apap.tugas.SISDM.model.TugasModel;

import java.util.List;

public interface TugasService {
    List<TugasModel> getListTugas();

    List<TugasModel> getListTugasAvail(PresensiModel presensi);

    void addTugas(TugasModel tugas);

    TugasModel getTugasById(Long id);
    List<TugasModel> getListTugasByIdPresensi(Long idPresensi);
}
