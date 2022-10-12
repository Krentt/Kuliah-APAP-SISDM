package apap.tugas.SISDM.services;

import apap.tugas.SISDM.model.SertifikasiKaryawanKey;
import apap.tugas.SISDM.model.SertifikasiKaryawanModel;
import apap.tugas.SISDM.repository.SertifikasiKaryawanDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<SertifikasiKaryawanModel> getAllSertifikasikaryawanById(Long idKaryawan) {
        return sertifikasiKaryawanDb.findAllById_IdKaryawan(idKaryawan);
    }
}
