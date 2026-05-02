package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class koneksi {
    private static Connection mysqlconfig;
    public static Connection configDB() throws SQLException {
        try {
            String url = "jdbc:mysql://localhost:3306/db_penjualan"; // sesuaikan nama db
            String user = "root"; // user default xampp
            String pass = ""; // password default xampp (kosong)
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            mysqlconfig = DriverManager.getConnection(url, user, pass);            
        } catch (Exception e) {
            System.err.println("Koneksi gagal " + e.getMessage()); // peringatan jika gagal
        }
        return mysqlconfig;
    }    
}