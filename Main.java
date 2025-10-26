package openholevolume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import openholevolume.io.FileHandler;
import openholevolume.io.InvalidRecordException;
import openholevolume.model.HoleInterval;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("===================================");
            System.out.println("OpenHole Volume Calculator");
            System.out.println("===================================");
            System.out.println("Menu:");
            System.out.println("1. Calculate Volume from File");
            System.out.println("2. Enter Data Manually");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    handleFileCalculation(scanner);
                    break;
                case "2":
                    handleManualEntry(scanner);
                    break;
                case "3":
                    System.out.println("Exiting...");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
            System.out.println();
        }

        scanner.close();
    }

    // Option 1: Read from CSV file
    private static void handleFileCalculation(Scanner scanner) {
        File inputFile;
        while (true) {
            System.out.print("Enter the input filename (e.g., input.csv): ");
            inputFile = new File(scanner.nextLine().trim());
            if (inputFile.exists() && inputFile.isFile()) break;
            System.out.println("File not found. Please check the filename and try again.");
        }

        System.out.print("Enter the output filename (e.g., result.csv): ");
        File outputFile = new File(scanner.nextLine().trim());

        try {
            List<HoleInterval> intervals = FileHandler.readIntervalsFromCsv(inputFile);
            FileHandler.writeReport(outputFile, intervals);

            System.out.println("Volume calculation completed successfully from file.");
            System.out.println("Output saved to: " + outputFile.getAbsolutePath());

        } catch (InvalidRecordException e) {
            System.out.println("Error in CSV data: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O Error: " + e.getMessage());
        }
    }

    // Option 2: Manual entry
    private static void handleManualEntry(Scanner scanner) {
        List<HoleInterval> intervals = new ArrayList<>();
        boolean addMore = true;

        while (addMore) {
            System.out.print("Enter label: ");
            String label = scanner.nextLine().trim();

            double diameter = 0, length = 0;
            while (true) {
                try {
                    System.out.print("Enter diameter in inches: ");
                    diameter = Double.parseDouble(scanner.nextLine().trim());
                    if (diameter <= 0) throw new NumberFormatException();
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Diameter must be a positive number.");
                }
            }

            while (true) {
                try {
                    System.out.print("Enter length in feet: ");
                    length = Double.parseDouble(scanner.nextLine().trim());
                    if (length <= 0) throw new NumberFormatException();
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Length must be a positive number.");
                }
            }

            intervals.add(new HoleInterval(label, diameter, length));

            System.out.print("Add another interval? (y/n): ");
            String ans = scanner.nextLine().trim().toLowerCase();
            addMore = ans.equals("y");
        }

        System.out.print("Enter the output filename for the report (e.g., result.csv): ");
        File outputFile = new File(scanner.nextLine().trim());

        try {
            FileHandler.writeReport(outputFile, intervals);
            System.out.println("Volume calculation completed successfully from manual entry.");
            System.out.println("Output saved to: " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("I/O Error: " + e.getMessage());
        }
    }
}
