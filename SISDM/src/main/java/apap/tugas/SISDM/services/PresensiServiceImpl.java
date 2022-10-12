package apap.tugas.SISDM.services;

import apap.tugas.SISDM.model.PresensiModel;
import apap.tugas.SISDM.repository.PresensiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PresensiServiceImpl implements PresensiService{
    @Autowired
    PresensiDb presensiDb;

    @Override
    public List<PresensiModel> getListPresensi() {
        return presensiDb.findAll();
    }

    @Override
    public void addPresensi(PresensiModel presensi) {
        presensiDb.save(presensi);
    }

    @Override
    public boolean isTerlambat(PresensiModel presensi) {
        LocalTime jamMasuk = LocalTime.of(7, 0);
        return presensi.getU_waktuMasuk().isAfter(jamMasuk);
    }

    @Override
    public PresensiModel getPresensiById(Long id) {
        Optional<PresensiModel> presensi = presensiDb.findById(id);
        if (presensi.isPresent())
            return presensi.get();
        return null;
    }
}
