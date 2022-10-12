package apap.tugas.SISDM.services;

import apap.tugas.SISDM.model.KaryawanModel;
import apap.tugas.SISDM.model.PresensiModel;

import java.util.List;

public interface PresensiService {
    List<PresensiModel> getListPresensi();

    void addPresensi(PresensiModel presensi);

    boolean isTerlambat(PresensiModel presensi);

    PresensiModel getPresensiById(Long id);
}
