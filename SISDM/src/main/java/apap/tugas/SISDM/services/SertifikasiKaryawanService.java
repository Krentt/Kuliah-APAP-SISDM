package apap.tugas.SISDM.services;

import apap.tugas.SISDM.model.SertifikasiKaryawanKey;
import apap.tugas.SISDM.model.SertifikasiKaryawanModel;

import java.util.List;
import java.util.Optional;

public interface SertifikasiKaryawanService {
    void addSertifikasiKaryawan(SertifikasiKaryawanModel sertifikasiKaryawan);

    SertifikasiKaryawanModel getSertifikasiKaryawanById(SertifikasiKaryawanKey id);

    void deleteSertifikasiKaryawan(SertifikasiKaryawanKey id);

    List<SertifikasiKaryawanModel> getAllSertifikasikaryawanById(Long idKaryawan);
}
