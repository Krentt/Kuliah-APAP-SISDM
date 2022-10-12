package apap.tugas.SISDM.services;

import apap.tugas.SISDM.model.PresensiModel;
import apap.tugas.SISDM.model.TugasModel;
import apap.tugas.SISDM.repository.TugasDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TugasServiceImpl implements TugasService{
    @Autowired
    TugasDb tugasDb;

    @Override
    public List<TugasModel> getListTugas() {
        return tugasDb.findAll();
    }

    @Override
    public List<TugasModel> getListTugasAvail(PresensiModel presensi) {
        List<TugasModel> listTugas = getListTugas();
        List<TugasModel> listTugasAvail = new ArrayList<>();
        for (TugasModel tugas : listTugas){
            if (tugas.getPresensi() == null || tugas.getPresensi().getIdPresensi().equals(presensi.getIdPresensi()))
                listTugasAvail.add(tugas);
        }
        return listTugasAvail;
    }

    @Override
    public void addTugas(TugasModel tugas) {
        tugasDb.save(tugas);
    }

    @Override
    public TugasModel getTugasById(Long id) {
        Optional<TugasModel> tugas = tugasDb.findById(id);
        if(tugas.isPresent())
            return tugas.get();
        return null;
    }

    @Override
    public List<TugasModel> getListTugasByIdPresensi(Long idPresensi) {
        return tugasDb.findAllByPresensi_IdPresensi(idPresensi);
    }
}
