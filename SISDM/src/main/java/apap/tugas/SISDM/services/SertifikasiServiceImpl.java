package apap.tugas.SISDM.services;

import apap.tugas.SISDM.model.SertifikasiModel;
import apap.tugas.SISDM.repository.SertifikasiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SertifikasiServiceImpl implements SertifikasiService{
    @Autowired
    SertifikasiDb sertifikasiDb;

    @Override
    public List<SertifikasiModel> getListSertifikasi() {
        return sertifikasiDb.findAll();
    }

    @Override
    public SertifikasiModel getSertifikasiById(Long Id) {
        Optional<SertifikasiModel> sertifikat = sertifikasiDb.findByIdSertifikat(Id);
        if(sertifikat.isPresent()){
            return sertifikat.get();
        }
        return null;
    }
}
