package com.fahrizal.hotel.repository;

import com.fahrizal.hotel.model.Reservasi;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservasiRepository extends JpaRepository<Reservasi, Long> {

    /**
     * Mencari reservasi yang tumpang tindih (overlapping) untuk tipe kamar tertentu
     * pada rentang tanggal check-in dan check-out yang diinputkan.
     * Rumus tumpang tindih: (requestedCheckIn < existingCheckOut) AND (requestedCheckOut > existingCheckIn)
     */
    @Query("SELECT r FROM Reservasi r WHERE r.tipeKamar = :tipeKamar AND " +
           "(r.tanggalCheckIn < :checkOut AND r.tanggalCheckOut > :checkIn)")
    List<Reservasi> findOverlappingReservations(
            @Param("tipeKamar") String tipeKamar,
            @Param("checkIn") LocalDate checkIn,
            @Param("checkOut") LocalDate checkOut
    );
}
