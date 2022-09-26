package apap.tugas.SISDM.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KaryawanController {
    @GetMapping("/karyawan")
    private String daftarKaryawan(){
        return "karyawan";
    }
}
