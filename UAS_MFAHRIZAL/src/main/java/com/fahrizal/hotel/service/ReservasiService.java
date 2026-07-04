package com.fahrizal.hotel.service;

import com.fahrizal.hotel.exception.KamarPenuhException;
import com.fahrizal.hotel.exception.ValidasiTanggalException;
import com.fahrizal.hotel.model.Reservasi;
import com.fahrizal.hotel.repository.ReservasiRepository;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservasiService {

    private final ReservasiRepository reservasiRepository;

    // Batasan kapasitas per tipe kamar
    private static final Map<String, Integer> KAPASITAS_KAMAR = new HashMap<>();

    static {
        KAPASITAS_KAMAR.put("Single Room", 3);
        KAPASITAS_KAMAR.put("Double Room", 2);
        KAPASITAS_KAMAR.put("Deluxe Room", 2);
        KAPASITAS_KAMAR.put("Suite Room", 1);
    }

    @Autowired
    public ReservasiService(ReservasiRepository reservasiRepository) {
        this.reservasiRepository = reservasiRepository;
    }

    /**
     * Mengambil seluruh data reservasi
     */
    public List<Reservasi> getAllReservasi() {
        return reservasiRepository.findAll();
    }

    /**
     * Menyimpan data reservasi dengan validasi tanggal dan kapasitas kamar
     */
    @Transactional
    public Reservasi buatReservasi(Reservasi reservasi) {
        LocalDate checkIn = reservasi.getTanggalCheckIn();
        LocalDate checkOut = reservasi.getTanggalCheckOut();
        String tipeKamar = reservasi.getTipeKamar();

        // 1. Validasi Tanggal
        if (checkIn == null || checkOut == null) {
            throw new ValidasiTanggalException("Tanggal check-in dan check-out tidak boleh kosong.");
        }

        if (checkIn.isBefore(LocalDate.now())) {
            throw new ValidasiTanggalException("Tanggal check-in tidak boleh sebelum hari ini.");
        }

        if (!checkOut.isAfter(checkIn)) {
            throw new ValidasiTanggalException("Tanggal check-out harus setelah tanggal check-in.");
        }

        // 2. Validasi Tipe Kamar
        if (!KAPASITAS_KAMAR.containsKey(tipeKamar)) {
            throw new IllegalArgumentException("Tipe kamar tidak dikenal.");
        }

        int maxKapasitas = KAPASITAS_KAMAR.get(tipeKamar);

        // 3. Validasi Kapasitas Kamar (Overlapping check)
        List<Reservasi> overlapping = reservasiRepository.findOverlappingReservations(tipeKamar, checkIn, checkOut);
        
        if (overlapping.size() >= maxKapasitas) {
            throw new KamarPenuhException("Kamar tipe '" + tipeKamar + "' penuh untuk periode tanggal " + 
                    checkIn + " s/d " + checkOut + ". Kapasitas maksimal: " + maxKapasitas + " kamar.");
        }

        // Simpan ke database
        return reservasiRepository.save(reservasi);
    }

    /**
     * Menghapus reservasi berdasarkan ID
     */
    @Transactional
    public void hapusReservasi(Long id) {
        if (!reservasiRepository.existsById(id)) {
            throw new IllegalArgumentException("Reservasi dengan ID " + id + " tidak ditemukan.");
        }
        reservasiRepository.deleteById(id);
    }

    /**
     * Mendapatkan informasi kapasitas kamar
     */
    public Map<String, Integer> getKapasitasKamarInfo() {
        return KAPASITAS_KAMAR;
    }
}
