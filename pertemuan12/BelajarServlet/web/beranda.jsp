<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Brosur Interface</title>
        <style>
            body { font-family: 'Poppins', sans-serif; background: #f0f2f5; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }
            .grid-container { background: #ffffff; padding: 40px; border-radius: 20px; box-shadow: 0 10px 30px rgba(0,0,0,0.05); width: 400px; position: relative; }
            .grid-container::before { content: ''; position: absolute; top: 0; left: 0; right: 0; height: 6px; background: linear-gradient(90deg, #00c6ff, #0072ff); }
            h2 { color: #2d3748; margin-top: 0; font-size: 24px; }
            .info-box { background: #f7fafc; padding: 15px; border-radius: 12px; border: 1px solid #e2e8f0; margin: 20px 0; }
            .info-title { font-size: 11px; color: #a0aec0; letter-spacing: 1px; font-weight: bold; margin: 0 0 5px 0; }
            .info-value { font-size: 15px; color: #4a5568; font-weight: 500; }
            .btn-back { display: inline-block; padding: 10px 20px; border: 2px solid #0072ff; color: #0072ff; text-decoration: none; border-radius: 10px; font-weight: bold; font-size: 13px; transition: 0.2s; }
            .btn-back:hover { background: #0072ff; color: #ffffff; }
        </style>
    </head>
    <body>
        <div class="grid-container">
            <h2>Halaman Brosur JSP</h2>
            <p style="font-size: 14px; color: #718096;">Komponen UI ini bertugas mengambil fungsi eksternal Java dan merangkainya ke dalam struktur DOM HTML.</p>
            
            <div class="info-box">
                <p class="info-title">REALTIME ENGINE TIMESTAMP</p>
                <div class="info-value">
                    <%
                        Date waktuDinamis = new Date();
                        SimpleDateFormat formatGamer = new SimpleDateFormat("hh:mm:ss a (z)");
                        out.print(formatGamer.format(waktuDinamis));
                    %>
                </div>
            </div>
            
            <a href="halo" class="btn-back">&larr; KEMBALI KE SERVLET</a>
        </div>
    </body>
</html>