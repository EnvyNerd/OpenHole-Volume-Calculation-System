package openholevolume.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import openholevolume.calc.OpenHoleCalculator;
import openholevolume.model.HoleInterval;

/**
 * Handles file input and output for the Openhole Volume Calculation project.
 *
 * Responsibilities:
 *  - Reading interval data from CSV input file
 *  - Validating and parsing numeric values
 *  - Writing computed results (volume in barrels and liters) to output CSV
 *
 * Author: Witschi Mihan
 * Project: Openhole Volume Calculation (Java OOP)
 */
public class FileHandler {

    /**
     * Reads a CSV file and returns a list of HoleInterval objects.
     *
     * CSV Format:
     *   label,diameter_in_inches,topDepth_in_feet,bottomDepth_in_feet
     *
     * The first line is assumed to be the header and is skipped.
     * Lines beginning with '#' are treated as comments and skipped.
     *
     * @param inputFile the CSV file to read
     * @return List of HoleInterval objects
     * @throws IOException              if file I/O fails
     * @throws InvalidRecordException   if a line is malformed or invalid
     */
    public static String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    public static void writeFile(String filePath, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        }
    }

    public static List<HoleInterval> readIntervalsFromCsv(File inputFile)
            throws IOException, InvalidRecordException {

        List<HoleInterval> intervals = new ArrayList<>();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(inputFile));
            String line;
            int lineNumber = 0;
            boolean isHeader = true;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                line = line.trim();

                // Skip comments or empty lines
                if (line.isEmpty() || line.startsWith("#")) continue;

                // Skip the header line
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                String[] parts = line.split(",");

                if (parts.length != 4) {
                    throw new InvalidRecordException(
                            "Invalid format at line " + lineNumber +
                            ": Expected 4 fields (label, diameter, topDepth, bottomDepth)"
                    );
                }

                try {
                    String label = parts[0].trim();
                    double diameter = Double.parseDouble(parts[1].trim());
                    double topDepth = Double.parseDouble(parts[2].trim());
                    double bottomDepth = Double.parseDouble(parts[3].trim());
                    double length = bottomDepth - topDepth;

                    if (length <= 0) {
                        throw new InvalidRecordException(
                                "Invalid data at line " + lineNumber + ": Bottom depth must be greater than top depth"
                        );
                    }

                    HoleInterval interval = new HoleInterval(label, diameter, length);
                    intervals.add(interval);

                } catch (NumberFormatException ex) {
                    throw new InvalidRecordException(
                            "Invalid numeric value at line " + lineNumber, ex
                    );
                } catch (IllegalArgumentException ex) {
                    throw new InvalidRecordException(
                            "Invalid data at line " + lineNumber + ": " + ex.getMessage(), ex
                    );
                }
            }

        } finally {
            // Always close reader safely
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ignore) {
                }
            }
        }
 
        return intervals;
    }

    /**
     * Writes a volume calculation report to a CSV file.
     *
     * Each line contains:
     *   label,diameter_in_inches,length_in_feet,volume_bbl,volume_liters
     *
     * @param outputFile File to write to
     * @param intervals  List of HoleInterval objects to process
     * @throws IOException if writing fails
     */
    public static void writeReport(File outputFile, List<HoleInterval> intervals)
            throws IOException {

        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter(outputFile));
            // Write header
            writer.write("Label,Diameter(in),Length(ft),Volume(bbl),Volume(L)\n");

            for (HoleInterval h : intervals) {
                double volumeBbl = OpenHoleCalculator.volumeBarrels(h);
                double volumeLiters = OpenHoleCalculator.volumeLiters(h);

                writer.write(String.format(
                        "%s,%.3f,%.3f,%.6f,%.2f%n",
                        h.getLabel(),
                        h.getDiameterInches(),
                        h.getLengthFeet(),
                        volumeBbl,
                        volumeLiters
                ));
            }

        } finally {
            // Ensure the writer is always closed
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException ignore) {
                }
            }
        }
    }
}
