-- ==========================================
-- SCRIPT INISIALISASI DATABASE & TABEL UAS
-- Mata Kuliah: Pemrograman II
-- Nama: M. Fahrizal
-- ==========================================

-- 1. Membuat Database jika belum ada
CREATE DATABASE IF NOT EXISTS db_hotel_uas;
USE db_hotel_uas;

-- 2. Membuat Tabel reservasi
CREATE TABLE IF NOT EXISTS reservasi (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nama_tamu VARCHAR(150) NOT NULL,
    tipe_kamar VARCHAR(50) NOT NULL,
    tanggal_check_in DATE NOT NULL,
    tanggal_check_out DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 3. Memasukkan Data Awal (Opsional untuk testing)
INSERT INTO reservasi (nama_tamu, tipe_kamar, tanggal_check_in, tanggal_check_out) VALUES
('Rian Hidayat', 'Single Room', '2026-07-10', '2026-07-12'),
('Siti Aminah', 'Double Room', '2026-07-15', '2026-07-18'),
('Budi Santoso', 'Deluxe Room', '2026-07-20', '2026-07-25');

-- 4. Query untuk melihat data
SELECT * FROM reservasi;
