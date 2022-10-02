package apap.tugas.SISDM.services;

import apap.tugas.SISDM.model.PresensiModel;
import apap.tugas.SISDM.repository.PresensiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PresensiServiceImpl implements PresensiService{
    @Autowired
    PresensiDb presensiDb;

    @Override
    public List<PresensiModel> getListPresensi() {
        return presensiDb.findAll();
    }
}
