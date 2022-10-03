package apap.tugas.SISDM.services;

import apap.tugas.SISDM.model.TugasModel;
import apap.tugas.SISDM.repository.TugasDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TugasServiceImpl implements TugasService{
    @Autowired
    TugasDb tugasDb;

    @Override
    public List<TugasModel> getListTugas() {
        return tugasDb.findAll();
    }
}
