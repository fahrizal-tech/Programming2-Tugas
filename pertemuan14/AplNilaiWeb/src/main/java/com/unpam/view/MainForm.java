package com.unpam.view;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MainForm", urlPatterns = {"/MainForm"})
public class MainForm extends HttpServlet {

    public void tampilkan(String konten, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        
        // Menu Navigasi Samping (Sidebar)
        String menu = """
            <br><b>Master Data</b><br>
            <a href="MahasiswaController">Mahasiswa</a><br>
            <a href="MataKuliahController">Mata Kuliah</a><br><br>
            <b>Transaksi</b><br>
            <a href="NilaiController">Nilai</a><br><br>
            <b>Laporan</b><br>
            <a href="LaporanController">Nilai</a><br><br>
            <a href="LoginController">Login</a><br><br>
            """;
        
        // Menu Navigasi Atas (Top Menu)
        String topMenu = """
            <nav>
                <ul>
                    <li><a href="MainForm">Home</a></li>
                    <li><a href="#">Master Data</a>
                        <ul>
                            <li><a href="MahasiswaController">Mahasiswa</a></li>
                            <li><a href="MataKuliahController">Mata Kuliah</a></li>
                        </ul>
                    </li>
                    <li><a href="#">Transaksi</a>
                        <ul>
                            <li><a href="NilaiController">Nilai</a></li>
                        </ul>
                    </li>
                    <li><a href="#">Laporan</a>
                        <ul>
                            <li><a href="LaporanController">Nilai</a></li>
                        </ul>
                    </li>
                    <li><a href="LoginController">Login</a></li>
                </ul>
            </nav>
            """;
        
        String userName = "";
        if (!session.isNew()) {
            try {
                if (session.getAttribute("userName") != null) {
                    userName = session.getAttribute("userName").toString();
                }
            } catch (Exception ex) {}
            
            if (userName != null && !userName.trim().isEmpty()) {
                if (konten == null || konten.trim().isEmpty()) {
                    konten = "<br><h1>Selamat Datang</h1><h2>" + userName + "</h2>";
                }
                
                try {
                    if (session.getAttribute("menu") != null) {
                        menu = session.getAttribute("menu").toString();
                    }
                    if (session.getAttribute("topMenu") != null) {
                        topMenu = session.getAttribute("topMenu").toString();
                    }
                } catch (Exception ex) {}
            }
        }
        
        try (PrintWriter out = response.getWriter()) {
            out.println("""
                <!DOCTYPE html>
                <html>
                <head>
                    <link href="style.css" rel="stylesheet" type="text/css" />
                    <title>Informasi Nilai Mahasiswa</title>
                </head>
                <body bgcolor="#808080">
                <center>
                    <table width="80%" bgcolor="#eeeeee">
                        <tr>
                            <td colspan="2" align="center">
                                <br>
                                <h2 style="margin-bottom:0px; margin-top:0px;">Informasi Nilai Mahasiswa</h2>
                                <h1 style="margin-bottom:0px; margin-top:0px;">UNIVERSITAS PAMULANG</h1>
                                <h4 style="margin-bottom:0px; margin-top:0px;">Jl. Surya Kencana No. 1 Pamulang, Tangerang Selatan, Banten</h4>
                                <br>
                            </td>
                        </tr>
                        <tr height="400">
                            <td width="200" align="center" valign="top" bgcolor="#eeffee">
                                <div id="menu">
                                    """ + menu + """
                                </div>
                            </td>
                            <td align="center" valign="top" bgcolor="#ffffff">
                                """ + topMenu + """
                                <br>
                                """ + konten + """
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" align="center" bgcolor="#eeeeff">
                                <small>
                                    Copyright &copy; 2026 Universitas Pamulang<br>
                                    Jl. Surya Kencana No. 1 Pamulang, Tangerang Selatan, Banten<br>
                                </small>
                            </td>
                        </tr>
                    </table>
                </center>
                </body>
                </html>
                """);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        tampilkan("", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        tampilkan("", request, response);
    }
}

