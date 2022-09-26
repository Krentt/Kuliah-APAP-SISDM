package apap.tugas.SISDM.services;

import apap.tugas.SISDM.model.KaryawanModel;
import apap.tugas.SISDM.repository.KaryawanDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class KaryawanServiceImpl implements KaryawanService {
    @Autowired
    KaryawanDb karyawanDb;

    @Override
    public List<KaryawanModel> getListKaryawan(){
        return karyawanDb.findAll();
    }
}
