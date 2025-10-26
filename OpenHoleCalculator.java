package openholevolume.calc;

import java.util.List;
import openholevolume.model.HoleInterval;

/**
 * The OpenHoleCalculator class provides static utility methods
 * to calculate borehole (openhole) volumes for drilling intervals.
 *
 * Volume is calculated using the formula for a cylinder:
 *     V = π * (D² / 4) * L
 * where:
 *     D = diameter (in inches)
 *     L = length (in inches)
 *
 * Units:
 *   - Input: diameter (inches), length (feet)
 *   - Output: cubic inches, barrels, and liters
 *
 * Author: Witschi Mihan
 * Project: Openhole Volume Calculation (Java OOP)
 */
public class OpenHoleCalculator {

    // === Constants for conversions ===
    private static final double INCHES_PER_FOOT = 12.0;
    private static final double CUBIC_INCHES_PER_BARREL = 9702.0; // 1 bbl = 9702 in³
    private static final double CUBIC_INCH_TO_LITERS = 0.016387064; // 1 in³ = 0.016387064 L

    /**
     * Calculates the openhole volume in cubic inches for a given interval.
     *
     * @param interval HoleInterval object containing dimensions.
     * @return Volume in cubic inches.
     */
    public static double volumeCubicInches(HoleInterval interval) {
        double diameter = interval.getDiameterInches();
        double lengthInches = interval.getLengthFeet() * INCHES_PER_FOOT;

        // Cylinder volume = π * (D² / 4) * L
        return Math.PI * Math.pow(diameter, 2) / 4.0 * lengthInches;
    }

    /**
     * Calculates the openhole volume in barrels (bbl).
     *
     * @param interval HoleInterval object containing dimensions.
     * @return Volume in barrels.
     */
    public static double volumeBarrels(HoleInterval interval) {
        return volumeCubicInches(interval) / CUBIC_INCHES_PER_BARREL;
    }

    /**
     * Calculates the openhole volume in liters.
     *
     * @param interval HoleInterval object containing dimensions.
     * @return Volume in liters.
     */
    public static double volumeLiters(HoleInterval interval) {
        return volumeCubicInches(interval) * CUBIC_INCH_TO_LITERS;
    }

    /**
     * Calculates the total openhole volume (in barrels)
     * for a list of intervals.
     *
     * @param intervals List of HoleInterval objects.
     * @return Total volume in barrels.
     */
    public static double totalVolumeBarrels(List<HoleInterval> intervals) {
        double total = 0.0;
        for (HoleInterval h : intervals) {
            total += volumeBarrels(h);
        }
        return total;
    }

    /**
     * Calculates the total openhole volume (in liters)
     * for a list of intervals.
     *
     * @param intervals List of HoleInterval objects.
     * @return Total volume in liters.
     */
    public static double totalVolumeLiters(List<HoleInterval> intervals) {
        double total = 0.0;
        for (HoleInterval h : intervals) {
            total += volumeLiters(h);
        }
        return total;
    }

    /**
     * Displays a summary report for a single interval.
     * Useful for console-based demonstration.
     *
     * @param interval HoleInterval object.
     */
    public static void printIntervalSummary(HoleInterval interval) {
        System.out.println("-------------------------------------------");
        System.out.println("Interval: " + interval.getLabel());
        System.out.printf("Diameter: %.3f in%n", interval.getDiameterInches());
        System.out.printf("Length: %.3f ft%n", interval.getLengthFeet());
        System.out.printf("Volume: %.4f bbl | %.2f L%n",
                volumeBarrels(interval),
                volumeLiters(interval));
        System.out.println("-------------------------------------------");
    }
}
