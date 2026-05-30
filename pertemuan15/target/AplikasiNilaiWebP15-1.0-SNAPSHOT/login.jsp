<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login - Administrasi Nilai UNPAM</title>
        <link rel="stylesheet" type="text/css" href="style.css">
        
        <style>
            body {
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                background: linear-gradient(135deg, #f0f2f5 0%, #d9e2ec 100%);
            }
            .login-box {
                background: white;
                padding: 40px;
                border-radius: 12px;
                box-shadow: 0px 10px 30px rgba(0, 0, 0, 0.1);
                width: 100%;
                max-width: 350px;
                text-align: center;
            }
            .login-box h2 {
                color: #2a5298;
                margin-top: 0;
                margin-bottom: 5px;
            }
            .login-box p {
                color: #777;
                font-size: 13px;
                margin-bottom: 25px;
            }
            .input-group {
                margin-bottom: 15px;
                text-align: left;
            }
            .input-group input {
                width: 90%;
                padding: 12px;
                border: 1px solid #ccc;
                border-radius: 6px;
                font-family: 'Poppins', sans-serif;
                font-size: 14px;
            }
            .input-group input:focus {
                border-color: #2a5298;
                outline: none;
                box-shadow: 0 0 5px rgba(42, 82, 152, 0.2);
            }
            .btn-login {
                width: 100%;
                padding: 12px;
                background-color: #2a5298;
                color: white;
                border: none;
                border-radius: 6px;
                font-family: 'Poppins', sans-serif;
                font-size: 15px;
                font-weight: 600;
                cursor: pointer;
                transition: background 0.3s;
                margin-top: 10px;
            }
            .btn-login:hover {
                background-color: #1e3c72;
            }
            .pesan-error {
                color: #d9534f;
                font-size: 13px;
                margin-top: 15px;
                font-weight: 600;
            }
        </style>
    </head>
    <body>
        
        <div class="login-box">
            <h2>Portal Admin</h2>
            <p>Silakan masuk menggunakan akun Anda</p>
            
            <form action="LoginController" method="POST">
                <div class="input-group">
                    <input type="text" name="username" placeholder="Username" required autocomplete="off">
                </div>
                <div class="input-group">
                    <input type="password" name="password" placeholder="Password" required>
                </div>
                <button type="submit" class="btn-login">MASUK</button>
            </form>
            
            <div class="pesan-error">
                ${pesanError}
            </div>
        </div>

    </body>
</html>