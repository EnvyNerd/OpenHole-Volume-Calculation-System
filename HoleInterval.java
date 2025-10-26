package openholevolume.model;

/**
 * Represents one openhole interval record.
 * Each interval includes a label, borehole diameter (in inches),
 * and interval length (in feet).
 *
 * Author: Witschi Mihan
 * Project: Openhole Volume Calculation (Java OOP)
 */

public class HoleInterval {

    // === Private fields ===
    private String label;            // e.g., "Interval-1"
    private double diameterInches;   // Borehole diameter in inches
    private double lengthFeet;       // Interval length in feet

    // === Constructor ===
    /**
     * Creates a new HoleInterval.
     *
     * @param label  Name or identifier for the interval.
     * @param diameterInches  Diameter of the borehole in inches (must be > 0).
     * @param lengthFeet  Length of the interval in feet (must be > 0).
     * @throws IllegalArgumentException if any value is invalid.
     */
    public HoleInterval(String label, double diameterInches, double lengthFeet) {
        if (label == null || label.trim().isEmpty()) {
            throw new IllegalArgumentException("Label cannot be null or empty.");
        }
        if (diameterInches <= 0) {
            throw new IllegalArgumentException("Diameter must be greater than 0.");
        }
        if (lengthFeet <= 0) {
            throw new IllegalArgumentException("Length must be greater than 0.");
        }

        this.label = label.trim();
        this.diameterInches = diameterInches;
        this.lengthFeet = lengthFeet;
    }

    // === Getters and Setters ===
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        if (label == null || label.trim().isEmpty()) {
            throw new IllegalArgumentException("Label cannot be empty.");
        }
        this.label = label.trim();
    }

    public double getDiameterInches() {
        return diameterInches;
    }

    public void setDiameterInches(double diameterInches) {
        if (diameterInches <= 0) {
            throw new IllegalArgumentException("Diameter must be greater than 0.");
        }
        this.diameterInches = diameterInches;
    }

    public double getLengthFeet() {
        return lengthFeet;
    }

    public void setLengthFeet(double lengthFeet) {
        if (lengthFeet <= 0) {
            throw new IllegalArgumentException("Length must be greater than 0.");
        }
        this.lengthFeet = lengthFeet;
    }

    // === Utility ===
    @Override
    public String toString() {
        return String.format("%s [Diameter: %.2f in | Length: %.2f ft]", 
                             label, diameterInches, lengthFeet);
    }
}
