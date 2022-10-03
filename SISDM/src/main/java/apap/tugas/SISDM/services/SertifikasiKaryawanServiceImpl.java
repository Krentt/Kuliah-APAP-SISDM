package apap.tugas.SISDM.services;

import apap.tugas.SISDM.model.KaryawanModel;
import apap.tugas.SISDM.model.SertifikasiKaryawanKey;
import apap.tugas.SISDM.model.SertifikasiKaryawanModel;
import apap.tugas.SISDM.model.SertifikasiModel;
import apap.tugas.SISDM.repository.SertifikasiKaryawanDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Transactional
public class SertifikasiKaryawanServiceImpl implements SertifikasiKaryawanService{
    @Autowired
    SertifikasiKaryawanDb sertifikasiKaryawanDb;

    @Override
    public void addSertifikasiKaryawan(SertifikasiKaryawanModel sertifikasiKaryawan) {
//        String noSertifikasi = generateId(sertifikasiKaryawan);
//        sertifikasiKaryawan.setD_noSertifikasi(noSertifikasi);
        sertifikasiKaryawanDb.save(sertifikasiKaryawan);
    }

    @Override
    public SertifikasiKaryawanModel getSertifikasiKaryawanById(SertifikasiKaryawanKey id) {
        Optional<SertifikasiKaryawanModel> sertfikasiKaryawan = sertifikasiKaryawanDb.findById(id);
        if(sertfikasiKaryawan.isPresent())
            return sertfikasiKaryawan.get();
        return null;
    }

    @Override
    public void deleteSertifikasiKaryawan(SertifikasiKaryawanKey id) {
        SertifikasiKaryawanModel sertfikasiKaryawan = getSertifikasiKaryawanById(id);
        sertifikasiKaryawanDb.delete(sertfikasiKaryawan);
    }
}
