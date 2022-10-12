package apap.tugas.SISDM.controller;

import apap.tugas.SISDM.model.KaryawanModel;
import apap.tugas.SISDM.model.PresensiModel;
import apap.tugas.SISDM.model.TugasModel;
import apap.tugas.SISDM.services.KaryawanService;
import apap.tugas.SISDM.services.TugasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TugasController {
    @Qualifier("tugasServiceImpl")
    @Autowired
    private TugasService tugasService;

    @Autowired
    private KaryawanService karyawanService;

    @GetMapping(value = "/tambah-tugas")
    private String addTugasFormPage(Model model){
        TugasModel tugas = new TugasModel();
        model.addAttribute("tugas", tugas);

        return "form-add-tugas";
    }

    @PostMapping(value = "/tambah-tugas")
    private String addTugasSubmitPage(
            @ModelAttribute TugasModel tugas,
            Model model
    ){
        tugasService.addTugas(tugas);
        model.addAttribute("tugas", tugas);

        return "add-tugas";
    }

    @GetMapping(value = "filter-tugas")
    private String filterTugasPage(Model model){
        TugasModel tempTugas = new TugasModel();
        List<KaryawanModel> listKaryawan = karyawanService.getListKaryawan();
        List<TugasModel> listTugas = new ArrayList<>();
        model.addAttribute("listKaryawan",listKaryawan);
        model.addAttribute("tugas",tempTugas);
        model.addAttribute("listTugas",listTugas);
        return "filter-tugas";
    }

    @PostMapping(value = "filter-tugas")
    private String searchTugas(
            @RequestParam(value = "id-karyawan") Long idKaryawan,
            @RequestParam(value = "status") String status,
            Model model
    ){
        List<KaryawanModel> listKaryawan = karyawanService.getListKaryawan();
        KaryawanModel karyawan = karyawanService.getKaryawanByIdKaryawan(idKaryawan);
        List<PresensiModel> listPresensi = karyawan.getListPresensi();
        List<TugasModel> listTugas = new ArrayList<>();
        for (PresensiModel presensi : listPresensi){
            for (TugasModel tugas : presensi.getListTugas()){
                if(tugas.getM_status().equals(status)){
                    listTugas.add(tugas);
                }
            }
        }

        model.addAttribute("karyawan", karyawan);
        model.addAttribute("statusTugas", status);
        model.addAttribute("listTugas", listTugas);
        model.addAttribute("listKaryawan", listKaryawan);
        return "filter-tugas";
    }
}
