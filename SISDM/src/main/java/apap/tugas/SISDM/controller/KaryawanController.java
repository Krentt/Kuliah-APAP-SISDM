package apap.tugas.SISDM.controller;

import apap.tugas.SISDM.model.*;
import apap.tugas.SISDM.services.KaryawanService;
import apap.tugas.SISDM.services.SertifikasiKaryawanService;
import apap.tugas.SISDM.services.SertifikasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class KaryawanController {

    @Qualifier("karyawanServiceImpl")
    @Autowired
    private KaryawanService karyawanService;

    @Autowired
    private SertifikasiService sertifikasiService;

    @Autowired
    private SertifikasiKaryawanService sertifikasiKaryawanService;

    @GetMapping("/karyawan")
    private String daftarKaryawan(Model model){
        List<KaryawanModel> listKaryawan = karyawanService.getListKaryawan();
        model.addAttribute(
                "listKaryawan", listKaryawan
        );
        return "karyawan";
    }

    @GetMapping("/karyawan/tambah")
    public String addKaryawanFormPage(Model model){
        KaryawanModel karyawan = new KaryawanModel();

        List<SertifikasiModel> listSertifikasi = sertifikasiService.getListSertifikasi();
        List<SertifikasiKaryawanModel> listSertifikasiNew = new ArrayList<>();

        karyawan.setSertifikatKaryawan(listSertifikasiNew);
        karyawan.getSertifikatKaryawan().add(new SertifikasiKaryawanModel());

        model.addAttribute("karyawan", karyawan);
        model.addAttribute("listSertifikasiExisting", listSertifikasi);

        return "form-add-karyawan";

    }

    @PostMapping(value = "/karyawan/tambah", params = {"addRow"})
    private String addRowSertifikatMultiple(
            @ModelAttribute KaryawanModel karyawan,
            Model model
    ){
        if(karyawan.getSertifikatKaryawan()==null){
            karyawan.setSertifikatKaryawan(new ArrayList<>());
        }

        karyawan.getSertifikatKaryawan().add(new SertifikasiKaryawanModel());
        List<SertifikasiModel> listSertifikasi = sertifikasiService.getListSertifikasi();

        model.addAttribute("karyawan", karyawan);
        model.addAttribute("listSertifikasiExisting", listSertifikasi);

        return "form-add-karyawan";
    }

    @PostMapping(value = "/karyawan/tambah", params = {"deleteRow"})
    private String deleteRowSertifikat(
            @ModelAttribute KaryawanModel karyawan,
            @RequestParam("deleteRow") Integer row,
            Model model
    ){
        final Integer rowId = Integer.valueOf(row);
        karyawan.getSertifikatKaryawan().remove(rowId.intValue());

        List<SertifikasiModel> listSertifikasi = sertifikasiService.getListSertifikasi();

        model.addAttribute("karyawan", karyawan);
        model.addAttribute("listSertifikasiExisting", listSertifikasi);

        return "form-add-karyawan";
    }

    public String generateId(SertifikasiKaryawanModel sertifikasiKaryawan) {
        DateTimeFormatter formatterBd = DateTimeFormatter.ofPattern("yyyy");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMM");
        String tanggalLahir = formatterBd.format(sertifikasiKaryawan.getB_karyawan().getTanggalLahirKaryawan());
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

    @PostMapping(value = "/karyawan/tambah", params={"save"})
    private String submitTambahKaryawan(
            @ModelAttribute KaryawanModel karyawan,
            Model model
    ){
        try{
            if(karyawan.getSertifikatKaryawan() == null){
                karyawan.setSertifikatKaryawan(new ArrayList<>());
            } else {
                List<SertifikasiKaryawanModel> sertifikatKaryawan = karyawan.getSertifikatKaryawan();
                karyawan.setSertifikatKaryawan(new ArrayList<>());
                karyawanService.addKaryawan(karyawan);
                for (SertifikasiKaryawanModel sertifikasiKaryawan : sertifikatKaryawan){
                    sertifikasiKaryawan.setB_karyawan(karyawan);
                    SertifikasiModel sertifikasi = sertifikasiService.getSertifikasiById(sertifikasiKaryawan.getId().getIdSertifikat());
                    sertifikasiKaryawan.setA_sertifikat(sertifikasi);
                    sertifikasiKaryawan.setD_noSertifikasi(generateId(sertifikasiKaryawan));
                }
                karyawan.setSertifikatKaryawan(sertifikatKaryawan);
            }
            karyawanService.addKaryawan(karyawan);
            model.addAttribute("karyawan", karyawan);

            return "add-karyawan";
        } catch (Exception e){
            KaryawanModel karyawanBefore = karyawanService.getKaryawanByIdKaryawan(karyawan.getIdKaryawan());
            karyawanService.deleteKaryawan(karyawanBefore);
            model.addAttribute("errorMessage", "ERROR! Sertifikasi Karyawan Tidak Boleh SAMA! ¯\\_(ツ)_/¯");
            return "error";
        }
    }

    @GetMapping(value = "/karyawan/{id}")
    private String viewDetailKaryawan(
            @PathVariable(value = "id") Long idKaryawan,
            Model model
    ){
        KaryawanModel karyawan = karyawanService.getKaryawanByIdKaryawan(idKaryawan);
        List<SertifikasiKaryawanModel> listSertifikasiKaryawan = karyawan.getSertifikatKaryawan();
        model.addAttribute("listSertifikasiKaryawan", listSertifikasiKaryawan);
        model.addAttribute("karyawan", karyawan);
        return "view-karyawan";
    }

    @GetMapping(value = "/karyawan/{id}/ubah")
    private String updateDetailKaryawanFormPage(
            @PathVariable(value = "id") Long idKaryawan,
            Model model
    ){
        KaryawanModel karyawan = karyawanService.getKaryawanByIdKaryawan(idKaryawan);
        List<SertifikasiKaryawanModel> listSertifikasiKaryawan = karyawan.getSertifikatKaryawan();
        List<SertifikasiModel> listSertifikasi = sertifikasiService.getListSertifikasi();

        model.addAttribute("karyawan", karyawan);
        model.addAttribute("listSertifikasiKaryawan", listSertifikasiKaryawan);
        model.addAttribute("listSertifikasiExisting", listSertifikasi);

        return "form-update-karyawan";
    }

    @PostMapping(value = "/karyawan/{id}/ubah", params = {"addRowUpdate"})
    private String addRowSertifikatUbahMultiple(
            @ModelAttribute KaryawanModel karyawan,
            Model model
    ){
        if(karyawan.getSertifikatKaryawan()==null){
            karyawan.setSertifikatKaryawan(new ArrayList<>());
        }
        karyawan.getSertifikatKaryawan().add(new SertifikasiKaryawanModel());
        List<SertifikasiModel> listSertifikasi = sertifikasiService.getListSertifikasi();

        model.addAttribute("karyawan", karyawan);
        model.addAttribute("listSertifikasiExisting", listSertifikasi);

        return "form-update-karyawan";
    }

    @PostMapping(value = "/karyawan/{id}/ubah", params={"saveUpdate"})
    private String submitUpdateKaryawan(
            @ModelAttribute KaryawanModel karyawan,
            @PathVariable(value = "id") Long id,
            Model model
    ){
        List<SertifikasiKaryawanModel> sertifikasiKaryawanModelListByIdKaryawan = sertifikasiKaryawanService.getAllSertifikasikaryawanById(karyawan.getIdKaryawan());
        for (SertifikasiKaryawanModel sertifikasiKaryawan : sertifikasiKaryawanModelListByIdKaryawan){
            sertifikasiKaryawanService.deleteSertifikasiKaryawan(sertifikasiKaryawan.getId());
        }
        if(karyawan.getSertifikatKaryawan() == null){
            karyawan.setSertifikatKaryawan(new ArrayList<>());
        } else {
            for (SertifikasiKaryawanModel sertifikasiKaryawan : karyawan.getSertifikatKaryawan()){
                sertifikasiKaryawan.setB_karyawan(karyawan);
                SertifikasiModel sertifikasi = sertifikasiService.getSertifikasiById(sertifikasiKaryawan.getId().getIdSertifikat());
                sertifikasiKaryawan.setA_sertifikat(sertifikasi);
                sertifikasiKaryawan.setD_noSertifikasi(generateId(sertifikasiKaryawan));
            }
        }
        KaryawanModel updatedKaryawan = karyawanService.updateKaryawan(karyawan);

        model.addAttribute("karyawan", updatedKaryawan);

        return "update-karyawan";
    }

    @PostMapping(value = "/karyawan/{id}/ubah", params = {"deleteRow"})
    private String deleteRowUpdateSertifikat(
            @ModelAttribute KaryawanModel karyawan,
            @RequestParam("deleteRow") Integer row,
            Model model
    ){
        final Integer rowId = Integer.valueOf(row);
        karyawan.getSertifikatKaryawan().remove(rowId.intValue());

        List<SertifikasiModel> listSertifikasi = sertifikasiService.getListSertifikasi();

        model.addAttribute("karyawan", karyawan);
        model.addAttribute("listSertifikasiExisting", listSertifikasi);

        return "form-update-karyawan";
    }

    @PostMapping(value = "/karyawan/{id}/hapus", params = {"hapus"})
    private String deleteKaryawan(
            @PathVariable(value = "id") Long id,
            Model model
    ){
        KaryawanModel karyawan = karyawanService.getKaryawanByIdKaryawan(id);

        // null tugas agar tidak kehapus
        for (PresensiModel presensi : karyawan.getListPresensi()){
            for(TugasModel tugas : presensi.getListTugas()){
                tugas.setPresensi(null);
            }
        }

        karyawanService.deleteKaryawan(karyawan);
        model.addAttribute("karyawan", karyawan);
        return "delete-karyawan";
    }

    @GetMapping(value = "filter-sertifikasi")
    private String filterKaryawanPage(
            Model model
    ){
        List<SertifikasiModel> listSertifikasi = sertifikasiService.getListSertifikasi();
        List<KaryawanModel> listKaryawan = new ArrayList<>();
        model.addAttribute("listSertifikasi", listSertifikasi);
        model.addAttribute("listKaryawan", listKaryawan);
        return "filter-karyawan";
    }

    @PostMapping(value = "filter-sertifikasi")
    private String searchKaryawan(
            @RequestParam(value = "id-sertifikasi") Long idSertifikat,
            Model model
    ){
        SertifikasiModel sertifikat = sertifikasiService.getSertifikasiById(idSertifikat);
        List<SertifikasiKaryawanModel> listSertifikasiKaryawan = sertifikat.getSertifikatKaryawan();
        List<KaryawanModel> listKaryawan = new ArrayList<>();
        for (SertifikasiKaryawanModel sertifikasiKaryawan : listSertifikasiKaryawan){
            listKaryawan.add(sertifikasiKaryawan.getB_karyawan());
        }
        List<SertifikasiModel> listSertifikasi = sertifikasiService.getListSertifikasi();
        model.addAttribute("listSertifikasi", listSertifikasi);
        model.addAttribute("listKaryawan", listKaryawan);
        model.addAttribute("sertifikat", sertifikat);
        return "filter-karyawan";
    }

    // INSENTIF
    @GetMapping(value = "/karyawan/{id}/insentif")
    private String insentifKaryawanPage(
            @PathVariable(value = "id") Long idKaryawan,
            Model model
    ){
        HashMap<String, Integer> daftarInsentif = new HashMap<>();
        KaryawanModel karyawan = karyawanService.getKaryawanByIdKaryawan(idKaryawan);
        int insentifSertifikat = karyawan.getSertifikatKaryawan().size() * 3000;

        int insentifPresensi = 0;
        int insentifTugas = 0;
        List<PresensiModel> listPresensiKaryawan = karyawan.getListPresensi();
        for (PresensiModel presensi : listPresensiKaryawan){
            if(presensi.getStatus() == 0){
                insentifPresensi -= 1000;
            }

            for(TugasModel tugas : presensi.getListTugas()){
                if(tugas.getM_status().equals("2")){
                    insentifTugas += (1000*tugas.getL_storyPoint());
                }
            }
        }

        long totalInsentif = insentifSertifikat + insentifPresensi + insentifTugas;
        if (totalInsentif < 0)
            totalInsentif = 0L;

        daftarInsentif.put("Sertifikasi", insentifSertifikat);
        daftarInsentif.put("Presensi", insentifPresensi);
        daftarInsentif.put("Tugas", insentifTugas);

        karyawan.setZa_insentif(totalInsentif);
        karyawanService.addKaryawan(karyawan);
        model.addAttribute("daftarInsentif", daftarInsentif);
        model.addAttribute("karyawan", karyawan);
        return "insentif";
    }

}
