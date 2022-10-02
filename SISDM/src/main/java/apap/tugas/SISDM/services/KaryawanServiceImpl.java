package apap.tugas.SISDM.services;

import apap.tugas.SISDM.model.KaryawanModel;
import apap.tugas.SISDM.model.SertifikasiKaryawanModel;
import apap.tugas.SISDM.repository.KaryawanDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class KaryawanServiceImpl implements KaryawanService {
    @Autowired
    KaryawanDb karyawanDb;

    @Override
    public List<KaryawanModel> getListKaryawan(){
        return karyawanDb.findAll();
    }

    @Override
    public void addKaryawan(KaryawanModel karyawan) {
        karyawanDb.save(karyawan);
    }

    @Override
    public KaryawanModel getKaryawanByIdKaryawan(Long Id) {
        Optional<KaryawanModel> karyawan = karyawanDb.findById(Id);
        if(karyawan.isPresent())
            return karyawan.get();
        return null;
    }

    @Override
    public KaryawanModel updateKaryawan(KaryawanModel karyawan) {
        karyawanDb.save(karyawan);
        return karyawan;
    }

    @Override
    public void deleteKaryawan(KaryawanModel karyawan) {
        karyawanDb.delete(karyawan);
    }
}
