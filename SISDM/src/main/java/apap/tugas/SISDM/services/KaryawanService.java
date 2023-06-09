package apap.tugas.SISDM.services;

import apap.tugas.SISDM.model.KaryawanModel;

import java.util.List;

public interface KaryawanService {
    // Method untuk mednapatkan daftar Karyawan yang telah tersimpan
    List<KaryawanModel> getListKaryawan();

    void addKaryawan(KaryawanModel karyawan);

    KaryawanModel getKaryawanByIdKaryawan(Long Id);

    KaryawanModel updateKaryawan(KaryawanModel karyawan);

    void deleteKaryawan(KaryawanModel karyawan);
}
