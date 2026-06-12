<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>VeloDrive | Rent Car Premium Management</title>
    <!-- Google Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Outfit:wght@300;400;500;600;700;800&family=Inter:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <!-- FontAwesome Icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/style.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom Premium Stylesheet -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="app-container">
        <!-- Sidebar Navigation -->
        <aside class="app-sidebar">
            <div class="sidebar-brand">
                <div class="brand-logo">
                    <i class="fa-solid fa-car-rear text-gradient"></i>
                </div>
                <span class="brand-name">Velo<span class="text-gradient">Drive</span></span>
            </div>
            
            <div class="sidebar-user">
                <div class="user-avatar">
                    <i class="fa-solid fa-user-tie"></i>
                </div>
                <div class="user-info">
                    <span class="user-name">Administrator</span>
                    <span class="user-role">Super Admin</span>
                </div>
            </div>
            
            <nav class="sidebar-menu">
                <a href="${pageContext.request.contextPath}/" class="menu-item ${pageContext.request.requestURI.endsWith('index.jsp') || pageContext.request.requestURI == pageContext.request.contextPath ? 'active' : ''}">
                    <i class="fa-solid fa-chart-pie"></i>
                    <span>Dashboard</span>
                </a>
                <a href="${pageContext.request.contextPath}/cars" class="menu-item ${pageContext.request.requestURI.contains('cars.jsp') || pageContext.request.requestURI.contains('car-form.jsp') ? 'active' : ''}">
                    <i class="fa-solid fa-car"></i>
                    <span>Manage Cars</span>
                </a>
                <a href="${pageContext.request.contextPath}/customers" class="menu-item ${pageContext.request.requestURI.contains('customers.jsp') || pageContext.request.requestURI.contains('customer-form.jsp') ? 'active' : ''}">
                    <i class="fa-solid fa-users"></i>
                    <span>Manage Customers</span>
                </a>
                <a href="${pageContext.request.contextPath}/rentals" class="menu-item ${pageContext.request.requestURI.contains('rentals.jsp') || pageContext.request.requestURI.contains('rent-form.jsp') || pageContext.request.requestURI.contains('return-form.jsp') ? 'active' : ''}">
                    <i class="fa-solid fa-handshake"></i>
                    <span>Rentals & Returns</span>
                </a>
            </nav>
            
            <div class="sidebar-footer">
                <span class="system-time">
                    <i class="fa-regular fa-clock me-1"></i>
                    <span id="live-clock">--:--:--</span>
                </span>
            </div>
        </aside>
        
        <!-- Main Content Area -->
        <main class="app-content-wrapper">
            <header class="app-topbar">
                <div class="topbar-search">
                    <!-- Placeholder search for modern UI layout -->
                    <div class="search-box">
                        <i class="fa-solid fa-magnifying-glass"></i>
                        <input type="text" placeholder="Search vehicle or client...">
                    </div>
                </div>
                <div class="topbar-actions">
                    <button class="topbar-btn">
                        <i class="fa-regular fa-bell"></i>
                        <span class="btn-badge"></span>
                    </button>
                    <div class="vertical-divider"></div>
                    <div class="quick-action">
                        <a href="${pageContext.request.contextPath}/rentals?action=rent" class="btn btn-gradient btn-sm">
                            <i class="fa-solid fa-plus me-1"></i> Rent a Car
                        </a>
                    </div>
                </div>
            </header>
            
            <div class="app-content">
                <!-- Page contents begin -->
