package apap.tugas.SISDM.controller;

import apap.tugas.SISDM.model.KaryawanModel;
import apap.tugas.SISDM.model.PresensiModel;
import apap.tugas.SISDM.services.KaryawanService;
import apap.tugas.SISDM.services.PresensiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PresensiController {
    @Qualifier("presensiServiceImpl")
    @Autowired
    private PresensiService presensiService;

    @GetMapping(value = "/presensi")
    private String daftarPresensi(Model model){
        List<PresensiModel> listPresensi = presensiService.getListPresensi();
        model.addAttribute(
                "listPresensi", listPresensi
        );
        return "presensi";
    }
}
