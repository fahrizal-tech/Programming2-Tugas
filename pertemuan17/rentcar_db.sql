-- SQL Script to Initialize VeloDrive Rent Car Database in phpMyAdmin (MySQL / MariaDB)
-- Database Name: rentcar_db

CREATE DATABASE IF NOT EXISTS rentcar_db;
USE rentcar_db;

-- 1. Table Structure for 'cars'
CREATE TABLE IF NOT EXISTS cars (
    car_id INT PRIMARY KEY AUTO_INCREMENT,
    plate_number VARCHAR(20) UNIQUE NOT NULL,
    brand VARCHAR(50) NOT NULL,
    model VARCHAR(50) NOT NULL,
    year INT NOT NULL,
    price_per_day DOUBLE NOT NULL,
    status VARCHAR(20) DEFAULT 'Available'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Seed Initial Data for 'cars'
INSERT INTO cars (plate_number, brand, model, year, price_per_day, status) VALUES
('B 1234 ABC', 'Toyota', 'Avanza', 2022, 350000.0, 'Available'),
('B 5678 DEF', 'Honda', 'Civic', 2021, 600000.0, 'Available'),
('B 9012 GHI', 'Mitsubishi', 'Xpander', 2023, 400000.0, 'Available'),
('D 2468 JKL', 'Suzuki', 'Ertiga', 2020, 300000.0, 'Available'),
('D 1357 MNO', 'Hyundai', 'Ioniq 5', 2023, 850000.0, 'Maintenance')
ON DUPLICATE KEY UPDATE brand=brand;


-- 2. Table Structure for 'customers'
CREATE TABLE IF NOT EXISTS customers (
    customer_id INT PRIMARY KEY AUTO_INCREMENT,
    nik VARCHAR(20) UNIQUE NOT NULL,
    name VARCHAR(100) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    address TEXT NOT NULL,
    license_number VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Seed Initial Data for 'customers'
INSERT INTO customers (nik, name, phone, address, license_number) VALUES
('3273012345678901', 'Fahrizal Tech', '08123456789', 'Jl. Sukajadi No. 12, Bandung', 'SIM-A-12345'),
('3171098765432109', 'Aulia Rahma', '08771234567', 'Jl. Sudirman No. 45, Jakarta', 'SIM-A-98765'),
('3204081234560002', 'Budi Santoso', '08529876543', 'Jl. Merdeka No. 99, Tangerang', 'SIM-A-67890')
ON DUPLICATE KEY UPDATE name=name;


-- 3. Table Structure for 'rentals'
CREATE TABLE IF NOT EXISTS rentals (
    rental_id INT PRIMARY KEY AUTO_INCREMENT,
    car_id INT NOT NULL,
    customer_id INT NOT NULL,
    rent_date DATE NOT NULL,
    due_date DATE NOT NULL,
    return_date DATE NULL,
    price_per_day DOUBLE NOT NULL,
    total_price DOUBLE NULL,
    fine DOUBLE DEFAULT 0,
    status VARCHAR(20) DEFAULT 'Rented',
    FOREIGN KEY (car_id) REFERENCES cars(car_id) ON DELETE CASCADE,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
