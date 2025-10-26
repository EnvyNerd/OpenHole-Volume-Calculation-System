Open Hole Volume Calculation Software (Java OOP Project)

## 📘 Project Overview
The **Open Hole Volume Calculation System** is a Java Object-Oriented Programming (OOP) project designed to compute the **open hole volume** in oil and gas drilling operations.  
This software provides two modes of operation:
1. **File-based calculation** — automatically reads drilling data from an input file.
2. **Manual calculation** — allows direct data entry through the console.

It demonstrates essential Java programming concepts such as:
- Object-Oriented Design (Encapsulation, Abstraction)
- Exception Handling (checked & unchecked exceptions)
- Custom Exception Class
- File Input/Output using `BufferedReader` and `BufferedWriter`
- Clean modular design and user interaction

---

## 📘 Formula Used

The open hole volume ($V$) represents the cylindrical space drilled into the subsurface, typically expressed in barrels (bbl).
It is calculated using the following industry-standard formula:

$V = 0.000971 \times D^2 \times L$

📏 Where:
| Symbol | Description | Unit |
|--------|-------------|------|
| $V$ | Open hole volume | barrels (bbl) |
| $D$ | Hole (caliper) diameter | inches (in) |
| $L$ | Hole length (interval) | feet (ft) |
| 0.000971 | Conversion factor | converts from cubic inches and feet to barrels |

🧠 Derivation (Reference Explanation)

The formula is derived from the volume of a cylinder:

$V = \pi \times (\frac{D}{2})^2 \times L$

Since the raw result is in cubic inches, we convert it to barrels, knowing that:

$1 \text{ ft} = 12 \text{ in}$

$1 \text{ bbl} = 9702 \text{ in}^3$

Hence:

$V = \frac{\pi D^2 L(12)}{4 \times 9702} = 0.000971 \times D^2 \times L$

🧮 Example Calculation

If:
- Hole Diameter = 8.5 in
- Hole Length = 1000 ft

Then:

$V = 0.000971 \times (8.5)^2 \times 1000 = 70.27 \text{ bbl}$

💡 Important Notes
- This formula assumes the borehole is a perfect cylinder.
- In real operations, the caliper diameter (actual measured hole size) is used for higher accuracy.
- If the hole is irregular or washed out, the calculated volume may slightly differ from actual mud displacement volumes.

---

## 🧩 Project Structure
```
src/
├── Main.java                 # Main menu and program controller
├── calc/
│   └── OpenHoleCalculator.java  # Core logic for volume calculation
├── io/
│   └── FileHandler.java      # File reading and writing utilities
├── exceptions/
│   └── InvalidRecordException.java # Custom exception for invalid file data
├── model/
│   └── HoleInterval.java     # Data model for an interval
└── data/
    └── Real_Offshore.csv     # Sample input data (optional)
```

---

## 📂 File Descriptions

### `Main.java`
- Acts as the program's **entry point**.
- Provides an interactive **menu-driven system**.
- Handles both **file-based** and **manual** input modes.
- Demonstrates **try–catch–finally** blocks and error messages for robustness.

### `OpenHoleCalculator.java`
- Encapsulates the **core computation logic**.
- Includes methods to compute per-interval volumes and totals.
- Validates inputs to ensure no negative or zero values.

### `FileHandler.java`
- Provides **file reading and writing** functionalities.
- Implements **BufferedReader/BufferedWriter** and convenience helpers used by GUI.

### `InvalidRecordException.java`
- Custom checked exception used when encountering **invalid record formats** or **missing data** in input files.

---

## 🧮 Example Input File (`data/Real_Offshore.csv`)
Each line contains CSV-style fields: `DiameterInInches, LengthInFeet, CaliperDiameter` (or label + numeric fields depending on your variant).

Example:
```
Label,Diameter(in),TopDepth(ft),BottomDepth(ft)
Interval-1,36,0,200
Interval-2,26,200,600
Interval-3,17.5,600,1100
Interval-4,12.25,1100,1600
Interval-5,8.5,1600,2200
Interval-6,6.0,2200,2800
Interval-7,5.875,2800,3400
Interval-8,8.5,3400,4100
Interval-9,12.25,4100,4600
Interval-10,17.5,4600,5200
Interval-11,6.75,5200,5800
Interval-12,9.875,5800,6300
Interval-13,8.375,6300,6900
Interval-14,12.25,6900,7400
Interval-15,17.5,7400,7900
Interval-16,6.125,7900,8300
Interval-17,7.875,8300,8800
Interval-18,9.0,8800,9300
Interval-19,8.5,9300,9800
Interval-20,6.75,9800,10300
Interval-21,17.5,10300,10800
Interval-22,12.25,10800,11300
Interval-23,8.5,11300,11800
Interval-24,6.0,11800,12200
Interval-25,5.5,12200,12600
Interval-26,9.875,12600,13000
Interval-27,8.375,13000,13400
Interval-28,6.75,13400,13800
Interval-29,12.25,13800,14200
Interval-30,17.5,14200,14700
Interval-31,8.5,14700,15100
Interval-32,6.125,15100,15500
Interval-33,5.875,15500,15900
Interval-34,9.0,15900,16300
Interval-35,12.25,16300,16700
Interval-36,17.5,16700,17200
Interval-37,8.5,17200,17600
Interval-38,6.0,17600,18000
Interval-39,5.5,18000,18400
Interval-40,8.375,18400,18800
Interval-41,9.875,18800,19200
Interval-42,12.25,19200,19600
Interval-43,17.5,19600,20100
Interval-44,8.5,20100,20500
Interval-45,6.75,20500,20900
Interval-46,5.875,20900,21300
Interval-47,6.0,21300,21700
Interval-48,12.25,21700,22200
Interval-49,17.5,22200,22700
Interval-50,8.5,22700,23200
```

