# 🚗 VeloDrive - Premium Rent Car Web Application
A modern, feature-rich Java Web Application for vehicle rental management. Built for Java EE 10 / Jakarta EE 10, running on Tomcat 10+, and ready to open in NetBeans.

---

## 🌟 Key Features
- **Modern UI & UX**: Fully custom responsive theme featuring sleek dark mode, glassmorphism card layouts, dynamic glowing transitions, and state-of-the-art aesthetics.
- **Dynamic Calculation Engine**: Client-side calculations via JavaScript for instant estimates of lease costs, overdue returns, and late return fines (Rp 75.000 / Day).
- **MySQL Database Integration**: Connects to your local XAMPP MySQL server automatically, utilizing standard relational table definitions.
- **Professional PDF Reports**: Landscaped reports featuring header logos, operational metrics cards (Total transactions, active leases, completed leases, total revenue), and formatted transaction registers.
- **Robust MVC Architecture**: Proper Java EE Servlet and JSP routing using DAO patterns and model wrappers.

---

## 📂 Project Architecture
```
pertemuan17/
│
├── pom.xml                               # Maven Project Configuration (Jakarta EE 10, OpenPDF, MySQL/SQLite, JSTL)
├── README.md                             # Project Documentation
│
└── src/
    └── main/
        ├── java/
        │   └── com/
        │       └── rentcar/
        │           ├── controller/       # MVC Servlets (Controllers)
        │           │   ├── CarController.java
        │           │   ├── CustomerController.java
        │           │   ├── RentalController.java
        │           │   └── ReportServlet.java
        │           │
        │           ├── dao/              # Database Persistence Layers (DAOs)
        │           │   ├── DatabaseHelper.java
        │           │   ├── CarDAO.java
        │           │   ├── CustomerDAO.java
        │           │   └── RentalDAO.java
        │           │
        │           ├── listener/         # Application Context Listeners
        │           │   └── AppContextListener.java
        │           │
        │           └── model/            # Object Entities (Models)
        │               ├── Car.java
        │               ├── Customer.java
        │               └── Rental.java
        │
        └── webapp/                       # Front-end Assets & Views (JSPs)
            ├── WEB-INF/
            │   └── web.xml               # Deployment Descriptor
            │
            ├── css/
            │   └── style.css             # Custom Premium CSS Design System
            │
            ├── index.jsp                 # Dashboard with charts & summary counters
            ├── cars.jsp                  # Cars Fleet Grid & Add Car Modal
            ├── car-form.jsp              # Dedicated Car Edit Form
            ├── customers.jsp             # Registered Clients Grid & Add Customer Modal
            ├── customer-form.jsp         # Dedicated Customer Edit Form
            ├── rentals.jsp               # Rentals Registry & Return launcher
            ├── rent-form.jsp             # Dynamic Car Lease form
            ├── return-form.jsp           # Overdue return calculator settlement form
            ├── header.jsp                # Core sidebar navigation header
            └── footer.jsp                # Core layout footer with real-time clock
```

---

## 🛠️ Environment Configuration & Setup

### Prerequisites
- **Java Development Kit (JDK)**: JDK 17 or 21 (Since your system has JDK 21, it is highly compatible).
- **Apache Tomcat Server**: Tomcat 10.x or 11.x (This is critical because Java EE 10 utilizes the new `jakarta.*` packages, which Tomcat 9 and below do not support).
- **NetBeans IDE**: Version 16 or newer (We recommend Apache NetBeans 17+ for full Maven & Tomcat 10 compatibility).
- **XAMPP**: Running MySQL/MariaDB and phpMyAdmin.

---

## 🚀 Steps to Run in NetBeans

1. **Open the Project**:
   - Open NetBeans IDE.
   - Click `File` -> `Open Project`.
   - Browse to the folder `E:\PERKULIAHAN\SEMESTER 6\programan2\Tugas pertemuan\pertemuan17`.
   - NetBeans will automatically recognize it as a **Maven Web Project** (indicated by the Maven logo on the project folder).
   - Click **Open Project**. NetBeans will automatically start scanning and downloading project dependencies (MySQL, OpenPDF, JSTL).

2. **Configure Tomcat**:
   - Make sure you have added your **Apache Tomcat 10.1+** instance to NetBeans:
     - Go to the `Services` tab (usually next to Projects in the left pane).
     - Right-click `Servers` -> `Add Server`.
     - Select `Apache Tomcat or TomEE` -> Click Next.
     - Choose your Tomcat installation directory and enter credentials.
   - Set the project's runtime server:
     - Right-click the project `RentCarApp` in the Projects tab -> Select `Properties`.
     - Click the `Run` category.
     - Select `Apache Tomcat` from the Server dropdown list.
     - Ensure Java Platform is set to **JDK 17 or JDK 21**.
     - Click `OK`.

3. **Build & Run**:
   - Right-click the project `RentCarApp` -> Select `Clean and Build`.
   - Once build is successful, right-click the project -> Select `Run`.
   - Your default browser will launch and navigate to the application dashboard:
     ```
     http://localhost:8080/RentCarApp/
     ```

---

## 💾 Database Engine & Seed Data
The application is pre-configured to connect to XAMPP's local MySQL server (`localhost:3306`) with username `root` and password `""` (empty).
- Before running, please create the database `rentcar_db` in **phpMyAdmin**.
- The database helper will automatically initialize all tables and populate them with beautiful seed data (5 vehicles, 3 customers) on first startup, allowing you to test all features immediately.
- Alternatively, you can use the SQL script provided in your workspace to manually import the schema and seed data into phpMyAdmin.

---

## 💡 System Design Highlights
1. **Dynamic Costing Engine** (`rent-form.jsp`): As soon as you select a car from the lease menu, its daily price is populated. Selecting dates calculates rental days and total rent price dynamically in real time before submission.
2. **Dynamic Return Settle & Overdue Overlays** (`return-form.jsp`): When returning a car, picking the return date instantly calculates the exact days rented, late return overdue days, active fine overlays (Rp 75,000 / late day), and updates the final settlement price.
3. **Robust Transactions** (`RentalDAO.java`): Database updates use transaction controls. Creating a lease or settling a return updates both the transaction records and the vehicle status ('Available' or 'Rented') atomically. If anything fails, the entire transaction is rolled back.
4. **Professional PDF Reports** (`ReportServlet.java`): Uses the OpenPDF library (maintained fork of iText 4) to stream landscape reports directly. It generates key operational indicators, formatted tables with zebra striping, currency parsing in IDR, and automatic total calculations.
