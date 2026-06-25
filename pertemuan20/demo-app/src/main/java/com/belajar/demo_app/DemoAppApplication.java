package com.belajar.demo_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoAppApplication.class, args);
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "<!DOCTYPE html>" +
               "<html>" +
               "<head>" +
               "    <title>Tugas Pemrograman 2</title>" +
               "    <style>" +
               "        body {" +
               "            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;" +
               "            background: linear-gradient(135deg, #e0eafc, #cfdef3);" +
               "            display: flex;" +
               "            justify-content: center;" +
               "            align-items: center;" +
               "            height: 100vh;" +
               "            margin: 0;" +
               "        }" +
               "        .card {" +
               "            background: white;" +
               "            padding: 30px;" +
               "            border-radius: 15px;" +
               "            box-shadow: 0 10px 20px rgba(0,0,0,0.1);" +
               "            text-align: center;" +
               "            max-width: 400px;" +
               "            width: 100%;" +
               "        }" +
               "        h2 {" +
               "            color: #2c3e50;" +
               "            margin-bottom: 5px;" +
               "        }" +
               "        .badge {" +
               "            background-color: #6db33f;" +
               "            color: white;" +
               "            padding: 5px 15px;" +
               "            border-radius: 20px;" +
               "            font-size: 14px;" +
               "            display: inline-block;" +
               "            margin-bottom: 20px;" +
               "            font-weight: bold;" +
               "        }" +
               "        table {" +
               "            width: 100%;" +
               "            margin-top: 15px;" +
               "            border-collapse: collapse;" +
               "        }" +
               "        td {" +
               "            padding: 10px;" +
               "            text-align: left;" +
               "            border-bottom: 1px solid #f2f2f2;" +
               "        }" +
               "        td.label {" +
               "            font-weight: bold;" +
               "            color: #7f8c8d;" +
               "            width: 40%;" +
               "        }" +
               "        td.value {" +
               "            color: #2c3e50;" +
               "        }" +
               "        .footer {" +
               "            margin-top: 25px;" +
               "            font-size: 13px;" +
               "            color: #27ae60;" +
               "            font-weight: bold;" +
               "        }" +
               "    </style>" +
               "</head>" +
               "<body>" +
               "    <div class='card'>" +
               "        <h2>Selamat Datang!</h2>" +
               "        <div class='badge'>Spring Boot Server Active</div>" +
               "        <table>" +
               "            <tr>" +
               "                <td class='label'>Nama</td>" +
               "                <td class='value'>Muhamad Fahrizal</td>" +
               "            </tr>" +
               "            <tr>" +
               "                <td class='label'>Mata Kuliah</td>" +
               "                <td class='value'>Pemrograman 2</td>" +
               "            </tr>" +
               "            <tr>" +
               "                <td class='label'>Materi</td>" +
               "                <td class='value'>Instalasi & Konfigurasi SpringBoot</td>" +
               "            </tr>" +
               "        </table>" +
               "        <div class='footer'>✓ Koneksi Database & Port 8080 Berjalan Lancar</div>" +
               "    </div>" +
               "</body>" +
               "</html>";
    }
}
