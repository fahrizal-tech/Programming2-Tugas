package com.fahrizal.hotel.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "reservasi")
public class Reservasi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nama tamu tidak boleh kosong")
    @Column(name = "nama_tamu", nullable = false)
    private String namaTamu;

    @NotBlank(message = "Tipe kamar tidak boleh kosong")
    @Column(name = "tipe_kamar", nullable = false)
    private String tipeKamar;

    @NotNull(message = "Tanggal check-in tidak boleh kosong")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "tanggal_check_in", nullable = false)
    private LocalDate tanggalCheckIn;

    @NotNull(message = "Tanggal check-out tidak boleh kosong")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "tanggal_check_out", nullable = false)
    private LocalDate tanggalCheckOut;

    // Constructors
    public Reservasi() {
    }

    public Reservasi(String namaTamu, String tipeKamar, LocalDate tanggalCheckIn, LocalDate tanggalCheckOut) {
        this.namaTamu = namaTamu;
        this.tipeKamar = tipeKamar;
        this.tanggalCheckIn = tanggalCheckIn;
        this.tanggalCheckOut = tanggalCheckOut;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaTamu() {
        return namaTamu;
    }

    public void setNamaTamu(String namaTamu) {
        this.namaTamu = namaTamu;
    }

    public String getTipeKamar() {
        return tipeKamar;
    }

    public void setTipeKamar(String tipeKamar) {
        this.tipeKamar = tipeKamar;
    }

    public LocalDate getTanggalCheckIn() {
        return tanggalCheckIn;
    }

    public void setTanggalCheckIn(LocalDate tanggalCheckIn) {
        this.tanggalCheckIn = tanggalCheckIn;
    }

    public LocalDate getTanggalCheckOut() {
        return tanggalCheckOut;
    }

    public void setTanggalCheckOut(LocalDate tanggalCheckOut) {
        this.tanggalCheckOut = tanggalCheckOut;
    }

    @Override
    public String toString() {
        return "Reservasi{" +
                "id=" + id +
                ", namaTamu='" + namaTamu + '\'' +
                ", tipeKamar='" + tipeKamar + '\'' +
                ", tanggalCheckIn=" + tanggalCheckIn +
                ", tanggalCheckOut=" + tanggalCheckOut +
                '}';
    }
}
