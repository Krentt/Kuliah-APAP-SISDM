package apap.tugas.SISDM.controller;

import apap.tugas.SISDM.model.*;
import apap.tugas.SISDM.services.KaryawanService;
import apap.tugas.SISDM.services.PresensiService;
import apap.tugas.SISDM.services.TugasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

        List<TugasModel> listTugas = tugasService.getListTugas();
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
        List<TugasModel> listTugas = tugasService.getListTugas();

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
        List<TugasModel> listTugas = tugasService.getListTugas();

        model.addAttribute("presensi", presensi);
        model.addAttribute("listKaryawan", listKaryawan);
        model.addAttribute("listTugasExisting", listTugas);

        return "form-add-presensi";
    }
}
