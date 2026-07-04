package com.fahrizal.hotel.controller;

import com.fahrizal.hotel.exception.KamarPenuhException;
import com.fahrizal.hotel.exception.ValidasiTanggalException;
import com.fahrizal.hotel.model.Reservasi;
import com.fahrizal.hotel.service.ReservasiService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ReservasiController {

    private final ReservasiService reservasiService;

    @Autowired
    public ReservasiController(ReservasiService reservasiService) {
        this.reservasiService = reservasiService;
    }

    /**
     * Menampilkan halaman utama: form pemesanan dan daftar reservasi
     */
    @GetMapping("/")
    public String index(Model model) {
        if (!model.containsAttribute("reservasi")) {
            model.addAttribute("reservasi", new Reservasi());
        }
        model.addAttribute("daftarReservasi", reservasiService.getAllReservasi());
        model.addAttribute("kapasitasKamar", reservasiService.getKapasitasKamarInfo());
        return "index";
    }

    /**
     * Memproses pengajuan reservasi baru
     */
    @PostMapping("/reservasi")
    public String buatReservasi(@Valid @ModelAttribute("reservasi") Reservasi reservasi, 
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        // Cek jika ada error validasi atau error binding tipe data (seperti parsing tanggal)
        if (bindingResult.hasErrors()) {
            String errorMsg = "Validasi gagal: ";
            if (bindingResult.getFieldError() != null) {
                errorMsg += bindingResult.getFieldError().getDefaultMessage();
            } else {
                errorMsg += "Format data input tidak valid.";
            }
            redirectAttributes.addFlashAttribute("errorMessage", errorMsg);
            redirectAttributes.addFlashAttribute("reservasi", reservasi);
            return "redirect:/";
        }

        try {
            reservasiService.buatReservasi(reservasi);
            redirectAttributes.addFlashAttribute("successMessage", 
                    "Reservasi atas nama " + reservasi.getNamaTamu() + " berhasil dibuat!");
            return "redirect:/";
        } catch (KamarPenuhException | ValidasiTanggalException e) {
            // Tangkap exception bisnis dan kirim pesan error sebagai flash attribute
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            redirectAttributes.addFlashAttribute("reservasi", reservasi);
            return "redirect:/";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Terjadi kesalahan internal: " + e.getMessage());
            redirectAttributes.addFlashAttribute("reservasi", reservasi);
            return "redirect:/";
        }
    }

    /**
     * Memproses penghapusan reservasi
     */
    @PostMapping("/reservasi/hapus/{id}")
    public String hapusReservasi(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            reservasiService.hapusReservasi(id);
            redirectAttributes.addFlashAttribute("successMessage", "Data reservasi berhasil dihapus!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Gagal menghapus: " + e.getMessage());
        }
        return "redirect:/";
    }
}