Lines with non-numeric values or missing fields will be reported as errors in the output.

## 📊 Example Output File (`Offshore_Result.csv`)
```
Label,Diameter(in),Length(ft),Volume(bbl),Volume(L)
Interval-1,36.000,200.000,251.793697,40032.00
Interval-2,26.000,400.000,262.673671,41761.78
Interval-3,17.500,500.000,148.749652,23649.30
Interval-4,12.250,500.000,72.887330,11588.16
Interval-5,8.500,600.000,42.111330,6695.17
Interval-6,6.000,600.000,20.982808,3336.00
Interval-7,5.875,600.000,20.117632,3198.45
Interval-8,8.500,700.000,49.129885,7811.03
Interval-9,12.250,500.000,72.887330,11588.16
Interval-10,17.500,600.000,178.499583,28379.17
Interval-11,6.750,600.000,26.556366,4222.12
Interval-12,9.875,500.000,47.364621,7530.37
Interval-13,8.375,600.000,40.881869,6499.70
Interval-14,12.250,500.000,72.887330,11588.16
Interval-15,17.500,500.000,148.749652,23649.30
Interval-16,6.125,400.000,14.577466,2317.63
Interval-17,7.875,500.000,30.121805,4788.98
Interval-18,9.000,500.000,39.342765,6255.00
Interval-19,8.500,500.000,35.092775,5579.31
Interval-20,6.750,500.000,22.130305,3518.44
Interval-21,17.500,500.000,148.749652,23649.30
Interval-22,12.250,500.000,72.887330,11588.16
Interval-23,8.500,500.000,35.092775,5579.31
Interval-24,6.000,400.000,13.988539,2224.00
Interval-25,5.500,400.000,11.754258,1868.78
Interval-26,9.875,400.000,37.891697,6024.30
Interval-27,8.375,400.000,27.254579,4333.13
Interval-28,6.750,400.000,17.704244,2814.75
Interval-29,12.250,400.000,58.309864,9270.53
Interval-30,17.500,500.000,148.749652,23649.30
Interval-31,8.500,400.000,28.074220,4463.44
Interval-32,6.125,400.000,14.577466,2317.63
Interval-33,5.875,400.000,13.411754,2132.30
Interval-34,9.000,400.000,31.474212,5004.00
Interval-35,12.250,400.000,58.309864,9270.53
Interval-36,17.500,500.000,148.749652,23649.30
Interval-37,8.500,400.000,28.074220,4463.44
Interval-38,6.000,400.000,13.988539,2224.00
Interval-39,5.500,400.000,11.754258,1868.78
Interval-40,8.375,400.000,27.254579,4333.13
Interval-41,9.875,400.000,37.891697,6024.30
Interval-42,12.250,400.000,58.309864,9270.53
Interval-43,17.500,500.000,148.749652,23649.30
Interval-44,8.500,400.000,28.074220,4463.44
Interval-45,6.750,400.000,17.704244,2814.75
Interval-46,5.875,400.000,13.411754,2132.30
Interval-47,6.000,400.000,13.988539,2224.00
Interval-48,12.250,500.000,72.887330,11588.16
Interval-49,17.500,500.000,148.749652,23649.30
Interval-50,8.500,500.000,35.092775,5579.31
```

---

## 💡 Features
- ✅ Fully OOP-based design (Encapsulation, Exception Handling, File I/O)
- ✅ Custom Exception (`InvalidRecordException`)
- ✅ Handles numeric and format errors gracefully
- ✅ User-friendly console interface
- ✅ Optional Swing GUI with lightweight bar chart visualization (see `gui/`)

---

## 🧰 Technical Details
| Component | Description |
|------------|-------------|
| **Language** | Java |
| **Paradigm** | Object-Oriented Programming |
| **Libraries Used** | java.io, java.util, javax.swing (GUI) |
| **Input/Output** | Text files (CSV-like) |
| **Error Handling** | try–catch–finally, custom exceptions |
| **File Handling** | BufferedReader & BufferedWriter |

---

## 🚀 How to Run
1. **Compile all Java files** (from project root):
```powershell
mkdir bin
$files = Get-ChildItem -Recurse -Filter "*.java" -Path src | ForEach-Object { $_.FullName }
javac -d bin -sourcepath src $files
```

2. **Run the program (console)**:
```powershell
java -cp bin openholevolume.Main
```

3. **Run the GUI (optional)**:
```powershell
java -cp bin openholevolume.gui.OpenHoleCalculatorGUI
```

Follow the menu options for file-based or manual input.

---

## 🧠 Learning Outcomes
By completing this project, students will:

- Understand how OOP principles are applied in practical software.
- Gain experience in handling real-world data input and validation.
- Learn how to design modular, maintainable, and scalable Java programs.
- Develop skills in custom exception handling and file I/O operations.

---

## 👨‍💻 Author
Project Title: Open Hole Volume Calculation Software
Developed by: Witschi Bin Mihan
Program: Diploma in Information Technology (Networking)
Institution: AMC University College
Year: 2025

---

## 🏗️ Future Enhancements
- Add a Graphical User Interface (GUI) using JavaFX or Swing (if not already present).
- Include data visualization of hole volume vs. depth (we provide a lightweight `VolumeBarChart` component).
- Enable database integration for long-term storage and analysis.
- Implement unit testing and CI for better validation and reliability.

---

## 📜 License
This project is open-source and free to use for educational purposes.
