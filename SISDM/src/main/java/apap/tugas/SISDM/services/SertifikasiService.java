package apap.tugas.SISDM.services;

import apap.tugas.SISDM.model.SertifikasiModel;

import java.util.List;

public interface SertifikasiService {
    List<SertifikasiModel> getListSertifikasi();

    SertifikasiModel getSertifikasiById(Long Id);
}
