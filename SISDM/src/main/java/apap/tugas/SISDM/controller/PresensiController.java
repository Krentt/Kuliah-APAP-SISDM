package apap.tugas.SISDM.controller;

import apap.tugas.SISDM.model.*;
import apap.tugas.SISDM.services.KaryawanService;
import apap.tugas.SISDM.services.PresensiService;
import apap.tugas.SISDM.services.TugasService;
import apap.tugas.SISDM.services.TugasServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PresensiController {
    @Qualifier("presensiServiceImpl")
    @Autowired
    private PresensiService presensiService;

    @Autowired
    private TugasService tugasService;

    @Autowired
    private KaryawanService karyawanService;

    @GetMapping(value = "/presensi")
    private String daftarPresensi(Model model){
        List<PresensiModel> listPresensi = presensiService.getListPresensi();
        model.addAttribute(
                "listPresensi", listPresensi
        );
        return "presensi";
    }

    @GetMapping(value = "/presensi/tambah")
    private String addPresensiFormPage(Model model){
        PresensiModel presensi = new PresensiModel();

        List<TugasModel> listTugas = tugasService.getListTugasAvail(presensi);
        List<KaryawanModel> listKaryawan = karyawanService.getListKaryawan();
        List<TugasModel> listTugasNew = new ArrayList<>();

        presensi.setListTugas(listTugasNew);
        presensi.getListTugas().add(new TugasModel());

        model.addAttribute("presensi", presensi);
        model.addAttribute("listTugasExisting", listTugas);
        model.addAttribute("listKaryawan", listKaryawan);

        return "form-add-presensi";
    }

    @PostMapping(value = "/presensi/tambah", params = {"addRow"})
    private String addRowTugasMultiple(
            @ModelAttribute PresensiModel presensi,
            @ModelAttribute KaryawanModel karyawan,
            Model model
    ){
        if(presensi.getListTugas()==null){
            presensi.setListTugas(new ArrayList<>());
        }

        List<KaryawanModel> listKaryawan = karyawanService.getListKaryawan();
        presensi.getListTugas().add(new TugasModel());
        List<TugasModel> listTugas = tugasService.getListTugasAvail(presensi);

        model.addAttribute("listKaryawan", listKaryawan);
        model.addAttribute("presensi", presensi);
        model.addAttribute("listTugasExisting", listTugas);

        return "form-add-presensi";
    }

    @PostMapping(value = "/presensi/tambah", params = {"deleteRow"})
    private String deleteRowSertifikat(
            @ModelAttribute KaryawanModel karyawan,
            @ModelAttribute PresensiModel presensi,
            @RequestParam("deleteRow") Integer row,
            Model model
    ){
        final Integer rowId = Integer.valueOf(row);
        presensi.getListTugas().remove(rowId.intValue());

        List<KaryawanModel> listKaryawan = karyawanService.getListKaryawan();
        List<TugasModel> listTugas = tugasService.getListTugasAvail(presensi);

        model.addAttribute("presensi", presensi);
        model.addAttribute("listKaryawan", listKaryawan);
        model.addAttribute("listTugasExisting", listTugas);

        return "form-add-presensi";
    }

    @PostMapping(value = "/presensi/tambah", params={"save"})
    private String submitTambahPresensi(
            @ModelAttribute PresensiModel presensi,
            Model model
    ){
        KaryawanModel karyawan = karyawanService.getKaryawanByIdKaryawan(presensi.getKaryawan().getIdKaryawan());
        presensi.setKaryawan(karyawan);
        
        // Add value nilai
        if (presensiService.isTerlambat(presensi)){
            presensi.setStatus(0);
        } else {
            presensi.setStatus(1);
        }
        presensiService.addPresensi(presensi);
        if(presensi.getListTugas() == null){
            presensi.setListTugas(new ArrayList<>());
//            presensiService.addPresensi(presensi);
        } else {
            for (TugasModel tugas : presensi.getListTugas()){
                TugasModel tugasModel = tugasService.getTugasById(tugas.getIdTugas());
                tugasModel.setPresensi(presensi);
                tugasModel.setM_status(tugas.getM_status());
                tugasService.addTugas(tugasModel);
            }
        }
        presensiService.addPresensi(presensi);
        model.addAttribute("presensi", presensi);

        return "add-presensi";
    }

    @GetMapping(value = "/presensi/{id}")
    private String viewDetailPresensi(
            @PathVariable(value = "id") Long idPresensi,
            Model model
    ){
        PresensiModel presensi = presensiService.getPresensiById(idPresensi);
        List<TugasModel> listTugas= presensi.getListTugas();
        model.addAttribute("listTugas", listTugas);
        model.addAttribute("presensi", presensi);
        return "view-presensi";
    }

    @GetMapping(value = "/presensi/{id}/ubah")
    private String updateDetailKaryawanFormPage(
            @PathVariable(value = "id") Long idPresensi,
            Model model
    ){
        PresensiModel presensi = presensiService.getPresensiById(idPresensi);
        List<TugasModel> listTugasEx = tugasService.getListTugasAvail(presensi);
        List<KaryawanModel> listKaryawan = karyawanService.getListKaryawan();

        model.addAttribute("presensi", presensi);
        model.addAttribute("listTugasExisting", listTugasEx);
        model.addAttribute("listKaryawan", listKaryawan);

        return "form-update-presensi";
    }

    @PostMapping(value = "/presensi/{id}/ubah", params = {"addRowUpdate"})
    private String addRowTugasUpdateMultiple(
            @ModelAttribute PresensiModel presensi,
            @ModelAttribute KaryawanModel karyawan,
            Model model
    ){
        if(presensi.getListTugas()==null){
            presensi.setListTugas(new ArrayList<>());
        }

        List<KaryawanModel> listKaryawan = karyawanService.getListKaryawan();
        presensi.getListTugas().add(new TugasModel());
        List<TugasModel> listTugas = tugasService.getListTugasAvail(presensi);

        model.addAttribute("listKaryawan", listKaryawan);
        model.addAttribute("presensi", presensi);
        model.addAttribute("listTugasExisting", listTugas);

        return "form-update-presensi";
    }

    @PostMapping(value = "/presensi/{id}/ubah", params = {"deleteRowUpdate"})
    private String deleteRowUpdateSertifikat(
            @ModelAttribute KaryawanModel karyawan,
            @ModelAttribute PresensiModel presensi,
            @RequestParam("deleteRowUpdate") Integer row,
            Model model
    ){
        final Integer rowId = Integer.valueOf(row);
        TugasModel deletedTugas = presensi.getListTugas().get(rowId);
//        tugasService.getTugasById(deletedTugas.getIdTugas()).setPresensi(null);
        presensi.getListTugas().remove(rowId.intValue());

        List<KaryawanModel> listKaryawan = karyawanService.getListKaryawan();
        List<TugasModel> listTugas = tugasService.getListTugasAvail(presensi);

        model.addAttribute("presensi", presensi);
        model.addAttribute("listKaryawan", listKaryawan);
        model.addAttribute("listTugasExisting", listTugas);

        return "form-update-presensi";
    }

    @PostMapping(value = "/presensi/{id}/ubah", params={"saveUpdate"})
    private String submitUpdatePresensi(
            @ModelAttribute PresensiModel presensi,
            Model model
    ) {
        KaryawanModel karyawan = karyawanService.getKaryawanByIdKaryawan(presensi.getKaryawan().getIdKaryawan());
        presensi.setKaryawan(karyawan);
        List<TugasModel> listTugasDb = tugasService.getListTugasByIdPresensi(presensi.getIdPresensi());

        // Kosongin presensi list TUgas
        for (TugasModel tugas : listTugasDb){
            tugas.setPresensi(null);
            tugasService.addTugas(tugas);
        }

        // Add value nilai
        if (presensiService.isTerlambat(presensi)) {
            presensi.setStatus(0);
        } else {
            presensi.setStatus(1);
        }

        if (presensi.getListTugas() == null) {
            presensi.setListTugas(new ArrayList<>());
            presensiService.addPresensi(presensi);
        } else {
            for (TugasModel tugas : presensi.getListTugas()) {
                TugasModel tugasModel = tugasService.getTugasById(tugas.getIdTugas());
                tugasModel.setPresensi(presensi);
                tugasModel.setM_status(tugas.getM_status());
                tugasService.addTugas(tugasModel);
            }
        }
        model.addAttribute("presensi", presensi);

        return "update-presensi";
    }
}
