package apap.tugas.SISDM.services;

import apap.tugas.SISDM.model.SertifikasiKaryawanKey;
import apap.tugas.SISDM.model.SertifikasiKaryawanModel;

public interface SertifikasiKaryawanService {
    void addSertifikasiKaryawan(SertifikasiKaryawanModel sertifikasiKaryawan);

    SertifikasiKaryawanModel getSertifikasiKaryawanById(SertifikasiKaryawanKey id);

    void deleteSertifikasiKaryawan(SertifikasiKaryawanKey id);
}
