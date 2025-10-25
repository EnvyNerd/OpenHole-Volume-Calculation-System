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
├── gui/
│   ├── OpenHoleCalculatorGUI.java # Swing GUI (optional)
│   └── chart/               # Lightweight chart component
│       └── VolumeBarChart.java
└── data/
    └── sample_input.txt     # Sample input data (optional)
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

## 🧮 Example Input File (`data/sample_input.txt`)
Each line contains CSV-style fields: `DiameterInInches, LengthInFeet, CaliperDiameter` (or label + numeric fields depending on your variant).

Example:
```
8.5, 1000, 8.7
9.0, 1200, 9.1
10.5, 900, ten
6.0, 800
```

Lines with non-numeric values or missing fields will be reported as errors in the output.

## 📊 Example Output File (`result.txt`)
```
8.5, 1000, 8.7 => Volume: 73.63 bbl
9.0, 1200, 9.1 => Volume: 96.55 bbl
10.5, 900, ten => ERROR: Invalid numeric value.
6.0, 800 => ERROR: Invalid record format: 6.0, 800
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
