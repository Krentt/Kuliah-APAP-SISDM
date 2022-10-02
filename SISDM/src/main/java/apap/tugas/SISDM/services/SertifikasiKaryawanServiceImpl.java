package apap.tugas.SISDM.services;

import apap.tugas.SISDM.model.KaryawanModel;
import apap.tugas.SISDM.model.SertifikasiKaryawanModel;
import apap.tugas.SISDM.repository.SertifikasiKaryawanDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Transactional
public class SertifikasiKaryawanServiceImpl implements SertifikasiKaryawanService{
    @Autowired
    SertifikasiKaryawanDb sertifikasiKaryawanDb;

    public String generateId(SertifikasiKaryawanModel sertifikasiKaryawan) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMM");
        String tanggalLahir = formatter.format(sertifikasiKaryawan.getB_karyawan().getTanggalLahirKaryawan());
        String tanggalPengambilan = formatter.format(sertifikasiKaryawan.getC_tanggalPengambilan());
        String codeAwal = String.valueOf(Integer.parseInt(tanggalLahir) + Integer.parseInt(tanggalPengambilan));

        String namaSertifikat = sertifikasiKaryawan.getA_sertifikat().getNamaSertifikat().toLowerCase();
        int temp = namaSertifikat.charAt(0);
        int codeAbjad = temp - 96;
        String codeNamaSertif = String.format("%02d", codeAbjad);

        String namaKaryawan = sertifikasiKaryawan.getB_karyawan().getA_namaDepanKaryawan().toLowerCase();
        int temp2 = namaKaryawan.charAt(0);
        int codeAbjadKaryawan = temp2 - 96;
        String codeNamaKaryawan = String.format("%02d", codeAbjadKaryawan);

        String codeIdKaryawan = String.format("%03d", (sertifikasiKaryawan.getB_karyawan().getIdKaryawan()));

        return "SER" + codeAwal + codeNamaSertif + codeNamaKaryawan + codeIdKaryawan;
    }
    @Override
    public void addSertifikasiKaryawan(SertifikasiKaryawanModel sertifikasiKaryawan) {
        String noSertifikasi = generateId(sertifikasiKaryawan);
        sertifikasiKaryawan.setD_noSertifikasi(noSertifikasi);
        sertifikasiKaryawanDb.save(sertifikasiKaryawan);
    }
}
