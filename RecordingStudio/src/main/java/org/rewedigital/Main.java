package org.rewedigital;

import org.rewedigital.services.Impl.RecordingStudioImpl;
import org.rewedigital.services.RecordingStudio;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try {
            var studio1 = new RecordingStudioImpl("Studio1", 50, 12);
            studio1.setRentedHours(10);

            var studio2 = new RecordingStudioImpl("Studio2", 100, 24);
            studio2.setRentedHours(8);

            System.out.println("Income for Studio1 in BGN: " + studio1.getIncomeInBGN());
            System.out.println("Income for Studio1 in EUR: " + studio1.getIncomeInEUR());

            System.out.println("Income for Studio2 in BGN: " + studio2.getIncomeInBGN());
            System.out.println("Income for Studio2 in EUR: " + studio2.getIncomeInEUR());

            int comparison = studio1.compareTo(studio2);
            if (comparison < 0) {
                System.out.println("Studio2 has higher income.");
            } else if (comparison > 0) {
                System.out.println("Studio1 has higher income.");
            } else {
                System.out.println("Both studios have equal income.");
            }

            studio1.setPrice(200.22);

            int comparison1 = studio1.compareTo(studio2);
            if (comparison1 < 0) {
                System.out.println("Studio2 has higher income.");
            } else if (comparison1 > 0) {
                System.out.println("Studio1 has higher income.");
            } else {
                System.out.println("Both studios have equal income.");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}